package az.ingress.msannouncementproject.service.impl;

import az.ingress.msannouncementproject.dto.request.LoginRequest;
import az.ingress.msannouncementproject.dto.request.UserRequest;
import az.ingress.msannouncementproject.dto.response.AuthResponse;
import az.ingress.msannouncementproject.dto.response.UserResponse;
import az.ingress.msannouncementproject.entity.Authority;
import az.ingress.msannouncementproject.entity.User;
import az.ingress.msannouncementproject.enums.ExceptionMessage;
import az.ingress.msannouncementproject.exception.AlreadyExistException;
import az.ingress.msannouncementproject.mapper.UserMapper;
import az.ingress.msannouncementproject.repository.AuthorityRepository;
import az.ingress.msannouncementproject.repository.UserRepository;
import az.ingress.msannouncementproject.security.JwtService;
import az.ingress.msannouncementproject.security.MyUserDetails;
import az.ingress.msannouncementproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.msannouncementproject.enums.RoleType.ROLE_USER;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        MyUserDetails user = (MyUserDetails) authenticate.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        return new AuthResponse(user.getUsername(), accessToken);
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        userRepository.findUserByUsername(userRequest.getUsername()).ifPresent(user -> {
            throw new AlreadyExistException(ExceptionMessage.USER_ALREADY_EXIST.getMessage().formatted(user.getUsername()));
        });

        User user = userMapper.requestToEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Authority authority = authorityRepository.findAuthoritiesByAuthority(ROLE_USER.value).orElseGet(() -> authorityRepository.save(new Authority().authority(ROLE_USER.value)));
        user.setAuthorities(List.of(authority));

        User savedUser = userRepository.save(user);
        return userMapper.entityToResponse(savedUser);
    }
}