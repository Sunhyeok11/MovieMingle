package movie.mingle.service;

import movie.mingle.dto.OrderDto;

public interface OrderCreationService {
    void createOrder(OrderDto orderDto);
}
