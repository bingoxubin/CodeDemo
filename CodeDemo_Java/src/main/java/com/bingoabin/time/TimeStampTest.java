package com.bingoabin.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author bingoabin
 * @date 2023/5/26 10:53
 * @Description:
 */
public class TimeStampTest {
	public static void main(String[] args){
		long timestamp = 1684944242; // 例如：1684944242
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateTime.format(formatter);
		System.out.println(formattedDate); // 输出：2023-05-25 00:04:02
	}

	// SELECT FROM_UNIXTIME(1684944242,'yyyy-MM-dd HH:mm:ss') AS formatted_date;
	// SELECT FROM_UNIXTIME(cast('1684944242' as INT),'yyyy-MM-dd HH:mm:ss') AS formatted_date;
	// select unix_timestamp('2023-05-25 00:04:02') as date;
}
