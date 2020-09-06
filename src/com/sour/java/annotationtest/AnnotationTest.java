package com.sour.java.annotationtest;


/**
 *  注解的使用
 *      1, jdk5.0新增的功能
 *      2, Annotation其实就是代码的特殊标记, 这些标记在类加载, 编译, 运行的时候被读取,
 *          程序员可以在不改变代码原逻辑的情况下, 在原文件嵌入补充信息
 *      3, 例如: 配置应用程序的任何切面, 代替javaEE旧版遗留的繁琐的代码和XML配置等;
 *
 *
 *   自定义注解
 *      1, 注解的声明为: @interface
 *      2, 内部定义成员, 通常使用value表示
 *      3, 可以指定成员的默认值, 可以使用default定义
 *      4, 如果 自定义注解没有成员, 表明是一个标识作用
 *
 *      如果 注解有成员, 在使用注解的时候需要给成员赋值
 *
 *
 *
 *
 *
 * @author xgl
 * @date 2020/9/6 13:49
 **/
public class AnnotationTest {
}
