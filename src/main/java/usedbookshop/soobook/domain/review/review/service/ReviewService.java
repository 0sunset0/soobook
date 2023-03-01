package usedbookshop.soobook.domain.review.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.member.repository.MemberRepository;
import usedbookshop.soobook.domain.review.review.dto.CreateReviewDto;
import usedbookshop.soobook.domain.review.review.repository.ReviewRepository;
import usedbookshop.soobook.domain.review.review.entity.Review;
import usedbookshop.soobook.domain.review.review.entity.ReviewScore;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    //리뷰 작성
    @Transactional
    public Long createReview(CreateReviewDto createReviewDto, Book book, Member member){
        Review review = Review.createReview(createReviewDto.getTitle(), createReviewDto.getContent(), createReviewDto.getScore(), book, member);
        reviewRepository.save(review);
        review.getBook().updateScore();
        return review.getId();
    }

    //리뷰 삭제
    public Long deleteReview(Long reviewId){
        return reviewRepository.delete(reviewId);
    }

    //리뷰 수정
    public Long updateReview(Long reviewId, String title, String contents, ReviewScore score, Long memberId){
        Review findReview = reviewRepository.findById(reviewId);
        Member findMember = memberRepository.findById(memberId);
        findReview.modifyReview(title, contents, score, findMember);
        return findReview.getId();
    }

    //도서 별 리뷰들 보기
    public List<Review> findByBook(Long bookId){
        List<Review> reviews = reviewRepository.findByBook(bookId);

        return reviews;
    }

    //멤버 별 리뷰들 보기
    public List<Review> findByMember(Long memberId){
        List<Review> reviews = reviewRepository.findByMember(memberId);
        return reviews;
    }

    // 리뷰 하나 보기
    public Review findReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId);
        return review;
    }


}
