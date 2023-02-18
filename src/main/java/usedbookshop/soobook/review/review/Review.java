package usedbookshop.soobook.review.review;

import lombok.*;
import usedbookshop.soobook.book.book.Book;
import usedbookshop.soobook.common.Date;
import usedbookshop.soobook.member.Member;
import usedbookshop.soobook.review.comment.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Date {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private ReviewScore score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


    @Builder
    private Review(String title, String content, ReviewScore score, Member member) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.member = member;
    }

    public static Review createReview(String title, String content, ReviewScore score, Book book, Member member){
        Review review = Review.builder()
                .title(title)
                .content(content)
                .score(score)
                .member(member)
                .build();
        review.setBook(book);
        return review;
    }

    public void modifyReview(String title, String content, ReviewScore score, Member member) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.member = member;
    }

    /**
     * 연관관계 메서드
     */
    private void setBook(Book book) {
        this.book = book;
        book.getReviewList().add(this);
    }
}
