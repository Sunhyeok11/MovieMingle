package movie.mingle.service;

import movie.mingle.domain.Product;
import movie.mingle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final ProductRepository productRepository;

    @Autowired
    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void placeOrder(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // 재고 감소 처리
            product.decreaseStock(quantity);
            productRepository.save(product); // 변경된 재고 정보 저장

            // 주문 처리 로직
            // 여기서 주문을 처리하거나 다른 로직을 추가할 수 있습니다.
        } else {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
        }
    }
}
