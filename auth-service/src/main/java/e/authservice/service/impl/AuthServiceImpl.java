package e.authservice.service.impl;

import e.authservice.dto.request.AuthRequest;
import e.authservice.dto.response.LoginResponseData;
import e.authservice.dto.response.UserCustomDetail;
import e.authservice.dto.response.UserDto;
import e.authservice.entity.User;
import e.authservice.entity.UserRole;
import e.authservice.filter.JwtService;
import e.authservice.repository.UserRepository;
import e.authservice.repository.UserRoleRepository;
import e.authservice.service.AuthService;
import e.infra.common.CommonCode;
import e.infra.common.CommonResponse;
import e.infra.common.HttpStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public CommonResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        Optional<User> userOptional = userRepository.findByUsername(authRequest.getUsername());
        if (userOptional.isPresent()) {
            String token = jwtService.generateToken(new UserCustomDetail(userOptional.get()));
            UserDto userDto = UserDto.fromEntity(userOptional.get());
            return CommonResponse
                    .builder()
                    .code(HttpStatusCodes.OK)
                    .data(LoginResponseData
                            .builder()
                            .token(token)
                            .user(userDto)
                            .build()
                    )
                    .message("Login success")
                    .build();
        }
        return CommonResponse
                .builder()
                .code(HttpStatusCodes.NOT_FOUND)
                .message("Token null & login false")
                .build();
    }

    @Transactional
    @Override
    public CommonResponse register(AuthRequest authRequest) {
        UserRole role = userRoleRepository.findById(authRequest.getRole()).orElse(null);

        if (role == null) {
            return CommonResponse
                    .builder()
                    .code(HttpStatusCodes.NO_CONTENT)
                    .message("Đăng ký không thành công")
                    .build();
        }

        User user = User
                .builder()
                .username(authRequest.getUsername())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .email(authRequest.getEmail())
                .role(role)
                .build();
        userRepository.save(user);
        return CommonResponse
                .builder()
                .data(user)
                .message("Đăng ký thành công")
                .code(HttpStatusCodes.OK)
                .build();
    }
}
