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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleDomain> roles = new HashSet<>();

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

    /**
     * user role add Method
     */
    public void addRole(UserRoleDomain role) {
        this.roles.add(role);
        role.addUser(this);
    }

    public void addRole(UserRoleDomain... roles) {
        Arrays.stream(roles).forEach(role -> {
            this.roles.add(role);
            role.addUser(this);
        });
    }

    /**
     * user role check Method
     */
    public boolean hasRole(UserRole role) {
        return this.roles.stream().map(UserRoleDomain::getRole).collect(Collectors.toSet()).contains(role);
    }

    public boolean hasRole(UserRole... roles) {
        Set<UserRole> searchRoles = Arrays.stream(roles).collect(Collectors.toSet());
        return this.roles.stream().map(UserRoleDomain::getRole).anyMatch(role -> searchRoles.contains(role));
    }
}
