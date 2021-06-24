package com.wdy.crawl.page;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import com.wdy.crawl.util.CharsetDetector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * page
 * 1: 保存获取到的响应的相关内容;
 *
 * @author wgch
 */
public class Page {

    private byte[] content;
    /**
     * 网页源码字符串
     */
    private String html;
    /**
     * 网页Dom文档
     */
    private Document doc;
    /**
     * 字符编码
     */
    private String charset;
    /**
     * url路径
     */
    private String url;
    /**
     * 内容类型
     */
    private String contentType;

    public Page(byte[] content, String url, String contentType) {
        this.content = content;
        this.url = url;
        this.contentType = contentType;
    }

    /**
     * 返回网页的源码字符串
     *
     * @return 网页的源码字符串
     */
    public String getHtml() {
        if (html != null) {
            return html;
        }
        if (content == null) {
            return null;
        }
        if (charset == null) {
            // 根据内容来猜测 字符编码
            charset = CharsetDetector.guessEncoding(content);
        }
        try {
            //返回的html数据乱码
            this.html = new String(content, charset);
            return html;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 得到文档
     */
    public Document getDoc() {
        if (doc != null) {
            return doc;
        }
        try {
            ThreadUtil.sleep(1, TimeUnit.SECONDS);
            String string = HttpUtil.get(url);
//            this.doc = Jsoup.parse(string, url);
            this.doc = Jsoup.parse(this.getHtml(), url);
            return doc;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public String getCharset() {
        return charset;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getContent() {
        return content;
    }
}
