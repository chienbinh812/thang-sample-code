package com.example.thang_sample_code.dto.request;

import com.example.thang_sample_code.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.example.thang_sample_code.util.Gender.*;
@Getter
public class UserRequestDTO implements Serializable {
    @NotBlank(message = "firstName must be not blank")
    private String firstName;

    @NotNull(message = "lastName must be not null")
    private String lastName;

    @Email(message = "email invalid format")
    private String email;

    @PhoneNumber(message = "phone invalid format")
    private String phone;

    @NotNull(message = "username must be not null")
    private String username;

    @NotNull(message = "password must be not null")
    private String password;

    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

    @NotEmpty(message = "permission must be not empty")
    private List<String> permission;

    //cach 1 validate enum
    @EnumPattern(name = "status", regex = "ACTIVE|INACTIVE|NONE")
    private UserStatus userStatus;

    //cach 2 validate enum
    @GenderSubset(anyOf = {MALE, FEMALE, OTHER})
    private Gender gender;

    //cach 3 validate enum

    @NotNull(message = "type must be not null")
    @EnumValue(name = "type", enumClass = UserType.class)
    private String type;

    @NotEmpty(message = "addresses can not empty")
    private Set<AddressDTO> addresses;

    public UserRequestDTO(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

}
