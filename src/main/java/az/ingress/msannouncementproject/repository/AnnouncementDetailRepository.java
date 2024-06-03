package az.ingress.msannouncementproject.repository;

import az.ingress.msannouncementproject.entity.AnnouncementDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDetailRepository extends JpaRepository<AnnouncementDetail, Long> {
}
