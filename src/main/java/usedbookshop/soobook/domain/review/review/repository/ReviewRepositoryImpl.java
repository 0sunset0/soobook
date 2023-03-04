package usedbookshop.soobook.domain.review.review.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.review.review.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
class ReviewRepositoryImpl implements ReviewRepository{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long save(Review review) {
        em.persist(review);
        em.flush();
        return review.getId();
    }

    @Override
    public Review findById(Long reviewId) {
        return em.find(Review.class, reviewId);
    }

    @Override
    public Review findWithMember(Long reviewId) {
        return em.createQuery("select r from Review r inner join fetch r.member m", Review.class).getSingleResult();
    }


    @Override
    public List<Review> findByMember(Long memberId) {
        return em.createQuery("select r from Review r inner join r.member m on m.id=:memberId", Review.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<Review> findByBook(Long bookId) {
        return em.createQuery("select r from Review r inner join r.book b on b.id=:bookId", Review.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    @Override
    public List<Review> findByBookWithMember(Long bookId) {
        return em.createQuery("select r from Review r" +
                        " join fetch r.member m" +
                        " inner join r.book b on b.id=:bookId", Review.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    @Override
    public Long delete(Long reviewId) {
        Review removeReview = findById(reviewId);
        removeReview.getBook().getReviewList().remove(removeReview);
        em.remove(removeReview);
        return removeReview.getId();
    }
}
