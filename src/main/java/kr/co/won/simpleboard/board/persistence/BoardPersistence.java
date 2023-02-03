package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardPersistence extends JpaRepository<BoardDomain, Long>, BoardPersistenceExtension {
    Optional<BoardDomain> findByIdx(Long boardIdx);
}
