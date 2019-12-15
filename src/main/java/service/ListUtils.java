package service;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ListUtils {

    private static final Random random = new Random();

    static public <T> T getRandomElement(List<T> nextPossiblePoints) {
        return nextPossiblePoints.get(random.nextInt(nextPossiblePoints.size()));
    }

    static public <T> T last(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("unreachable statement");
        }
        return list.get(list.size() - 1);
    }

}
