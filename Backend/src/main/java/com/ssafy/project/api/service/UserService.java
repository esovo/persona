package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.UserModifyReqDto;
import com.ssafy.project.common.db.dto.response.UserDetailResDto;
import com.ssafy.project.common.db.dto.response.UserSearchDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void logoutUser(HttpServletRequest request);

    UserDetailResDto detailUserById(long id);
    UserSearchDto detailUserByEmail(String email);
    void deleteUser(Long id);
    void modifyUser(Long id, UserModifyReqDto userModifyReqDto);
}
