package com.bingoabin.illegalcheck;

import com.bingoabin.utils.LazyInit;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xubin34
 * @Date: 2022/2/17 10:20 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IllegalChar {
    private static final LazyInit<Pattern> ILLEGAL_CHAR_REGEX = LazyInit.of(() ->
            Pattern.compile("([\\u00a0]{1})"));

    public static Optional<String> searchIllegalChar(String input) {
        if (Objects.isNull(input)) {
            return Optional.empty();
        }
        Matcher m = ILLEGAL_CHAR_REGEX.get().matcher(input);
        if (m.find()) {
            StringBuilder str = new StringBuilder();
            str.append("\\u");
            String hexStr = Integer.toHexString(m.group().charAt(0));
            str.append(String.format("%4s", hexStr).replace(' ', '0'));
            return Optional.of(str.toString());
        }
        return Optional.empty();
    }

    public static void main(String[] args){
        String input = "  asfsafa";
        Optional<String> res = searchIllegalChar(input);
        System.out.println(res.get());
    }
}
