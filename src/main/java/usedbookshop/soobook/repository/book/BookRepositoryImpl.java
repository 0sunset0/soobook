package usedbookshop.soobook.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
class BookRepositoryImpl implements BookRepository {
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
    public List<Book> findBooksOrderByCreatedDate() {
        return em.createQuery("select b from Book b order by b.createdDate desc", Book.class)
                .getResultList();
    }

    // TODO : 카테고리 별로 책 조회하는 기능
    @Override
    public List<Book> findByCategoryBook(CategoryBook categoryBook) {
        return null;
    }

    @Override
    public List<Book> findBest10Books() {
        return em.createQuery("select b from Book b order by b.score desc", Book.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Book> findByMember(Long memberId) {
        return em.createQuery("select b from Book b inner join b.member m on m.id=:memberId", Book.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

}
