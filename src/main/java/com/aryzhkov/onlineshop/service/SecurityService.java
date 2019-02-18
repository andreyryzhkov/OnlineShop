package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SecurityService {
    private UserService userService;
    private List<Session> sessions = Collections.synchronizedList(new ArrayList<>());

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public Session login(String login, String password) {
        User user = userService.getUserByName(login);
        String securePassword = getSecurePassword(password, user.getSalt());

        if (securePassword.equals(user.getPassword())) {
            Session session = new Session();
            String token = UUID.randomUUID().toString();
            session.setToken(token);
            session.setUser(user);
            session.setExpireDate(LocalDateTime.now().plusHours(2));
            sessions.add(session);
            return session;
        }
        return null;
    }

    public Session getSession(String token) {
        for (Session session : sessions) {
            if (session.getToken().equals(token)) {
                if (isSessionExpired(session)) {
                    removeSession(session);
                    return null;
                }
                return session;
            }
        }
        return null;
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    private boolean isSessionExpired(Session session) {
        return !session.getExpireDate().isAfter(LocalDateTime.now());
    }

    public static String getSecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        byte[] saltBytes = salt.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(saltBytes);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}