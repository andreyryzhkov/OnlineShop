package com.aryzhkov.onlineshop.service;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.Assert.*;

public class SecurityServiceTest {

    @Test
    public void getSecurePassword() throws NoSuchProviderException, NoSuchAlgorithmException {
        String passwordToHash = "password";
        String salt = "salt";
        System.out.println(SecurityService.getSecurePassword(passwordToHash, salt));
    }
}

//[B@134b60d
//        67a1e09bb1f83f5007dc119c14d663aa