package com.example.demo.services.topupgame;

import com.example.demo.utils.constants.FnCommon;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GamotaService {
    @Value("${gamota.search-username}")
    String searchUrl;

    public String getUsernameById(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("paygate_id", "6621acd940d7aa5bfc7ba2c0");
        params.put("serverID", "1051169902");
        params.put("role", id);
        String res = FnCommon.doPostRequestWithCookie(searchUrl, params, null, "_gid=GA1.2.1123368069.1713482968; _gat=1; _fbp=fb.1.1713482967877.2078034002; _ga=GA1.1.2031674636.1712518082; PHPSESSID=84a03136a239304c54394176c32c5bce; _ga_VE1NT14GF5=GS1.1.1713482967.1.0.1713482969.58.0.0; _ga_S01HFLJJM6=GS1.1.1713482969.4.0.1713482974.0.0.0");
        if (res == null) throw new CustomException(ErrorApp.IGG_INVALID_ID);
        int index = res.indexOf("<option");
        if (index == -1) {
            throw new CustomException(ErrorApp.IGG_INVALID_ID);
        }
        int index2 = res.indexOf("</option>");
        String res2 = res.substring(index + 1, index2 + 1);
        index = res2.indexOf(">");
        index2 = res2.indexOf("<");
        String username = res2.substring(index + 1, index2 - 1);
        System.out.println(username);
        return username;
    }
}
