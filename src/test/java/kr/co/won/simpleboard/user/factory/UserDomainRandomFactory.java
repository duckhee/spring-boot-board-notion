package kr.co.won.simpleboard.user.factory;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.domain.UserRole;
import kr.co.won.simpleboard.user.domain.UserRoleDomain;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;


@TestComponent
public class UserDomainRandomFactory {

    public UserDomain allRandomUser(UserRole role) {
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .randomize(executeDeleted, () -> false);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        return userDomain;
    }

    public UserDomain allRandomUser(String userId, String email, String password, UserRole role) {
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
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        return userDomain;
    }

    public UserDomain allRandomUser(String userId, String email, String username, String password, UserRole role) {
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
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserName, () -> username)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        return userDomain;
    }
    public UserDomain allRandomUser(Long userIdx,String userId, String email, String username, String password, UserRole role) {
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
                .randomize(executeDeleted, () -> false)
                .randomize(executeIdx, ()-> userIdx)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserName, () -> username)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, role);
        return userDomain;
    }

    public UserDomain allRandomUser() {
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .randomize(executeDeleted, () -> false);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        return userDomain;
    }

    public UserDomain allRandomUser(String userId, String email, String password) {
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
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        return userDomain;
    }

    public UserDomain allRandomUser(String userId, String email, String username, String password) {
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
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserName, () -> username)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        return userDomain;
    }


    public UserDomain allRandomUser(Long userIdx, String userId, String email, String username, String password) {
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
                .randomize(executeIdx, ()-> userIdx)
                .randomize(executeDeleted, () -> false)
                .randomize(executeUserId, () -> userId)
                .randomize(executeUserEmail, () -> email)
                .randomize(executeUserName, () -> username)
                .randomize(executeUserPassword, () -> password);
        UserDomain userDomain = new EasyRandom(parameters).nextObject(UserDomain.class);
        UserRoleDomain.of(userDomain, UserRole.USER);
        return userDomain;
    }
}
