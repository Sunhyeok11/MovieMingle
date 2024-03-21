package movie.mingle.repository;

import movie.mingle.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import movie.mingle.domain.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMember(Member member);

    Optional<Order> findById(Long id);

    List<Order> findAll();
}
