package com.wdy.springboot.wdy;

import com.wdy.springboot.constant.PublicConstant;
import com.wdy.springboot.util.DTOEntiyUtils;
import com.wdy.springboot.vo.response.BlogVo;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wgch
 * @date 2020/6/18
 */
public class WdyMainMethod {


    public static void main(String[] args) {
        testDozer();

    }

    /**
     * 对象转换bean到dto或vo
     */
    private static void testDozer() {
        List<BlogVo> blogs = new ArrayList<>();
        for (int i = 0; i < PublicConstant.TEN; i++) {
            BlogVo blog = new BlogVo();
            blog.setId(i);
            blog.setTitle("标题" + i);
            blog.setContent("blog内容" + i);
            blogs.add(blog);
        }
        List<BlogVo> voList = DTOEntiyUtils.trans(blogs, BlogVo.class);
        System.err.println(voList);
    }
}
