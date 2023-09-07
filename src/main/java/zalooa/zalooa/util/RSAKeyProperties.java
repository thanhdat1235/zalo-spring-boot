package zalooa.zalooa.util;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RSAKeyProperties {

  private RSAPublicKey publicKey;
  private RSAPrivateKey privateKey;

  public RSAKeyProperties() {
    KeyPair pair = KeyGenerationUtility.generateRsaKey();
    this.publicKey = (RSAPublicKey) pair.getPublic();
    this.privateKey = (RSAPrivateKey) pair.getPrivate();
  }

  public void setPublicKey(RSAPublicKey publicKey) {
    this.publicKey = publicKey;
  }

  public void setPrivateKey(RSAPrivateKey privateKey) {
    this.privateKey = privateKey;
  }

}