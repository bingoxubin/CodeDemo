package com.bingoabin.vinci;

import com.bingoabin.domain.Staff;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: xubin34
 * @Date: 2022/3/16 12:41 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FunctionTest {
    public static void main(String[] args) {

    }

    //Function<T,R> 输入T，运行后，返回R
    @Test
    public void test1() {
        Function<String, Staff> function = x -> {
            Staff staff = new Staff();
            staff.setName(x + "aaaaa");
            return staff;
        };
        Staff res = function.apply("nihao ");
        System.out.println(res);
    }

    //compose方法，由两个旧的Function，得到一个新的Function
    @Test
    public void test2() {
        //根据string入参，计算长度
        Function<String, Integer> functionA = x -> {
            Objects.requireNonNull(x, "参数不能为空");
            x = x.replace(" ", "");
            return x.length();
        };

        //通过长度，设置staff的age
        Function<Integer, Staff> functionB = x -> {
            Objects.requireNonNull(x, "参数不能为空");
            Staff s = new Staff();
            s.setAge(x);
            return s;
        };

        Function<String, Staff> functionC = functionB.compose(functionA);
        Staff staff = functionC.apply(" 我 是 参 数 ！ ");
        // 输出: Staff{name='null', age=5, number='null'}
        System.out.println(staff);
    }

    //identify方法，将输入的参数进行返回
    @Test
    public void test3() {
        Map<Integer, String> mapOne = Stream.of("a", "ab", "abc", "abcd", "abcde").collect(
                Collectors.toMap(String::length, param -> param)
        );
        // 输出: {1=a, 2=ab, 3=abc, 4=abcd, 5=abcde}
        System.out.println(mapOne);

        //使用Function.identity()无疑更优雅
        Map<Integer, String> mapTwo = Stream.of("a", "ab", "abc", "abcd", "abcde").collect(
                Collectors.toMap(String::length, Function.identity())
        );

        // 输出: {1=a, 2=ab, 3=abc, 4=abcd, 5=abcde}
        System.out.println(mapTwo);
    }
}
