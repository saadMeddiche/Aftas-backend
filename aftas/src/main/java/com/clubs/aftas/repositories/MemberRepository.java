package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Page<Member> findAll(Pageable pageable);

    // Check If There is already a member with the same identity document and identityNumber
    Optional<Member> findByIdentityDocumentAndIdentityNumber(Member.IdentityDocumentType identityDocument, String identityNumber);


    Page<Member> findAll(Specification<Member> specification , Pageable pageable);
    List<Member> findAll(Specification<Member> specification);

}
