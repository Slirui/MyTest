/**
 * 
 */
package com.entity;

import java.util.Date;

/**
 * @author 分裂状态。
 *
 */
public class FileOperate {

	private String fileName;
	private String title;
	private Date date;
	private String icon;
	
	public FileOperate() {
		super();
	}

	public FileOperate(String fileName, String title, Date date, String icon) {
		super();
		this.fileName = fileName;
		this.title = title;
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
