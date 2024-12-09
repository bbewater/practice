package com.sz.bewater.practice.interview.basic.simpleCopyWithDeepCopy.simpleCopy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person implements Cloneable{
    private String name;
    private Integer age;
    private Address address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();   //浅拷贝
    }
}
