package zuhriddinscode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zuhriddinscode.domain.dto.request.ChangePasswordRequest;
import zuhriddinscode.domain.dto.request.EditUserRequest;
import zuhriddinscode.domain.dto.request.SearchRequest;
import zuhriddinscode.domain.dto.response.MessageResponse;
import zuhriddinscode.domain.model.Order;
import zuhriddinscode.domain.model.UserEntity;

public interface UserService2 {

    UserEntity getAuthenticatedUser();

    Page<Order> searchUserOrders(SearchRequest request, Pageable pageable);

    MessageResponse editUserInfo(EditUserRequest request);

    MessageResponse changePassword(ChangePasswordRequest request);
}
