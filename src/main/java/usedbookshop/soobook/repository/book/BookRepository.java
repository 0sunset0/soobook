package usedbookshop.soobook.repository.book;

import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Category;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.domain.Member;

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
