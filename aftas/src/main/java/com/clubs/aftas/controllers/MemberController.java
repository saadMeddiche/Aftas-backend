package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.services.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/members")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<?> createCompetition(@Valid @RequestBody MemberRequest memberRequest) {
        Member addedCompetition = memberService.createMember(memberRequest);
        return new ResponseEntity<>(addedCompetition, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateCompetition(@PathVariable Long memberId, @Valid @RequestBody MemberRequest memberRequest) {
        Member updatedCompetition = memberService.updateMember(memberRequest, memberId);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }
}
