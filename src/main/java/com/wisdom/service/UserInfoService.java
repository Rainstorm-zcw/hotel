package com.wisdom.service;

import com.wisdom.entity.UserInfo;
import com.wisdom.vo.ReturnResult;

/**
 * 用户信息接口
 */
public interface UserInfoService {

    UserInfo getUserInfo(UserInfo userInfo);

    int addUserInfo(UserInfo userInfo);

    ReturnResult<UserInfo> loginInfo(UserInfo userInfo);
}
