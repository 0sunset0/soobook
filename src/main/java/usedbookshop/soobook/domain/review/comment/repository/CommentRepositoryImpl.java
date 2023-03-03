package usedbookshop.soobook.domain.review.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.review.comment.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
class CommentRepositoryImpl implements CommentRepository{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    @Override
    public Comment findById(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public List<Comment> findByReview(Long reviewId) {
        return em.createQuery("select c from Comment c inner join c.review r on r.id=:reviewId", Comment.class)
                .setParameter("reviewId", reviewId)
                .getResultList();
    }

    @Override
    public Long delete(Long commentId) {
        Comment removeComment = findById(commentId);
        em.remove(removeComment);
        return removeComment.getId();
    }
}
