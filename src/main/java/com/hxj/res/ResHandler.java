package com.hxj.res;

import java.io.InputStream;

/** 资源处理接口
 * @author Hxuejie
 * @param <T> 资源类型 */
public interface ResHandler<T> {
	/** 查找资源
	 * @param path 资源路径
	 * @return 返回资源 */
	T find(String path);

	/** 获得资源输入流
	 * @param path 资源路径
	 * @return 没有找资源文件返回NULL */
	InputStream getInputStream(String path);

}
