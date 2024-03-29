package usedbookshop.soobook.domain.book.book.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedbookshop.soobook.domain.book.category.CategoryBook;
import usedbookshop.soobook.domain.model.Date;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.book.book.exception.NotEnoughStockException;
import usedbookshop.soobook.domain.review.review.entity.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Date {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String title;
    private Long price;
    private Long score;
    private String author;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_book_id")
    private CategoryBook categoryBook;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_member_id")
    private Member member;


    @Builder
    private Book(String title, Long price, String author, Long quantity, Member member) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.quantity = quantity;
        this.bookStatus = BookStatus.SALE;
        this.member = member;
    }

    public static Book createBook(String title, Long price, String author, Long quantity, Member member){
        return Book.builder()
                .title(title)
                .price(price)
                .author(author)
                .quantity(quantity)
                .member(member)
                .build();
    }

    public void modifyBook(String title, Long price, String author, Long quantity, CategoryBook categoryBook) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.quantity = quantity;
        this.categoryBook = categoryBook;
    }

    private void modifyBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }


    /**
     * 비즈니스 로직
     */
    //quantity 증가
    public void increaseQuantity(Long quantity){
        this.quantity += quantity;
    }

    //quantity 감소
    public void reduceQuantity(Long quantity){
        long restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.quantity = restStock;

        if(restStock == 0){
            this.modifyBookStatus(BookStatus.SOLD_OUT);
        }
    }

    //책의 평점 계산 후 업데이트
    public void updateScore(){

        long bookScore = 0;
        for (Review review : reviewList){
            bookScore += review.getScore().getValue();
        }
        score =  bookScore/reviewList.size();
    }


}
