package com.example.prj1be231109.service;

import com.example.prj1be231109.domain.Board;
import com.example.prj1be231109.domain.Member;
import com.example.prj1be231109.mapper.BoardMapper;
import com.example.prj1be231109.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberService memberService;
    private final BoardMapper mapper;
    private final CommentMapper commentMapper;

    public boolean save(Board board, Member login) {
        board.setWriter(login.getId());

        return mapper.insert(board) == 1;
    }

    public boolean validate(Board board) {
        if (board == null) {
            return false;
        }

        if (board.getContent() == null || board.getContent().isBlank()) {
            return false;
        }

        if (board.getTitle() == null || board.getTitle().isBlank()) {
            return false;
        }
        return true;
    }

    public List<Board> list() {
        return mapper.selectAll();
    }

    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public boolean remove(Integer id) {
        commentMapper.deleteByComment(id);

        return mapper.deleteById(id) == 1;
    }

    public boolean update(Board board) {
        return mapper.update(board) == 1;
    }

    public boolean hasAccess(Integer id, Member login) {
        if (memberService.isAdmin(login)) {
            return true;
        }

        Board board = mapper.selectById(id);

        return board.getWriter().equals(login.getId());
    }

}
