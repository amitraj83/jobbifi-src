package com.interview.beans;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class MockUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String userActualName;
	private String username;
	private String password;
	private String userRole;
	
	public MockUser(String username, String password, String userRole, String userActualName){
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.userActualName = userActualName;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		
		 Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();	     
		 if(userRole != null && userRole.equals("ADMIN")){
		    	authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));		    	
		 } else {
		    	authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		    	authorities.add(new GrantedAuthorityImpl("ROLE_" + userRole));
		 }
		return authorities;
	}

	public String getUserActualName() {
		return userActualName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}