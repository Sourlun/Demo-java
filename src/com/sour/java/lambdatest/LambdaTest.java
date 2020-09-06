package com.sour.java.lambdatest;

import java.util.Comparator;

/**
 *  Lambda表达式
 *
 * @author xgl
 * @date 2020/9/6 15:55
 **/
public class LambdaTest {

    public static void main(String[] args) {


        System.out.println("=====  原来的方法  =======");

        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(com1.compare(12, 22));



        //  =====  Lambda表达式  =======

        System.out.println("=====  Lambda表达式  =======");

        Comparator<Integer> com2 = (o1, o2) -> {
           return Integer.compare(o1, o2);
        };
        System.out.println(com2.compare(12, 22));



        //  ============  简写  ========================

        System.out.println("=====  简写  =======");

        Comparator<Integer> compare3 = Integer::compare;
        System.out.println(compare3.compare(11,10));

    }


}
