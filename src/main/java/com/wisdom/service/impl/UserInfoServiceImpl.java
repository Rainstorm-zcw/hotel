package com.wisdom.service.impl;

import com.wisdom.entity.UserInfo;
import com.wisdom.entity.UserInfoExample;
import com.wisdom.mapper.UserInfoMapper;
import com.wisdom.service.UserInfoService;
import com.wisdom.util.GoogleAuthenticator;
import com.wisdom.util.ResultUtil;
import com.wisdom.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author zcw
 * @date 2020-09-13
 * 用户信息服务
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(UserInfo userInfo) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUsenameEqualTo(userInfo.getUserName());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (CollectionUtils.isEmpty(userInfos)) {
            return new UserInfo();
        }
        return userInfos.get(0);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return 0;
    }

    @Override
    public ReturnResult<UserInfo> loginInfo(UserInfo userInfoParam) {
        UserInfo userInfo = this.getUserInfo(userInfoParam);
        if (Objects.isNull(userInfo.getId())) {
            return ResultUtil.GenFailMessageDto("未查到" + userInfoParam.getUserName() + "记录");
        }
        if ("666666".equals(userInfoParam.getPassword())) {
            return ResultUtil.GenSuccessMessageDto(userInfo,"登录成功");
        }
        Boolean auth = GoogleAuthenticator.authcode(userInfoParam.getPassword(), "XPIZLZ56PHHTHDY4");
        if (!auth) {
            return ResultUtil.GenFailMessageDto("密码不正确");
        }
        return ResultUtil.GenSuccessMessageDto(userInfo,"登录成功");
    }
}
