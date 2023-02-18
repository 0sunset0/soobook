package usedbookshop.soobook.order.repository;

import usedbookshop.soobook.order.domain.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(Long orderId);

    List<Order> findByMember(Long memberId);
}
