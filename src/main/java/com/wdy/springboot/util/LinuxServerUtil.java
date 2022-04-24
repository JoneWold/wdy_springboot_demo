package com.wdy.springboot.util;

import ch.ethz.ssh2.Connection;

import java.util.ResourceBundle;

/**
 * Linux服务器连接
 *
 * @author wgch
 * @date 2022/4/23
 */
public class LinuxServerUtil {
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


    public static synchronized Connection getSSHConnection() throws Exception {
        Connection connection = new Connection(host, 22);
        connection.connect();
        //验证用户密码
        boolean b = connection.authenticateWithPassword(userName, password);
//        File file = new File(privateKeyFile);
//        connection.authenticateWithPublicKey(userName, file, password);
        return b ? connection : null;
    }


}
