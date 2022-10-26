package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
