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
        params.put("paygate_id", "6612f4675fc53262f422ce03");
        params.put("serverID", "1051169902");
        params.put("role", id);
        String res = FnCommon.doPostRequestWithCookie(searchUrl, params, null, "PHPSESSID=13709b01c4b7c6bb3882254474b9139a; _ga=GA1.1.2031674636.1712518082; _ga_S01HFLJJM6=GS1.1.1712518081.1.1.1712518252.0.0.0");
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
