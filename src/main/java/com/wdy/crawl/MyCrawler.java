package com.wdy.crawl;

import cn.hutool.core.io.FileUtil;
import com.wdy.crawl.link.LinkFilter;
import com.wdy.crawl.link.Links;
import com.wdy.crawl.page.Page;
import com.wdy.crawl.page.PageParserTool;
import com.wdy.crawl.page.RequestAndResponseTool;
import com.wdy.crawl.tjsj.CodeValueVo;
import com.wdy.crawl.util.FileTool;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wgch
 * @date 2021/02/03
 */
public class MyCrawler {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html");
        }};
        MyCrawler myCrawler = new MyCrawler();
        myCrawler.crawling(list);

//        getData();
    }

    public static void getData() {
        String xz = FileUtil.readUtf8String("D:\\wdy\\wdy_springboot_demo\\src\\main\\java\\com\\wdy\\crawl\\tjsj\\data.txt");
        List<CodeValueVo> voList = new ArrayList<>();
        for (String str : xz.split(",")) {
            str = str.replace(" ", "");
            String code = "";
            if (str.contains("/")) {
                code = str.substring(str.indexOf("/") + 1, str.indexOf("="));
            } else {
                code = str.substring(0, str.indexOf("="));
            }
            String name = str.substring(str.indexOf("=") + 1);
            String parentCode = "";
            if (code.length() == 2) {
                parentCode = "-1";
            } else if (code.length() == 4) {
                parentCode = code.substring(0, 2);
            } else if (code.length() == 6) {
                parentCode = code.substring(0, 4);
            }
            // 组装结果集
            CodeValueVo codeValueVo = new CodeValueVo();
            codeValueVo.setCodeValue(code);
            codeValueVo.setCodeName(name);
            codeValueVo.setParentCode(parentCode);
            voList.add(codeValueVo);
        }
        int id = 21103;
        for (CodeValueVo valueVo : voList.stream().sorted(Comparator.comparing(CodeValueVo::getCodeValue)).collect(Collectors.toList())) {
            String codeValue = valueVo.getCodeValue();
            String codeName = valueVo.getCodeName();
            String parentCode = valueVo.getParentCode();

            System.out.println("INSERT INTO \"code_value\"(\"id_\", \"code_type_\", \"code_value_\", \"code_name_\", \"code_value_parent_\") " +
                    "VALUES (" + id + ", 'XZQH', '" + codeValue + "', '" + codeName + "', '" + parentCode + "');");
            id++;
        }

    }


    /**
     * 抓取过程
     */
    public void crawling(List<String> seeds) {
        Map<String, String> map = new HashMap<>();
        //初始化 URL 队列
        this.initCrawlerWithSeeds(seeds);
        //定义过滤器，提取以 http://www.baidu.com 开头的链接
        String startUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";
        LinkFilter filter = url -> url.startsWith(startUrl);
        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum() <= 1000) {
            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null) {
                continue;
            }
            //根据URL得到page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);
            //对page进行处理： 访问DOM的某个标签
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
            //将保存文件
            FileTool.saveToLocal(page);
            //将已经访问过的链接放入已访问的链接中；
            Links.addVisitedUrlSet(visitUrl);
            //得到超链接
            Set<String> links = PageParserTool.getLinks(page, startUrl, "provincetr");
            links.addAll(PageParserTool.getLinks(page, startUrl, "citytr"));
            links.addAll(PageParserTool.getLinks(page, startUrl, "countytr"));
            for (String link : links) {
                Links.addUnvisitedUrlQueue(link);
                System.err.println("新增爬取路径: " + link);
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println(map);
    }

    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     */
    private void initCrawlerWithSeeds(List<String> seeds) {
        seeds.forEach(Links::addUnvisitedUrlQueue);
    }


}
