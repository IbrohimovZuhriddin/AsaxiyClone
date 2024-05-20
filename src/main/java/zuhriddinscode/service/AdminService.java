package zuhriddinscode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import zuhriddinscode.domain.dto.request.PerfumeRequest;
import zuhriddinscode.domain.dto.request.SearchRequest;
import zuhriddinscode.domain.dto.response.MessageResponse;
import zuhriddinscode.domain.dto.response.UserInfoResponse;
import zuhriddinscode.domain.model.Order;
import zuhriddinscode.domain.model.Perfume;
import zuhriddinscode.domain.model.UserEntity;

public interface AdminService {


    Page<Perfume> getPerfumes(Pageable pageable);

    Page<Perfume> searchPerfumes(SearchRequest request, Pageable pageable);

    Page<UserEntity> getUsers(Pageable pageable);

    Page<UserEntity> searchUsers(SearchRequest request, Pageable pageable);

    Order getOrder(Long orderId);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(SearchRequest request, Pageable pageable);

    Perfume getPerfumeById(Long perfumeId);

    MessageResponse editPerfume(PerfumeRequest perfumeRequest, MultipartFile file);

    MessageResponse addPerfume(PerfumeRequest perfumeRequest, MultipartFile file);

    UserInfoResponse getUserById(Long userId, Pageable pageable);
}