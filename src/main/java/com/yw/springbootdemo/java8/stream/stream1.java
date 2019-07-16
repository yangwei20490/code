package com.yw.springbootdemo.java8.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangwei
 * @date 2019/5/10 17:16
 */
public class stream1 {
    public static void main(String[] args) {
//        List<Integer> list = Lists.newArrayList(1, null, 3, 4, null, 6);
//        System.out.println(list.stream().filter(num -> num != null).count());

        /* map/flatMap
        * 它的作用就是把 input Stream 的每一个元素，映射成 output Stream 的另外一个元素。*/
        //转换大写
        List<String> wordList = Lists.newArrayList("abc", "def", "ghi");
        List<String> output = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
        output.forEach(System.out::println);

        //平方数
        List<Integer> nums = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
        squareNums.forEach(System.out::println);

        /*
        从上面例子可以看出，map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素。
        还有一些场景，是一对多映射关系的，这时需要 flatMap。
         */

        //一对多
        Stream<List<Integer>> inputStream = Stream.of(
                Lists.newArrayList(1),
                Lists.newArrayList(2,3),
                Lists.newArrayList(4, 5, 6)
        );
        Stream<Integer> output1 = inputStream.flatMap(
             childList -> childList.stream()
        );
        output1.forEach(System.out::println);

        /*filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。*/
        //留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);

        System.out.println("~~~~~");

        /*map：通过一个 Function 把一个元素类型为 T 的流转换成元素类型为 R 的流。
          flatMap：通过一个 Function 把一个元素类型为 T 的流中的每个元素转换成一个元素类型为 R 的流，再把这些转换之后的流合并。
          filter：过滤流中的元素，只保留满足由 Predicate 所指定的条件的元素。
          distinct：使用 equals 方法来删除流中的重复元素。
          limit：截断流使其最多只包含指定数量的元素。
          skip：返回一个新的流，并跳过原始流中的前 N 个元素。
          sorted：对流进行排序。
          peek：返回的流与原始流相同。当原始流中的元素被消费时，会首先调用 peek 方法中指定的 Consumer 实现对元素进行处理。
          dropWhile：从原始流起始位置开始删除满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素。
          takeWhile：从原始流起始位置开始保留满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素。*/

        //在清单 7 中，第一段代码展示了 flatMap 的用法，第二段代码展示了 takeWhile 和 dropWhile 的用法。
        Stream.of(1, 2, 3)
                .map(v -> v + 1)
                .flatMap(v -> Stream.of(v * 5, v* 10))
                .forEach(System.out::println);

        /*jdk9
        Stream.of(1, 2, 3)
                .takeWhile(v -> v <  3)
                .dropWhile(v -> v <  2)
                .forEach(System.out::println);
        //输出 2
        */

        /*
        在清单 8 中，第一个 reduce 操作是最简单的形式，只需要声明叠加器即可。初始值是流的第一个元素；
        第二个 reduce 操作提供了初始值和叠加器；第三个 reduce 操作声明了初始值、叠加器和合并器。
         */
        Stream.of(1, 2, 3)
                .reduce((v1, v2) -> v1 + v2)
                .ifPresent(System.out::println);

        final Map<Character, List<String>> names = Stream.of("Alex", "Bob", "David", "Amy")
                .collect(Collectors.groupingBy(v -> v.charAt(0)));
        System.out.println(names);

        String str = Stream.of("a", "b", "c")
                .collect(Collectors.joining(", "));
        System.out.println(str);
    }
}
