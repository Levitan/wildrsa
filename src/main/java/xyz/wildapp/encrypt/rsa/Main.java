package xyz.wildapp.encrypt.rsa;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
//        Integer p = 229;
//        Integer q = 631;
        BigInteger p = BigInteger.valueOf(NumUtils.generatePrimeNumber());
        BigInteger q = BigInteger.valueOf(NumUtils.generatePrimeNumber());
        BigInteger n = p.multiply(q); // 1829

        System.out.printf("p = %d, q = %d, n = %s\n", p, q, n.toString());

        BigInteger fi = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1))); // 1740
        Exponents exponents = NumUtils.createExponents(fi);
        BigInteger e = exponents.getOpen(); // 443
//        Integer e = 14449;
//        Integer d = 169; // 77
        BigInteger d = exponents.getClose(); // 179
        System.out.printf("Open: {%s, %s}\nClose: {%s, %s}\n", String.valueOf(e), String.valueOf(n), String.valueOf(d), String.valueOf(n));
//
//        // {e , n} - open key
//        // {d , n} - secret key
//
        final Integer P = 1014;
        BigInteger encrypted = BigInteger.valueOf(P).pow(e.intValue()).mod(n);
        System.out.println("Encrypted data: " + encrypted);
        BigInteger decrypted = encrypted.pow(d.intValue()).mod(n);
        System.out.println("Decrypted data: " + decrypted);
//        getMinD(fi);
//        NumUtils.createExponents(fi);


    }

//    public static void getMinD(Integer fi) {
//        Integer d = fi;
//        for (Integer e = 2; e < fi; e++) {
//            if (NumUtils.isPrime(e)) {
//                Integer tmp = NumUtils.getBackFi(fi, e);
//                if (tmp < d && tmp != -1) {
//                    d = tmp;
//                    System.out.println("e = " + e + "; d = " + d);
//                }
//            }
//        }
//    }
}
