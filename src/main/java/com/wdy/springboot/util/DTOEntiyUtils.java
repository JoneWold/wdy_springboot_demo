package com.wdy.springboot.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wgch
 * @Description 对象转换bean到dto或vo
 * @date 2020/06/18
 */
public class DTOEntiyUtils {

    private static Mapper mapper = new DozerBeanMapper();

    private static <D, E> E trans(D t, Class<E> clazz) {
        if (t == null) {
            return null;
        }
        return mapper.map(t, clazz);
    }

    public static <D, E> List<E> trans(D[] ts, Class<E> clazz) {
        List<E> es = new ArrayList<E>();
        if (ts == null) {
            return es;
        }


        for (D d : ts) {
            E e = (E) trans(d, clazz);
            if (e != null) {
                es.add(e);
            }
        }

        return es;
    }


    public static <D, E> List<E> trans(List<D> ts, Class<E> clazz) {
        List<E> es = new ArrayList<E>();
        if (ts == null) {
            return es;
        }

        for (D d : ts) {
            E e = (E) trans(d, clazz);
            if (e != null) {
                es.add(e);
            }

        }
        return es;
    }

}
