package com.wisdom.service;

import com.wisdom.entity.HotelOrder;

import java.util.List;

public interface HotelOrderService {

    /**
     * 添加酒店订单
     *
     * @param order 订单信息
     * @return 返回添加jieguo
     */
    int addHotelOrder(HotelOrder order);

    /**
     * 查询订单列表
     *
     * @param order 查询订单参数
     * @return 返回订单列表
     */
    List<HotelOrder> queryHotelOrderAll(HotelOrder order);

    /**
     * 获取订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    HotelOrder getHotelOrder(Integer orderId);

    /**
     * 修改酒店订单
     *
     * @param hotelOrder 订单信息
     * @return 返回修改结果
     */
    int updateHotelOrder(HotelOrder hotelOrder);
}
