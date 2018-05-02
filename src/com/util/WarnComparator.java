/**
 * 
 */
package com.util;

import java.util.Comparator;

import com.entity.Warn;

/**
 * @author 分裂状态。
 *
 */
public class WarnComparator implements Comparator<Warn> {
	@Override
	public int compare(Warn o1, Warn o2) {
		if (o1.getWarndate().getTime() > o2.getWarndate().getTime()) {
			return 1;
		} else if (o1.getWarndate().getTime() == o2.getWarndate().getTime()) {
			return 0;
		} else {
			return -1;
		}
	}
}
