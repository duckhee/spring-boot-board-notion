package kr.co.won.simpleboard.auth;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.exception.UserErrorCode;
import kr.co.won.simpleboard.user.exception.UserException;
import kr.co.won.simpleboard.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserPersistence userPersistence;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomain findUser = userPersistence.findByEmail(username).orElse(userPersistence.findByUserId(username).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND)));
        return new AuthUser(findUser);
    }
}
