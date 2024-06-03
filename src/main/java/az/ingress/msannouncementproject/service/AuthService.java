package az.ingress.msannouncementproject.service;


import az.ingress.msannouncementproject.dto.request.LoginRequest;
import az.ingress.msannouncementproject.dto.request.UserRequest;
import az.ingress.msannouncementproject.dto.response.AuthResponse;
import az.ingress.msannouncementproject.dto.response.UserResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    UserResponse register(UserRequest userRequest);
}
