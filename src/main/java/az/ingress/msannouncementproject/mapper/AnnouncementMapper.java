package az.ingress.msannouncementproject.mapper;

import az.ingress.msannouncementproject.dto.request.AnnouncementRequest;
import az.ingress.msannouncementproject.dto.response.AnnouncementResponse;
import az.ingress.msannouncementproject.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnnouncementMapper {
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "title", source = "announcementDetail.title")
    @Mapping(target = "description", source = "announcementDetail.description")
    @Mapping(target = "price", source = "announcementDetail.price")
    AnnouncementResponse entityToResponse(Announcement announcement);

    Announcement requestToEntity(AnnouncementRequest announcementRequest);
}
