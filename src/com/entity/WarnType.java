/**
 * 
 */
package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

/**
 * @author 分裂状态。
 *
 */
public class WarnType {

	private String type;

	@OneToMany
	private Set<Warn> warns = new HashSet<Warn>();

	public WarnType() {
		super();
	}

	public WarnType(String type) {
		super();
		this.type = type;
	}

	public WarnType(String type, Set<Warn> warns) {
		super();
		this.type = type;
		this.warns = warns;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Warn> getWarns() {
		return warns;
	}

	public void setWarns(Set<Warn> warns) {
		this.warns = warns;
	}

}
