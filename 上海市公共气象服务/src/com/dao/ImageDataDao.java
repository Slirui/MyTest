/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entity.ImageData;

/**
 * 
 * @author 分裂状态。
 *
 */
public interface ImageDataDao extends GenericRepository<ImageData, Integer> {

	@Query(value = "select name,icon from ImageData", nativeQuery = true)
	public List<Object[]> getImageInfo();
}
