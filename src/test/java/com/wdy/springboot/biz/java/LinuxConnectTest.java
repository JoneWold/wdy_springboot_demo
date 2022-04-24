package com.wdy.springboot.biz.java;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.wdy.springboot.util.LinuxServerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * java连接Linux服务器
 *
 * @author wgch
 * @date 2022/4/22
 */
public class LinuxConnectTest {


    public static void main(String[] args) throws Exception {
        testLinuxOpt();

    }


    /**
     * Linux操作测试
     */
    private static void testLinuxOpt() throws IOException {
        Connection conn = LinuxServerUtil.getSSHConnection();
        //获取会话信息
        Session session = conn.openSession();
        //执行命令
        session.execCommand("uname -a && uptime && who && cd /home/html; ls;");
        // 消费所有输入流
        String inStr = LinuxServerUtil.consumeInputStream(session.getStdout());
        String errStr = LinuxServerUtil.consumeInputStream(session.getStderr());
        System.out.println("inStr ......" + inStr);
        System.err.println("errStr ......" + errStr);
        System.out.println("..." + session.getExitStatus());
        session.close();
        conn.close();
    }


    /**
     * 获取指定log文件的指定关键字的日志信息
     *
     * @param connection SSH的连接
     * @param logFile    需要读取的log文件 全路径 /opt/dubbo_server/risk-server-provider-xjd/logs/server-info.log
     * @param key        关键字     例如  节点3150调用策略引擎开始
     * @param timeKey    時間关键字   例如 09-21 14:42:1
     * @return 返回需要的日志信息行 如果有多天日志信息满足条件，只返回最后一天日志信息
     * @throws IOException
     */
    public static String getLogInfo(Connection connection, String logFile, String key, String timeKey) throws IOException {
        String cmd = "tail -1000 " + logFile + " | grep '" + key + "'" + " | grep '" + timeKey + "'";
        System.out.println("====cmd===" + cmd);
        //"tail -1000 /opt/dubbo_server/risk-server-provider-xjd/logs/server-info.log | grep '节点3150调用策略引擎开始，' | grep '09-21 14:42:1'";
        Session session = connection.openSession();
        session.execCommand(cmd);//执行shell命令
        //处理获取的shell命令的输出信息
        InputStream stdout = session.getStdout();
        InputStreamReader inputStreamReader = new InputStreamReader(stdout);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String s = bufferedReader.readLine();
        String lastLineLog = null;
        System.out.println("==========以下是获取日志的全部信息============");

        while (s != null) {
            lastLineLog = s;
            System.out.println(s);
            s = bufferedReader.readLine();
        }

        System.out.println("==========以上是获取日志的全部信息============");
        System.out.println("========以下是日志的最后一行数据=======");
        System.out.println(lastLineLog);

        //最后关闭session资源
        session.close();
        return lastLineLog;
    }


}
