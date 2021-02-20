package com.wdy.springboot.scheduled.file;

import cn.hutool.core.io.FileUtil;
import com.wdy.springboot.constant.PublicConstant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * 清理多余下载文件
 *
 * @author wgch
 * @date 2021/2/20
 */
@Component
public class FileDelete {

    /**
     * 执行结束后7天跑一次
     */
    @Scheduled(fixedDelay = 7 * 24 * 60 * 60 * 1000)
    public void run() {
        String basePath = null;
        try {
            basePath = ResourceUtils.getURL(PublicConstant.CLASSPATH).getPath() + PublicConstant.STATIC + PublicConstant.BASE_DOWNLOAD_PATH;
            FileUtil.del(basePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
