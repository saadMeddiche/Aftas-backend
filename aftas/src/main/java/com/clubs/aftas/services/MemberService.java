package com.clubs.aftas.services;

import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    public List<Member> getAllMembers();

    public Page<Member> getAllMembersWithPagination(Pageable pageable);

    public Member getMemberById(Long id);

    public List<Competition> getCompetitions(Member member);

    public List<Ranking> getRankings(Member member);

    public Member createMember(MemberRequest memberRequest);

    public Member updateMember(MemberRequest member , Long memberId);
}