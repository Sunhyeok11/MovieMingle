package movie.mingle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {
    private Long productId;
    private int quantity;

}

