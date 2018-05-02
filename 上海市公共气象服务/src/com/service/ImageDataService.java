/**
 * 
 */
package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entity.ImageData;

/**
 * 
 * @author 分裂状态。
 *
 */
public interface ImageDataService {

	public List<Object[]> getImageInfo();
}
