/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ImageDataDao;
import com.service.ImageDataService;

/**
 * 
 * @author 分裂状态。
 *
 */
@Service
public class ImageDataServiceImpl implements ImageDataService {

	@Autowired
	private ImageDataDao imagedatadao;
	
	public List<Object[]> getImageInfo(){
		return imagedatadao.getImageInfo();
	}
}
