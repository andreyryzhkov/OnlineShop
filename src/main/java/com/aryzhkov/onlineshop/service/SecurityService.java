package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;

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

        if (password.equals(user.getPassword())) {
            if (UserType.getByName(user.getUserType()) == UserType.ADMIN) {
                Session session = new Session();
                String token = UUID.randomUUID().toString();
                session.setToken(token);
                session.setUser(user);
                session.setExpireDate(LocalDateTime.now().plusHours(2));
                sessions.add(session);
                return session;
            }
        }
        return null;
    }

    public Session getSession(String token) {
        for (Session session : sessions) {
            if (session.getToken().equals(token)) {
                if (isSessionExpired(session)) {
                    sessions.remove(session);
                    return null;
                }
                return session;
            }
        }
        return null;
    }

    public boolean isSessionExpired(Session session) {
        if (session.getExpireDate().isAfter(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}