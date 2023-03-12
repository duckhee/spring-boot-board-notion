package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardCategoryPersistence extends JpaRepository<BoardCategoryDomain, Long>, BoardCategoryPersistenceExtension {

    Optional<BoardCategoryDomain> findByCategoryCode(String code);
}
