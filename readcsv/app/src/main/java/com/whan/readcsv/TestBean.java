package com.whan.readcsv;

import com.opencsv.bean.CsvBindByName;

public class TestBean {
    // 通过属性名绑定csv列名, 属性名必须与csv列名相同
    @CsvBindByName
    private String code;

    @CsvBindByName
    private String name;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}