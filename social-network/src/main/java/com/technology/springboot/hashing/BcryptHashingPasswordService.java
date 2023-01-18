package com.technology.springboot.hashing;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BcryptHashingPasswordService implements BcryptHashingPassword {

  private final int logRounds;

  public BcryptHashingPasswordService(@Value("${logRounds}") int logRounds) {
    this.logRounds = logRounds;
  }

  @Override
  public String generateHash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
  }

  @Override
  public boolean verifyHash(String password, String hash) {
    return BCrypt.checkpw(password, hash);
  }

}
