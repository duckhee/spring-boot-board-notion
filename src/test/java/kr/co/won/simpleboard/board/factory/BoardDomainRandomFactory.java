package kr.co.won.simpleboard.board.factory;


import kr.co.won.simpleboard.board.domain.BoardDomain;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

@TestComponent
public class BoardDomainRandomFactory {

    public BoardDomain allRandomBoard() {
        // random parameter
        EasyRandomParameters randomParameters = new EasyRandomParameters()
                .seed(System.currentTimeMillis());
        return new EasyRandom(randomParameters).nextObject(BoardDomain.class);
    }

}
