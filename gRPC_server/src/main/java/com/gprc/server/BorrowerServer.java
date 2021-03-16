package com.gprc.server;

import com.gprc.server.service.BorrowerServiceImpl;
import com.gprc.server.service.CreditInfoServiceImpl;
import com.gprc.server.service.PersonalInfoServiceImpl;
import com.grpc.server.borrower.CreditInfoServiceGrpc;
import com.grpc.server.borrower.PersonalInfoServiceGrpc;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import java.io.IOException;

public class BorrowerServer {

    private static final String HOST_NAME = "localhost";
    private static final int creditInfoPort = 8081;
    private static final int personalInfoPort = 8082;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server1 = NettyServerBuilder
                .forPort(creditInfoPort)
                .addService(new CreditInfoServiceImpl())
                .build()
                .start();

        Server server2 = NettyServerBuilder
                .forPort(personalInfoPort)
                .addService(new PersonalInfoServiceImpl())
                .build()
                .start();

        CreditInfoServiceGrpc.CreditInfoServiceBlockingStub creditStub = CreditInfoServiceGrpc
                .newBlockingStub(NettyChannelBuilder.forAddress(HOST_NAME, creditInfoPort)
                        .usePlaintext(true)
                        .build());
        PersonalInfoServiceGrpc.PersonalInfoServiceBlockingStub infoStub = PersonalInfoServiceGrpc
                .newBlockingStub(NettyChannelBuilder.forAddress(HOST_NAME, personalInfoPort)
                        .usePlaintext(true)
                        .build());

        BorrowerServiceImpl borrowerService = new BorrowerServiceImpl(infoStub, creditStub);

        Server server3 = NettyServerBuilder
                .forPort(8080)
                .addService(borrowerService)
                .build()
                .start();

        server1.awaitTermination();
        server2.awaitTermination();
        server3.awaitTermination();
    }
}
