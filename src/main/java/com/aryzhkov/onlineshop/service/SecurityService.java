package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;

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

    public User newUser(String login, String password, String usertype) {
        byte[] salt = getSalt();
        String securePassword = SecurityService.getSecurePassword(password, salt);
        User user = new User(login, securePassword, UserType.getByName(usertype), "salt15", salt);
        userService.addUser(user);
        return user;
    }

    public User login(String login, String password) {
        User user = userService.getUserByName(login);
        String securePassword = getSecurePassword(password, user.getSaltBytes());

        if (securePassword.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public Session getSession(User user) {
        Session session = new Session();
        String token = UUID.randomUUID().toString();
        session.setToken(token);
        session.setUser(user);
        session.setExpireDate(LocalDateTime.now().plusHours(2));
        sessions.add(session);
        return session;
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

    public static String getSecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        //   byte[] saltBytes = salt.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
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

    public static byte[] getSalt() {
        //Always use a SecureRandom generator
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            //Create array for salt
            byte[] salt = new byte[16];
            //Get a random salt
            sr.nextBytes(salt);
            //return salt
            return salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}