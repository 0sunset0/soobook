package usedbookshop.soobook.domain.review.review.repository;

import usedbookshop.soobook.domain.review.review.entity.Review;

import java.util.List;

public interface ReviewRepository {

    Long save(Review review);

    Review findById(Long reviewId);

    List<Review> findByMember(Long memberId);

    List<Review> findByBook(Long bookId);

    Long delete(Long reviewId);
}
