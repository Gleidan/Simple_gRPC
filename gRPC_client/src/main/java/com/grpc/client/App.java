package com.grpc.client;

import com.grpc.server.borrower.BorrowerOuterClass;
import com.grpc.server.borrower.BorrowerRequest;
import com.grpc.server.borrower.BorrowerResponse;
import com.grpc.server.borrower.BorrowerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class App {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8080")
                .usePlaintext()
                .build();

        BorrowerServiceGrpc.BorrowerServiceBlockingStub stub = BorrowerServiceGrpc.newBlockingStub(channel);
        BorrowerRequest request = BorrowerRequest.newBuilder().setId(2L).build();

        BorrowerResponse response = stub.getBorrowerById(request);

        System.out.println(response);

        channel.shutdownNow();
    }
}
