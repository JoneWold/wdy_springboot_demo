package com.wdy.crawl.tjsj;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.wdy.crawl.page.PageData;
import com.wdy.crawl.page.PageParserTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 行政区划数据获取
 *
 * @author wgch
 * @date 2021/2/3
 */
public class ReadXzQhByHtml {


    public static void main(String[] args) {
//        String visitUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html";
//        Page page = HttpClientTool.getPageBySendUrl(visitUrl);
//        readCity(page);
//        //区县
//        String countyUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/50/5001.html";
//        Page countyPage = HttpClientTool.getPageBySendUrl(countyUrl);
//        readCounty(countyPage);
//
//        readA(countyPage);
        //乡镇街道
        String towntrUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/50/02/500235.html";
        readTowntrByUrl(towntrUrl);
    }

    /**
     * 省市
     */
    public static Map<String, String> readCity(PageData page) {
        Map<String, String> map = new LinkedHashMap<>();
        Elements elements = PageParserTool.selectByClass(page, "provincetr");
        elements.addAll(PageParserTool.selectByClass(page, "citytr"));
        for (Element element : elements) {
            List<Node> nodes = element.childNodes();
            for (Node node : nodes) {
                Node childNode = node.childNode(0);
                String href = childNode.attr("href");
                String text = ((Element) childNode).text();
                if (StrUtil.isNotBlank(href)) {
                    map.put(href.replace(".html", ""), text);
                }
            }
        }
        return map;
    }

    /**
     * 区县
     */
    public static Map<String, String> readCounty(PageData page) {
        Map<String, String> map = new LinkedHashMap<>();
        Elements elements = PageParserTool.selectByClass(page, "countytr");
        for (Element element : elements) {
            List<Node> nodes = element.childNodes();
            if (CollectionUtil.isNotEmpty(nodes)) {
                Element node0 = (Element) nodes.get(0);
                Element node1 = (Element) nodes.get(1);
                String code = node0.text().substring(0, 6);
                map.put(code, node1.text());
            }
        }
        return map;
    }

    /**
     * 乡镇街道
     */
    public static Map<String, String> readTowntr(PageData page) {
        Map<String, String> map = new LinkedHashMap<>();
        Elements towns = PageParserTool.selectByClass(page, "towntr");
        for (Element element : towns) {
            List<Node> nodes = element.childNodes();
            if (CollUtil.isNotEmpty(nodes)) {
                Element node0 = (Element) nodes.get(0);
                Element node1 = (Element) nodes.get(1);
                String code = node0.text().substring(0, 9);
                map.put(code, node1.text());
            }
        }
        return map;
    }

    private static void readTowntrByUrl(String url) {
        Map<String, String> map = new LinkedHashMap<>();
        ThreadUtil.sleep(1, TimeUnit.SECONDS);
        String string = HttpUtil.get(url);
        //获取网页数据文档
        Document doc = Jsoup.parse(string, url);
        Elements towns = doc.getElementsByClass("towntr");
        for (Element element : towns) {
            List<Node> nodes = element.childNodes();
            if (CollUtil.isNotEmpty(nodes)) {
                Element node0 = (Element) nodes.get(0);
                String code = node0.text().substring(0, 9);
                Element node1 = (Element) nodes.get(1);
                map.put(code, node1.text());
            }
        }
        System.out.println(map);
    }


    public static void readA(PageData page) {
        Map<String, String> map = new LinkedHashMap<>();
        Elements elements = PageParserTool.select(page, "a");
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                String href = element.attr("href");
                String node = element.childNodes().get(0).outerHtml();
                if (Character.isDigit(href.charAt(0))) {
                    map.put(href.replace(".html", ""), node);
                }
            }
        }
        System.out.println(map);
    }

}
