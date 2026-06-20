package com.fayda.health.domain.port.outbound.service;

import com.fayda.health.domain.model.User;

import java.util.UUID;

public interface TokenPort {

    String accessToken(User user);

    String refreshToken(User user);

    String type(String token);

    UUID subject(String token);
}
