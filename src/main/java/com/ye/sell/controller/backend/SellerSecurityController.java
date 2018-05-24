package com.ye.sell.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerSecurityController {

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String e) {
        if (e != null) {
            if (e.equals("true")) {
                Map<String, String> map = new HashMap<>();
                map.put("msg", "用户名或密码错误");
                map.put("code", "error");

                return new ModelAndView("security/login", map);
            }
        }

        return new ModelAndView("security/login");
    }
}
