package zuhriddinscode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zuhriddinscode.domain.dto.request.OrderRequest;
import zuhriddinscode.domain.model.Order;
import zuhriddinscode.domain.model.Perfume;
import zuhriddinscode.domain.model.UserEntity;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Perfume> getOrdering();

    Page<Order> getUserOrdersList(Pageable pageable);

    Long postOrder(UserEntity user, OrderRequest orderRequest);
}
