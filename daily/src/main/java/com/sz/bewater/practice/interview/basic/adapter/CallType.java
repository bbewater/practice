package com.sz.bewater.practice.interview.basic.adapter;

import java.lang.annotation.*;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CallType {
    String value() default "";
}
