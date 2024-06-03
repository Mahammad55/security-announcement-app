package az.ingress.msannouncementproject.dto.request;

import az.ingress.msannouncementproject.annotation.ValidAge;
import az.ingress.msannouncementproject.annotation.ValidPassword;
import az.ingress.msannouncementproject.annotation.ValidUsername;
import az.ingress.msannouncementproject.enums.GenderType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String name;

    private String surname;

    @ValidUsername
    private String username;

    @ValidPassword
    private String password;

    @ValidAge
    private Integer age;

    private GenderType genderType;
}
