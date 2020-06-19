package com.wdy.springboot.biz.wdy;

import com.wdy.springboot.entity.Blog;
import com.wdy.springboot.util.DTOEntiyUtils;
import com.wdy.springboot.vo.BlogVo;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wgch
 * @date 2020/6/18
 */
public class WdyMain {


    public static void main(String[] args) {
        testDozer();

    }

    /**
     * 对象转换bean到dto或vo
     */
    private static void testDozer() {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Blog blog = new Blog();
            blog.setId(i);
            blog.setTitle("标题" + i);
            blog.setContent("blog内容" + i);
            blogs.add(blog);
        }
        List<BlogVo> voList = DTOEntiyUtils.trans(blogs, BlogVo.class);
        System.err.println(voList);
    }
}
