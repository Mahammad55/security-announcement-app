package az.ingress.msannouncementproject.mapper;

import az.ingress.msannouncementproject.dto.response.AnnouncementResponse;
import az.ingress.msannouncementproject.dto.response.PageAnnouncementResponse;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageAnnouncementResponse mapAnnouncementResponsePageToCustomPageResponse(Page<AnnouncementResponse> page) {
        return PageAnnouncementResponse.builder()
                .announcementResponseList(page.getContent())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber())
                .hasNextPage(page.hasNext())
                .lastPageNumber(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
