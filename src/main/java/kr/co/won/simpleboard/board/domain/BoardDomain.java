package kr.co.won.simpleboard.board.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode(of = {"idx"})
@Entity
@Table(name = "tbl_board")
@Where(clause = "deleted_flag = false")
@SQLDelete(sql = "UPDATE tbl_board SET deleted_flag=true where idx = ?")
@Filter(name = "allDataFilter")
@FilterDef(name = "allDataFilter", defaultCondition = "deleted_flag not nullable")
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
