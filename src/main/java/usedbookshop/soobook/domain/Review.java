package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Review extends Date {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String title;

    @Lob
    private String contents;

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


    private Review(String title, String contents, ReviewScore score, Member member) {
        this.title = title;
        this.contents = contents;
        this.score = score;
        this.member = member;
    }

    public static Review createReview(String title, String contents, ReviewScore score, Book book, Member member){
        Review review = new Review(title, contents, score, member);
        review.addReview(book);
        return review;
    }


    public Long updateReview(String title, String contents, ReviewScore score, Member member) {
        this.title = title;
        this.contents = contents;
        this.score = score;
        this.member = member;
        return this.getId();
    }


    /**
     * 연관관계 메서드
     */
    private void addReview(Book book) {
        this.book = book;
        book.getReviewList().add(this);
    }
}
