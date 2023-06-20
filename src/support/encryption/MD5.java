package support.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static String encryptWithMD5(String phrase) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] encrypted = md5.digest(phrase.getBytes());
			
			BigInteger inNumbers =new BigInteger(1,encrypted);
			
			String hashedPhrase = inNumbers.toString();
			
			while(hashedPhrase.length() < 32) {
				hashedPhrase = "0" + hashedPhrase;
			}
			
			return hashedPhrase;
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
