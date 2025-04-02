package net.chikorita_lover.kaleidoscope;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class CollectionUtils {
    @SafeVarargs
    public static <E> List<E> append(List<E> list, E... elements) {
        return ImmutableList.<E>builder().addAll(list).add(elements).build();
    }

    @SafeVarargs
    public static <E> List<E> append(List<E> list, int index, E... elements) {
        ImmutableList.Builder<E> builder = ImmutableList.builder();
        int length = elements.length;
        for (int i = 0; i < list.size() + length; ++i) {
            if (i < index) {
                builder.add(list.get(i));
            } else if (i == index) {
                builder.add(elements);
            } else {
                builder.add(list.get(i - length));
            }
        }
        return builder.build();
    }

    @SafeVarargs
    public static <E> List<E> join(List<E> list, List<E>... otherLists) {
        ImmutableList.Builder<E> builder = ImmutableList.<E>builder().addAll(list);
        for (List<E> otherList : otherLists) {
            builder.addAll(otherList);
        }
        return builder.build();
    }
}
