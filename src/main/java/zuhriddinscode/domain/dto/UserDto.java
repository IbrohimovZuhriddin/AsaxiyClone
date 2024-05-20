package zuhriddinscode.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import zuhriddinscode.domain.model.Role;
import zuhriddinscode.domain.model.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private Integer userid;
//    private String username;
    private String name;
    private String surname;
    private String contact;
    private String password;

    @NotNull
    @NotEmpty(message = "sdadasdasdasd")
    private String email;

    private Status status;
    private Role role;
    private LocalDateTime createdDate;

}