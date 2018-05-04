package xyz.wildapp.encrypt.rsa;

import java.math.BigInteger;
import java.util.Random;

public class NumUtils {

    public static Integer INVALID = -1;

    public static Integer gcd(Integer a, Integer b) {
        while (!a.equals(b)) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static Boolean isPrime(Integer value) {
        BigInteger prime = BigInteger.valueOf(value);
        return prime.isProbablePrime((int) Math.log(value));
    }

    public static Integer generatePrimeNumber() {
        Random random = new Random();
        while (true) {
            int prime = random.nextInt(50) + 20;
            if (isPrime(prime)) {
                return prime;
            }
        }
    }

    public static Integer getMutual(Integer fi) {
        int mutual = INVALID;
        for (Integer i = 0; i < 30; i++) {
            if (isPrime(i)) {
                mutual = i;
            }
        }
        return mutual;
    }

    public static Integer getBackFi(Integer fi, Integer e) {
        Integer back = INVALID;
        for (Integer i = 0; i < fi; i++) {
            int tmp = (i * e) % fi;
            if (tmp == 1) {
                return i;
            }
        }
        return back;
    }
}
