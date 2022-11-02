package usedbookshop.soobook.repository.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Order;
import usedbookshop.soobook.domain.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Review review) {
        em.persist(review);
    }

    @Override
    public Review findById(Long reviewId) {
        return em.find(Review.class, reviewId);
    }


    @Override
    public List<Review> findByMember(Member member) {
        return em.createQuery("select r from Review r inner join r.member m on m.id=:memberId", Review.class)
                .setParameter("memberId", member.getId())
                .getResultList();
    }

    @Override
    public List<Review> findByBook(Book book) {
        return em.createQuery("select r from Review r inner join r.book b on b.id=:bookId", Review.class)
                .setParameter("bookId", book.getId())
                .getResultList();
    }

    @Override
    public Long delete(Review review) {
        em.remove(review);
        return review.getId();
    }
}
