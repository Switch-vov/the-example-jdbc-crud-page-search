package com.pc.project.crud.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页Bean
 * 
 * @author Switch
 * @data 2016年10月20日
 * @version V1.0
 */
public class PageBean<T> implements Serializable {
	/**
	 * 当前页数
	 */
	private Long pageNumber;
	/**
	 * 总页数
	 */
	private Long totalPageNumber;
	/**
	 * 每页记录数
	 */
	private Long pageSize;
	/**
	 * 总记录条数
	 */
	private Long totalRecordNumber;
	/**
	 * 起始索引下标
	 */
	private Long startIndex;

	/**
	 * 分页查询出来的信息
	 */
	private List<T> pageContent;

	/**
	 * 必须要传入当前页数，每页记录数，总记录数，才能使用
	 * 
	 * @param pageNumber
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @param totalRecordNumber
	 *            总记录数
	 */
	public PageBean(Long pageNumber, Long pageSize, Long totalRecordNumber) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRecordNumber = totalRecordNumber;
		this.totalPageNumber = (this.totalRecordNumber + this.pageSize - 1) / this.pageSize;
		this.startIndex = (this.pageNumber - 1) * this.pageSize;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(Long totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalRecordNumber() {
		return totalRecordNumber;
	}

	public void setTotalRecordNumber(Long totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getPageContent() {
		return pageContent;
	}

	public void setPageContent(List<T> pageContent) {
		this.pageContent = pageContent;
	}

	@Override
	public String toString() {
		return "PageBean [pageNumber=" + pageNumber + ", totalPageNumber=" + totalPageNumber + ", pageSize=" + pageSize
				+ ", totalRecordNumber=" + totalRecordNumber + ", startIndex=" + startIndex + ", pageContent="
				+ pageContent + "]";
	}
}
