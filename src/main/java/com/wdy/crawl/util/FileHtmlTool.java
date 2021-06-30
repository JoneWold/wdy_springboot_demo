package com.wdy.crawl.util;


import com.wdy.crawl.page.PageData;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 本类主要是 下载那些已经访问过的文件
 *
 * @author wgch
 */
public class FileHtmlTool {

    private static String dirPath;


    /**
     * getMethod.getResponseHeader("Content-Type").getValue()
     * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
     */
    private static String getFileNameByUrl(String url, String contentType) {
        //去除 http://
        url = url.substring(7);
        //text/html 类型
        if (contentType.contains("html")) {
            url = url.replaceAll("[\\?/:*|<>\"]", "_").replaceAll(".html", "") + ".html";
            return url;
        }
        //如 application/pdf 类型
        else {
            String path = url.replaceAll("[\\?/:*|<>\"]", "_") + "." +
                    contentType.substring(contentType.lastIndexOf("/") + 1);
            return path;
        }
    }

    /**
     * 生成目录
     */
    private static void mkdir() {
        if (dirPath == null) {
            dirPath = Class.class.getClass().getResource("/").getPath() + "temp\\";
        }
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    /**
     * 保存网页字节数组到本地文件，filePath 为要保存的文件的相对地址
     */
    public static void saveToLocal(PageData page) {
        mkdir();
        String fileName = getFileNameByUrl(page.getUrl(), page.getContentType());
        String filePath = dirPath + fileName;
        byte[] data = new String(page.getContent(), StandardCharsets.UTF_8).getBytes();
        try {
            //Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
            //OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(filePath)), StandardCharsets.UTF_8);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i = 0; i < data.length; i++) {
                out.write(data[i]);
            }
            out.flush();
            out.close();
            System.out.println("文件：" + fileName + "已经被存储在" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
