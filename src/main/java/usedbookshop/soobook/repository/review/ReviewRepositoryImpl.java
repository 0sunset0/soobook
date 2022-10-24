package usedbookshop.soobook.repository.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Order;
import usedbookshop.soobook.domain.Review;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository{

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
    public List<Review> findAll() {
        return em.createQuery("select r from Review r", Review.class)
                .getResultList();
    }

    @Override
    public List<Review> findByMember(Member member) {
        return em.createQuery("select r from Review r inner join r.member m on m.id=:memberId", Review.class)
                .setParameter("memberId", member.getId())
                .getResultList();
    }

    @Override
    public void delete(Review review) {
        em.remove(review);
    }
}
