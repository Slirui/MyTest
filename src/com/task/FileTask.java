/**
 * 
 */
package com.task;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 分裂状态。
 *
 */
@Component("FileTask")
public class FileTask {

//	@Scheduled(cron = "0/2 * * * * ?")
//	public void copyFile() {
//		String path = System.getProperty("user.dir");
//		path = path + "\\doc";
//		System.out.println(path);
//		File file1 = new File("E:\\典型案例");
//		File file2 = new File(path);
//		try {
//			FileUtils.copyDirectory(file1, file2);
//			System.out.println("复制文件");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
