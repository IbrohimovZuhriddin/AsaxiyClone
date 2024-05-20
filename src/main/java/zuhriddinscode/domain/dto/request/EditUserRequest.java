package zuhriddinscode.domain.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import zuhriddinscode.domain.model.ErrorMessage;

@Data
public class EditUserRequest {

    @NotBlank(message = ErrorMessage.EMPTY_FIRST_NAME)
    private String firstName;

    @NotBlank(message = ErrorMessage.EMPTY_LAST_NAME)
    private String lastName;

    private String city;
    private String address;
    private String phoneNumber;
    private String postIndex;
    private String email;

}