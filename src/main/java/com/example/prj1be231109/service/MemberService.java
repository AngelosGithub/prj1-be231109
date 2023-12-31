package com.example.prj1be231109.service;

import com.example.prj1be231109.domain.Auth;
import com.example.prj1be231109.domain.Member;
import com.example.prj1be231109.mapper.BoardMapper;
import com.example.prj1be231109.mapper.CommentMapper;
import com.example.prj1be231109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;
    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;

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

        if (member.getNickName().isBlank()) {
            return false;
        }

        if (member.getEmail().isBlank()) {
            return false;
        }
        return true;
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member getMember(String id) {
        return mapper.selectById(id);
    }

    public boolean deleteMember(String id) {
        // * 이 멤버가 작성한 댓글삭제
        commentMapper.deleteByComment2(id);
        // 1. 이 멤버가 작성한 게시물 삭제
        boardMapper.deleteByWriter(id);
        // 2. 멤버 삭제
        return mapper.deleteById(id) == 1;
    }

    public boolean update(Member member) {
//        Member oldMember = mapper.selectById(member.getId());
//        if (member.getPassword().equals("")) {
//            member.setPassword(oldMember.getPassword());
//        }

        return mapper.update(member) == 1;
    }

    public String getEmail(String email) {
        return mapper.selectEmail(email);
    }

    public String getNickName(String nickName) {
        return mapper.selectNickName(nickName);
    }

    public boolean login(Member member, WebRequest request) {
        Member dbMember = mapper.selectById(member.getId());


        if (dbMember != null) {
            if (dbMember.getPassword().equals(member.getPassword())) {
                List<Auth> auth = mapper.selectAuthById(member.getId());
                dbMember.setAuth(auth);

                dbMember.setPassword("");
                request.setAttribute("login", dbMember, RequestAttributes.SCOPE_SESSION);
                return true;
            }
        }
        return false;
    }

    public boolean hasAccess(String id, Member login) {
        if (isAdmin(login)) {
            return true;
        }

        return login.getId().equals(id);
    }

    public boolean isAdmin(Member login) {
        if (login.getAuth() != null) {
            return login.getAuth()
                    .stream()
                    .map(e -> e.getName())
                    .anyMatch(n -> n.equals("admin"));
        }
        return false;
    }
}
