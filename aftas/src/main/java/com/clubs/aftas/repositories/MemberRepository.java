package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    // Check If There is already a member with the same identity document and identityNumber
    Optional<Member> findByIdentityDocumentAndIdentityNumber(Member.IdentityDocumentType identityDocument, String identityNumber);
}
