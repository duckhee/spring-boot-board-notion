package kr.co.won.simpleboard.email;

public interface EmailService {

    /**
     * registered user send verified email
     */
    default void registeredEmail() {
    }

    /**
     * user forget password send Email
     */
    default void forgetPasswordEmail() {

    }
}
