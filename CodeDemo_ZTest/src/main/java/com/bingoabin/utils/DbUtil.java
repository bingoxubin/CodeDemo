package com.bingoabin.utils;

import com.google.common.base.Strings;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.regex.Pattern;

public class DbUtil {
    public static final Pattern ESCAPE_PATTERN = Pattern.compile("\\\\");
    public static final String ESCAPE_REPLACE = "\\\\\\\\";
    public static final Pattern WC_ONE = Pattern.compile("_");
    public static final String WC_ONE_REPLACE = "\\\\_";
    public static final Pattern WC_ALL = Pattern.compile("%");
    public static final String WC_ALL_REPLACE = "\\\\%";

    private static final String CASE = " (case when %s then %s else %s end) ";
    private static final String NUM_REGEXP = " %s regexp '^[0-9]{%s}' ";
    private static final String NUM_REPLACE = " concat('%s', substr(%s, %s, %s)) ";
    public static final String NUM_PAD = String.format(CASE, NUM_REGEXP, NUM_REPLACE, " %s ");

    private DbUtil() {
        super();
    }

    public static void like(String filter, Consumer<String> str) {
        if (filter == null || filter.isEmpty()) {
            return;
        }
        str.accept(like(filter));
    }

    public static Consumer<String> like(Consumer<String> str) {
        return s -> like(s, str);
    }

    public static String like(String filter) {
        if (filter == null) {
            return "%";
        }
        return "%" + escape(filter) + "%";
    }

    public static String escape(String filter) {
        if (filter == null) {
            return "";
        }
        filter = ESCAPE_PATTERN.matcher(filter).replaceAll(ESCAPE_REPLACE);
        filter = WC_ONE.matcher(filter).replaceAll(WC_ONE_REPLACE);
        filter = WC_ALL.matcher(filter).replaceAll(WC_ALL_REPLACE);
        return filter;
    }

    public static <T> T ifOnlyOne(List<T> list) {
        return ifOneOrNone(list, i -> null);
    }

    public static <T> T ifOnlyOne(List<T> list, IntFunction<T> onError) {
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return onError.apply(list == null ? 0 : list.size());
    }

    public static <T> T ifOneOrNone(List<T> list, IntFunction<T> onError) {
        return ifOnlyOne(list, i -> i == 0 ? null : onError.apply(i));
    }

    public static <T, E, P> T ifOnlyOne(SelectByExampleWithPage<T, E, P> select, E example, P page) {
        return ifOnlyOne(select.exec(example, page));
    }

    public static interface SelectByExampleWithPage<T, E, P> {
        List<T> exec(E example, P page);
    }

    public static String order(String field, boolean asc) {
        return field + (asc ? " ASC " : " DESC ");
    }

    public static String orderNumPad(String field, int n) {
        if (n <= 0) {
            return field;
        }
        String pad = Strings.repeat("a", n);
        String inner = "'" + pad + "'";
        for (int i = 1; i <= n; i++) {
            inner = orderNumPadInner(field, n, i, inner);
        }
        return inner;
    }

    public static String orderNumPadInner(String field, int max, int n, String inner) {
        String pad = Strings.repeat("0", max - n);
        return String.format(NUM_PAD, field, n, pad, field, "1", n, inner);
    }
}
