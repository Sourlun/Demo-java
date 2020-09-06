package com.sour.java.lambdatest;


import java.util.Comparator;
import java.util.function.Consumer;

/**
 *  Lambda表达式的使用
 *
 *  1, 举例: (o1, o2) -> {
 *            return Integer.compare(o1, o2);
 *          };
 *
 *  2, 格式:
 *      -> 左边: 形参列表(a, b)
 *      -> 右边: 重写的方法体
 *
 *  3, 使用
 *      下面
 *
 *  4, Lambda本质也是对象
 *
 * @author xgl
 * @date 2020/9/6 16:03
 **/
public class LambdaUse {


    public static void main(String[] args) {
        //格式一: 无参,无返回值
        LambdaUse.test1();

        //格式二: 有参,无返回值
        LambdaUse.test2();

        // 格式三: 类型推断, 自动根据返回值来推断形参类型
        LambdaUse.test3();

        // 格式四: 两个以上的参数, 多条执行语句, 有返回值
        LambdaUse.test4();
    }


    /**
     *  格式一: 无参,无返回值
     *
     * @author xgl
     * @date 2020/9/6 16:08
     **/
    public static void test1() {
        Runnable t1 = () -> System.out.println("无参,无返回值");
        t1.run();
    }

    /**
     *  格式二: 有参,无返回值
     *
     * @author xgl
     * @date 2020/9/6 16:08
     **/
    public static void test2() {
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("谎言和誓言最大的区别是什么?");


        Consumer<String> con2 = (String s) -> {
            System.out.println(s);
        };
        con2.accept("一个听的人当真了, 一个说的人当真了");
    }

    /**
     *  格式三: 类型推断, 自动根据返回值来推断形参类型
     *
     * @author xgl
     * @date 2020年9月6日 16点22分
     **/
    public static void test3() {
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("谎言和誓言最大的区别是什么?");


        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("一个听的人当真了, 一个说的人当真了");
    }


    /**
     *  格式四: 两个以上的参数, 多条执行语句, 有返回值
     *
     * @author xgl
     * @date 2020年9月6日 16点33分
     **/
    public static void test4() {

        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };

        System.out.println(com1.compare(12, 44));

        Comparator<Integer> com2 = ((o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        });

        System.out.println(com2.compare(12, 44));
    }


}
