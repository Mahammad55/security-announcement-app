package az.ingress.msannouncementproject.service.impl;

import az.ingress.msannouncementproject.entity.User;
import az.ingress.msannouncementproject.exception.NotFoundException;
import az.ingress.msannouncementproject.repository.UserRepository;
import az.ingress.msannouncementproject.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static az.ingress.msannouncementproject.enums.ExceptionMessage.USER_NOT_FOUND;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> {
                            log.error("UserDetailsService.loadUserByUsername.error -- user not found username:{}", username);
                            return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(username));
                        }
                );

        return new MyUserDetails(user);
    }
}
