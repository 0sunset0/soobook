package usedbookshop.soobook.repository.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.Order;
import usedbookshop.soobook.domain.Comment;
import usedbookshop.soobook.domain.Review;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{

    private final EntityManager em;

    @Override
    public void save(Comment comment) {
        em.persist(comment);
    }

    @Override
    public Comment findOne(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    @Override
    public List<Comment> findByReview(Review review) {
        return em.createQuery("select c from Comment c inner join c.review r on r.id=:reviewId", Comment.class)
                .setParameter("reviewId", review.getId())
                .getResultList();
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);
    }
}
