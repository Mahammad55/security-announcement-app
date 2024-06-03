package az.ingress.msannouncementproject.dto.request;

import az.ingress.msannouncementproject.annotation.ValidPassword;
import az.ingress.msannouncementproject.annotation.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @ValidUsername
    private String username;

    @ValidPassword
    private String password;
}
