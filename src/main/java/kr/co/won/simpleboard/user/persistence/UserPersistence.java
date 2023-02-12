package kr.co.won.simpleboard.user.persistence;

import kr.co.won.simpleboard.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersistence extends JpaRepository<UserDomain, Long> {
}
