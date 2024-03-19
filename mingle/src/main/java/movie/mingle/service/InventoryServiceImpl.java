package movie.mingle.service;

import movie.mingle.domain.Product;
import movie.mingle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    @Autowired
    public InventoryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public int getStockQuantity(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product != null ? product.getStockQuantity() : 0;
    }

    @Override
    public void decreaseStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int currentStock = product.getStockQuantity();
            if (currentStock >= quantity) {
                product.setStockQuantity(currentStock - quantity);
            } else {
                throw new IllegalArgumentException("재고가 부족합니다.");
            }
        } else {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
        }
    }

    @Override
    public void increaseStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int currentStock = product.getStockQuantity();
            product.setStockQuantity(currentStock + quantity);
        } else {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
        }
    }

    @Override
    public void setStockQuantity(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setStockQuantity(quantity);
        } else {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
        }
    }
}
