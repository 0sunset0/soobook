package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Comment extends Date{

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private String contents;

    public Comment(Member member, Review review, String contents) {
        this.member = member;
        this.contents = contents;
        addComment(review);
    }

    /**
     * 연관관계 메서드
     */
    public void addComment(Review review){
        this.review = review;
        review.getCommentList().add(this);
    }
}
