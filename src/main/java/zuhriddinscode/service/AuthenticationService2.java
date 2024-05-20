package zuhriddinscode.service;

import zuhriddinscode.domain.dto.request.PasswordResetRequest;
import zuhriddinscode.domain.dto.response.MessageResponse;

public interface AuthenticationService2 {

    MessageResponse sendPasswordResetCode(String email);

    String getEmailByPasswordResetCode(String code);

    MessageResponse resetPassword(PasswordResetRequest request);
}