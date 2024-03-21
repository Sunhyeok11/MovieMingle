package movie.mingle.repository;

import movie.mingle.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 특정 가격 범위에 해당하는 상품 조회
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // 특정 카테고리에 속한 상품 조회
    List<Product> findByCategory(String category);
}
