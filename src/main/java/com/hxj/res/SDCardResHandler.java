package com.hxj.res;

import java.io.File;

import android.os.Environment;

/** SD卡资源操作类
 * @author Hxuejie */
public class SDCardResHandler extends DefResHandler {

	@Override
	public synchronized void setRootPath(String path) {
		String esDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		super.setRootPath(esDir + File.separator + path);
	}

}
