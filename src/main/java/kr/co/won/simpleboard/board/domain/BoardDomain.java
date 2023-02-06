package kr.co.won.simpleboard.board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tbl_board")
@Where(clause = "deleted_flag=false")
@SQLDelete(sql = "deleted_flag=true where idx = ?")
public class BoardDomain {

    protected BoardDomain() {

    }

    @Id
    @Column(name = "idx", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @Column(name = "board_title", nullable = false)
    private String title;

    @Lob
    @Setter
    @Column(name = "board_content")
    private String content;

    @Setter
    @Builder.Default
    @Column(name = "deleted_flag", nullable = false)
    private boolean deletedFlag = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    private BoardDomain(String title, String content) {
        this.title = title;
        this.content = content;
        this.deletedFlag = false;
    }

    public static BoardDomain of(String title, String content) {
        return BoardDomain.builder()
                .title(title)
                .content(content)
                .build();
    }
}
