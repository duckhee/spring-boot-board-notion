package kr.co.won.simpleboard.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = {"parent"})
@EqualsAndHashCode(of = {"idx"})
@Entity
@Table(name = "tbl_board_category")
@SQLDelete(sql = "UPDATE tbl_board_category SET deleted=TRUE WHERE idx=?")
@Where(clause = "deleted=false")
@AllArgsConstructor
public class BoardCategoryDomain {

    protected BoardCategoryDomain() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private Long idx;

    @Column(name = "category_code", nullable = false)
    private String categoryCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder.Default
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Setter
    @Builder.Default
    @Column(name = "category_depth", nullable = false)
    private int depth = 0;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardCategoryDomain.class)
    @JoinColumn(name = "parent_id")
    private BoardCategoryDomain parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = BoardCategoryDomain.class)
    private List<BoardCategoryDomain> subCategory = new ArrayList<>();

    @Builder
    private BoardCategoryDomain(String categoryCode, String name) {
        this.categoryCode = categoryCode;
        this.name = name;
    }

    private BoardCategoryDomain(String categoryCode, String name, int depth, BoardCategoryDomain parent) {
        this.categoryCode = categoryCode;
        this.name = name;
        this.depth = 0;
        if (parent != null) {
            parent.addSubCategory(this);
        }
    }

    public void addSubCategory(BoardCategoryDomain boardCategoryDomain) {
        this.subCategory.add(boardCategoryDomain);
        boardCategoryDomain.setDepth(this.depth + 1);
        boardCategoryDomain.addParentCategory(this);
    }

    public void addParentCategory(BoardCategoryDomain boardCategoryDomain) {
        this.parent = boardCategoryDomain;
    }

    public static BoardCategoryDomain of(String categoryCode, String name) {
        return new BoardCategoryDomain(categoryCode, name);
    }

    public static BoardCategoryDomain of(String categoryCode, String name, int depth, BoardCategoryDomain parent) {
        BoardCategoryDomain boardCategoryDomain = new BoardCategoryDomain(categoryCode, name, depth, parent);
        return boardCategoryDomain;
    }
}
