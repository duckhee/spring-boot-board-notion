package kr.co.won.simpleboard.user.redis.repository;

import kr.co.won.simpleboard.user.redis.domain.PasswordForgetToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordForgetRepository extends KeyValueRepository<PasswordForgetToken, String> {
}
