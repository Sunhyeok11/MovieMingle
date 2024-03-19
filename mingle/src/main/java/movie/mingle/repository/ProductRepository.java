package movie.mingle.repository;

import movie.mingle.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Product 엔티티와 관련된 추가적인 메서드가 필요한 경우 여기에 선언할 수 있습니다.
}
