package usedbookshop.soobook.book.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.book.book.dto.book.CreateBookDto;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.book.book.repository.BookRepository;
import usedbookshop.soobook.book.book.dto.book.UpdateBookDto;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(CreateBookDto createBookDto, Member member){
        Book book = Book.createBook(createBookDto.getTitle(), createBookDto.getPrice(), createBookDto.getAuthor(), createBookDto.getQuantity(), member);
        bookRepository.save(book);
    }


    @Transactional
    public void updateBook(UpdateBookDto updateBookDto){
        Book findBook = bookRepository.findById(updateBookDto.getId());
        findBook.modifyBook(updateBookDto.getTitle(), updateBookDto.getPrice(), updateBookDto.getAuthor(), updateBookDto.getQuantity(), updateBookDto.getCategoryBook());
    }

    //모든 책 보기
    public List<Book> findAllBooks(){
        List<Book> allBooks = bookRepository.findAll();
        return allBooks;
    }

    //검색
    public Book findBook(Long bookDtoId){
        Book findBook = bookRepository.findById(bookDtoId);
        return findBook;
    }

    //score 순으로 보기
    public List<Book> findBest10Books(){
        List<Book> best10Books = bookRepository.findBest10Books();
        return best10Books;
    }

    //최신순으로 보기
    public List<Book> findBooksOrderByCreatedDate(){
        return bookRepository.findBooksOrderByCreatedDate();
    }


    public List<Book> findByMember(Long memberId) {
        List<Book> booksByMember = bookRepository.findByMember(memberId);
        return booksByMember;
    }
}
