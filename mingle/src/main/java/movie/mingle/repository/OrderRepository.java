package movie.mingle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import movie.mingle.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // 특별한 메서드가 필요하다면 여기에 추가할 수 있음
}
