package zuhriddinscode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zuhriddinscode.UserPrincipal;
import zuhriddinscode.domain.dto.request.ChangePasswordRequest;
import zuhriddinscode.domain.dto.request.EditUserRequest;
import zuhriddinscode.domain.dto.request.SearchRequest;
import zuhriddinscode.domain.dto.response.MessageResponse;
import zuhriddinscode.domain.model.ErrorMessage;
import zuhriddinscode.domain.model.Order;
import zuhriddinscode.domain.model.SuccessMessage;
import zuhriddinscode.domain.model.UserEntity;
import zuhriddinscode.repository.OrderRepository;
import zuhriddinscode.repository.UserRepository;
import zuhriddinscode.service.UserService2;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService2 {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getAuthenticatedUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername());
    }


    @Override
    public Page<Order> searchUserOrders(SearchRequest request, Pageable pageable) {
        UserEntity user = getAuthenticatedUser();
        return orderRepository.searchUserOrders(user.getUserid(), request.getSearchType(), request.getText(), pageable);
    }

    @Override
    @Transactional
    public MessageResponse editUserInfo(EditUserRequest request) {
        UserEntity user = getAuthenticatedUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCity(request.getCity());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPostIndex(request.getPostIndex());
        return new MessageResponse("alert-success", SuccessMessage.USER_UPDATED);
    }

    @Override
    @Transactional
    public MessageResponse changePassword(ChangePasswordRequest request) {
        if (request.getPassword() != null && !request.getPassword().equals(request.getPassword2())) {
            return new MessageResponse("passwordError", ErrorMessage.PASSWORDS_DO_NOT_MATCH);
        }
        UserEntity user = getAuthenticatedUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return new MessageResponse("alert-success", SuccessMessage.PASSWORD_CHANGED);
    }
}