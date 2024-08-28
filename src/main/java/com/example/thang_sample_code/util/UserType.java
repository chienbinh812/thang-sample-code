package com.example.thang_sample_code.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    @JsonProperty("owner")
    OWNER,
    @JsonProperty("admin")
    ADMIN ,
    @JsonProperty("user")
    USER
}
