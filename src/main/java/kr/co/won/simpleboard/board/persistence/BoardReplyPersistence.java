package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.ReplyDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReplyPersistence extends JpaRepository<ReplyDomain, Long> {

}
