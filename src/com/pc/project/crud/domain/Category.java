package com.pc.project.crud.domain;

/**
 * 目录Bean
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class Category {
	private String cid;
	private String cname;

	public Category() {

	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
}
