package movie.mingle.service;

import movie.mingle.domain.Member;
import movie.mingle.domain.OrderItem;
import movie.mingle.domain.Product;
import movie.mingle.dto.OrderItemDto;
import movie.mingle.repository.MemberRepository;
import movie.mingle.repository.OrderItemRepository;
import movie.mingle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import movie.mingle.dto.OrderDto;
import movie.mingle.domain.Order;
import movie.mingle.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderCreationServiceImpl implements OrderCreationService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public OrderCreationServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        // Order 엔티티 생성
        Order order = new Order();

        // 회원 정보 설정
        Member member = memberRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        order.setMember(member);

        order.setOrderDate(LocalDateTime.now()); // 주문 시간 설정
        orderRepository.save(order); // 주문 저장

        // OrderItem 엔티티 생성
        List<OrderItemDto> orderItemDtoList = orderDto.getOrderItems();
        for (OrderItemDto orderItemDto : orderItemDtoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order); // 주문 정보 설정

            // 상품 정보 설정
            Product product = productRepository.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);

            orderItem.setQuantity(orderItemDto.getQuantity()); // 수량 설정
            orderItem.setTotalPrice(product.getPrice() * orderItemDto.getQuantity()); // 총 가격 설정

            orderItemRepository.save(orderItem); // OrderItem 저장
        }
    }

}

