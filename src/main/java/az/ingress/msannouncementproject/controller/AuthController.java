package az.ingress.msannouncementproject.controller;

import az.ingress.msannouncementproject.dto.request.LoginRequest;
import az.ingress.msannouncementproject.dto.request.UserRequest;
import az.ingress.msannouncementproject.dto.response.AuthResponse;
import az.ingress.msannouncementproject.dto.response.UserResponse;
import az.ingress.msannouncementproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserResponse register(@Validated @RequestBody UserRequest userRequest) {
        return authService.register(userRequest);
    }
}
