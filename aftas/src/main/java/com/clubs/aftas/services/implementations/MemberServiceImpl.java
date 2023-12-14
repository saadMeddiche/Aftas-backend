package com.clubs.aftas.services.implementations;


import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;

import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;
import com.clubs.aftas.repositories.MemberRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.MemberService;
import com.clubs.aftas.services.businessLogic.BLMemberService;
import com.clubs.aftas.services.validations.ValidationMemberService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.criteria.Predicate;



@Service

public class MemberServiceImpl extends BaseService<Member, Long> implements MemberService {

    private final BLMemberService blMemberService;
    private final ValidationMemberService validationMemberService;
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository, BLMemberService blMemberService, ValidationMemberService validationMemberService){
        super(memberRepository , Member.class);
        this.memberRepository = memberRepository;
        this.blMemberService = blMemberService;
        this.validationMemberService = validationMemberService;
    }


    @Override
    public List<Member> getAllMembers() {
        return getAllEntities();
    }

    @Override
    public Page<Member> getAllMembersWithPagination(Pageable pageable) {
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Member getMemberById(Long id) {
        return getEntityById(id);
    }

    @Override
    public List<Competition> getCompetitions(Member member) {
        return null;
    }


    @Override
    public Member createMember(MemberRequest memberRequest) {

        // Validate The Member
        validationMemberService.validateMemberWhenCreating(memberRequest);

        // Save The Member
        return memberRepository.save(buildCompetitionObject(memberRequest , null));
    }

    @Override
    public Member updateMember(MemberRequest memberRequest, Long memberId) {

        // Validate The Member
        validationMemberService.validateMemberWhenUpdating(memberRequest , memberId);

        // Save The Member
        return memberRepository.save( buildCompetitionObject(memberRequest , memberId));
    }

    @Override
    public List<Member> searchMembersByCriteria(List<FilterDTO> filters) {
        return Optional.of(memberRepository.findAll(searchByCriteria(filters)))
                .orElseThrow(() -> new EmptyException("No member has been found"));
    }



    @Override
    public Page<Member> searchMembers(String value , Pageable pageable) {
        return Optional.of(memberRepository.findAll(search(value) , pageable))
                .orElseThrow(() -> new EmptyException("No member has been found"));
    }

    @Override
    public void deleteMember(Long id) {
        deleteEntityById(id);
    }

    private Member buildCompetitionObject(MemberRequest memberRequest, Long memberId) {
        return Member.builder()
                .id(memberId)
                .name(memberRequest.getName())
                .familyName(memberRequest.getFamilyName())
                .accessionDate(memberRequest.getAccessionDate())
                .nationality(memberRequest.getNationality())
                .identityDocument(memberRequest.getIdentityDocument())
                .identityNumber(memberRequest.getIdentityNumber())
                .rankings(new ArrayList<Ranking>())
                .build();

    }
}
