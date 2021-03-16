package com.gprc.server.service;

import com.gprc.server.provider.CreditInfoProvider;
import com.grpc.server.borrower.BorrowerRequest;
import com.grpc.server.borrower.CreditInfo;
import com.grpc.server.borrower.CreditInfoServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.function.Supplier;

public class CreditInfoServiceImpl extends CreditInfoServiceGrpc.CreditInfoServiceImplBase {

    private final Supplier<CreditInfo> creditInfoProvider = new CreditInfoProvider();

    @Override
    public void getCreditInfo(BorrowerRequest request, StreamObserver<CreditInfo> responseObserver) {
        long id = request.getId();

        if (id == 2) {
            responseObserver.onNext(creditInfoProvider.get());
            responseObserver.onCompleted();
        }
    }
}
