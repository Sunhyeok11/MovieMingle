package movie.mingle.service;

import movie.mingle.domain.Product;
import movie.mingle.error.ErrorCode;
import movie.mingle.error.ErrorMessage;
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
            if (product.getStockQuantity() < quantity) {
                throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorCode.INSUFFICIENT_STOCK));
            }
            // 재고 감소 처리
            product.decreaseStock(quantity);
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
        }
    }
}
