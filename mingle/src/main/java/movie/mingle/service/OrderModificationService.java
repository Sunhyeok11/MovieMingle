package movie.mingle.service;

import movie.mingle.dto.OrderItemDto;

import java.util.List;

public interface OrderModificationService {
    void modifyOrder(Long orderId, List<OrderItemDto> updatedOrderItems); // 주문 수정
}

