package usedbookshop.soobook.repository.comment;

import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.domain.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    Comment findById(Long commentId);

    List<Comment> findAll();

    List<Comment> findByReview(Review review);

    Long delete(Comment comment);
}
