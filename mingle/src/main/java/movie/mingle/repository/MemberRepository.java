package movie.mingle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import movie.mingle.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 여기에 추가적으로 필요한 메서드를 선언할 수 있습니다.
}

