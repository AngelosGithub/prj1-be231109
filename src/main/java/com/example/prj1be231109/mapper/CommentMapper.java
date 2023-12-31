package com.example.prj1be231109.mapper;

import com.example.prj1be231109.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("""
            INSERT INTO comment (id, boardId, comment, memberId)
            VALUES (#{id}, #{boardId}, #{comment}, #{memberId})
            """)
    int insert(Comment comment);

    @Select("""
            SELECT 
                c.id,
                c.comment,
                c.inserted,
                c.boardId,
                c.memberId,
                m.nickName memberNickName
            FROM comment c JOIN member m ON c.memberId = m.id
            WHERE boardId = #{boardId}
            """)
    List<Comment> selectByBoardId(Integer boardId);

    @Delete("""
            DELETE FROM comment
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Select("""
            SELECT * FROM comment
            WHERE id = #{id}
            """)
    Comment selectById(Integer id);

    @Update("""
            UPDATE comment
            SET comment = #{comment}
            WHERE id = #{id}
            """)
    int update(Comment comment);

    @Delete("""
            DELETE FROM comment
            WHERE boardId = #{boardId}
            """)
    void deleteByComment(Integer id);

    @Delete("""
            DELETE FROM comment
            WHERE memberId = #{memberId}
            """)
    void deleteByComment2(String id);
}
