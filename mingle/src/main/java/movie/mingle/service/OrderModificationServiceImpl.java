package movie.mingle.service;

import movie.mingle.domain.Order;
import movie.mingle.domain.OrderItem;
import movie.mingle.domain.Product;
import movie.mingle.dto.OrderItemDto;
import movie.mingle.repository.OrderRepository;
import movie.mingle.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderModificationServiceImpl implements OrderModificationService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderModificationServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void modifyOrder(Long orderId, List<OrderItemDto> updatedOrderItems) {
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 수정된 주문 항목들을 반복하여 처리
        for (OrderItemDto updatedOrderItem : updatedOrderItems) {
            // 상품 정보 조회
            Product product = productRepository.findById(updatedOrderItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // 기존 주문 항목 찾기
            Optional<OrderItem> existingOrderItemOptional = order.getOrderItems().stream()
                    .filter(item -> item.getProduct().getId().equals(updatedOrderItem.getProductId()))
                    .findFirst();

            // 기존 주문 항목이 존재하면 업데이트, 아니면 새로운 주문 항목 추가
            if (existingOrderItemOptional.isPresent()) {
                OrderItem existingOrderItem = existingOrderItemOptional.get();
                existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
                existingOrderItem.setTotalPrice(product.getPrice() * updatedOrderItem.getQuantity());
            } else {
                OrderItem newOrderItem = new OrderItem();
                newOrderItem.setProduct(product);
                newOrderItem.setQuantity(updatedOrderItem.getQuantity());
                newOrderItem.setTotalPrice(product.getPrice() * updatedOrderItem.getQuantity());
                order.getOrderItems().add(newOrderItem);
            }
        }

        // 주문 상태 변경 등의 추가 로직이 있다면 여기서 수행

        // 주문 엔티티 저장
        orderRepository.save(order);
    }
}

