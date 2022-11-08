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


    public Review(String title, String contents, ReviewScore score, Book book, Member member) {
        this.title = title;
        this.contents = contents;
        this.score = score;
        this.member = member;
        addReview(book);
    }

    //연관관계 메서드
    private void addReview(Book book) {
        book.getReviewList().add(this);
        this.setBook(book);
    }

    public void setBook(Book book) {
        this.book = book;
    }

    //연관관계 메서드
    public void addComment(Comment comment){
        commentList.add(comment);
        comment.setReview(this);
    }

}
