package com.sour.java.streamapitest;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *  1, stream关注的是数据的运行, 与cpu打交道
 *      集合关注的是数据的存储, 与内存打交道
 *
 *  2,
 *      1,Stream 自己不会存储元素
 *      2,Stream 不会改变对象,执行完会返回带有结果的Stream
 *      3,Stream 操作是延迟的, 意味着它会等到需要结果的时候才执行
 *
 *  3, Stream 执行过程
 *      1, Stream的实例化
 *      2, 一系列的中心操作(过滤, 映射..)
 *          对数据进行处理
 *      3, 终止操作
 *
 * @author xgl
 * @date 2020/9/6 16:40
 **/
public class StreamApiTest {

    public static void main(String[] args) {

        // Stream的中间操作: 筛选和切片
        StreamApiTest.test4();

        // Stream的中间操作: 映射
        StreamApiTest.test5();

        // Stream的中间操作: 映射
        StreamApiTest.test6();

        // Stream的中间操作: 匹配与查找
        StreamApiTest.test7();

        // Stream的中间操作: 收集
        StreamApiTest.test8();
    }



    /**
     *  创建Stream的方式一: 通过集合
     *
     * @author xgl
     * @date 2020/9/6 16:46
     **/
    public static void test1() {
        List<Employee> employeeList = Employee.getEmployeeList();

        // 顺序流
        Stream<Employee> stream = employeeList.stream();

        // 并行流
        Stream<Employee> parallelStream = employeeList.parallelStream();
    }


    /**
     *  创建Stream的方式二: 通过数组
     *
     * @author xgl
     * @date 2020年9月6日 16点58分
     **/
    public static void test2() {
       int[] arr = {2,4,2,1,9,8,55,44,3,25,45,78,11,46};
       //  返回一个流
        IntStream stream = Arrays.stream(arr);
    }


    /**
     *  创建Stream的方式三: 通过Stream.of()
     *
     * @author xgl
     * @date 2020年9月6日 16点58分
     **/
    public static void test3() {
        Stream<Integer> integerStream = Stream.of(2, 4, 7, 3, 5, 4, 2, 1, 9);
    }


    /**
     *  Stream的中间操作: 筛选和切片
     *
     * @author xgl
     * @date 2020年9月6日 16点58分
     **/
    public static void test4() {
        List<Employee> list = Employee.getEmployeeList();

        // 过滤, 查询工资高于10 000 的人
        list.stream().filter( employee -> employee.getSalary() > 10000).forEach(System.out::println);

        System.out.println("----------  截流, 只查询前x个元素  -----------");
        // 截流, 只查询前x个元素
        list.stream().limit(5).forEach(System.out::println);

        System.out.println("----------  跳过, 跳过前x个元素  -----------");
        // 跳过, 跳过前x个元素
        list.stream().skip(5).forEach(System.out::println);

        System.out.println("----------  去重  -----------");
        // 去重
        list.stream().distinct().forEach(System.out::println);
    }


    /**
     *  Stream的中间操作: 映射
     *
     * @author xgl
     * @date 2020年9月6日 17点21分
     **/
    public static void test5() {

        System.out.println("-----------------  批量转大写  --------------------------------");
        // 批量转大写
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map( s -> s.toUpperCase()).forEach(System.out::println);

        System.out.println("-----------------  获取 员工名字长度大于3  --------------------------------");
        // 获取 员工名字长度大于3
        List<Employee> employeeList = Employee.getEmployeeList();
        Stream<String> stringStream = employeeList.stream().map(Employee::getName).filter(name -> name.length() > 3);
        stringStream.forEach(System.out::println);
    }


    /**
     *  Stream的中间操作: 排序
     *
     * @author xgl
     * @date 22020年9月6日 17点34分
     **/
    public static void test6() {

        List<Integer> list = Arrays.asList(2, 4, 2, 1, 9, 8, 55, 44, 3, 25, 45, 78, 11, 46, 44, 22, 1, 9, 44, 65, 23, 9, 444, 52, 413, 69, 999, 12, -45, 2);
        List<Employee> employeeList = Employee.getEmployeeList();

        System.out.println("-----------------  自然排序  --------------------------------");
        // 自然排序
        list.stream().sorted().forEach(System.out::println);
        employeeList.stream().map(Employee::getSalary).sorted().forEach(System.out::println);

        System.out.println("-----------------  自定排序  --------------------------------");
        // 自定排序
        employeeList.stream().sorted( (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()) ).forEach(System.out::println);
        employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).forEach(System.out::println);

    }


    /**
     *  Stream的中间操作: 匹配与查找
     *
     * @author xgl
     * @date 2020年9月6日 17点48分
     **/
    public static void test7() {

        List<Employee> employeeList = Employee.getEmployeeList();

        System.out.println("-----------------  是否所有员工年龄大于18岁 allMatch --------------------------------");
        // 是否所有员工年龄大于18岁
        boolean b = employeeList.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(b);

        System.out.println("-----------------  是否有员工工资大于12 000 anyMatch  --------------------------------");
        // 是否有员工工资大于12 000
        boolean b1 = employeeList.stream().anyMatch(employee -> employee.getSalary() > 12000);
        System.out.println(b1);

        System.out.println("-----------------  是否 没有 员工的名字长度是2 noneMatch --------------------------------");
        // 是否有员工的名字长度是2
        boolean b2 = employeeList.stream().noneMatch(employee -> employee.getName().length() == 2);
        System.out.println(b2);

        System.out.println("-----------------  员工的名字长度是3的数量 filter --------------------------------");
        // 员工的名字长度是3的数量
        long count = employeeList.stream().filter(employee -> employee.getName().length() == 3).count();
        System.out.println(count);

        System.out.println("-----------------  返回 最高的工资 max(获取最右边的元素)  --------------------------------");
        // 返回 最高的工资
        Optional<Employee> max = employeeList.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max);
    }



    /**
     *  Stream的中间操作: 收集
     *
     * @author xgl
     * @date 2020年9月6日 18点20分
     **/
    public static void test8() {

        List<Employee> employeeList = Employee.getEmployeeList();

        System.out.println("-----------------  返回 List<Employee>, 条件是工资大于10 000  --------------------------------");
        // 返回 List<Employee>, 条件是工资大于10 000
        List<Employee> employeeList1 = employeeList.stream().filter(employee -> employee.getSalary() > 10000).collect(Collectors.toList());
        employeeList1.forEach(System.out::println);


        System.out.println("-----------------  返回 Set<Employee>, 条件是工资大于10 000  --------------------------------");
        // 返回 Set<Employee>, 条件是工资大于10 000
        Set<Employee> employeeSet = employeeList.stream().filter(employee -> employee.getSalary() > 10000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);

    }

}
