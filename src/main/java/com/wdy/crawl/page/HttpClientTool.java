package com.wdy.crawl.page;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;


/**
 * http请求
 *
 * @author wgch
 */
public class HttpClientTool {

    /**
     * 发送请求并获取响应数据
     *
     * @param url 请求路径
     * @return 数据集
     */
    public static Page getPageBySendUrl(String url) {
        Page page = null;
        // 1.生成 HttpClinet 对象并设置参数
        HttpClient httpClient = new HttpClient();
        // 设置 HTTP 连接超时 5s
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        // 2.生成 GetMethod 对象并设置参数
        GetMethod getMethod = new GetMethod(url);
        // 设置 get 请求超时 5s
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 设置请求重试处理
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 3.执行 HTTP GET 请求
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            // 判断访问的状态码
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
            // 4.处理 HTTP 响应内容
            byte[] responseBody = getMethod.getResponseBody();

            // 得到当前返回类型
            String contentType = getMethod.getResponseHeader("Content-Type").getValue();
            String contentEncoding = getMethod.getResponseHeader("Content-Encoding").getValue();
            // 建立gzip解压工作流
            if (StrUtil.isNotEmpty(contentEncoding) && contentEncoding.contains("gzip")) {
//                GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(responseBody));
                GZIPInputStream gzip = new GZIPInputStream(getMethod.getResponseBodyAsStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(gzip, "gbk"));
                StringBuilder sb = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
                br.close();
                gzip.close();
                page = new Page(sb.toString().getBytes(), url, contentType);
            } else {
                //封装成为页面
                page = new Page(responseBody, url, contentType);
            }
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (Exception e) {
            // 发生网络异常
            e.printStackTrace();
        } finally {
            // 释放连接
            getMethod.releaseConnection();
        }
        return page;
    }


}
