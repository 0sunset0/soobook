package usedbookshop.soobook.domain.review.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.review.comment.entity.Comment;
import usedbookshop.soobook.domain.review.comment.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 등록
    @Transactional
    public Long createComment(Comment comment){
        return commentRepository.save(comment);
    }

    //댓글 삭제
    @Transactional
    public Long deleteComment(Long commentId){
        return commentRepository.delete(commentId);
    }

    //댓글들 조회
    public List<Comment> findCommentsByReview(Long reviewId){
        return commentRepository.findByReview(reviewId);
    }
}
