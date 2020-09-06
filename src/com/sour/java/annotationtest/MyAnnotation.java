package com.sour.java.annotationtest;

/**
 *
 *
 * @author xgl
 * @date 2020/9/6 13:59
 **/
public @interface MyAnnotation {

    String value() default "hi";

}
