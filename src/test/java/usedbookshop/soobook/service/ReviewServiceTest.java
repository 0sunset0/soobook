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
        Member member = getMember();
        Book book = getBook();
        Review review = getReview("a","a", ReviewScore.FIVE, book, member);

        // when
        Long saveId = reviewService.createReview(review);

        // then
        Assertions.assertThat(review.getId()).isEqualTo(saveId);
    }

    @Test
    void 리뷰들_점수의_평균이_책평점() {
        // given
        Member member = getMember();
        Book book = getBook();

        Review review1 = getReview("a","a", ReviewScore.FIVE, book, member);
        Review review2 = getReview("a","a", ReviewScore.ONE, book, member);

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
        Member member = getMember();
        Book book = getBook();
        Review review = getReview("a","a", ReviewScore.FIVE, book, member);
        Comment comment = getComment(member, review, "Good");

        // when
        reviewService.createReview(review);
        reviewService.deleteReview(review);

        //then
        List<Review> findReviews = reviewRepository.findByBook(book);
        List<Comment> findComments = commentRepository.findAll();
        Assertions.assertThat(review).isNotIn(findReviews);
        Assertions.assertThat(comment).isNotIn(findComments);

    }

    private Review getReview(String title, String contents, ReviewScore reviewScore, Book book, Member member) {
        Review review = new Review(title, contents, reviewScore, book, member);
        return review;
    }

    private Book getBook() {
        CategoryBook categoryBook = new CategoryBook();
        em.persist(categoryBook);
        Book book = new Book("a",50000, "a",5, categoryBook);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Address homeAddress = new Address("a", "a", 1111);
        Address workAddress = new Address("a", "a", 2222);
        Member member = new Member("a", homeAddress, workAddress, "sunset@naver.com", "1234");
        em.persist(member);
        return member;
    }



    private Comment getComment(Member member, Review review, String contents) {
        Comment comment = new Comment(member, review, contents);
        em.persist(comment);
        return comment;
    }

}