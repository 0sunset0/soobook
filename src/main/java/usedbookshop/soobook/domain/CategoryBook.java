package usedbookshop.soobook.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class CategoryBook {

    @Id @GeneratedValue
    @Column(name = "category_book_id")
    private Long id;
}
