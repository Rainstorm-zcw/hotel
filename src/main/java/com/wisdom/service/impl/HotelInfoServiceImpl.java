package com.wisdom.service.impl;

import com.alibaba.fastjson.JSON;
import com.wisdom.entity.HotelInfo;
import com.wisdom.entity.HotelInfoExample;
import com.wisdom.mapper.HotelInfoMapper;
import com.wisdom.service.HotelInfoService;
import com.wisdom.util.ResultUtil;
import com.wisdom.vo.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 酒店信息服务
 */
@Slf4j
@Service
public class HotelInfoServiceImpl implements HotelInfoService {

    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    /**
     * 查看酒店详情
     *
     * @param hotelId 酒店id
     * @return 返回酒店详情
     */
    public HotelInfo getHotelInfo(Integer hotelId) {
        return hotelInfoMapper.selectByPrimaryKey(hotelId);
    }

    /**
     * 获取酒店详情信息
     *
     * @param hotelInfo 酒店信息
     * @return 返回酒店列表
     */
    public ReturnResult<List<HotelInfo>> queryHotelInfoAll(HotelInfo hotelInfo) {
        ReturnResult<List<HotelInfo>> returnResult = new ReturnResult<>();
        if (Objects.isNull(hotelInfo)) {
            hotelInfo = new HotelInfo();
        }
        HotelInfoExample hotelInfoExample = new HotelInfoExample();
        HotelInfoExample.Criteria criteria = hotelInfoExample.createCriteria();
        hotelInfoExample.setOrderByClause("ID DESC");
        if (Strings.isNotBlank(hotelInfo.getHoteltype())) {
            criteria.andHoteltypeEqualTo(hotelInfo.getHoteltype());
        }
        if (Strings.isNotBlank(hotelInfo.getHotelname())) {
            criteria.andHotelnameLike("%" + hotelInfo.getHotelname() + "%");
        }
        if (Strings.isNotBlank(hotelInfo.getHoteladdress())) {
            criteria.andHoteladdressLike("%" + hotelInfo.getHoteladdress() + "%");
        }
        if (Objects.nonNull(hotelInfo.getUserId())) {
            criteria.andUserIdEqualTo(hotelInfo.getUserId());
        }
        try {
            List<HotelInfo> hotelInfos = hotelInfoMapper.selectByExample(hotelInfoExample);
            returnResult = ResultUtil.GenSuccessMessageDto(hotelInfos, "查询酒店列表成功");
            log.info("输出酒店列表结果:{}", JSON.toJSONString(returnResult));
        } catch (Exception ex) {
            log.error("查询酒店列表异常:", ex);
            return ResultUtil.GenFailMessageDto("查询酒店列表异常");
        }
        return returnResult;
    }


    /**
     * 删除酒店
     *
     * @param hotelId 酒店id
     * @return 返回删除结果
     */
    @Override
    public Integer delHotelInfo(Integer hotelId) {
        return hotelInfoMapper.deleteByPrimaryKey(hotelId);
    }

    /**
     * 保存酒店
     *
     * @param hotelInfo 酒店信息
     * @return 返回保存结果
     */
    @Override
    public Integer saveHotelInfo(HotelInfo hotelInfo) {
        return hotelInfoMapper.insert(hotelInfo);
    }

    @Override
    public int updateHotelInfo(HotelInfo hotelInfo){
        log.info("更新酒店信息参数为:{}", JSON.toJSONString(hotelInfo));
        return hotelInfoMapper.updateByPrimaryKeySelective(hotelInfo);
    }

}
