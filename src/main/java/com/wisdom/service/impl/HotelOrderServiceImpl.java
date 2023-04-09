package com.wisdom.service.impl;

import com.alibaba.fastjson.JSON;
import com.wisdom.entity.HotelOrder;
import com.wisdom.entity.HotelOrderExample;
import com.wisdom.mapper.HotelOrderMapper;
import com.wisdom.service.HotelOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class HotelOrderServiceImpl implements HotelOrderService {

    @Autowired
    private HotelOrderMapper hotelOrderMapper;

    /**
     * 添加酒店订单
     *
     * @param order 订单信息
     * @return 返回添加jieguo
     */
    @Override
    public int addHotelOrder(HotelOrder order) {
        order.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return hotelOrderMapper.insert(order);
    }

    /**
     * 查询订单列表
     *
     * @param order 查询订单参数
     * @return 返回订单列表
     */
    @Override
    public List<HotelOrder> queryHotelOrderAll(HotelOrder order) {
        HotelOrderExample hotelOrderExample = new HotelOrderExample();
        HotelOrderExample.Criteria criteria = hotelOrderExample.createCriteria();
        hotelOrderExample.setOrderByClause("ID DESC");
        if (Strings.isNotBlank(order.getOrderNo())) {
            criteria.andOrderNoEqualTo(order.getOrderNo());
        }
        if (Strings.isNotBlank(order.getRoomType())) {
            criteria.andRoomTypeEqualTo(order.getRoomType());
        }
        if (Strings.isNotBlank(order.getUserName())) {
            criteria.andUserNameEqualTo(order.getUserName());
        }
        if (Strings.isNotBlank(order.getUserMobile())) {
            criteria.andUserMobileEqualTo(order.getUserMobile());
        }
        if (Objects.nonNull(order.getHotelId())) {
            criteria.andHotelIdEqualTo(order.getHotelId());
        }
        if (Objects.nonNull(order.getUserId())) {
            criteria.andUserIdEqualTo(order.getUserId());
        }
        if (Strings.isNotBlank(order.getCreateTime())) {
            criteria.andCreateTimeEqualTo(order.getCreateTime());
        }
        if (Strings.isNotBlank(order.getEndTime())) {
            criteria.andEndTimeEqualTo(order.getEndTime());
        }
        if(!CollectionUtils.isEmpty(order.getHotelIds())){
            criteria.andHotelIdIn(order.getHotelIds());
        }
        log.info("查询订单列表参数:{}", JSON.toJSONString(hotelOrderExample));
        List<HotelOrder> hotelOrderList = hotelOrderMapper.selectByExample(hotelOrderExample);
        log.info("返回订单列表结果:{}", JSON.toJSONString(hotelOrderList));
        return hotelOrderList;
    }

    /**
     * 获取订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    @Override
    public HotelOrder getHotelOrder(Integer orderId) {
        return hotelOrderMapper.selectByPrimaryKey(orderId);
    }

    /**
     * 修改酒店订单
     *
     * @param hotelOrder 订单信息
     * @return 返回修改结果
     */
    @Override
    public int updateHotelOrder(HotelOrder hotelOrder) {
        return hotelOrderMapper.updateByPrimaryKeySelective(hotelOrder);
    }

    @Override
    public List<HotelOrder> queryHotelOrderAll(Integer hotelId) {
        HotelOrderExample hotelOrderExample = new HotelOrderExample();
        HotelOrderExample.Criteria criteria = hotelOrderExample.createCriteria();
        hotelOrderExample.setOrderByClause("ID DESC");

        if (Objects.nonNull(hotelId)) {
            criteria.andHotelIdEqualTo(hotelId);
        }
        log.info("查询订单列表参数:{}", JSON.toJSONString(hotelOrderExample));
        List<HotelOrder> hotelOrderList = hotelOrderMapper.selectByExample(hotelOrderExample);
        log.info("返回订单列表结果:{}", JSON.toJSONString(hotelOrderList));
        return hotelOrderList;
    }
}
