package kr.co.won.simpleboard.board.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import kr.co.won.simpleboard.board.dto.BoardCategoryResponseDto;
import kr.co.won.simpleboard.board.factory.BoardCategoryDomainFactory;
import kr.co.won.simpleboard.board.factory.BoardCategoryRandomFactory;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Rollback
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName(value = "Board Category DataBase Tests")
@Import(value = {
        BoardCategoryDomainFactory.class,
        BoardCategoryRandomFactory.class
})
class BoardCategoryPersistenceTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BoardCategoryPersistence categoryPersistence;

    @Autowired
    private BoardCategoryDomainFactory dbCategoryFactory;

    @Autowired
    private BoardCategoryRandomFactory factory;

    @Rollback(value = false)
    @DisplayName(value = "01. create Category Tests")
    @Test
    void createCategoryTests() {
        BoardCategoryDomain test = dbCategoryFactory.createCategory("200", "test", 0, null);
        BoardCategoryDomain subTest = dbCategoryFactory.createCategory("201", "subTest", 1, test);
        BoardCategoryDomain subTests = dbCategoryFactory.createCategory("202", "subTest2", 1, test);
        BoardCategoryDomain subTests2 = dbCategoryFactory.createCategory("2022", "subTest222", 1, subTest);
        BoardCategoryDomain test1 = dbCategoryFactory.createCategory("300", "test1", 0, null);
        BoardCategoryDomain subTest1 = dbCategoryFactory.createCategory("301", "subTest1", 1, test1);
    }

    @DisplayName(value = "02. list category Tests")
    @Test
    void listCategoryTests() {
        BoardCategoryDomain test = dbCategoryFactory.createCategory("200", "test", 0, null);
        BoardCategoryDomain subTest = dbCategoryFactory.createCategory("201", "subTest", 1, test);
        BoardCategoryDomain subTests = dbCategoryFactory.createCategory("202", "subTest2", 1, test);
        BoardCategoryDomain subTests2 = dbCategoryFactory.createCategory("2022", "subTest222", 1, subTest);
        BoardCategoryDomain test1 = dbCategoryFactory.createCategory("300", "test1", 0, null);
        BoardCategoryDomain subTest1 = dbCategoryFactory.createCategory("301", "subTest1", 1, test1);
        entityManager.flush();
        entityManager.clear();
        List<BoardCategoryDomain> all = categoryPersistence.findAll();
        System.out.println("all = " + all);
//        List<BoardCategoryResponseDto.Categories> allCategoriesDto = categoryPersistence.findAllCategoriesDto();
//        System.out.println("allCategoriesDto = " + allCategoriesDto);
        List<BoardCategoryDomain> allCategories = categoryPersistence.findAllCategories();
        System.out.println("allCategories = " + allCategories);
    }

    // TODO change
    @DisplayName(value = "03. detail Category Tests")
    @Test
    void detailCategoryTests() {
        BoardCategoryDomain test = dbCategoryFactory.createCategory("200", "test", 0, null);
        BoardCategoryDomain subTest = dbCategoryFactory.createCategory("201", "subTest", 1, test);
        BoardCategoryDomain subTests = dbCategoryFactory.createCategory("202", "subTest2", 1, test);
        BoardCategoryDomain subTests2 = dbCategoryFactory.createCategory("2022", "subTest222", 1, subTest);
        BoardCategoryDomain test1 = dbCategoryFactory.createCategory("300", "test1", 0, null);
        BoardCategoryDomain subTest1 = dbCategoryFactory.createCategory("301", "subTest1", 1, test1);
        entityManager.flush();
        entityManager.clear();
        PageDto pageDto = new PageDto();
//        Page<BoardCategoryResponseDto.Categories> categories = categoryPersistence.pagingCategories(null, null, pageDto.makePageable(0, "idx"));
//        System.out.println("categories = " + categories);

    }
}