package zuhriddinscode.service;

import zuhriddinscode.domain.dto.request.UserRequest;
import zuhriddinscode.domain.dto.response.MessageResponse;

public interface RegistrationService {

    MessageResponse registration(String captchaResponse, UserRequest user);
    MessageResponse activateEmailCode(String code);

}