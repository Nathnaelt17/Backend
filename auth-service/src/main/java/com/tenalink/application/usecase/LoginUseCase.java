package com.tenalink.application.usecase;

import com.tenalink.application.dto.AuthResult;
import com.tenalink.application.dto.LoginCommand;

public interface LoginUseCase {
    AuthResult login(LoginCommand command);
}
