package kr.co.won.simpleboard.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"idx"})
@ToString(exclude = {"board"})
@Entity
@Table(name = "tbl_board_reply")
@SQLDelete(sql = "UPDATE tbl_baord_reply SET deleted=TRUE WHERE idx=?")
@Where(clause = "deleted=false")
public class ReplyDomain {

    protected ReplyDomain() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private Long idx;

    @Column(name = "replyer", nullable = false)
    private String replyer;

    @Column(name = "reply_content", nullable = false)
    private String replyContent;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder.Default
    @Column(name = "deleted", nullable = false
    )
    private boolean deleted = false;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_idx")
    private BoardDomain board;

    @Builder
    private ReplyDomain(String replyer, String replyContent) {
        this.replyContent = replyContent;
        this.replyer = replyer;
    }

    public static ReplyDomain of(String replyer, String replyContent) {
        return new ReplyDomain(replyer, replyContent);
    }

}
