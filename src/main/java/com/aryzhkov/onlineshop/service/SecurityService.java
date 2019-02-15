package com.aryzhkov.onlineshop.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(saltBytes);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}