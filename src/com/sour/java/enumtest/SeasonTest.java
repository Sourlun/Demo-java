package com.sour.java.enumtest;


/**
 *  枚举类的使用
 *      1,理解: 类的对象只有有限个,确定的,我们称为枚举类
 *      2,当定义一组常量的时候,建议使用枚举类
 *      3,如果 枚举类只有一个对象, 则可以作为单例模式的实现方式
 *
 *   如何定义枚举类(使用enum关键字)
 *
 *
 * @author xgl
 * @date 2020/9/6 11:39
 **/
public class SeasonTest {


    public static void main(String[] args) {
        Season summer = Season.SUMMER;
        System.out.println(summer);

        System.out.println(summer.getSeasonName());
        System.out.println(summer.getSeasonDesc());

        // 输出枚举类的父类
        System.out.println(Season.class.getSuperclass());

        // -------  输出枚举类含有的类  ------------------

        Season[] values = Season.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        // --------  根据类名查找同名的枚举类  ----------------------

        Season winter = Season.valueOf("WINTER");
        System.out.println(winter);
    }

}
