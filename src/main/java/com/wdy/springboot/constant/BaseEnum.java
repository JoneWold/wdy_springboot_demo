package com.wdy.springboot.constant;

/**
 * 主要用于值和名的对应和查询
 *
 * @author lhp
 * @creed Talk is cheap,show me the code
 * @date 2020年12月10日 14时35分
 */
public interface BaseEnum<T> {
    /**
     * 获取值
     *
     * @return T
     */
    T getKey();

    /**
     * 获取名称
     *
     * @return String
     */
    String getValue();
}
