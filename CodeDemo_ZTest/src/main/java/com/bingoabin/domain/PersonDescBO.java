package com.bingoabin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bingoabin
 * @date 2022/8/30 17:50
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDescBO {
	private Integer age;
	private List<String> names;
}
