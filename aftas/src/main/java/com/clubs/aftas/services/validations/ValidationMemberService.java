package com.clubs.aftas.services.validations;

import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidationMemberService {

    private final MemberRepository memberRepository;

    public void validateMemberWhenCreating(MemberRequest memberRequest) {
       throwExceptionIfAlreadyThereIsAMemberWithTheSameIdentityDocumentAndIdentityNumber(memberRequest);
    }

    public void validateMemberWhenUpdating(MemberRequest memberRequest , Long memberId) {
        throwExceptionIfAlreadyThereIsAMemberWithTheSameIdentityDocumentAndIdentityNumberButWithADifferentId(memberRequest , memberId);
    }

    public void throwExceptionIfAlreadyThereIsAMemberWithTheSameIdentityDocumentAndIdentityNumberButWithADifferentId(MemberRequest memberRequest , Long memberId) {

        Optional<Member> member = memberRepository.findByIdentityDocumentAndIdentityNumber(memberRequest.getIdentityDocument(), memberRequest.getIdentityNumber());

        if(member.isPresent() && !member.get().getId().equals(memberId)) {
            throw new AlreadyExistsException("There is already a member with the same identity document and identity number");
        }
    }

    public void throwExceptionIfAlreadyThereIsAMemberWithTheSameIdentityDocumentAndIdentityNumber(MemberRequest memberRequest) {

       Optional<Member> member = memberRepository.findByIdentityDocumentAndIdentityNumber(memberRequest.getIdentityDocument(), memberRequest.getIdentityNumber());

       if(member.isPresent()) {
           throw new AlreadyExistsException("There is already a member with the same identity document and identity number");
       }
    }
}
