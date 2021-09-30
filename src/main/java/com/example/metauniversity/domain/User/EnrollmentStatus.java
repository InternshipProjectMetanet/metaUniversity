package com.example.metauniversity.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnrollmentStatus {
    ATTENDING("ENROLLMENT_ATTENDING", "재학"),
    LEAVE("ENROLLMENT_LEAVE", "휴학"),
    GRADUATED("ENROLLMENT_GRADUATED", "졸업");

    private final String key;
    private final String value;
}
