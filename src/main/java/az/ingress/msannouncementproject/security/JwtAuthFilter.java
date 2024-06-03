package az.ingress.msannouncementproject.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    public static final String BEARER_ = "Bearer ";

    public static final String AUTHORIZATION = "Authorization";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER_)) {
            String tokenWithoutBearer = token.substring(BEARER_.length());

            Date date = jwtService.extractClaims(tokenWithoutBearer, Claims::getExpiration);
            if (date.before(new Date())) return;

            String username = jwtService.extractClaims(tokenWithoutBearer, Claims::getSubject);
            Authentication authentication = jwtService.getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
