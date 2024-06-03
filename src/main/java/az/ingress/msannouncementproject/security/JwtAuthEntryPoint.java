package az.ingress.msannouncementproject.security;

import az.ingress.msannouncementproject.dto.response.ErrorResponse;
import az.ingress.msannouncementproject.dto.response.GeneralErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss")))
                .status(response.getStatus())
                .path(request.getRequestURI())
                .build();

        GeneralErrorResponse generalErrorResponse = GeneralErrorResponse.builder()
                .errorResponse(errorResponse)
                .message(authException.getMessage())
                .build();

        ServletOutputStream outputStream = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputStream, generalErrorResponse);
    }
}
