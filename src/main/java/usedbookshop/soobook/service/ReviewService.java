package usedbookshop.soobook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.comment.CommentRepository;
import usedbookshop.soobook.repository.review.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //리뷰 작성
    @Transactional
    public void createReview(Review review){
        reviewRepository.save(review);
        review.getBook().updateScore();
    }

    //리뷰 삭제
    public Long deleteReview(Review review){
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
