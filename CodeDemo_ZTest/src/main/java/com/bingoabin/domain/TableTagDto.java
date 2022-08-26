package com.bingoabin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bingoabin
 * @date 2022/8/26 16:46
 * @Description: 库表的标签
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableTagDto {
	/**
	 * 库名
	 */
	private String dbName;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表上打的标签
	 */
	private List<TagBo> tags;
}
