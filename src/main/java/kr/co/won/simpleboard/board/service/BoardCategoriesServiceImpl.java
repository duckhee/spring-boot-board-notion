package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.persistence.BoardCategoryPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCategoriesServiceImpl implements BoardCategoriesService {

    private final BoardCategoryPersistence categoryPersistence;
}
