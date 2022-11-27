package usedbookshop.soobook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.web.dto.book.ViewBookDto;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(String title, int price, String author, int quantity, Member member){
        Book book = Book.createBook(title, price, author, quantity, member);
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


    //모든 책 보기
    public List<ViewBookDto> findAllBooks(){
        List<Book> allBooks = bookRepository.findAll();
        List<ViewBookDto> allBookDtos = new ArrayList<>();
        for (Book book : allBooks) {
            allBookDtos.add(book.toViewBookDto());
        }
        return allBookDtos;
    }

    //검색
    public ViewBookDto findBook(Long bookDtoId){
        Book findBook = bookRepository.findById(bookDtoId);
        return findBook.toViewBookDto();
    }

    //score 순으로 보기
    public List<ViewBookDto> findBest10Books(){
        List<Book> best10Books = bookRepository.findBest10Books();
        List<ViewBookDto> best10BookDtos = new ArrayList<>();
        for (Book book : best10Books) {
            best10BookDtos.add(book.toViewBookDto());
        }
        return best10BookDtos;
    }

    //최신순으로 보기
    public List<Book> findBooksOrderByCreatedDate(){
        return bookRepository.findBooksOrderByCreatedDate();
    }


    public List<ViewBookDto> findByMember(Long memberId) {
        List<Book> booksByMember = bookRepository.findByMember(memberId);
        List<ViewBookDto> bookDtosByMember = new ArrayList<>();
        for (Book book : booksByMember) {
            bookDtosByMember.add(book.toViewBookDto());
        }
        return bookDtosByMember;
    }
}
