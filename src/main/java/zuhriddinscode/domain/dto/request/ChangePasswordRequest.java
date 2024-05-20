package zuhriddinscode.domain.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import zuhriddinscode.domain.model.ErrorMessage;

@Data
public class ChangePasswordRequest {


    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD2_CHARACTER_LENGTH)
    private String password2;

}