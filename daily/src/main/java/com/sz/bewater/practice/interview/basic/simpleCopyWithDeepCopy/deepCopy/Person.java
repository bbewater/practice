package com.sz.bewater.practice.interview.basic.simpleCopyWithDeepCopy.deepCopy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person implements Cloneable{

    private String name;
    private Address address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person clone = (Person)super.clone();
        //对象类型需要手动进行拷贝 来实现深拷贝
        clone.address = (Address)clone.address.clone();
        return clone;
    }
}
