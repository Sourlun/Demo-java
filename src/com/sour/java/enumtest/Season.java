package com.sour.java.enumtest;

public enum Season {

    // 1,提供当前枚举类的对象, 多个对象用","隔开, 末尾用";"结束
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");

    // 2,声明Season对象的属性:private final 修饰
    private final String seasonName;
    private final String seasonDesc;

    // 3, 私有化类的构造器,开始对象属性的赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 4, 获取枚举类对象的属性

    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
