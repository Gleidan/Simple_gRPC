package com.gprc.server.provider;

import com.grpc.server.borrower.CreditInfo;

import java.util.Random;
import java.util.function.Supplier;

public class CreditInfoProvider implements Supplier<CreditInfo> {

    private final Random random = new Random();

    @Override
    public CreditInfo get() {
        return CreditInfo.newBuilder().setRub(random.nextLong()).setKop(random.nextInt()).build();
    }
}
