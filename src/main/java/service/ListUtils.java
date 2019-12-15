package service;

import java.util.List;

public class ListUtils {

    static public <T> T last(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("unreachable statement");
        }
        return list.get(list.size() - 1);
    }

}
