package com.bingoabin.test;

import com.bingoabin.domain.TableTagDto;
import com.bingoabin.domain.TagBo;
import com.bingoabin.utils.ListUtil;

import java.util.*;

/**
 * @author bingoabin
 * @date 2022/8/26 17:14
 * @Description: 测试分组
 */
public class TestGrouping2 {
	public static void main(String[] args){
		List<TableTagDto> tags = new ArrayList<>();
		TableTagDto tag1 = new TableTagDto("db1", "table1", Arrays.asList(new TagBo(1L,"tag1","fenlei1"),new TagBo(1L,"tag2","fenlei1")));
		TableTagDto tag2 = new TableTagDto("db2", "table2", Arrays.asList(new TagBo(1L,"tag2","fenlei1")));
		TableTagDto tag3 = new TableTagDto("db1", "table3", Arrays.asList(new TagBo(1L,"tag3","fenlei1")));
		TableTagDto tag4 = new TableTagDto("db1", "table4", Arrays.asList(new TagBo(1L,"tag2","fenlei1")));
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		tags.add(tag4);
		Map<String, Map<String, List<String>>> tagAuthMap = new HashMap<>();
		for (TableTagDto tag : tags) {
			Map<String, List<String>> tagMap = tagAuthMap.computeIfAbsent(tag.getDbName(), key -> new HashMap<>());
			List<TagBo> tagBos = tag.getTags();
			ListUtil.stream(tagBos).forEach(tagBo ->{
				List<String> tableList = tagMap.computeIfAbsent(tagBo.getTagName(), key -> new ArrayList<>());
				tableList.add(tag.getTableName());
			});
		}
		System.out.println(tagAuthMap);
	}
}
