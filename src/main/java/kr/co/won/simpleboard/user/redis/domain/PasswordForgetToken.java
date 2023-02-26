package kr.co.won.simpleboard.user.redis.domain;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@RedisHash(value = "forget_pw_token", timeToLive = 3600)
public class PasswordForgetToken {

    protected PasswordForgetToken() {
    }

    @Id
    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "email_token")
    private String emailVerifiedToken;

    private PasswordForgetToken(String token, String email, String emailVerifiedToken) {
        this.token = token;
        this.email = email;
        this.emailVerifiedToken = emailVerifiedToken;
    }

    public static PasswordForgetToken of(String email, String emailVerifiedToken) {
        return new PasswordForgetToken(UUID.randomUUID().toString(), email, emailVerifiedToken);
    }
}
