package az.ingress.msannouncementproject.repository;

import az.ingress.msannouncementproject.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findAuthoritiesByAuthority(String authority);
}
