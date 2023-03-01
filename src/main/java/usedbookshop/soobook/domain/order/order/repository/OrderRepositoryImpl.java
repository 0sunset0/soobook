package usedbookshop.soobook.domain.order.order.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import usedbookshop.soobook.domain.order.order.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
class OrderRepositoryImpl implements OrderRepository{
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
    public List<Order> findByMember(Long memberId) {
        return em.createQuery("select o from Order o inner join o.member m on m.id=:memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
