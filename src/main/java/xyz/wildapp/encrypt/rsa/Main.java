package xyz.wildapp.encrypt.rsa;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Integer p = 31;
        Integer q = 59;
//        Integer p = NumUtils.generatePrimeNumber();
//        Integer q = NumUtils.generatePrimeNumber();
        Integer n = p * q; // 1829
//
        Integer fi = (p - 1) * (q - 1); // 1740
        Integer e = 197; // 443
//        Integer d = NumUtils.getBackFi(fi, e); // 77
        Integer d = 53; // 179
        System.out.println();
//
//        // {e , n} - open key
//        // {d , n} - secret key
//
        final Integer P = 1014;
        BigInteger encrypted = BigInteger.valueOf(P).pow(e).mod(BigInteger.valueOf(n));
        System.out.println("Encrypted data: " + encrypted);
        BigInteger decrypted = encrypted.pow(d).mod(BigInteger.valueOf(n));
        System.out.println("Decrypted data: " + decrypted);
        getMinD(fi);


    }

    public static void getMinD(Integer fi) {
        Integer d = fi;
        for (Integer i = 2; i < fi; i++) {
            if (NumUtils.isPrime(i)) {
                Integer tmp = NumUtils.getBackFi(fi, i);
                if (tmp < d && tmp != -1) {
                    d = tmp;
                    System.out.println("i = " + i + "; d = " + d);
                }
            }
        }
    }
}
