package com.crypto.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {

	private static final String AES_BLOCK_MODE = "AES/ECB/PKCS5Padding";
	private static final String AES = "AES";
	private static String secret;
	private static final String salt = "random12345";

	public static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), AES);
		return secret;
	}

	public static String encrypt(String input)
			throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException {
		byte[] crypted = null;

		SecretKey key = getKeyFromPassword(secret, salt);
		Cipher aesCipher = Cipher.getInstance(AES_BLOCK_MODE);
		aesCipher.init(Cipher.ENCRYPT_MODE, key);
		crypted = aesCipher.doFinal(input.getBytes());
		Encoder encoder = Base64.getEncoder();
		String encrypted = encoder.encodeToString(crypted);
		return encrypted;
	}

	public static String decrypt(String input)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, InvalidKeySpecException, UnsupportedEncodingException {
		byte[] output = null;
		Decoder decoder = Base64.getDecoder();
		SecretKey key = getKeyFromPassword(secret, salt);
		Cipher aesCipher = Cipher.getInstance(AES_BLOCK_MODE);
		aesCipher.init(Cipher.DECRYPT_MODE, key);
		output = aesCipher.doFinal(decoder.decode(input));

		return new String(output);
	}

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		CryptoUtil.secret = secret;
	}

}
