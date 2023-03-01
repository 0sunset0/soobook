package usedbookshop.soobook.domain.book.book.repository;

import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.book.category.CategoryBook;

import java.util.List;

public interface BookRepository {

    void save(Book book);

    Book findById(Long bookId);

    List<Book> findAll();

    List<Book> findByName(String name);

    List<Book> findBooksOrderByCreatedDate();

    List<Book> findByCategoryBook(CategoryBook categoryBook);

    List<Book> findBest10Books();

    List<Book> findByMember(Long id);
}
