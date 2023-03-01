package usedbookshop.soobook.domain.order.order.repository;

import usedbookshop.soobook.domain.order.order.entity.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(Long orderId);

    List<Order> findByMember(Long memberId);
}
