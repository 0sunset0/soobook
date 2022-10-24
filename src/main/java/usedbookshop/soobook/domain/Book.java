package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Book {

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
    private CategoryBook categorybook;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
}
