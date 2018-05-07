package xyz.wildapp.encrypt.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

    public static Boolean isPrime(BigInteger value) {
        return value.isProbablePrime((int) Math.log(value.intValue()));
    }

    public static Integer generatePrimeNumber() {
        Random random = new Random();
        while (true) {
            int prime = random.nextInt(10000) + 100;
            if (isPrime(prime)) {
                return prime;
            }
        }
    }

    public static Exponents createExponents(BigInteger fi) {
        BigInteger d;
        List<Exponents> exponentsList = new ArrayList<>();
        for (BigInteger e = BigInteger.valueOf(2); e.compareTo(fi) < 0; e = e.add(BigInteger.ONE)) {
            if (NumUtils.isPrime(e)) {
                BigInteger tmp = getBackFi(fi, e);
                if (tmp.compareTo(fi) < 0 && tmp.compareTo(BigInteger.valueOf(INVALID)) != 0) {
                    d = tmp;
                    exponentsList.add(new Exponents(e, d));
                    System.out.println("e = " + e + "; d = " + d);
//                    return new Exponents(e, d);
                }
            }
        }
        return exponentsList.get(new Random().nextInt(exponentsList.size() - 1));
    }

    public static BigInteger getMutual(BigInteger fi) {
        BigInteger mutual = BigInteger.valueOf(INVALID);
        for (BigInteger e = BigInteger.ONE; e.compareTo(fi) < 0; e = e.add(BigInteger.ONE)) {
            if (isPrime(e)) {
                BigInteger tmp = getBackFi(fi, e);
                if (tmp.compareTo(fi) < 0 && tmp.compareTo(BigInteger.valueOf(INVALID)) != 0) {
                    mutual = e;
                }
            }
        }
        return mutual;
    }

    public static BigInteger getBackFi(BigInteger fi, BigInteger e) {
        BigInteger back = BigInteger.valueOf(INVALID);
        for (BigInteger i = BigInteger.ZERO; i.compareTo(fi) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger tmp = e.multiply(i).mod(fi);
            if (tmp.equals(BigInteger.ONE)) {
                return i;
            }
        }
        return back;
    }
}
