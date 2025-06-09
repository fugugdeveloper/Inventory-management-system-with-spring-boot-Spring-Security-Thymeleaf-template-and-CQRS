package com.ktun.inventory_management_system.service;

import com.ktun.inventory_management_system.config.CustomUserDetails;
import com.ktun.inventory_management_system.model.User;
import com.ktun.inventory_management_system.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user =	userQueryService.getByUsername(userName);


        if(user == null)
        {
            throw new UsernameNotFoundException("User not Found !!");
        }
        else {
            return new CustomUserDetails(user);
        }

    }


}
