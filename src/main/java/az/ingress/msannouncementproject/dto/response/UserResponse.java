package az.ingress.msannouncementproject.dto.response;

import az.ingress.msannouncementproject.enums.GenderType;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private Integer age;

    private GenderType genderType;
}
