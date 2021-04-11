package com.sour.java.nio;

import java.nio.IntBuffer;

/**
 * Buffer基础使用
 *
 * @author xgl
 * @date 2021/3/21 13:41
 **/
public class BasicBuffer {
    public static void main(String[] args) {

        // 举例说明buffer的使用 (简单说明)
        // 创建一个buffer, 大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 向buffer存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put( i * 2 );
        }

        // 读取数据
        // 将buffer转换 (读写切换)
        intBuffer.flip();
        while ( intBuffer.hasRemaining() ) {
            // get(): 指针后移, 前提要flip()
            System.out.println(intBuffer.get());
        }


    }
}
