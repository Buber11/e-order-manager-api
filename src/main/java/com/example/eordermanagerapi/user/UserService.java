package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;

import java.util.List;

public interface UserService {

   UserInfoResponse getUser(Long userId);

   Boolean deleteUser(Long userId);

   UserInfoResponse updateUser(Long userId, UserChangesRequest request);

}
