package com.fayda.health.infrastructure.security;

import com.fayda.health.domain.model.User;
import com.fayda.health.domain.port.outbound.service.TokenPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtTokenAdapter implements TokenPort {

    private final SecretKey key;
    private final long accessTokenMinutes;
    private final long refreshTokenDays;

    public JwtTokenAdapter(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-minutes}") long accessTokenMinutes,
            @Value("${jwt.refresh-token-days}") long refreshTokenDays) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenMinutes = accessTokenMinutes;
        this.refreshTokenDays = refreshTokenDays;
    }

    @Override
    public String accessToken(User user) {
        return token(user, accessTokenMinutes * 60, "access");
    }

    @Override
    public String refreshToken(User user) {
        return token(user, refreshTokenDays * 24 * 60 * 60, "refresh");
    }

    @Override
    public UUID subject(String token) {
        return UUID.fromString(claims(token).getSubject());
    }

    @Override
    public String type(String token) {
        return claims(token).get("type", String.class);
    }

    private Claims claims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    private String token(User user, long seconds, String type) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.id().toString())
                .claim("email", user.email() != null ? user.email().value() : null)
                .claim("faydaId", user.faydaId() != null ? user.faydaId().value() : null)
                .claim("role", user.role().name())
                .claim("type", type)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(seconds)))
                .signWith(key)
                .compact();
    }
}
