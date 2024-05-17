package com.example.jpastudy.dirtycheck.controller;

import com.example.jpastudy.dirtycheck.entity.Member;
import com.example.jpastudy.dirtycheck.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@Tag(name = "Member API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void createMember(@RequestParam String username, @RequestParam int age) {
        memberService.createMember(username, age);
    }

    @PatchMapping
    public void updateMember(@RequestParam Long id, @RequestParam String username) {
        memberService.updateMember(id, username);
    }

    @GetMapping
    public Member getMember(@RequestParam Long id) {
        return memberService.getMember(id);
    }

    @GetMapping("/list")
    public List<Member> getMembers(
            @RequestParam(defaultValue = "0", value = "page") int pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(defaultValue = "id", value = "sort") String criteria) {
        return memberService.getMembers(pageNum, pageSize, criteria);
    }
}
