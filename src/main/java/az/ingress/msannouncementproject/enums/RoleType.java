package az.ingress.msannouncementproject.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleType {
    ROLE_USER("USER"),

    ROLE_ADMIN("ADMIN");

    public final String value;
}
