package xyz.wildapp.encrypt.rsa;

import java.math.BigInteger;

public class Exponents {

    private BigInteger open;
    private BigInteger close;

    public Exponents(BigInteger open, BigInteger close) {
        this.open = open;
        this.close = close;
    }

    public BigInteger getOpen() {
        return open;
    }

    public void setOpen(BigInteger open) {
        this.open = open;
    }

    public BigInteger getClose() {
        return close;
    }

    public void setClose(BigInteger close) {
        this.close = close;
    }
}
