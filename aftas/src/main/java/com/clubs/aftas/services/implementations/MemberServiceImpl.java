package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;
import com.clubs.aftas.repositories.MemberRepository;
import com.clubs.aftas.services.MemberService;
import com.clubs.aftas.services.businessLogic.BLMemberService;
import com.clubs.aftas.services.validations.ValidationMemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final BLMemberService blMemberService;
    private final ValidationMemberService validationMemberService;
    private final MemberRepository memberRepository;


    @Override
    public List<Member> getAllMembers() {
        return Optional.of(memberRepository.findAll()).filter(members -> !members.isEmpty()).orElseThrow(() -> new EmptyException("No members have been added yet"));
    }

    @Override
    public Page<Member> getAllMembersWithPagination(Pageable pageable) {
        return Optional.of(memberRepository.findAll(pageable)).filter(members -> !members.isEmpty()).orElseThrow(() -> new EmptyException("No member has been found"));
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new DoNotExistException("No member has been found with id: " + id));
    }

    @Override
    public List<Competition> getCompetitions(Member member) {
        return null;
    }

    @Override
    public List<Ranking> getRankings(Member member) {
        return null;
    }

    @Override
    public Member createMember(MemberRequest memberRequest) {

        // Validate The Member
        validationMemberService.validateMemberWhenCreating(memberRequest);

        // Create The Member
        Member member = buildCompetitionObject(memberRequest , null);

        // Save The Member
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(MemberRequest memberRequest, Long memberId) {

        // Validate The Member
        validationMemberService.validateMemberWhenUpdating(memberRequest , memberId);

        // Create The Member
        Member member = buildCompetitionObject(memberRequest , memberId);

        // Save The Member
        return memberRepository.save(member);
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
                .build();

    }
}
