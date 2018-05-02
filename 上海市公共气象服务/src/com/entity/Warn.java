/**
 * 
 */
package com.entity;

import java.util.Date;

import javax.persistence.ManyToOne;

/**
 * 
 * @author 分裂状态。
 *
 */
public class Warn {

	private String warnname;  // 预警名称
	private String warntitle; // 预警标题
	private Date warndate;    // 预警时间
	private String warnicon;  // 预警图标
	
	
	public Warn() {
		super();
	}

	public Warn(String warnname, String warntitle, Date warndate, String warnicon) {
		super();
		this.warnname = warnname;
		this.warntitle = warntitle;
		this.warndate = warndate;
		this.warnicon = warnicon;
	}

	public String getWarnname() {
		return warnname;
	}

	public void setWarnname(String warnname) {
		this.warnname = warnname;
	}

	public String getWarntitle() {
		return warntitle;
	}

	public void setWarntitle(String warntitle) {
		this.warntitle = warntitle;
	}

	public Date getWarndate() {
		return warndate;
	}

	public void setWarndate(Date warndate) {
		this.warndate = warndate;
	}

	public String getWarnicon() {
		return warnicon;
	}

	public void setWarnicon(String warnicon) {
		this.warnicon = warnicon;
	}

}
