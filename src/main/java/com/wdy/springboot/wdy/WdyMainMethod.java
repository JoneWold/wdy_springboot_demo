package com.wdy.springboot.wdy;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.github.stuxuhai.jpinyin.PinyinHelper;
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


    public static void main(String[] args) throws Exception {
        testDozer();
        testPinYinStr();
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

    /**
     * 获取汉语拼音
     */
    private static void testPinYinStr() throws Exception {
        String pinyin = PinyinUtil.getPinyin("少时诵诗书", " ");
        String letter = PinyinUtil.getFirstLetter("少时诵诗书", "");
        String pinyin2 = PinyinHelper.getShortPinyin("少时诵诗书");
        System.out.println(pinyin);
        System.out.println(letter);
        System.out.println(pinyin2);
    }


}
