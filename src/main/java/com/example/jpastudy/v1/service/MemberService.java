package com.example.jpastudy.v1.service;

import com.example.jpastudy.v1.entity.Member;
import com.example.jpastudy.v1.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 더티 체킹 기능을 테스트하기 위한 메서드
     */
    @Transactional
    public void updateMember(Long id, String username) {
        log.info("[updateMember] id = {}, username = {}", id, username);
        Member member = memberRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));
        member.setUsername(username);
    }

    /**
     * 페이징 처리를 위한 메서드
     */
    public List<Member> getMembers(int pageNum, int pageSize, String criteria) {
        log.info("[getMembers] pageNum = {}, pageSize = {}, criteria = {}", pageNum, pageSize, criteria);
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));

        return memberRepository.findAll(pageable).getContent();
    }

    public void createMember(String username, int age) {
        log.info("[createMember] username = {}, age = {}", username, age);
        Member member = new Member();
        member.setUsername(username);
        member.setAge(age);
        memberRepository.save(member);
    }

    public Member getMember(Long id) {
        log.info("[getMember] id = {}", id);
        return memberRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));
    }


}
