package com.example.proj4;

public interface Customizable {
    /**
     Denotes the behavior of adding objects
     @param obj the object that is to be added
     */
    boolean addObject(Object obj);

    /**
     Denotes the behavior of removing objects
     @param obj the object that is to be removed
     */
    boolean remove(Object obj);
}
