package com.ye.sell.security;

import com.ye.sell.exception.SellException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        boolean accountNotLocked = true;

        if (!s.equals("admin")){
            throw new SellException("用户名或密码不正确");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new org.springframework.security.core.userdetails.User(s, "$2a$10$mBw20WdYfC/imdpZKX4kQeoETrRk9rQ1DWKW7FiGsxLAIbYSH6L7a", true,
                true, true, accountNotLocked, authorities);
    }
}
