package kr.co.won.simpleboard.board.factory;


import kr.co.won.simpleboard.board.domain.BoardDomain;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;


@TestComponent
public class BoardDomainRandomFactory {

    public BoardDomain allRandomBoard() {
        // random parameter
        EasyRandomParameters randomParameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis());
        return new EasyRandom(randomParameters).nextObject(BoardDomain.class);
    }

    public BoardDomain createBoard(String title, String content) {
        LongRangeRandomizer longRangeRandomizer = new LongRangeRandomizer(1L, 1000L);
        Predicate<Field> executeBoardIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeTitle = named("title")
                .and(ofType(String.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeContent = named("content")
                .and(ofType(String.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeDeleteFlag = named("deletedFlag")
                .and(ofType(boolean.class))
                .and(inClass(BoardDomain.class));
        EasyRandomParameters randomParameters = new EasyRandomParameters().seed(System.currentTimeMillis())
                .randomize(executeBoardIdx, longRangeRandomizer)
                .randomize(executeTitle, () -> title)
                .randomize(executeContent, () -> content)
                .randomize(executeDeleteFlag, () -> false);
        BoardDomain testBoard = new EasyRandom(randomParameters).nextObject(BoardDomain.class);
        return testBoard;
    }

    public BoardDomain createBoard(Long idx, String title, String content) {
        Predicate<Field> executeBoardIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeTitle = named("title")
                .and(ofType(String.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeContent = named("content")
                .and(ofType(String.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeDeleteFlag = named("deletedFlag")
                .and(ofType(boolean.class))
                .and(inClass(BoardDomain.class));
        EasyRandomParameters randomParameters = new EasyRandomParameters().seed(System.currentTimeMillis())
                .randomize(executeBoardIdx, () -> idx)
                .randomize(executeTitle, () -> title)
                .randomize(executeContent, () -> content)
                .randomize(executeDeleteFlag, () -> false);
        BoardDomain testBoard = new EasyRandom(randomParameters).nextObject(BoardDomain.class);
        return testBoard;
    }

    public BoardDomain createBoard(Long idx) {
        Predicate<Field> executeBoardIdx = named("idx")
                .and(ofType(Long.class))
                .and(inClass(BoardDomain.class));
        Predicate<Field> executeDeleteFlag = named("deletedFlag")
                .and(ofType(boolean.class))
                .and(inClass(BoardDomain.class));
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters().seed(System.currentTimeMillis())
                .randomize(executeBoardIdx, () -> idx)
                .randomize(executeDeleteFlag, () -> false);

        BoardDomain testBoard = new EasyRandom(easyRandomParameters).nextObject(BoardDomain.class);
        return testBoard;

    }

}
