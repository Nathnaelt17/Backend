package com.tenalink.application.service;

import com.tenalink.domain.entity.User;

import java.util.UUID;

public interface TokenService {
    String accessToken(User user);
    String refreshToken(User user);
    UUID subject(String token);
    String type(String token);
}
