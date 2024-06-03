package az.ingress.msannouncementproject.mapper;

import az.ingress.msannouncementproject.dto.request.UserRequest;
import az.ingress.msannouncementproject.dto.response.UserResponse;
import az.ingress.msannouncementproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User requestToEntity(UserRequest userRequest);

    UserResponse entityToResponse(User user);


}
