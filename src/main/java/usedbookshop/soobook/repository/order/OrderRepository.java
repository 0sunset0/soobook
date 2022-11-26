package usedbookshop.soobook.repository.order;

import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(Long orderId);

    List<Order> findByMember(Long memberId);
}
