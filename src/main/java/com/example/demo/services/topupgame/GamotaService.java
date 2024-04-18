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
        params.put("paygate_id", "66215974d01c4a2c4e42c654");
        params.put("serverID", "1051169902");
        params.put("role", id);
        String res = FnCommon.doPostRequestWithCookie(searchUrl, params, null, "_ga=GA1.1.2031674636.1712518082; PHPSESSID=1211a17d61c69e21a3218cbed4d1d310; _ga_S01HFLJJM6=GS1.1.1713461269.3.1.1713461623.0.0.0");
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
