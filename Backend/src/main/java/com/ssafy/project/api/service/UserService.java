package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.response.UserResDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void logoutUser(HttpServletRequest request);

    UserResDto detailUserById(long id);
    UserResDto detailUserByEmail(String email);
}
