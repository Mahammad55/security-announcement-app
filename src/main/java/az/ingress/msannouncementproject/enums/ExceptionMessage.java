package az.ingress.msannouncementproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    ANNOUNCEMENT_NOT_FOUND("Announcement by parameter=%s not found"),

    ANNOUNCEMENT_DETAILS_NOT_FOUND("Announcement details by parameter=%s not found"),

    USER_NOT_FOUND("User by parameter=%s not found"),

    USER_ALREADY_EXIST("User by parameter=%s is already exist");

    private final String message;
}
