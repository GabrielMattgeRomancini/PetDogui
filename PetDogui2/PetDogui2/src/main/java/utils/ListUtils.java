package utils;

import java.util.List;

public class ListUtils {
    public static <T> void printList(List<T> list, String emptyMessage) {
        if (list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (T item : list) {
                System.out.println(item);
            }
        }
    }
}
