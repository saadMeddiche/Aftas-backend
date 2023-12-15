package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.dtos.member.requests.MemberRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.handlingExceptions.costumExceptions.ValidationException;
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

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
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

    @PostMapping("/searchByCriteria")
    public ResponseEntity<?> searchMembersByCriteria(@RequestBody List<FilterDTO> filters) {
        List<Member> members = memberService.searchMembersByCriteria(filters);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<?> searchMembers(@PathVariable String value ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Member> members = memberService.searchMembers(value , pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value =  {"/search" , "/search/"})
    public ResponseEntity<?> searchMembersDefault( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        // check if page or size is null or negative
        if (page < 0 || size < 0) throw new ValidationException("page and size must be greater than 0");

        PageRequest pageable = PageRequest.of(page, size);
        Page<Member> members = memberService.searchMembers("" , pageable);

        int tototalPages = members.getTotalPages();

        if(page >= tototalPages){
            throw new ValidationException("The page is out of its range");
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

}
