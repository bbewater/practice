package com.sz.bewater.practice.interview.basic.simpleCopyWithDeepCopy.simpleCopy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address implements Cloneable{
    private String city;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();   //浅拷贝
    }
}
