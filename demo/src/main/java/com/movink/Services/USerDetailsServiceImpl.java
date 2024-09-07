package com.movink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movink.demo.Entity.Movink_user;
import com.movink.demo.Reposetory.userReposetory;

@Service
public class USerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userReposetory userreposetory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Movink_user user1 = userreposetory.findByusername(username);
        CustomuserDetails customuserDetails = new CustomuserDetails(user1);

        return customuserDetails;

    }


}