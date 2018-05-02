/**
 * 
 */
package com.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entity.ImageResult;
import com.google.gson.Gson;
import com.service.ImageDataService;



/**
 * @author 分裂状态。
 *
 */
@Component("ImageData")
public class ImageData {

	@Autowired
	private ImageDataService imagedataservice;

	//@Scheduled(cron = "0/15 * * * * ?")
	public void getImageDataInfo() {
		List<Object[]> list = imagedataservice.getImageInfo();
		List<ImageResult> img = new ArrayList<ImageResult>();
		for (int i = 0; i < list.size(); i++) {
			ImageResult id  = new ImageResult();
			id.setName(list.get(i)[0].toString());
			id.setIcon(list.get(i)[1].toString());
			img.add(id);
			//System.out.println(list.get(i)[0] + "\t" + list.get(i)[1]);
		}
		String json = new Gson().toJson(img);
		//JSONArray ja = new JSONArray(img);
		System.out.println("json：" + json);
	}
}
