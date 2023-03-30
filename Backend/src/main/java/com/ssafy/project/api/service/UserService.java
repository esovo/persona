package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.UserModifyReqDTO;
import com.ssafy.project.common.db.dto.response.UserDetailResDTO;
import com.ssafy.project.common.db.dto.response.UserSearchDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void logoutUser(HttpServletRequest request);
    UserDetailResDTO detailUserById();
    UserSearchDTO detailUserByEmail(String email);
    void deleteUser();
    void modifyUser(UserModifyReqDTO userModifyReqDto);


}
