package com.gprc.server.service;

import com.gprc.server.provider.PersonalInfoProvider;
import com.grpc.server.borrower.BorrowerRequest;
import com.grpc.server.borrower.PersonalInfo;
import com.grpc.server.borrower.PersonalInfoServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.function.Supplier;

public class PersonalInfoServiceImpl extends PersonalInfoServiceGrpc.PersonalInfoServiceImplBase {

    private final Supplier<PersonalInfo> personalInfoProvider = new PersonalInfoProvider();

    @Override
    public void getPersonalInfo(BorrowerRequest request, StreamObserver<PersonalInfo> responseObserver) {
        long id = request.getId();

        if (id == 2L) {
            responseObserver.onNext(personalInfoProvider.get());
            responseObserver.onCompleted();
        }
    }
}
