package com.example.prj1be231109.service;

import com.example.prj1be231109.domain.Member;
import com.example.prj1be231109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    public boolean add(Member member) {
        return mapper.insert(member) == 1;
    }

    public String getId(String id) {
        return mapper.selectId(id);
    }

    public boolean validate(Member member) {
        if (member == null) {
            return false;
        }

        if (member.getId().isBlank()) {
            return false;
        }

        if (member.getPassword().isBlank()) {
            return false;
        }

        if (member.getEmail().isBlank()) {
            return false;
        }
        return true;
    }
}