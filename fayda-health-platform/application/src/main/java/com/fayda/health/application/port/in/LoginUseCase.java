package com.fayda.health.application.port.in;

import com.fayda.health.application.dto.AuthResult;
import com.fayda.health.application.dto.LoginCommand;
import com.fayda.health.application.dto.RegisterUserCommand;

public interface LoginUseCase {
    AuthResult login(LoginCommand command);
}
