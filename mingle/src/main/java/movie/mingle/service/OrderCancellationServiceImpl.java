package movie.mingle.service;

import movie.mingle.domain.Order;
import movie.mingle.domain.OrderStatus;
import movie.mingle.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderCancellationServiceImpl implements OrderCancellationService {

    private final OrderRepository orderRepository;

    public OrderCancellationServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 주문 상태를 취소된 상태로 변경
        order.updateStatus(OrderStatus.CANCELLED);

        // 주문 엔티티 저장
        orderRepository.save(order);
    }
}

