package com.bingoabin.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: niujianghao
 * @date: 2022/2/15  2:29 下午
 */
public class ParamCheckHelper {
    private static final LazyInit<Pattern> ILLEGAL_CHAR_REGEX = LazyInit.of(() ->
            Pattern.compile("([\\u00a0]{1})"));

    public static Optional<String> searchIllegalChar(String input) {
        if (Objects.isNull(input)) {
            return Optional.empty();
        }
        Matcher m = ILLEGAL_CHAR_REGEX.get().matcher(input);
        if (m.find()) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\u");
            String hexStr = Integer.toHexString(m.group().charAt(0));
            sb.append(String.format("%4s", hexStr).replace(' ', '0'));
            return Optional.of(sb.toString());
        }
        return Optional.empty();
    }
}
