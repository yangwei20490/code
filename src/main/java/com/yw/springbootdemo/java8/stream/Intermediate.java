package com.yw.springbootdemo.java8.stream;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author yangwei
 * @date 2019/5/23 11:13
 */
public class Intermediate {
    public static void main(String[] args) {
        /*concat
        * concat方法将两个Stream连接在一起，合成一个Stream。
        * 若两个输入的Stream都时排序的，则新Stream也是排序的；
        * 若输入的Stream中任何一个是并行的，则新的Stream也是并行的；
        * 若关闭新的Stream时，原两个输入的Stream都将执行关闭处理。*/
        Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5))
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ concat ↑↑↑↑↑");

        /*distinct
        * distinct方法以达到去除掉原Stream中重复的元素，生成的新Stream中没有没有重复的元素。*/
        Stream.of(1, 2, 2, 3, 5, 4, 4)
                .distinct()
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ distinct ↑↑↑↑↑");

        /*filter
        * filter方法对原Stream按照指定条件过滤，在新建的Stream中，只包含满足条件的元素，将不满足条件的元素过滤掉。*/
        Stream.of(1, 2, 3, 4, 5)
                .filter(item -> item > 3) //filter传入的Lambda表达式必须是Predicate实例，参数可以为任意类型，而其返回值必须是boolean类型。
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ filter ↑↑↑↑↑");

        /*map
        * map方法将对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
        * 为了提高处理效率，官方已封装好了，三种变形：mapToDouble，mapToInt，mapToLong。
        * 其实很好理解，如果想将原Stream中的数据类型，转换为double,int或者是long是可以调用相对应的方法。*/
        Stream.of("a", "b", "hello world")
                .map(item -> item.toUpperCase()) //map传入的Lambda表达式必须是Function实例，参数可以为任意类型，而其返回值也是任性类型，javac会根据实际情景自行推断。
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ map ↑↑↑↑↑");

        /*flatMap
        * flatMap方法与map方法类似，都是将原Stream中的每一个元素通过转换函数转换，
        * 不同的是，该换转函数的对象是一个Stream，也不会再创建一个新的Stream，
        * 而是将原Stream的元素取代为转换的Stream。如果转换函数生产的Stream为null，应由空Stream取代。
        * flatMap有三个对于原始类型的变种方法，分别是：flatMapToInt，flatMapToLong和flatMapToDouble。*/
        Stream.of(1, 2, 3)
                .flatMap(item -> Stream.of(item * 10)) //flatMap传入的Lambda表达式必须是Function实例，参数可以为任意类型，而其返回值类型必须是一个Stream。
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ flatMap ↑↑↑↑↑");

        /*peek
        * peek方法生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），
        * 新Stream每个元素被消费的时候都会执行给定的消费函数，并且消费函数优先执行*/
        Stream.of(1, 2, 3, 4, 5)
                .peek(item -> System.out.println("accept: " + item))
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ peek ↑↑↑↑↑");

        /*skip
        * skip方法将过滤掉原Stream中的前N个元素，返回剩下的元素所组成的新Stream。
        * 如果原Stream的元素个数大于N，将返回原Stream的后（原Stream长度-N）个元素所组成的新Stream；
        * 如果原Stream的元素个数小于或等于N，将返回一个空Stream。*/
        Stream.of(1, 2, 3, 4, 5)
                .skip(2)
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ skip ↑↑↑↑↑");

        /*sorted
        * sorted方法将对原Stream进行排序，返回一个有序列的新Stream。
        * sorted有两种变体sorted()，sorted(Comparator)，前者将默认使用Object.equals(Object)进行排序，
        * 而后者接受一个自定义排序规则函数(Comparator)，可按照意愿排序。*/
        Stream.of(1, 4, 3, 5, 2)
                .sorted()
                .forEach(System.out::println);
        Stream.of(1, 4, 3, 5, 2)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        System.out.println("↑↑↑↑↑ sorted ↑↑↑↑↑");
    }
}
