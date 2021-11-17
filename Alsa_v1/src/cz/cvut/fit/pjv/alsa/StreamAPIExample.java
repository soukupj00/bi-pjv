package cz.cvut.fit.pjv.alsa;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cz.cvut.fit.pjv.alsa.common.SampleData.*;
import static cz.cvut.fit.pjv.alsa.common.util.ProductUtils.printProducts;

public class StreamAPIExample {

    public static void main(String[] args) {
        functions();
        streams();
    }

    private static void functions() {
        System.out.println("---------------------------------------------");
        System.out.println("functions:");
        System.out.println("---------------------------------------------");

        // Simple "addition" function
        Function<Integer, Integer> add5 = a -> a + 5;
        System.out.println(add5.apply(3).intValue());

        // Simple "multiplication" function
        Function<Integer, Integer> times2 = a -> a * 2;
        System.out.println(times2.apply(3).intValue());

        // Compose function
        Function<Integer, Integer> composed = times2.compose(add5);
        System.out.println(composed.apply(4).intValue());

        // Function with more parameter - curry function, partial application
        Function<Integer, Function<Integer, Integer>> add = a -> b -> a + b;
        System.out.println(add.apply(2).apply(3).intValue());
        System.out.println("---------------------------------------------");
    }

    private static List<Product> products = Arrays.asList(lenovoE500, hpBusinnesPlus, samsungMediaPlus);

    private static void streams() {
        streamForEach();
        streamMap();
        streamConvertToMap();
        streamFilter();
        streamSort();
        streamReduce();
        stringJoiner();
    }

    private static void streamForEach() {
        System.out.println("---------------------------------------------");
        System.out.println("streamForEach:");
        System.out.println("---------------------------------------------");

        // Klasicky for-each
        for (Product product : products) {
            System.out.print(product);
        }
        System.out.println("");

        // Streamy
        products.stream().forEach(product -> System.out.print(product));
        System.out.println("");

        products.stream().forEach(System.out::print);
        System.out.println("");

        System.out.println("---------------------------------------------");
    }

    private static void streamMap() {
        printProducts(
                "streamMap 1",
                products.stream()
                        .map(product -> product.increaseCount())
                        .collect(Collectors.toList())
        );
        printProducts(
                "streamMap 2",
                products.stream()
                        .map(Product::increaseCount)
                        .collect(Collectors.toList()));
    }

    private static void streamConvertToMap() {
        System.out.println("---------------------------------------------");
        System.out.println("streamConvertToMap:");
        System.out.println("---------------------------------------------");
        System.out.println(
                products.stream()
                        .filter(product -> product.price() >= 10000)
                        .collect(Collectors.toMap(Product::name, Function.identity())));
        System.out.println("---------------------------------------------");
    }

    private static void streamFilter() {
        printProducts(
                "streamFilter",
                products.stream()
                        .filter(product -> product.price() >= 10000)
                        .collect(Collectors.toList())
        );
    }

    private static void streamSort() {
        printProducts(
                "streamSort 1",
                products.stream()
                        .sorted((p1, p2) -> Double.compare(p1.price(), p2.price()))
                        .collect(Collectors.toList())
        );

        printProducts(
                "streamSort 2",
                products.stream()
                        .sorted(Comparator.comparingDouble(Product::price))
                        .collect(Collectors.toList())
        );
    }

    private static void streamReduce() {
        System.out.println("---------------------------------------------");
        System.out.println("streamReduce:");
        System.out.println("---------------------------------------------");
        System.out.println(
                products.stream()
                        .map(Product::price)
                        .reduce(0.0, (price1, price2) -> price1 + price2)
        );
        System.out.println("---------------------------------------------");
    }

    private static void stringJoiner() {
        System.out.println("---------------------------------------------");
        System.out.println("stringJoiner:");
        System.out.println("---------------------------------------------");
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        products.stream().map(Product::name).forEach(joiner::add);
        System.out.println(joiner.toString());
        System.out.println("---------------------------------------------");
    }
}
