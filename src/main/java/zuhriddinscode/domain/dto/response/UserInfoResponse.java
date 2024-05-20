package zuhriddinscode.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import zuhriddinscode.domain.model.Order;
import zuhriddinscode.domain.model.UserEntity;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private UserEntity user;
    private Page<Order> orders;
}
