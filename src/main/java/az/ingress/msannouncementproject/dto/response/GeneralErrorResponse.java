package az.ingress.msannouncementproject.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralErrorResponse {
    private String message;

    private ErrorResponse errorResponse;
}
