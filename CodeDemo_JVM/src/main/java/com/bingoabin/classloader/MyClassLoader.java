package com.bingoabin.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {
	// 要加载的class文件所在的目录
	private String classpath;

	public MyClassLoader(String classpath) {
		this.classpath = classpath;
	}

	/**
	 * 重写findClass类
	 *
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = this.findLoadedClass(name);
		FileChannel fileChannel = null;
		WritableByteChannel outChannel = null;

		if (null == clazz) {
			try {
				String classFile = getClassFile(name);

				FileInputStream fis = new FileInputStream(classFile);
				fileChannel = fis.getChannel();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				outChannel = Channels.newChannel(baos);
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

				while (true) {
					int i = fileChannel.read(buffer);
					if (i == 0 || i == -1) {
						break;
					}
					buffer.flip();
					outChannel.write(buffer);
					buffer.clear();
				}

				byte[] bytes = baos.toByteArray();
				clazz = defineClass(name, bytes, 0, bytes.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fileChannel != null) {
						fileChannel.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (outChannel != null) {
						outChannel.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return clazz;
	}

	private String getClassFile(String name) {
		return classpath + "/" + name.replace(".", "/") + ".class";
	}

	// 返回类的字节码
	private byte[] getDate(String className) throws IOException {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		String path = classpath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
		try {
			in = new FileInputStream(path);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[2048];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			return out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
		return null;
	}
}
