package com.bingoabin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bingoabin
 * @date 2022/8/5 14:49
 * @Description: 标签数据描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagBo {
	/**
	 * 标签id
	 */
	private Long tagId;
	/**
	 * 标签名
	 */
	private String tagName;
	/**
	 * 标签类型
	 */
	private String tagType;
}
