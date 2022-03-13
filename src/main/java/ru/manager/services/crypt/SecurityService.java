package ru.manager.services.crypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * Класс для шифрования паролей пользователей и проверки их паролей.
 */
public class SecurityService implements ISecurityService {

    private static final int ITERATION = 1000;
    private static final int KEY_LENGTH = 300;
    private static final int RADIX = 16;

    private SecretKeyFactory keyFactory;
    private SecureRandom random;

    public SecurityService() {
        try {
            this.keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            this.random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод шифрует пароль пользователя.
     * @param password не шифрованый пароль.
     * @return шифрованый пароль.
     */
    @Override
    public String encrypt(String password) {

        var bytesPassword = new byte[RADIX];
        random.nextBytes(bytesPassword);

        try {
            var spec = new PBEKeySpec(password.toCharArray(), bytesPassword, ITERATION, KEY_LENGTH);
            var encoded = keyFactory.generateSecret(spec).getEncoded();

            var salt = new BigInteger(1, bytesPassword).toString(RADIX);
            var hash = new BigInteger(1, encoded).toString(RADIX);

            return String.format("%s:%s", salt, hash);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для проверки пароля при авторизации.
     * @param checkPassword присланый пароль.
     * @param originalPassword оригинальный пароль.
     * @return true если пароли совпадают, false если пароли не совпадают.
     */
    @Override
    public boolean isMatchPassword(String checkPassword, String originalPassword) {

        var part = originalPassword.split(":");
        var salt = fromByte(part[0]);
        var hash = fromByte(part[1]);

        try {
            var spec = new PBEKeySpec(checkPassword.toCharArray(), salt, ITERATION, KEY_LENGTH);
            var testHash = keyFactory.generateSecret(spec).getEncoded();
            return Arrays.compare(hash, testHash) == 0;
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Метод для преобразования строки в байты.
     * @param str строка для преобразования байтов.
     * @return массива байтов.
     */
    private static byte[] fromByte(String str) {
        var bytes = new byte[str.length() / 2];
        for (var i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2), RADIX);
        }
        return bytes;
    }

}
