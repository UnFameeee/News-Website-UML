package com.example.newswebsite.utils;

import java.lang.reflect.Field;

public class LoopObjectInstance {
    private static LoopObjectInstance instance;
    private LoopObjectInstance(){}
    public static LoopObjectInstance getInstance(){
        if(instance == null){
            synchronized (LoopObjectInstance.class){
                if(instance == null){
                    instance = new LoopObjectInstance();
                }
            }
        }
        return instance;
    }

    public void mergingContent(Class targetClass, Object targetObject, Object oldTargetObject){
        for (Field field : targetClass.getDeclaredFields()) {
            field.setAccessible(true);
            try{
                if(field.get(targetObject) == ""){
                    field.set(targetObject, field.get(oldTargetObject));
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
