package az.ingress.msannouncementproject.dto.response;

import az.ingress.msannouncementproject.validation.ValidationError;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrorResponse {
    private List<ValidationError> validationErrors;

    private ErrorResponse errorResponse;
}
