package com.flightsecurity.config;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flightsecurity.entity.UserCredentials;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails{
	
	private String userName;
	private String password;
	private String role;
	
	

	public CustomUserDetails(UserCredentials userCredentials) {
		super();
		this.userName = userCredentials.getUserName();
		this.password = userCredentials.getPassword();
		this.role=userCredentials.getRole();
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of(new SimpleGrantedAuthority(this.role));
	}
	
	
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
