package usedbookshop.soobook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.comment.CommentRepository;
import usedbookshop.soobook.repository.review.ReviewRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    @PersistenceContext
    private final EntityManager em;

    //리뷰 작성
    @Transactional
    public Long createReview(Review review){
        reviewRepository.save(review);
        review.getBook().updateScore();
        return review.getId();
    }

    //리뷰 삭제
    public Long deleteReview(Review review){
        review.getBook().getReviewList().remove(review);
        return reviewRepository.delete(review);
    }

    //도서 별 리뷰들 보기
    public List<Review> findByBook(Book book){
        return reviewRepository.findByBook(book);
    }

    //멤버 별 리뷰들 보기
    public List<Review> findByMember(Member member){
        return reviewRepository.findByMember(member);
    }


}
