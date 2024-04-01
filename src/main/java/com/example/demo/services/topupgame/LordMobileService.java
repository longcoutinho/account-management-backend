package com.example.demo.services.topupgame;

import com.example.demo.dtos.topup.RequestTokenLordMobile;
import com.example.demo.dtos.topup.ResponseSendOTPLordMobile;
import com.example.demo.utils.constants.Constants;
import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LordMobileService {
    @Value("${lord-mobile.send-otp}")
    String lordMobileSendOtpUrl;

    public Object sendOtpLordMobile(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("game_id", "1051029902");
        params.put("user_id", id);
        String res = FnCommon.doGetRequest(lordMobileSendOtpUrl, null, params);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseSendOTPLordMobile responseSendOTPLordMobile = objectMapper.readValue(res, ResponseSendOTPLordMobile.class);
            String errorCode = responseSendOTPLordMobile.getError().getCode();
            if (errorCode.equals(Constants.ERROR_CODE_SEND_OTP.TOO_MANY_REQUEST)) {
                throw new CustomException(ErrorApp.TOO_MANY_REQUEST);
            }
            else if (errorCode.equals(Constants.ERROR_CODE_SEND_OTP.IGG_ID_INVALID)) {
                throw new CustomException(ErrorApp.IGG_INVALID_ID);
            }
            return 1L;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getAccessToken(String id, RequestTokenLordMobile req) {
        Map<String, String> params = new HashMap<>();
        params.put("game_id", "1051029902");
        params.put("user_id", id);
        String res = FnCommon.doGetRequest(lordMobileSendOtpUrl, null, params);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseSendOTPLordMobile responseSendOTPLordMobile = objectMapper.readValue(res, ResponseSendOTPLordMobile.class);
            String errorCode = responseSendOTPLordMobile.getError().getCode();
            if (errorCode.equals(Constants.ERROR_CODE_SEND_OTP.TOO_MANY_REQUEST)) {
                throw new CustomException(ErrorApp.TOO_MANY_REQUEST);
            }
            else if (errorCode.equals(Constants.ERROR_CODE_SEND_OTP.IGG_ID_INVALID)) {
                throw new CustomException(ErrorApp.IGG_INVALID_ID);
            }
            return 1L;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
