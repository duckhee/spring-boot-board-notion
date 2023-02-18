package kr.co.won.simpleboard.user.factory;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.domain.UserRole;
import kr.co.won.simpleboard.user.domain.UserRoleDomain;
import kr.co.won.simpleboard.user.persistence.UserPersistence;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;
import static org.jeasy.random.FieldPredicates.inClass;

@TestComponent
public class UserRandomFactory {

    private final UserPersistence userPersistence;

    public UserRandomFactory(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }


    public UserDomain allRandomUser(UserRole role) {
        Predicate<Field> executeIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .excludeField(executeIdx)
                .randomize(executeDeleted, () -> false);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        UserDomain savedUser = userPersistence.save(userDomain);
        return savedUser;
    }

    public UserDomain allRandomUser(String userId, String email, String password, UserRole role) {
        Predicate<Field> executeIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserId = named("userId")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserEmail = named("email")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserName = named("name")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserPassword = named("password")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .excludeField(executeIdx)
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        UserDomain savedUser = userPersistence.save(userDomain);

        return savedUser;
    }

    public UserDomain allRandomUser(String userId, String email, String username, String password, UserRole role) {
        Predicate<Field> executeIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserId = named("userId")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserEmail = named("email")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserName = named("name")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserPassword = named("password")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .excludeField(executeIdx)
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserName, () -> username)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        UserDomain savedUser = userPersistence.save(userDomain);

        return savedUser;
    }


    public UserDomain allRandomUser() {
        Predicate<Field> executeIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .excludeField(executeIdx)
                .seed(System.currentTimeMillis())
                .randomize(executeDeleted, () -> false);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        UserDomain savedUser = userPersistence.save(userDomain);

        return savedUser;
    }

    public UserDomain allRandomUser(String userId, String email, String password) {
        Predicate<Field> executeIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserId = named("userId")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserEmail = named("email")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserName = named("name")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserPassword = named("password")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .excludeField(executeIdx)
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        UserDomain savedUser = userPersistence.save(userDomain);

        return savedUser;
    }

    public UserDomain allRandomUser(String userId, String email, String username, String password) {
        Predicate<Field> executeIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserId = named("userId")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserEmail = named("email")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserName = named("name")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeUserPassword = named("password")
                .and(ofType(String.class))
                .and(inClass(UserDomain.class));
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .excludeField(executeIdx)
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserName, () -> username)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        UserDomain savedUser = userPersistence.save(userDomain);

        return savedUser;
    }


}
