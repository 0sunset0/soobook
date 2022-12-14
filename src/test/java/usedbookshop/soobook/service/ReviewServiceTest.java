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
import usedbookshop.soobook.repository.comment.CommentRepository;
import usedbookshop.soobook.repository.review.ReviewRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired ReviewRepository reviewRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired EntityManager em;


    @Test
    void 리뷰작성() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Book book = getBook("데이터베이스", 20000, "박다솜", 5, member);
        Review review = getReview("리뷰제목","리뷰내용", ReviewScore.FIVE, book, member);

        // when
        Long saveId = reviewService.createReview(review);

        // then
        Assertions.assertThat(review.getId()).isEqualTo(saveId);
    }

    @Test
    void 리뷰들_점수의_평균이_책평점() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Book book = getBook("데이터베이스", 20000, "박다솜", 5, member);

        Review review1 = getReview("리뷰제목1","good", ReviewScore.FIVE, book, member);
        Review review2 = getReview("리뷰제목2","bad", ReviewScore.ONE, book, member);

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
        Member member = getMember("노을", "1234@naver.com", "1234");
        Book book = getBook("데이터베이스", 20000, "박다솜", 5, member);
        Review review = getReview("리뷰제목","good", ReviewScore.FIVE, book, member);
        Comment comment = getComment(member, review, "Good");

        // when
        reviewService.createReview(review);
        reviewService.deleteReview(review.getId());

        //then
        List<Review> findReviews = reviewRepository.findByBook(book.getId());
        List<Comment> findComments = commentRepository.findAll();
        Assertions.assertThat(review).isNotIn(findReviews);
        Assertions.assertThat(comment).isNotIn(findComments);

    }

    @Test
    void 리뷰수정() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Book book = getBook("데이터베이스", 20000, "박다솜", 5, member);
        Review review = getReview("리뷰제목","good", ReviewScore.FIVE, book, member);

        // when
        reviewService.createReview(review);
        reviewService.updateReview(review.getId(), "리뷰 제목", "bad", ReviewScore.ONE, member.getId());

        // then
        Review findReview = reviewRepository.findById(review.getId());
        Assertions.assertThat(findReview.getScore()).isEqualTo(ReviewScore.ONE);
    }

    private Review getReview(String title, String contents, ReviewScore reviewScore, Book book, Member member) {
        Review review = Review.createReview(title, contents, reviewScore, book, member);
        return review;
    }

    private Book getBook(String title, int price, String author, int quantity, Member member) {
        CategoryBook categoryBook = new CategoryBook();
        em.persist(categoryBook);
        Book book = Book.createBook(title, price, author, quantity, member);
        em.persist(book);
        return book;
    }

    private Member getMember(String name, String email, String password) {
        Address homeAddress = new Address("인천", 1111, "원당대로");
        Address workAddress = new Address("서울", 2222, "양화대로");
        Member member = Member.createMember(name, homeAddress, workAddress, email, password);
        em.persist(member);
        return member;
    }

    private Comment getComment(Member member, Review review, String contents) {
        Comment comment = Comment.createComment(member, review, contents);
        em.persist(comment);
        return comment;
    }

}