package com.wdy.crawl.page;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * html文件解析
 *
 * @author wgch
 */
public class PageParserTool {


    /**
     * 通过选择器来选取页面的
     */
    public static Elements select(Page page, String cssSelector) {
        return page.getDoc().select(cssSelector);
    }


    public static Elements selectByClass(Page page, String cssSelector) {
        return page.getDoc().getElementsByClass(cssSelector);
    }

    /**
     * 通过css选择器来得到指定元素
     */
    public static Element select(Page page, String cssSelector, int index) {
        Elements elements = select(page, cssSelector);
        int realIndex = index;
        if (index < 0) {
            realIndex = elements.size() + index;
        }
        return elements.get(realIndex);
    }


    /**
     * 获取满足选择器的元素中的链接 选择器cssSelector必须定位到具体的超链接
     * 例如我们想抽取id为content的div中的所有超链接，这里
     * 就要将cssSelector定义为div[id=content] a
     * 放入set 中 防止重复；
     */
    public static Set<String> getLinks(Page page, String startUrl, String cssSelector) {
        Set<String> links = new HashSet<String>();
        Elements es = selectByClass(page, cssSelector);
        for (Element element : es) {
            Elements elements = element.getElementsByAttribute("href");
            for (Element sunE : elements) {
                String href = sunE.attr("href");
                String node = sunE.childNodes().get(0).outerHtml();
                if (Character.isDigit(href.charAt(0)) && !Character.isDigit(node.charAt(0))
                        && href.replace(".html", "").length() <= 7) {
                    links.add(startUrl + href);
                }
            }
//            if (element.hasAttr("href")) {
//                links.add(element.attr("abs:href"));
//            } else if (element.hasAttr("src")) {
//                links.add(element.attr("abs:src"));
//            }
        }
        return links;
    }


    /**
     * 获取网页中满足指定css选择器的所有元素的指定属性的集合
     * 例如通过getAttrs("img[src]","abs:src")可获取网页中所有图片的链接
     */
    public static ArrayList<String> getAttrs(Page page, String cssSelector, String attrName) {
        ArrayList<String> result = new ArrayList<String>();
        Elements elements = select(page, cssSelector);
        for (Element ele : elements) {
            if (ele.hasAttr(attrName)) {
                result.add(ele.attr(attrName));
            }
        }
        return result;
    }
}
