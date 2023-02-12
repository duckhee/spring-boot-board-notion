package kr.co.won.simpleboard.user.persistence;

import kr.co.won.simpleboard.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPersistence extends JpaRepository<UserDomain, Long> {

    Optional<UserDomain> findByEmail(String email);

    Optional<UserDomain> findByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);

    boolean existsByEmailAndUserId(String email, String userId);
}
