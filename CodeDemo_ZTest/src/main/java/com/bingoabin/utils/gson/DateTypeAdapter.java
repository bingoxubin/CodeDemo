package com.bingoabin.utils.gson;

import com.google.gson.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author: lipan
 * @Date: 2021/8/10
 */
public class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    public DateTypeAdapter() {
    }

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        try {
            return NumberUtils.isParsable(json.getAsJsonPrimitive().getAsString()) ? new Date(json.getAsJsonPrimitive().getAsLong()) : DateUtils.parseDate(json.getAsJsonPrimitive().getAsString(), new String[]{"yyyy-MM-dd HH:mm:ss"});
        } catch (ParseException var5) {
            throw new IllegalArgumentException("DateTypeAdapter.deserialize fail!", var5);
        }
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());
    }
}
