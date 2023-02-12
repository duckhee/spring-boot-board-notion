package kr.co.won.simpleboard.user.domain;

@Getter
@ToString
@Entity
@SqlDelete()
@Where()
public class UserDomain {

    protected UserDomain() {

    }

    private long idx;

    private String email;

    private String name;

    private boolean deletedFlag;

    private boolean verifiedFlag;

    private String verifiedToken;

    private LocalDateTime verifiedTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
