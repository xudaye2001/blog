package com.example.demo.blog.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ConstraintViolationExceptionHandler {
    /**
     * 获取批量异常信息
     */
    public static String getMessage(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (javax.validation.ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return messages;
    }
}
