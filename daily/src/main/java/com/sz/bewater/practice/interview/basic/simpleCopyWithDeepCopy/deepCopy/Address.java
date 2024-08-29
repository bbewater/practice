package com.sz.bewater.practice.interview.basic.simpleCopyWithDeepCopy.deepCopy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address implements Cloneable{
    private String city;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
