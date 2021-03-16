package com.gprc.server.provider;

import com.grpc.server.borrower.PersonalInfo;

import java.util.function.Supplier;

public class PersonalInfoProvider implements Supplier<PersonalInfo> {

    @Override
    public PersonalInfo get() {
        PersonalInfo personalInfo = PersonalInfo.newBuilder()
                .setFirstName("Foo")
                .setMidName("Boo")
                .setLastName("Doo")
                .build();

        return personalInfo;
    }
}
