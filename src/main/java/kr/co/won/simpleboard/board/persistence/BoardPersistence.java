package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPersistence extends JpaRepository<BoardDomain, Long> {
}
