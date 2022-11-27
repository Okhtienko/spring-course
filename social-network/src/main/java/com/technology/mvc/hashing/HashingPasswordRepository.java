package com.technology.mvc.hashing;

public interface HashingPasswordRepository {
  String generateHash(String password);
  boolean verifyHash(String password, String hash);
}
