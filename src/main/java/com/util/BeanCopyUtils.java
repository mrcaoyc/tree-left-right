package com.util;

import org.springframework.beans.BeanUtils;

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
}
