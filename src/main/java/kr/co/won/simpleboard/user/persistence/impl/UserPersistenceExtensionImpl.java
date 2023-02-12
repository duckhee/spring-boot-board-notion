package kr.co.won.simpleboard.user.persistence.impl;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.persistence.UserPersistenceExtension;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserPersistenceExtensionImpl extends QuerydslRepositorySupport implements UserPersistenceExtension {

    public UserPersistenceExtensionImpl() {
        super(UserDomain.class);
    }
}
