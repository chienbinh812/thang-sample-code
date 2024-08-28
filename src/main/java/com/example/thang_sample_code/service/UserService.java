package com.example.thang_sample_code.service;

import com.example.thang_sample_code.dto.request.UserRequestDTO;
import com.example.thang_sample_code.dto.response.UserDetailResponse;

import java.util.List;

public interface UserService {
    public long saveUser(UserRequestDTO userRequestDTO);

    public void updateUser(long userId,UserRequestDTO userRequestDTO);

    public void changeStatus(long userId,UserRequestDTO userRequestDTO);

    public void deleteUser(long userId);

    public UserDetailResponse getUserDetail(long userId);

    public List<UserDetailResponse> getAllUserDetail(int pageNo, int pageSize);
}
