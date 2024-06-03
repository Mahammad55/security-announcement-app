package az.ingress.msannouncementproject.repository;

import az.ingress.msannouncementproject.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @EntityGraph(attributePaths = User.Fields.authorities)
    Optional<User> findUserByUsername(String username);
}