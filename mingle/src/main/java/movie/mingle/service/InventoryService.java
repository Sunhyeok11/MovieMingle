package movie.mingle.service;

import org.springframework.stereotype.Service;

@Service
public interface InventoryService {

    int getStockQuantity(Long productId);

    void decreaseStock(Long productId, int quantity);

    void increaseStock(Long productId, int quantity);

    void setStockQuantity(Long productId, int quantity);
}
