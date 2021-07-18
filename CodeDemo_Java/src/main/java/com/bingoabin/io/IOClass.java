package com.bingoabin.io;

import java.io.*;

/**
 * @author xubin03
 * @date 2021/5/19 8:53 下午
 */
public class IOClass {
	//字节流：图片复制
	public void copyFile(String srcPath, String destPath) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			File srcFile = new File(srcPath);
			File destFile = new File(destPath);

			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);

			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//缓冲流：【字节流、处理流】图片复制
	public void copyFileWithBuffered(String srcPath, String destPath) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File srcFile = new File(srcPath);
			File destFile = new File(destPath);

			FileInputStream fis = new FileInputStream((srcFile));
			FileOutputStream fos = new FileOutputStream(destFile);

			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);

			byte[] buffer = new byte[1024];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略.
			// fos.close();
			// fis.close();
		}
	}

	//字符流：文件复制
	public void testFileReaderFileWriter() {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			File srcFile = new File("hello.txt");
			File destFile = new File("hello2.txt");

			fr = new FileReader(srcFile);
			fw = new FileWriter(destFile);

			char[] cbuf = new char[5];
			int len;
			while ((len = fr.read(cbuf)) != -1) {
				fw.write(cbuf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//缓冲流：【字符流、处理流】 文本文件内容复制
	public void testBufferedReaderBufferedWriter() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(new File("dbcp.txt")));
			bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));
			//读写操作
			//方式一：使用char[]数组
			// char[] cbuf = new char[1024];
			// int len;
			// while ((len = br.read(cbuf)) != -1) {
			//     bw.write(cbuf, 0, len);
			//     bw.flush();
			// }

			//方式二：使用String
			String data;
			while ((data = br.readLine()) != null) {
				bw.write(data);//data中不包含换行符
				bw.newLine();//提供换行的操作
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
