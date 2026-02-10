package com.yug.bfhl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MathUtils {

    public static List<Integer> fibonacci(int n) {
        List<Integer> result = new ArrayList<>();
        if (n <= 0)
            return result;
        result.add(0);
        if (n == 1)
            return result;
        result.add(1);
        for (int i = 2; i < n; i++) {
            result.add(result.get(i - 1) + result.get(i - 2));
        }
        return result;
    }

    public static List<Integer> filterPrimes(List<Integer> numbers) {
        return numbers.stream()
                .filter(MathUtils::isPrime)
                .collect(Collectors.toList());
    }

    public static int lcm(List<Integer> numbers) {
        return numbers.stream()
                .reduce(1, MathUtils::lcm);
    }

    public static int hcf(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, MathUtils::gcd);
    }

    private static boolean isPrime(int n) {
        if (n < 2)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        return Math.abs(a * (b / gcd(a, b)));
    }

}
