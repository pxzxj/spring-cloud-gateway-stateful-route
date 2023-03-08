package io.github.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    private final Map<String, String> map = new HashMap<>();

    @RequestMapping("/get")
    public String get() {
        return map.get("key");
    }

    @RequestMapping("/set/{value}")
    public void set(@PathVariable String value, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("sc-lb-instance-id".equals(cookie.getName())) {
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        map.put("key", value);
    }
}
