package kr.co.won.simpleboard.auth;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.domain.UserRoleDomain;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Transactional(readOnly = true)
public class AuthUser extends User {

    private UserDomain user;

    public AuthUser(UserDomain user) {
        super(user.getUserId(), user.getPassword(), makeUserAuthorities(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> makeUserAuthorities(Set<UserRoleDomain> roles) {
        return roles.stream().map(UserRoleDomain::getRole).map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
    }
}
