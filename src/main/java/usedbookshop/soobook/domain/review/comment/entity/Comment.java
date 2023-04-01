package usedbookshop.soobook.domain.review.comment.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedbookshop.soobook.domain.model.Date;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.review.review.entity.Review;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Date {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private String content;

    public static Comment createComment(Member member, Review review, String content) {
        Comment comment = new Comment(member, content);
        comment.addComment(review);
        return comment;
    }

    private Comment(Member member, String content) {
        this.member = member;
        this.content = content;
    }

    /**
     * 연관관계 메서드
     */
    public void addComment(Review review){
        this.review = review;
        review.getCommentList().add(this);
    }
}
