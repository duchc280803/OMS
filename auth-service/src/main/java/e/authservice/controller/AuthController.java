package e.authservice.controller;

import e.authservice.dto.request.AuthRequest;
import e.authservice.service.AuthService;
import e.infra.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public CommonResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("register")
    public CommonResponse register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }
}
