package com.bingoabin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bingoabin
 * @date 2022/8/30 17:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonBO {
	private Integer id;
	private PersonDescBO bos;
}
