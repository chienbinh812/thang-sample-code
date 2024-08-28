package com.example.thang_sample_code.controller;

import com.example.thang_sample_code.configuration.Translator;
import com.example.thang_sample_code.dto.request.UserRequestDTO;
import com.example.thang_sample_code.dto.response.ResponseData;
import com.example.thang_sample_code.dto.response.ResponseError;
import com.example.thang_sample_code.dto.response.ResponseSuccess;
import com.example.thang_sample_code.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
@Slf4j
@Tag(name = "User Controller")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/{userId}")
    public ResponseSuccess getUser(@Min(1) @PathVariable long userId) {
        System.out.println("Request get user detail by userId = " + userId);
        return new ResponseSuccess(HttpStatus.OK, "user", new UserRequestDTO("lomhma", "antony", "m@gmail.com", "123"));
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccess getAllUsers(@RequestParam(required = false) String email,
                                       @RequestParam(defaultValue = "0") int pageNo,
                                       @Min(10) @RequestParam(defaultValue = "20") int pageSize) {
        System.out.println("List all users:");
        return new ResponseSuccess(HttpStatus.OK, "Request get all of users", List.of(new UserRequestDTO("lomhma", "antony", "m@gmail.com", "123"),
                new UserRequestDTO("lomhma", "antony", "m@gmail.com", "123")));
    }

    @Operation(summary = "Add user", description = "API create new user")
    @PostMapping("/")
    public ResponseData<Long> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
        log.info("Request add user, {} {}", userDTO.getFirstName(), userDTO.getLastName());
        try {
            long userId = userService.saveUser(userDTO);
            return new ResponseData<>(HttpStatus.CREATED, Translator.toLocale("user.add.success"), userId);
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST, e.getMessage());
        }
//        return new ResponseData<>(HttpStatus.CREATED    , "User added successfully");
    }

    @PutMapping("/{userId}")
    public ResponseSuccess updateUser(@Valid @Min(1) @PathVariable("userId") long id, @Valid @RequestBody UserRequestDTO userDTO) {
        System.out.println("Request update user = " + id);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "User updated successfully");
    }

    @PatchMapping("{userId}")
    public ResponseSuccess changeStatusUser(@Min(1) @PathVariable long userId, @RequestParam(required = false) boolean status) {
        System.out.println("Request change user status, userId " + userId);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "User changed successfully");
    }


    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseSuccess deleteUser(@Min(1) @PathVariable long userId) {
        System.out.println("Request delete userId " + userId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "User deleted successfully");
    }
}
