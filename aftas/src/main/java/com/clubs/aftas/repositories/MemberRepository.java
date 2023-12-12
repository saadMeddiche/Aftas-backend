package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Page<Member> findAll(Pageable pageable);

    // Check If There is already a member with the same identity document and identityNumber
    Optional<Member> findByIdentityDocumentAndIdentityNumber(Member.IdentityDocumentType identityDocument, String identityNumber);


    List<Member> findAll(Specification<Member> specification);
}
