package com.pc.project.crud.utils;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class UUIDUtils {
	/**
	 * 生成唯一的ID
	 * 
	 * @return 唯一ID的字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
