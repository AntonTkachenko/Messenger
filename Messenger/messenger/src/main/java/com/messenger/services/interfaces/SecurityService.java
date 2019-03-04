package com.messenger.services.interfaces;

public interface SecurityService {

	public String findLoggedInPhone();

	public void autoLogin(String phone, String password);
}
