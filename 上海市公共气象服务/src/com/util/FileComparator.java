/**
 * 
 */
package com.util;

import java.util.Comparator;

import com.entity.FileOperate;

/**
 * @author 分裂状态。
 *
 */
public class FileComparator implements Comparator<FileOperate> {

	@Override
	public int compare(FileOperate o1, FileOperate o2) {
		if (o1.getDate().getTime() > o2.getDate().getTime()) {
			return 1;
		} else if (o1.getDate().getTime() == o2.getDate().getTime()) {
			return 0;
		} else {
			return -1;
		}
	}

}
