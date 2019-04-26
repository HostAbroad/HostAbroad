package com.presentation.loginUI;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;

import com.business.transfers.TUser;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

public class AuthService {
	private static final String EMAIL_ATTRIBUTE = "email";
	private static final String COOKIE_REMEMBERME_NAME = "remember-me";
	private static final String COOKIE_SESSION_NAME = "session";
	private static final String COOKIE_NICKNAME_NAME = "nickname";
	public static final int COOKIE_MAX_AGE_DAYS = 30;

    private static Map<String, String> remembered = new HashMap<>();
    private static SecureRandom random = new SecureRandom();
	
	public static boolean authenticate(TUser user, boolean rememberMe) {
		
        Pair<Integer, Object> userLoggedPair = Controller.getInstance().action(Commands.CommandLogin, user);
        TUser userLogged = (TUser)userLoggedPair.getRight();
        
        int isLogged = userLoggedPair.getLeft();
        
        if (isLogged == 1) {
        	String email = userLogged.getEmail();
            if (rememberMe) {
                rememberUser(email);
            }
            else {
            	rememberUserSession(email);
            }
            Pair<Integer, Object> userNickPair = Controller.getInstance().action(Commands.CommandReadUserNickName, userLogged);
            if(userNickPair.getLeft() == 1) {
            	 rememberNickName(((TUser)userNickPair.getRight()).getNickname());
            	 System.out.println("PRINTING IN AUTHSESSION");
            	 System.out.println(((TUser)userNickPair.getRight()).getNickname());
            }
        }

        return isLogged == 1 ? true : false;
    }
	
	public static boolean isAuthenticated() {
		boolean isAutenticated = false;
        if (VaadinSession.getCurrent().getAttribute(EMAIL_ATTRIBUTE) != null) {
            isAutenticated = true;
        }
         else if (isRememberedUser()) {
            String email = remembered.get(getCookie(COOKIE_REMEMBERME_NAME).get().getValue());
            VaadinSession.getCurrent().setAttribute(EMAIL_ATTRIBUTE, email);
            isAutenticated = true;
        }
         else if(isSessionOpen()) {
        	 String email = remembered.get(getCookie(COOKIE_SESSION_NAME).get().getValue());
             VaadinSession.getCurrent().setAttribute(EMAIL_ATTRIBUTE, email);
             isAutenticated = true;
         }

        return isAutenticated;
    }

	public static void logout() {
        removeRememberedUser();
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        Page.getCurrent().setLocation("HostAbroad");
    }

	public static String getAuthenticatedEmail() {
        return (String)VaadinSession.getCurrent().getAttribute(EMAIL_ATTRIBUTE);
    }
	
	public static String getUserNickName() {
		Optional<Cookie> cookieUserNick = getCookie(COOKIE_NICKNAME_NAME);
		String userNickName = "";
		if(cookieUserNick.isPresent()) {
			userNickName = remembered.get(cookieUserNick.get().getValue());
		}
		return userNickName;
	}
	
	public static boolean isRememberedUser() {
        Optional<Cookie> cookie = getCookie(COOKIE_REMEMBERME_NAME);
        return cookie.isPresent() && remembered.containsKey(cookie.get().getValue());
    }
	
	public static boolean isSessionOpen() {
		Optional<Cookie> cookie = getCookie(COOKIE_SESSION_NAME);
        return cookie.isPresent() && remembered.containsKey(cookie.get().getValue());
	}
	
	public static boolean isUserNickOpen() {
		Optional<Cookie> cookie = getCookie(COOKIE_NICKNAME_NAME);
        return cookie.isPresent() && remembered.containsKey(cookie.get().getValue());
	}
	
	private static void removeRememberedUser() {
        Optional<Cookie> cookieRemember = getCookie(COOKIE_REMEMBERME_NAME);
        Optional<Cookie> cookieSession = getCookie(COOKIE_SESSION_NAME);
        Optional<Cookie> cookieUserNick = getCookie(COOKIE_NICKNAME_NAME);
        
        if (cookieRemember.isPresent()) {
            remembered.remove(cookieRemember.get().getValue());
            saveCookie(COOKIE_REMEMBERME_NAME,"", 0);
        }
        if(cookieSession.isPresent()) {
        	remembered.remove(cookieSession.get().getValue());
            saveCookie(COOKIE_SESSION_NAME, "",0);
        }
        if(cookieUserNick.isPresent()) {
        	remembered.remove(cookieUserNick.get().getValue());
        	saveCookie(COOKIE_NICKNAME_NAME, "", 0);
        }
    }
	
	private static void rememberUser(String email) {
        String randomKey = new BigInteger(130, random).toString(32);
        remembered.put(randomKey, email);
        saveCookie(COOKIE_REMEMBERME_NAME,randomKey, 60 * 60 * 24 * COOKIE_MAX_AGE_DAYS);
    }
	
	private static void rememberUserSession(String email) {
        String randomKey = new BigInteger(130, random).toString(32);
        remembered.put(randomKey, email);
        //The session is for 24 hours
        saveCookie(COOKIE_SESSION_NAME,randomKey, 60 * 60 * 24);
    }
	
	private static void rememberNickName(String username) {
        String randomKey = new BigInteger(130, random).toString(32);
        remembered.put(randomKey, username);
        saveCookie(COOKIE_NICKNAME_NAME,randomKey, 60 * 60 * 24 * COOKIE_MAX_AGE_DAYS);
    }

    private static void saveCookie(String cookieName, String value, int age) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    private static Optional<Cookie> getCookie(String cookie) {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        return Arrays.stream(cookies)
                .filter(c -> cookie.equals(c.getName()))
                .findFirst();
    }
}
