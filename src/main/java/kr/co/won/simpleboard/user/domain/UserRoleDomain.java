package kr.co.won.simpleboard.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tbl_user_role")
public class UserRoleDomain {

    protected UserRoleDomain() {

    }

    @Id
    @Column(name = "idx", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role = UserRole.USER;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    private UserDomain user;


    private UserRoleDomain(UserRole role) {
        this.role = role;
    }

    @Builder
    private UserRoleDomain(UserDomain user, UserRole role) {
        this.user = user;
        this.role = role;
        user.addRole(this);
    }

    public static UserRoleDomain of(UserDomain user, UserRole role) {
        return new UserRoleDomain(user, role);
    }


    public void addUser(UserDomain user) {
        this.user = user;
    }

}
