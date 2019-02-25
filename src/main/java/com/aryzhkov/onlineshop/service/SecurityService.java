package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;
    private List<Session> sessions = Collections.synchronizedList(new ArrayList<>());

    public User newUser(String login, String password, UserType usertype) {
        byte[] salt = getSalt();
        byte[] securePassword = SecurityService.getSecurePassword(password, salt);
        User user = new User(login, securePassword, usertype, salt);
        userService.addUser(user);

        return user;
    }

    public User login(String login, String password) {
        User user = userService.getUserByName(login);
        byte[] securePassword = getSecurePassword(password, user.getSalt());

        if (Arrays.equals(securePassword, user.getPassword())) {
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

    private static byte[] getSecurePassword(String passwordToHash, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] generatedPassword = md.digest(passwordToHash.getBytes());

            return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);

            return salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}