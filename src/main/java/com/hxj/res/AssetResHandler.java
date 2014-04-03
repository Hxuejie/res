package com.hxj.res;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

public class AssetResHandler implements ResHandler<AssetFileDescriptor> {

	private static final AssetResHandler	INSTANCE	= new AssetResHandler();

	public static final AssetResHandler getInstance() {
		return INSTANCE;
	}

	private AssetManager	assetManager;

	/** 获得Asset资源管理
	 * @param context
	 * @return 获取成功返回true,否则返回false */
	public synchronized boolean get(Context context) {
		if (context == null) return false;
		if (assetManager != null) return true;
		assetManager = context.getResources().getAssets();
		return true;
	}

	@Override
	public AssetFileDescriptor find(String path) {
		if (assetManager == null) return null;
		try {
			return assetManager.openFd(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public InputStream getInputStream(String path) {
		if (assetManager == null) return null;
		try {
			return assetManager.open(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
