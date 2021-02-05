package com.wdy.crawl.tjsj;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.wdy.crawl.page.Page;
import com.wdy.crawl.page.PageParserTool;
import com.wdy.crawl.page.RequestAndResponseTool;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政区划数据
 *
 * @author wgch
 * @date 2021/2/3
 */
public class ReadXzQh {


    public static void main(String[] args) {
        String visitUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html";
        Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);
        readCity(page);
        String countyUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/65/6501.html";
        Page page1 = RequestAndResponseTool.sendRequstAndGetResponse(countyUrl);
        readCounty(page1);
    }

    public static Map<String, String> readCity(Page page) {
        Map<String, String> map = new HashMap<>();
        //省市
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

    public static Map<String, String> readCounty(Page page) {
        Map<String, String> map = new HashMap<>();
        //区县
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
}
