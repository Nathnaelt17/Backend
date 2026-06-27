package com.tenalink.auth.service;
import com.tenalink.auth.dto.AuthDto;
import com.tenalink.auth.entity.UserEntity;
import com.tenalink.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Value("${jwt.secret}") private String jwtSecret;

    public AuthService(UserRepository userRepository) { this.userRepository = userRepository; }

    public AuthDto.AuthResponse register(AuthDto.RegisterRequest req) {
        UserEntity user = new UserEntity();
        user.setEmail(req.getEmail());
        user.setFaydaId(req.getFaydaId());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());
        user.setRole(req.getRole() != null ? req.getRole() : "PATIENT");
        user = userRepository.save(user);
        return createResponse(user);
    }

    public AuthDto.AuthResponse login(AuthDto.LoginRequest req) {
        Optional<UserEntity> userOpt;
        if(req.getIdentifier() != null && req.getIdentifier().contains("@")) {
            userOpt = userRepository.findByEmail(req.getIdentifier());
        } else {
            userOpt = userRepository.findByFaydaId(req.getIdentifier());
        }
        UserEntity user = userOpt.orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) throw new RuntimeException("Invalid password");
        return createResponse(user);
    }

    private AuthDto.AuthResponse createResponse(UserEntity user) {
        String token = Jwts.builder().subject(user.getId().toString()).claim("role", user.getRole())
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8))).compact();
        AuthDto.AuthResponse res = new AuthDto.AuthResponse();
        res.setToken(token); res.setUserId(user.getId().toString()); res.setRole(user.getRole());
        return res;
    }
}
