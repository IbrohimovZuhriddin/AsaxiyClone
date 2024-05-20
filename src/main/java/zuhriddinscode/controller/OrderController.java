package zuhriddinscode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zuhriddinscode.domain.dto.request.OrderRequest;
import zuhriddinscode.domain.model.Pages;
import zuhriddinscode.domain.model.PathConstants;
import zuhriddinscode.domain.model.UserEntity;
import zuhriddinscode.service.OrderService;
import zuhriddinscode.service.UserService2;
import zuhriddinscode.utils.ControllerUtils;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.ORDER)
public class OrderController {


    private final OrderService orderService;
    private final UserService2 userService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping
    public String getOrdering(Model model) {
        model.addAttribute("perfumes", orderService.getOrdering());
        return Pages.ORDERING;
    }

    @GetMapping("/user/orders")
    public String getUserOrdersList(Model model, Pageable pageable) {
        controllerUtils.addPagination(model, orderService.getUserOrdersList(pageable));
        return Pages.ORDERS;
    }

    @PostMapping
    public String postOrder(@Valid OrderRequest orderRequest,
                            BindingResult bindingResult, Model model) {
        UserEntity user = userService.getAuthenticatedUser();
        if (controllerUtils
                .validateInputFields(bindingResult, model,
                        "perfumes", user.getPerfumeList())) {
            return Pages.ORDERING;
        }
        model.addAttribute("orderId",
                orderService.postOrder(user, orderRequest));
        return Pages.ORDER_FINALIZE;
    }
}