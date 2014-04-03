package com.hxj.res;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.text.TextUtils;

/** 文件资源操作类
 * @author Hxuejie */
public class FileResHandler implements ResHandler<File> {
	private static FileResHandler	instance	= new FileResHandler();

	public static final FileResHandler getInstance() {
		return instance;
	}

	private String	PATH	= File.separator;

	FileResHandler() {}

	/** 设置资源跟路径
	 * @param path */
	public synchronized void setRootPath(String path) {
		if (TextUtils.isEmpty(path)) {
			path = File.separator;
		}
		else {
			if (!path.endsWith(File.separator)) {
				path = path + File.separator;
			}
		}
		PATH = path;
	}

	@Override
	public File find(String path) {
		File file = new File(PATH + path);
		return file.exists() ? file : null;
	}

	@Override
	public InputStream getInputStream(String path) {
		File file = new File(PATH + path);
		try {
			return file.exists() && file.isFile() && file.canRead() ? new FileInputStream(file) : null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 删除文件
	 * @param path 路径
	 * @return 文件不存在返回false,删除失败返回false,删除成功返回true */
	public boolean delete(String path) {
		File file = new File(PATH + path);
		if (!file.exists()) return false;
		return file.delete();
	}

	/** 创建文件
	 * @param path 文件路径
	 * @return 创建成功返回File对象，否则返回NULL */
	public File createFile(String path) {
		File file = new File(PATH + path);
		if (file.exists()) return file;
		if (file.isDirectory()) {
			if (file.mkdirs()) return file;
			return null;
		}
		File dir = file.getParentFile();
		if (!dir.exists()) dir.mkdirs();
		try {
			file.createNewFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 创建文件，并写入
	 * @param path 文件路径
	 * @param is 输入流
	 * @return */
	public File createFile(String path, InputStream is) {
		File file = new File(PATH + path);
		if (file.isDirectory()) return null;
		if (file.exists()) file.delete();
		File dir = file.getParentFile();
		if (!dir.exists()) dir.mkdirs();
		try {
			file.createNewFile();
			writeFile(is, file);
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeFile(InputStream is, File file) throws IOException {
		FileOutputStream fos = null;
		try {
			writeFile(is, fos = new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	private void writeFile(InputStream is, OutputStream os) throws IOException {
		byte[] buff = new byte[512];
		int len;
		while ((len = is.read(buff)) != -1) {
			os.write(buff, 0, len);
		}
	}

}
