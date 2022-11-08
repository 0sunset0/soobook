package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Book extends Date{

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;
    private int price;
    private int score;
    private String author;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_book_id")
    private CategoryBook categoryBook;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Review> reviewList = new ArrayList<>();


    public Book(String name, int price, String author, int quantity, CategoryBook categoryBook) {
        this.name = name;
        this.price = price;
        this.score = 0;
        this.author = author;
        this.quantity = quantity;
        this.categoryBook = categoryBook;
        this.bookStatus = BookStatus.SALE;
    }


    public void modifyName(String name) {
        this.name = name;
    }

    public void modifyPrice(int price) {
        this.price = price;
    }

    public void modifyAuthor(String author) {
        this.author = author;
    }

    public void modifyQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void modifyCategoryBook(CategoryBook categoryBook) {
        this.categoryBook = categoryBook;
    }

    public void modifyBookStatus(BookStatus bookStatus){
        this.bookStatus = bookStatus;
    }



    /**
     * 비즈니스 로직
     */
    //quantity 증가
    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    //quantity 감소
    public void removeQuantity(int quantity){
        int restStock = this.quantity - quantity;
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
        int bookScore = 0;
        for (Review review : reviewList){
            bookScore += review.getScore().getValue();
        }
        System.out.println("review의 개수 : "+reviewList.size());
        score =  bookScore/reviewList.size();
    }

}
