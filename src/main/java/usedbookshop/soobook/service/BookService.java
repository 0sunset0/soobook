package usedbookshop.soobook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.repository.book.BookRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Long bookId, String name, int price, String author, int quantity, CategoryBook categoryBook){
        Book findBook = bookRepository.findById(bookId);
        findBook.modifyName(name);
        findBook.modifyPrice(price);
        findBook.modifyAuthor(author);
        findBook.modifyQuantity(quantity);
        findBook.modifyCategoryBook(categoryBook);
    }


    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBook(Long bookId){
        return bookRepository.findById(bookId);
    }


}
