package movie.mingle.service;

import movie.mingle.dto.OrderDto;

import java.util.List;

public interface OrderQueryService {
    List<OrderDto> getOrdersByUserId(Long userId); // 특정 회원의 주문 조회
    OrderDto getOrderById(Long orderId); // 주문 번호를 기준으로 주문 조회
    List<OrderDto> getAllOrders(); // 모든 주문 조회
}

