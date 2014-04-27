package com.agreeble.auth;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class PasswordGenerator
{

    public synchronized String encrypt(String plaintext) throws Exception
    {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA");
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new Exception(e.getMessage());
        }
        try
        {
            md.update(plaintext.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException e)
        {
            throw new Exception(e.getMessage());
        }

        String encoded = Base64.encodeBase64String(md.digest());
        return encoded;
    }

    public synchronized String decrypt(String encoded) throws Exception
    {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA");
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new Exception(e.getMessage());
        }
        try
        {
            md.update(encoded.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException e)
        {
            throw new Exception(e.getMessage());
        }

        String decoded = Base64.decodeBase64(md.digest()).toString();
        return decoded;
    }



    }