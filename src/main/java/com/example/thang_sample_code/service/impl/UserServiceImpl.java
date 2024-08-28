package com.example.thang_sample_code.service.impl;

import com.example.thang_sample_code.dto.request.AddressDTO;
import com.example.thang_sample_code.dto.request.UserRequestDTO;
import com.example.thang_sample_code.dto.response.UserDetailResponse;
import com.example.thang_sample_code.exception.ResourceNotFoundException;
import com.example.thang_sample_code.model.Address;
import com.example.thang_sample_code.model.User;
import com.example.thang_sample_code.repository.UserRepository;
import com.example.thang_sample_code.service.UserService;
import com.example.thang_sample_code.util.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public long saveUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .gender(userRequestDTO.getGender())
                .username(userRequestDTO.getUsername())
                .status(userRequestDTO.getUserStatus())
                .type(UserType.valueOf(userRequestDTO.getType().toUpperCase()))
                .addresses(convertToAdress(userRequestDTO.getAddresses()))
                .build();
        userRepository.save(user);
        log.info("User has been saved");
        return user.getId();
    }

    @Override
    public void updateUser(long userId, UserRequestDTO userRequestDTO) {

    }

    @Override
    public void changeStatus(long userId, UserRequestDTO userRequestDTO) {

    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public UserDetailResponse getUserDetail(long userId) {
        return null;
    }

    @Override
    public List<UserDetailResponse> getAllUserDetail(int pageNo, int pageSize) {
        return List.of();
    }

    private Set<Address> convertToAdress(Set<AddressDTO> addressDTOSet) {
        Set<Address> result = new HashSet<>();
        addressDTOSet.forEach(addressDTO -> result.add(Address.builder()
                        .apartmentNumber(addressDTO.getApartmentNumber())
                        .floor(addressDTO.getFloor())
                        .building(addressDTO.getBuilding())
                        .street(addressDTO.getStreet())
                        .streetNumber(addressDTO.getStreetNumber())
                        .city(addressDTO.getCity())
                        .country(addressDTO.getCountry())
                        .addressType(addressDTO.getAddressType())
                .build()));
        return result;
    }
}
