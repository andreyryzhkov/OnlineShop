package com.aryzhkov.onlineshop.service;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.Assert.*;

public class SecurityServiceTest {

    @Test
    public void getSecurePassword() throws NoSuchProviderException, NoSuchAlgorithmException {
        String passwordToHash = "user10";
        String salt = "salt10";
        System.out.println(SecurityService.getSecurePassword(passwordToHash, salt.getBytes()));
    }
}