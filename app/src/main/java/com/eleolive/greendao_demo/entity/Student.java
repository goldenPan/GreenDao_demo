package com.eleolive.greendao_demo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @project_name GreenDao_demo
 * @class name：com.eleolive.greendao_demo.gen
 * @class describe
 * @anthor yj
 * @time 2017-04-16 15:48
 * @change_user
 * @chang time
 * @class describe
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String address;
    Integer age;
    @Generated(hash = 260639190)
    public Student(Long id, String name, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Id: "+getId()+"   name: "+getName()+"   addr: "+getAddress()+"   age: "+getAge()+"\n";
    }
}
