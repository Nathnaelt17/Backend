package com.tenalink.application.usecase;

import com.tenalink.application.dto.AuthResult;
import com.tenalink.application.dto.RefreshCommand;

public interface RefreshTokenUseCase {
    AuthResult refresh(RefreshCommand command);
}
