package com.example.usualtest.springtest;

import lombok.Data;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class SpringIOC {
    PropertyDescriptor pd;
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    {
        try {
            pd = new PropertyDescriptor(SpringIOC.class.getName(), SpringIOC.class);
            System.out.println(pd);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    @Autowired()
    //一级缓存：单例池 singletonObjects
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    //二级缓存：earlySingletonObjects
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    //三级缓存：singletonFactories
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
    public void test(){
    }


    private String name;


}
