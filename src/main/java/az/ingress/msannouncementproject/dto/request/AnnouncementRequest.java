package az.ingress.msannouncementproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementRequest {
    private String username;

    private Integer viewCount;

    private Long announcementDetailsId;
}
