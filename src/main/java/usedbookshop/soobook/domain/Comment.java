package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
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

    public static Comment createComment(Member member, Review review, String contents) {
        Comment comment = new Comment(member, contents);
        comment.addComment(review);
        return comment;
    }

    private Comment(Member member, String contents) {
        this.member = member;
        this.contents = contents;
    }

    /**
     * 연관관계 메서드
     */
    public void addComment(Review review){
        this.review = review;
        review.getCommentList().add(this);
    }
}
