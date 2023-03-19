package kr.co.won.simpleboard.board.factory;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import lombok.RequiredArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;

@TestComponent
public class BoardCategoryRandomFactory {

    public BoardCategoryDomain allBoardCategory() {
        EasyRandomParameters parameters = new EasyRandomParameters().seed(System.currentTimeMillis());
        return new EasyRandom(parameters).nextObject(BoardCategoryDomain.class);
    }

    public BoardCategoryDomain createBoardCategory(String code, String name, int depth, BoardCategoryDomain parentCategory) {
        LongRangeRandomizer rangeRandomizer = new LongRangeRandomizer(1L, 100L);
        Predicate<Field> executeCategoryIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(BoardCategoryDomain.class));
        Predicate<Field> executeCategoryCode = named("categoryCode")
                .and(ofType(String.class))
                .and(inClass(BoardCategoryDomain.class));
        Predicate<Field> executeCategoryName = named("name")
                .and(ofType(String.class))
                .and(inClass(BoardCategoryDomain.class));
        Predicate<Field> executeCategoryDepth = named("depth")
                .and(ofType(int.class))
                .and(inClass(BoardCategoryDomain.class));
        Predicate<Field> executeParentCategory = named("parent")
                .and(ofType(BoardCategoryDomain.class))
                .and(inClass(BoardCategoryDomain.class));
        EasyRandomParameters parameters = new EasyRandomParameters().seed(System.currentTimeMillis())
                .randomize(executeCategoryIdx, () -> rangeRandomizer)
                .randomize(executeCategoryCode, ()-> code)
                .randomize(executeCategoryName, ()-> name)
                // todo check
                .randomize(executeCategoryDepth, ()-> depth > 0 ? depth : 0)
                .randomize(executeParentCategory, ()-> executeParentCategory != null ? executeParentCategory: null);
        return new EasyRandom(parameters).nextObject(BoardCategoryDomain.class);
    }
}
