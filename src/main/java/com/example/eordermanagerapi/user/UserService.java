package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;

public interface UserService {

   UserInfoResponse getUser(Long userId);

   Boolean deleteUser(Long userId);

   JwtResponse updateUser(Long userId, UserChangesRequest request);

}
