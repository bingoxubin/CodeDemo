package com.bingoabin.stream;

/**
 * @author xubin34
 * @date 2021/7/17 7:16 下午
 */
public class WordCount {
	public String word;
	public long count;

	//记得要有这个空构建
	public WordCount() {
	}

	public WordCount(String word, long count) {
		this.word = word;
		this.count = count;
	}

	@Override
	public String toString() {
		return "WordCount{" +
				"word='" + word + '\'' +
				", count=" + count +
				'}';
	}
}
