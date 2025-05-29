package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.stereotype.Component;

public abstract class ProvinceTemplate{

    public final void  templateMethod(){
        check();
        specialBusiness();
        commonBusiness();
    }


    private void check(){
        System.out.println("校验操作");
    }

    private void commonBusiness(){
        System.out.println("公共的业务逻辑");
    }

    protected void specialBusiness(){
        throw new UnsupportedOperationException("子类自选实现");
    }

}
