package usedbookshop.soobook.repository.order;

import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findOne(Long orderId);

    List<Order> findByMember(Member member);
}
