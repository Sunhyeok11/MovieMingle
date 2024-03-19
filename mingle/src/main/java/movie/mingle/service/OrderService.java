package movie.mingle.service;

import movie.mingle.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order createOrder(Long memberId, Long productId, int quantity);

    Order getOrder(Long orderId);

    List<Order> getAllOrders();

    void cancelOrder(Long orderId);
}
