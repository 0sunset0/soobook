package usedbookshop.soobook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.repository.review.ReviewRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired ReviewRepository reviewRepository;
    @Autowired BookService bookService;


    @Test
    void 리뷰작성() {
        // given
        Address homeAddress = new Address("인천", "원당대로", 1111);
        Address workAddress = new Address("경기도", "와우안길", 2222);
        Member member = new Member("노을", homeAddress, workAddress, "sunset@naver.com", "1234");

        CategoryBook categoryBook = new CategoryBook();
        Book book = new Book("DB",50000, "박다솜",5, categoryBook);

        Review review = new Review("책 좋아요", "내용 없음", ReviewScore.FIVE, book, member);


        // when
        reviewRepository.save(review);


        // then
    }

    @Test
    void 리뷰들_점수의_평균이_책평점() {
        // given
        Address homeAddress = new Address("인천", "원당대로", 1111);
        Address workAddress = new Address("경기도", "와우안길", 2222);
        Member member = new Member("노을", homeAddress, workAddress, "sunset@naver.com", "1234");

        CategoryBook categoryBook = new CategoryBook();
        Book book = new Book("DB",50000, "박다솜",5, categoryBook);

        Review review1 = new Review("책 좋아요", "내용 없음", ReviewScore.FIVE, book, member);
        Review review2 = new Review("책 싫어요", "내용 없음", ReviewScore.ONE, book, member);

        // when
        reviewService.createReview(review1);
        reviewService.createReview(review2);

        // then
        int bookScore = book.getScore();
        Assertions.assertThat(bookScore).isEqualTo((review1.getScore().getValue()+review2.getScore().getValue())/2);
    }

    @Test
    void 리뷰삭제시_댓글들도_삭제() {

        // given

        // when

        // then
    }




}