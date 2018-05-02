/**
 * 
 */
package com.service;

import java.util.Date;
import java.util.List;

/**
 * @author 分裂状态。
 *
 */
public interface RainType3Service {

	// 日雨量类型3 (累计雨量)
	public List<Object[]> getRainType3SumPreDesc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	// 日雨量类型3 (累计雨量)
	public List<Object[]> getRainType3SumPreAsc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	// 日雨量类型3 (每日雨量)
	public List<Object[]> getRainType3Desc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	// 日雨量类型3 (每日雨量)
	public List<Object[]> getRainType3Asc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);
}
