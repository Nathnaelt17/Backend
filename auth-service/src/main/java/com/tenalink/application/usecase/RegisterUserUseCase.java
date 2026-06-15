package com.tenalink.application.usecase;

import com.tenalink.application.dto.AuthResult;
import com.tenalink.application.dto.RegisterCommand;

public interface RegisterUserUseCase {
    AuthResult register(RegisterCommand command);
}
