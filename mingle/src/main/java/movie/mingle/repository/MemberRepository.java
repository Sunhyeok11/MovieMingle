package movie.mingle.repository;

import movie.mingle.controller.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import movie.mingle.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 등록 메서드
    Member save(MemberDto member);

    // 특정 회원 조회 메서드
    Optional<Member> findById( Long id);

    // 모든 회원 조회 메서드
    List<Member> findAll();

}

