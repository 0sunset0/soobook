package usedbookshop.soobook.repository.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Order findById(Long orderId) {
        return em.find(Order.class, orderId);
    }

    @Override
    public List<Order> findByMember(Member member) {
        return em.createQuery("select o from Order o inner join o.member m on m.id=:memberId", Order.class)
                .setParameter("memberId", member.getId())
                .getResultList();
    }
}
