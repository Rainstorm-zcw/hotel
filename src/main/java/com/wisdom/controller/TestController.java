
package com.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.wisdom.entity.HotelInfo;
import com.wisdom.entity.HotelInfoExample;
import com.wisdom.entity.HotelOrder;
import com.wisdom.service.HotelImgService;
import com.wisdom.service.HotelInfoService;
import com.wisdom.service.HotelOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.ch.ThreadPool;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private HotelInfoService hotelInfoService;


    @Autowired
    private HotelOrderService hotelOrderService;


    @RequestMapping(value = "/testJsp", method = RequestMethod.GET)
    public ModelAndView getTest() {

        return new ModelAndView("test");
    }


    @RequestMapping(value = "/printInfo", method = RequestMethod.POST)
    public void printInfo(@RequestBody Map map) {

        log.info("輸出:{}", JSON.toJSONString(map));
    }


    @RequestMapping(value = "getHotelInfo", method = RequestMethod.GET)
    public HotelInfo getHotelInfo(Long hotelId) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<HotelInfo> completableFuture = CompletableFuture.supplyAsync(() ->
                        hotelInfoService.getHotelInfo(hotelId.intValue()), executor).
                thenCombine(CompletableFuture.supplyAsync(() -> hotelOrderService.queryHotelOrderAll(hotelId.intValue()), executor), (a, b) -> {
                    a.setHotelOrderList(b);
                    return a;
                });

        HotelInfo join = completableFuture.join();
        log.info("hotelInfo结果为:{}", JSON.toJSONString(join));
        CompletableFuture<HotelInfo> first = CompletableFuture.completedFuture(hotelInfoService.getHotelInfo(hotelId.intValue()));
        CompletableFuture<HotelInfo> future = CompletableFuture.supplyAsync(() ->
                hotelOrderService.queryHotelOrderAll(hotelId.intValue()), executor).thenCombine(first, (a, b) -> {
            b.setHotelOrderList(a);
            return b;
        });
        log.info("hotelInfo新写法结果为:{}", JSON.toJSONString(future.get()));
        return join;
    }

}

