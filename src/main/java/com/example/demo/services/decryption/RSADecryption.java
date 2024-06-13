package com.example.demo.services.decryption;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class RSADecryption {
    public static String decryptCard(String encrypted) throws Exception {
        String privateKeyText = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCbGwGD3EEHK3FkzyWAWcSk3hr8fzMWaQdgzbZI7LejDcY1njcfC7K1CyL1TNNhDcDcPHJt9bno8oSUBlvEbeX5OdczyVaLjDinWg+i0zEI8f4ciTYLSPW1JozYQzwK9rMCrx0yNJWhZlw0/LNPNii3oN4b9wuL4xvKlLRwHulwi1/25yqNSI6iKn5it0DQ+Cz5SUPsNW+Bin/cghGuQHDni3+zH0nsShcD/6LI5rqafX4rHQGgriEwAIjdkHtBKqexDmU+7578sFtRHvA4jb3wDjIdWQmplebAtPN2X5wR0TRoQEEyPxB3P3rh1TZQvByo163jQJTyHDTCuwxXfYanAgMBAAECggEAFeWGHTtm4sA7TeQAg1Io9K12WdcLO0qwg/YGmdzxP8+d/8vKNFPOcsiTstWZscXjSDQwY12hVUWHee473ks1gC2uN91gnmq5RMbuFiqCbaTcHuMwAf/W0DAVCiLTwyh4tRRLpt7NkO+GbOSP28rljKblNEV4F9OEBOFhZ8K1afHK2fCx1jk7JhPXr/FoGBQVoR5VrmRem5LuFYqamBDUZpImvBX/U56QyFErMmqXHGPpyX+T0b9WsDE+Bnecb25s7ww/x6gabchOnAv1h1pWGYSwt7octOaxL0SAi/F0G6UvPXGwPRYHSZ1VaxmN0K5IptAE0trJAyIYKOCA92EZAQKBgQDOjASPIKgVbpCR2k3nuu7pWksPCmlRoHW90pkTQ1X+6hH3HOweNo797D6F8mT3CgWsxeMK/baXSz0660C0UKFuA0srEX5bK8s3k3EbGAqHp8r0Hbs9Xx3zt0mizVtwLrcxaPSND4J+gzmYBslNsjAmE/dQdSdIo/sEcn5ncu/rYQKBgQDAPfZQaf8Q2cINMGRBeGy3lEIV+4dbh2OD71QENbH8d0LuysCGFvcTJaVYgVhwQF0ViNPFPLkakFEiIDKy7uhJzA5Mxi6wc0u6XW2YYiYXS9Z+Zul9g9EZRwVGPnzrqbv3MUv9N7PtReh47I/sUhChVBxc6GjhOcoaphryXwR3BwKBgGmdEQu6sBfdZFEgJmvSmy15PR4gvb++Y9Y/ZISEDun8+Fi6UkYUscNHVKUbT+M+Ha69tR2+o+ny0Albl8db31+okXwhQtINr6joXJJ1FZVOCCoRNdfo1iDz3NRBW3seeA/s0fd7McfNA9snxTFUZuhhlzlt3jt3lfgtL1NoYyOhAoGAGsLFXg5TTmIxzCo3WpHL+IYv6OV5wzkGlmAjfu6Gl1F2xMz+nevmewjbioV+7SlYi93fNO6uCc+bK7f1tsjhFxzLxM41w6RZH1lQH01SZh3Nfw0OZcWKvw0O4+dg9mFLZC69mEM/zcDUirjLlCccX4jY5eoIZ4nm42zKBYFKmtMCgYBMJ4rCn+YaPQx2tk848UyeH7GUFdp+2J3RJPeOYM/93BziX1d9uL4wg6/sam8+4V0QfTjDRp8gOWk929Wv37NJDYJj3Qtc1+1EDEVFs4sE5N3Jm1fdLqZwKExjpRVAh/uirBltwLWxqmIJg4O2V6zXdFxGdrICBttf9bR4QlkLig==";
        // Đọc khóa riêng tư từ file
        PrivateKey privateKey = getPrivateKeyFromBase64(privateKeyText);

        // Giải mã tin nhắn
        byte[] decryptedBytes = decryptMessage(encrypted, privateKey);
        String decryptedMessage = new String(decryptedBytes);
        return decryptedMessage;
    }

    private static PrivateKey getPrivateKeyFromBase64(String base64String) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] bytes = Base64.decodeBase64(base64String);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    private static byte[] decryptMessage(String encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = Base64.decodeBase64(encryptedMessage);
        return cipher.doFinal(bytes);
    }
}