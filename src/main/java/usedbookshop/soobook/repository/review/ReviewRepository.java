package usedbookshop.soobook.repository.review;

import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.domain.Member;

import java.util.List;

public interface ReviewRepository {

    Long save(Review review);

    Review findById(Long reviewId);

    List<Review> findByMember(Long memberId);

    List<Review> findByBook(Long bookId);

    Long delete(Long reviewId);
}
