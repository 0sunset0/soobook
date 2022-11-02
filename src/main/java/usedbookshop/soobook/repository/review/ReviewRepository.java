package usedbookshop.soobook.repository.review;

import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.domain.Member;

import java.util.List;

public interface ReviewRepository {

    void save(Review review);

    Review findById(Long reviewId);

    List<Review> findByMember(Member member);

    List<Review> findByBook(Book book);

    Long delete(Review review);
}
