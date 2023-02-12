package kr.co.won.simpleboard.user.factory;

import kr.co.won.simpleboard.user.domain.UserDomain;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;


@TestComponent
public class UserDomainRandomFactory {

    public UserDomain allRandomUser() {
        Predicate<Field> executeDeleted = named("deletedFlag")
                .and(ofType(Boolean.class))
                .and(inClass(UserDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .randomize(executeDeleted, () -> false);
        return new EasyRandom(parameters).nextObject(UserDomain.class);
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
        return new EasyRandom(parameters).nextObject(UserDomain.class);
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
        return new EasyRandom(parameters).nextObject(UserDomain.class);
    }
}
