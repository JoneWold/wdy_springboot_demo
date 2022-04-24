package com.wdy.springboot.util;

import ch.ethz.ssh2.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Linux服务器连接
 *
 * @author wgch
 * @date 2022/4/23
 */
public class LinuxServerUtil {
    private static final Logger log = LoggerFactory.getLogger(LinuxServerUtil.class);
    private static final String host, userName, password, privateKeyFile;

    static {
        final ResourceBundle rb = ResourceBundle.getBundle("server");
        //服务器ip地址
        host = rb.getString("hostName");
        //端口号 默认的
        int port = 22;
        //用户名
        userName = rb.getString("userName");
        //密码
        password = rb.getString("password");
        //密钥文件 与服务器公钥对应的私钥文件  String pubkeypath = "src/main/resources/sshkey/id_rsa";
        privateKeyFile = " ";
    }


    /**
     * 连接服务器
     */
    public static synchronized Connection getSSHConnection() {
        Connection conn = new Connection(host, 22);
        try {
            conn.connect();
            //验证用户密码
            boolean b = conn.authenticateWithPassword(userName, password);
//        File file = new File(privateKeyFile);
//        connection.authenticateWithPublicKey(userName, file, password);
            if (!b) {
                throw new IOException("connection is not authenticated！");
            }
        } catch (Exception e) {
            log.error("连接服务端异常...", e);
        }
        return conn;
    }

    /**
     * 消费所有输入流
     */
    public static String consumeInputStream(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        try {
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                sb.append(" ").append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
