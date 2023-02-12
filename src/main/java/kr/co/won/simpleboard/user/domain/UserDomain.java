package kr.co.won.simpleboard.user.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_user")
@EqualsAndHashCode(of = {"idx"})
@ToString
@SQLDelete(sql = "UPDATE tbl_user SET deleted_flag = true where idx=?")
@Where(clause = "deleted_flag = false")
public class UserDomain {

    protected UserDomain() {

    }

    @Id
    @Column(name = "idx", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "deleted_flag", nullable = false)
    @Builder.Default
    private boolean deletedFlag = false;

    @Column(name = "verified_flag", nullable = false)
    @Builder.Default
    private boolean verifiedFlag = false;

    @Column(name = "verified_token")
    private String verifiedToken;

    @Column(name = "verified_time")
    private LocalDateTime verifiedTime;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    private UserDomain(String userId, String userEmail, String name, String password) {
        this.userId = userId;
        this.email = userEmail;
        this.name = name;
        this.password = password;
        this.verifiedToken = UUID.randomUUID().toString();
    }

    public static UserDomain of(String userId, String userEmail, String name, String password) {
        return new UserDomain(userId, userEmail, name, password);
    }

    /** User Domain Method */

    /**
     * verified token generate
     */
    public String generateToken() {
        this.verifiedToken = UUID.randomUUID().toString();
        return this.verifiedToken;
    }

    /**
     * verified token
     */
    public boolean tokenVerified(String token) {
        if (token.isBlank()) {
            return false;
        }
        if (token.equals(this.verifiedToken)) {
            this.verifiedTime = LocalDateTime.now();
            this.verifiedFlag = true;
            return true;
        }
        return false;
    }
}
