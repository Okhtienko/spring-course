package com.technology.mvc.hashing;

public interface BcryptHashingPassword {
  String generateHash(String password);
  boolean verifyHash(String password, String hash);
}
