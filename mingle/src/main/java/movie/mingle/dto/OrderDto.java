package movie.mingle.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderDto {
    private Long userId; // 주문하는 사용자의 ID
    private Long productId; // 주문 상품의 ID
    private int quantity; // 주문 수량

    private List<OrderItemDto> orderItems; // 주문 항목들

}

