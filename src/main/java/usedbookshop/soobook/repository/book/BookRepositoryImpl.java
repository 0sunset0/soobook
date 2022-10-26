package usedbookshop.soobook.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Category;
import usedbookshop.soobook.domain.CategoryBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Book book) {
        em.persist(book);
    }

    @Override
    public Book findById(Long bookId) {
        return em.find(Book.class, bookId);
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findByName(String name) {
        return em.createQuery("select b from Book b where name=:name", Book.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Book> findAllOrderByCreatedDate() {
        return em.createQuery("select b from Book b order by b.createdDate desc", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findByCategoryBook(CategoryBook categoryBook) {
        //이거 마저 구현하기...
        return null;
    }

}
