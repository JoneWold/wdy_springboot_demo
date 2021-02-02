package com.wdy.springboot.util;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV
 *
 * @author wgch
 * @date 2020/06/18
 */
public class CsvUtils {

    /**
     * 导入csv
     *
     * @param file csv文件(路径+文件)
     * @return
     * @Title: importCsv
     */
    public static List<String> importCsv(File file, String charset) {
        List<String> dataList = new ArrayList<>();

        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream, charset));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }


    /**
     * 导出 csv
     *
     * @param file     csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     * @Title: exportCsv
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public static boolean exportCsv(File file, List<String> dataList) {
        boolean isSucess = false;

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            byte[] uft8bom = {(byte) 0xef, (byte) 0xbb, (byte) 0xbf};
            //utf-8 with bom
            out.write(uft8bom);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSucess;
    }
}
