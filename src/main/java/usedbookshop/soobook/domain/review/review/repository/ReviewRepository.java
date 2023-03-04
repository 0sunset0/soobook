package usedbookshop.soobook.domain.review.review.repository;

import usedbookshop.soobook.domain.review.review.entity.Review;

import javax.persistence.TypedQuery;
import java.util.List;

public interface ReviewRepository {

    Long save(Review review);

    Review findById(Long reviewId);

    Review findWithMember(Long reviewId);

    List<Review> findByMember(Long memberId);

    List<Review> findByBook(Long bookId);

    List<Review> findByBookWithMember(Long bookId);

    Long delete(Long reviewId);
}
