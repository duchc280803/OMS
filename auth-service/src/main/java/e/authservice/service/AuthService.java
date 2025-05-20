package e.authservice.service;

import e.authservice.dto.request.AuthRequest;
import e.infra.common.CommonResponse;

public interface AuthService {

    CommonResponse login(AuthRequest authRequest);

    CommonResponse register(AuthRequest authRequest);
}
