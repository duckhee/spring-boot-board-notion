package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardPersistence extends JpaRepository<BoardDomain, Long>, BoardPersistenceExtension {
    Optional<BoardDomain> findByIdx(Long boardIdx);

    List<BoardDomain> findByIdxIn(List<Long> boardIdxes);

    @Query(value = "SELECT * FROM tbl_board as board WHERE board.deleted_flag is not NULL", nativeQuery = true)
    List<BoardDomain> allBoards();
}
