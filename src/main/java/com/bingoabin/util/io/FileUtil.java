package com.bingoabin.util.io;

import java.io.*;

/**
 * @author xubin03
 * @date 2021/5/13 3:07 下午
 */
//File类的常见方法（参考一位大牛的，不小心把链接关了）
// 1.创建。
// boolean createNewFile(); //创建文件
// boolean mkdir();创建文件夹
// boolean mkdirs();创建多级文件夹。
//
// 2.删除。
// boolean delete();
// void deleteOnExit();在程序退出时删除文件。
//
// 3.判断。
// boolean canExcute(); 判断是否可执行
// boolean exists(); 文件事是否存在。
// isFile();文件
// isDirectory();文件夹
// isHidden();//java能得到文件中的隐藏文件但是对隐藏文件时不能访问的
// isAbsolute();//绝对路径即时不存在也能得到。
// 4.获取信息。
// getName();
// getPath();
// getParent();
//
// 4.三种文件创建方式：
// File file = new File("E:/...");//文件/文件夹路径对象
// File file = new File("..." ,""...);//父目录绝对路径 + 子目录名称
// File file = new File("...","...");//父目录File对象 + 子目录名称
//
// file.exists():判断文件/文件夹是否存在
// file.delete():删除文件/文件夹
// file.isDirectory():判读是否为目录
// file.isFile():判读是否为文件夹
// file.mkdir():创建文件夹(仅限一级目录)
// file.mkdirs():创建多及目录文件夹(包括但不限一级目录)
// file.createNewFile():创建文件
// file.getAbsolutePath():得到文件/文件夹的绝对路径
// file.getName():得到文件/文件夹的名字
// file.String():同样是得到文件/文件夹的绝对路径等于file.getAbsolutePath()
// file.getParent():得到父目录的绝对路径String 不可以调用可以调用mkdir()方法
//
// file.getParentFile()：得到父目录的绝对路径的返回值是File型可以调用mkdir()方法
//
// String[] gdir.list():得到目录的子目录\文件的名称(不是绝对路径)
// File[] dir.listFiles():得到目录的子目录\文件事是否存在。
public class FileUtil {

	public static void main(String[] args) {
		//读取file.properties中的值，按照这些值，在目录中创建对应的文件名
		readFileCreateFile("file.properties", "/Users/xubin03/Downloads/table");
		//读取大文件all.txt中的数据，形如dw.tablename,将这些数据按照dw作为文件名归类
		splitFileToSmallFile("/Users/xubin03/Downloads/all.txt", "/Users/xubin03/Downloads/table");
	}

	//循环遍历文件，将文件中的每一行作为文件名，创建文件
	public static void readFileCreateFile(String filename, String path) {
		String fileName = FileUtil.class.getClassLoader().getResource(filename).getPath();
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				createFile(path, tempStr);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	//实现在目标目录创建文件
	public static void createFile(String filepath, String filename) throws IOException {
		File testFile = new File(filepath + File.separator + filename);
		File fileParent = testFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
		String fileParentPath = testFile.getParent();//返回的是String类型
		if (!fileParent.exists()) {
			fileParent.mkdirs();// 能创建多级目录
		}
		if (!testFile.exists())
			testFile.createNewFile();//有路径才能创建文件
		// System.out.println(testFile);
		// String path = testFile.getPath();
		// String absolutePath = testFile.getAbsolutePath();//得到文件/文件夹的绝对路径
		// String getFileName = testFile.getName();//得到文件/文件夹的名字
		// System.out.println("path:"+path);
		// System.out.println("absolutePath:"+absolutePath);
		// System.out.println("getFileName:"+getFileName);
	}

	//读取文件中的内容
	public static String readFileContent(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}

	//读一个大而全的文件  按照前缀分别写入到该写入的文件中
	public static void splitFileToSmallFile(String bigfile, String smallpath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(bigfile)));
			String data;
			while ((data = br.readLine()) != null) {
				String filename = data.split("\\.")[0];
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(smallpath + File.separator + filename),true));
				bw.write(data);//data中不包含换行符
				bw.newLine();//提供换行的操作
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

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
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fr != null)
					fr.close();
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
