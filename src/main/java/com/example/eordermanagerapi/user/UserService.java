package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface UserService {

   ResponseEntity getUser(HttpServletRequest httpServletRequest);

   ResponseEntity deleteUser(HttpServletRequest httpServletRequest);

   ResponseEntity updateUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserChangesRequest request);
   ResponseEntity existsAsAuthor(HttpServletRequest httpServletRequest);

}
