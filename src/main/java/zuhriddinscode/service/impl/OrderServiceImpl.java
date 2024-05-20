package zuhriddinscode.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import zuhriddinscode.domain.dto.request.OrderRequest;
import zuhriddinscode.domain.model.ErrorMessage;
import zuhriddinscode.domain.model.Order;
import zuhriddinscode.domain.model.Perfume;
import zuhriddinscode.domain.model.UserEntity;
import zuhriddinscode.repository.OrderRepository;
import zuhriddinscode.service.MailService;
import zuhriddinscode.service.OrderService;
import zuhriddinscode.service.UserService2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService2 userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    @Override
    public Order getOrder(Long orderId) {
        UserEntity user = userService.getAuthenticatedUser();
        return orderRepository.getByIdAndUserId(orderId, user.getUserid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Perfume> getOrdering() {
        UserEntity user = userService.getAuthenticatedUser();
        return user.getPerfumeList();
    }

    @Override
    public Page<Order> getUserOrdersList(Pageable pageable) {
        UserEntity user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getUserid(), pageable);
    }

    @Override
    @Transactional
    public Long postOrder(UserEntity user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getPerfumes().addAll(user.getPerfumeList());
        orderRepository.save(order);
        user.getPerfumeList().clear();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        mailService.sendMessageHtml(order.getEmail(), "Order #" + order.getId(), "order-template", attributes);
        return order.getId();
    }
}
