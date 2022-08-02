package com.bingoabin.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class JsonUtil {
    private static final Consumer<Object> LOGGER = e -> {
    };

    public static final Gson gson;
    public static final JsonParser jsonParser;
    private static final String DEAFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        jsonParser = new JsonParser();
    }

    private static final String[] DATE_FORMATS = new String[]{
            DEAFAULT_DATE_FORMAT,
            "MMM d, yyyy h:mm:ss a"
    };

    private static class DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
            for (String format : DATE_FORMATS) {
                try {
                    return new SimpleDateFormat(format, Locale.US).parse(jsonElement.getAsString());
                } catch (ParseException e) {
                    LOGGER.accept(e);
                }
            }
            throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                    + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
        }

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(new SimpleDateFormat(DEAFAULT_DATE_FORMAT, Locale.US).format(src));
        }
    }

    // 过滤掉一些字段
    private static class SetterExclusionStrategy implements ExclusionStrategy {
        private String[] fields;

        public SetterExclusionStrategy(String[] fields) {
            this.fields = fields;

        }

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        /**
         * 过滤字段的方法
         */
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            if (fields != null) {
                for (String name : fields) {
                    if (f.getName().equals(name)) {
                        /** true 代表此字段要过滤 */
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static String toJSONExcludeFieldString(Object obj,String[] fiels){
        return new GsonBuilder().setExclusionStrategies(new SetterExclusionStrategy(fiels)).create().toJson(obj);
    }

    /**
     * toJSONString
     *
     * @param obj 需要转换的对象
     * @return json字符串
     */
    public static String toJSONString(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * parseObject
     *
     * @param text json字符串
     * @return JSONObject
     */
    public static JSONObject parse(String text) {
        JsonObject obj = jsonParser.parse(text).getAsJsonObject();
        return new JSONObject(obj);
    }

    public static <T> T parse(String text, Class<T> clazz) {
        return gson.fromJson(text, clazz);
    }

    public static <T> T parse(String text, TypeToken<T> t) {
        return gson.fromJson(text, t.getType());
    }

    public static <T> T parse(Reader reader, TypeToken<T> t) {
        return gson.fromJson(reader, t.getType());
    }

    public static class JSON {
        /**
         * toJSONString
         *
         * @param obj 需要转换的对象
         * @return json字符串
         */
        public static String toJSONString(Object obj) {
            return JsonUtil.toJSONString(obj);
        }

        /**
         * parseObject
         *
         * @param text json字符串
         * @return JSONObject
         */
        public static JSONObject parse(String text) {
            return JsonUtil.parse(text);
        }

        /**
         * parseObject
         *
         * @param json json字符串
         * @return JSONObject
         */
        public static JSONObject parseObject(String json) {
            return JsonUtil.parse(json);
        }

        /**
         * parseObject
         *
         * @param json     json字符串
         * @param classOfT 类型
         * @param <T>      该类型的数据结构
         * @return
         */
        public static <T> T parseObject(String json, Class<T> classOfT) {
            return JsonUtil.parse(json, classOfT);
        }

        /**
         * parseArray
         *
         * @param json     json字符串
         * @param classOfT Class<T>类型
         * @param <T>      范型
         * @return List<T>
         */
        public static <T> List<T> parseArray(String json, Class<T> classOfT) {
            return JSONArray.parseArray(json, classOfT);
        }

        /**
         * parseArray
         *
         * @param json json字符串
         * @return JSONArray
         */
        public static JSONArray parseArray(String json) {
            return new JSONArray(jsonParser.parse(json).getAsJsonArray());
        }
    }

    public static class JSONObject {

        private JsonObject jsonObject;

        public JSONObject(JsonObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        public JsonObject getJsonObject() {
            return jsonObject;
        }

        public void setJsonObject(JsonObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        /**
         * toJSONString
         *
         * @param obj 需要转换的对象
         * @return json字符串
         */
        public static String toJSONString(Object obj) {
            return JsonUtil.toJSONString(obj);
        }

        /**
         * isNon 判断是否为null
         *
         * @return Boolean
         */
        public Boolean isNon() {
            return this.jsonObject == null;
        }

        /**
         * parseObject
         *
         * @param text json字符串
         * @return JSONObject
         */
        public static JSONObject parse(String text) {
            return JsonUtil.parse(text);
        }

        /**
         * parse to object
         *
         * @param json  json字符串
         * @param clazz Class
         * @param <T>   object
         * @return
         */
        public static <T> T parse(String json, Class<T> clazz) {
            return JsonUtil.parse(json, clazz);
        }

        /**
         * get
         *
         * @param field 字段
         * @return Integer类型的值
         */
        public Integer get(String field) {
            return this.jsonObject.get(field).getAsInt();
        }

        /**
         * getInteger
         *
         * @param field 字段
         * @return Integer类型的值
         */
        public Integer getInteger(String field) {
            return this.get(field);
        }

        /**
         * getBoolean
         *
         * @param field 字段
         * @return Boolean类型的值
         */
        public Boolean getBoolean(String field) {
            return this.jsonObject.get(field).getAsBoolean();
        }

        /**
         * getJSONObject
         *
         * @param field 字段
         * @return JSONObject类型的值
         */
        public JSONObject getJSONObject(String field) {
            return new JSONObject(this.jsonObject.get(field).getAsJsonObject());
        }

        /**
         * getString
         *
         * @param field 字段
         * @return String类型的值
         */
        public String getString(String field) {
            return this.jsonObject.get(field).getAsString();
        }

        /**
         * getJSONArray
         *
         * @param field 字段
         * @return JSONArray类型的值
         */
        public JSONArray getJSONArray(String field) {
            return new JSONArray(this.jsonObject.get(field).getAsJsonArray());
        }

        /**
         * entrySet
         *
         * @return Set返回值
         */
        public Set<Map.Entry<String, JsonElement>> entrySet() {
            return this.jsonObject.entrySet();
        }
    }

    public static class JSONArray {

        private JsonArray jsonArray = null;

        public JSONArray(JsonArray jsonArray) {
            this.jsonArray = jsonArray;
        }

        public JsonArray getJsonArray() {
            return jsonArray;
        }

        public void setJsonArray(JsonArray jsonArray) {
            this.jsonArray = jsonArray;
        }

        /**
         * size
         *
         * @return int类型size值
         */
        public int size() {
            return this.jsonArray.size();
        }

        /**
         * get JSONObject
         *
         * @param index 索引值
         * @return JSONObject
         */
        public JSONObject get(int index) {
            return new JSONObject(this.jsonArray.get(index).getAsJsonObject());
        }

        /**
         * contains
         *
         * @param obj 是否包含该对象
         * @return Boolean
         */
        public Boolean contains(Object obj) {
            return this.jsonArray.contains(jsonParser.parse(gson.toJson(obj)));
        }

        /**
         * isEmpty
         *
         * @return Boolean
         */
        public Boolean isEmpty() {
            return this.size() == 0;
        }

        /**
         * parseArray
         *
         * @param json     json字符串
         * @param classOfT Class<T>类型
         * @param <T>      范型
         * @return List<T>
         */
        public static <T> List<T> parseArray(String json, Class<T> classOfT) {
            List<T> result = gson.fromJson(json, TypeToken.getParameterized(List.class, classOfT).getType());
            return result;
        }

        public static <T> List<T> getParse(String jsonStr, Class<T> classOfT) {
            return jsonStr == null ? Collections.emptyList() : parseArray(jsonStr, classOfT);
        }
    }
}
