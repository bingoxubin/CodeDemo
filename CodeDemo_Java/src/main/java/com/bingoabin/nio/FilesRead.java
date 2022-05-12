package com.bingoabin.nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/5/12 17:56
 */
public class FilesRead {
	public static void main(String[] args) throws IOException {
		List<String> files = Files.readAllLines(Paths.get("E:\\60.test\\test.txt"));
		for (String file : files) {
			System.out.println(file);
		}

		Path write = Files.write(Paths.get("E:\\60.test\\test1.txt"), files, StandardCharsets.UTF_8);
		System.out.println(write);

		Path copy = Files.copy(Paths.get("E:\\60.test\\test.txt"), Paths.get("E:\\60.test\\test2.txt"), StandardCopyOption.COPY_ATTRIBUTES);
		System.out.println(copy);
	}
}
