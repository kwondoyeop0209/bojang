package com.ssafy.api.service.orderInfo;

import com.ssafy.api.request.orderinfo.OrderInfoPatchRequest;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.OrderStatus;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.OrderInfoRepository;
import com.ssafy.db.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public OrderInfo getOrderInfo(String orderInfoId) {
        Optional<OrderInfo> optionalOrderInfo = orderInfoRepository.findById(orderInfoId);
        if (optionalOrderInfo.isPresent())
            return optionalOrderInfo.get();
        return null;
    }

    @Override
    public List<OrderInfo> getOrderInfoList(User user, Integer year) {

        if (year == null) {
            LocalDateTime endDatetime = LocalDateTime.now();
            LocalDateTime startDatetime = endDatetime.minusMonths(6);
            List<OrderInfo> orderInfoList = orderInfoRepository.findByRegisterTimeBetweenAndUserOrderByRegisterTimeDesc(startDatetime, endDatetime, user);
            return orderInfoList;
        } else {
            LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59, 59);
            List<OrderInfo> orderInfoList = orderInfoRepository.findByRegisterTimeBetweenAndUserOrderByRegisterTimeDesc(start, end, user);
            return orderInfoList;
        }
    }

    @Override
    public List<OrderInfo> getMarketInfoList(Long marketId) {
        return orderInfoRepository.findByMarket_IdAndOrderStatus_IdNot(marketId, 3L);
    }

    @Override
    public OrderInfo patchOrderInfoStatus(OrderInfoPatchRequest orderInfoPatchRequest) {
        OrderInfo orderInfo = orderInfoRepository.findById(orderInfoPatchRequest.getOrderInfoId())
                .orElseThrow(() -> new ResourceNotFoundException("OrderInfo", "Id", orderInfoPatchRequest.getOrderInfoId()));
        OrderStatus orderStatus = orderStatusRepository.findById(orderInfoPatchRequest.getOrderStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatus", "Id", orderInfoPatchRequest.getOrderStatusId()));
        orderInfo.setOrderStatus(orderStatus);
        return orderInfoRepository.save(orderInfo);
    }
}
