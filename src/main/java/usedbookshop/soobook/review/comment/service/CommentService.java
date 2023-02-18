package usedbookshop.soobook.review.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.review.comment.Comment;
import usedbookshop.soobook.review.comment.repository.CommentRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 등록
    @Transactional
    public void createComment(Comment comment){
        commentRepository.save(comment);
    }

    //댓글 삭제
    @Transactional
    public Long deleteComment(Long commentId){
        return commentRepository.delete(commentId);
    }
}
