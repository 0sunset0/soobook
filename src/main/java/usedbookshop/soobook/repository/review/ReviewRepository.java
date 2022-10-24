package usedbookshop.soobook.repository.review;

import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.domain.Member;

import java.util.List;

public interface ReviewRepository {

    void save(Review review);

    Review findById(Long reviewId);

    List<Review> findAll();

    List<Review> findByMember(Member member);

    void delete(Review review);
}
