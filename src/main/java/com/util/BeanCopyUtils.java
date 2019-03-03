package com.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T target;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> List<T> copyList(List<?> source, Class<T> clazz) {
        int size = CollectionUtils.isEmpty(source) ? 0 : source.size();
        List<T> list = new ArrayList<>(size);
        if (size == 0) {
            return list;
        }
        source.forEach(s -> list.add(BeanCopyUtils.copy(s, clazz)));
        return list;
    }
}
