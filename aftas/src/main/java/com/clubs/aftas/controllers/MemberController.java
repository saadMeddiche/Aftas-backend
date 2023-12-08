package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.services.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/members")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }
    @GetMapping("/pagination")
    public Page<Member> getMembersWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return memberService.getAllMembersWithPagination(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberRequest memberRequest) {
        Member addedMember = memberService.createMember(memberRequest);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable Long memberId, @Valid @RequestBody MemberRequest memberRequest) {
        Member updatedMember = memberService.updateMember(memberRequest, memberId);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }
}
