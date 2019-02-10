package com.aryzhkov.onlineshop.service;

import com.aryzhkov.onlineshop.entity.Session;
import com.aryzhkov.onlineshop.entity.User;
import com.aryzhkov.onlineshop.entity.UserType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SecurityService {
    private UserService userService;
    private List<Session> sessions;

    public SecurityService(UserService userService, List<Session> sessions) {
        this.userService = userService;
        this.sessions = sessions;
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

    public Session getByToken(String token) {
        for (Session session : sessions) {
            if (session.getToken().equals(token)) {
                return session;
            }
        }
        return null;
    }

    public boolean isSessionExpired(Session session) {
        if (session.getExpireDate().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    public boolean isSessionExists(Session session) {
        if (sessions.contains(session)) {
            return true;
        }
        return false;
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}