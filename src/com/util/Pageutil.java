/**
 * 
 */
package com.util;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author 分裂状态。
 *
 */
public class Pageutil {

	public static Pageable getPageable(int start, int len) {
		Pageable page = new PageRequest(start / len, len);
		return page;
	}

	public static Pageable getPageable(int start, int len, Sort sort) {
		Pageable page = new PageRequest(start / len, len, sort);
		return page;
	}

}
