package usedbookshop.soobook.domain.review.comment.repository;

import usedbookshop.soobook.domain.review.comment.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    Comment findById(Long commentId);

    List<Comment> findAll();

    List<Comment> findByReview(Long reviewId);

    Long delete(Long commentId);
}
