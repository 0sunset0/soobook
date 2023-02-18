package usedbookshop.soobook.order.order.repository;

import usedbookshop.soobook.order.order.domain.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(Long orderId);

    List<Order> findByMember(Long memberId);
}
