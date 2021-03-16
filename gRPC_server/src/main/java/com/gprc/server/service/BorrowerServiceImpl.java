package com.gprc.server.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.server.borrower.*;
import com.grpc.server.borrower.Borrower;
import com.grpc.server.borrower.BorrowerRequest;
import com.grpc.server.borrower.BorrowerResponse;
import com.grpc.server.borrower.CreditInfo;
import com.grpc.server.borrower.PersonalInfo;
import io.grpc.stub.StreamObserver;

public class BorrowerServiceImpl extends BorrowerServiceGrpc.BorrowerServiceImplBase {

    private final PersonalInfoServiceGrpc.PersonalInfoServiceBlockingStub personalInfoService;
    private final CreditInfoServiceGrpc.CreditInfoServiceBlockingStub creditInfoService;

    public BorrowerServiceImpl(PersonalInfoServiceGrpc.PersonalInfoServiceBlockingStub personalInfoService,
                               CreditInfoServiceGrpc.CreditInfoServiceBlockingStub creditInfoService) {
        this.personalInfoService = personalInfoService;
        this.creditInfoService = creditInfoService;
    }

    @Override
    public void getBorrowerById(BorrowerRequest request, StreamObserver<BorrowerResponse> responseObserver) {
        PersonalInfo personalInfo = personalInfoService.getPersonalInfo(request);
        CreditInfo creditInfo = creditInfoService.getCreditInfo(request);

        Borrower borrower = Borrower.newBuilder().setCreditInfo(creditInfo).setPersonalInfo(personalInfo).build();

        BorrowerResponse response = BorrowerResponse.newBuilder().setBorrower(borrower).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
