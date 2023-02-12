package kr.co.won.simpleboard.user.factory;

import kr.co.won.simpleboard.user.persistence.UserPersistence;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class UserRandomFactory {

    private final UserPersistence userPersistence;

    public UserRandomFactory(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }


}
