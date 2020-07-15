import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/* @author Manal Irfan Khalil, Reem Al Mulla, Ahmed AbdelAziz, Saeed Ghafli
 * Description : A class to Encrypt and Decrypt messages from chatting clients
 * 4th April, 2019
 * @Version 1.4
 */
 
public class EncryptDecryptUtil {
    private static final String key = "gZkOKcQpeympHMOHaMRKZHaB";
    private static final String paramSpec = "hJQwsFSoJNKmEUYZ";

    /**
     * A method to encrypt a string
     * @param input
     * @return
     */
    public static String encrypt(String input){
        try{
            IvParameterSpec ivParameterSpec = new IvParameterSpec(paramSpec.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedString = cipher.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(encryptedString);
        }catch (Exception unExp){
            System.out.println(unExp.getMessage());
        }
        return null;
    }

    /**
     * A method to decrypt a string
     * @param input
     * @return
     */
    public static String decrypt(String input){
        try{
            IvParameterSpec ivParameterSpec = new IvParameterSpec(paramSpec.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(input));

            return new String(decrypted);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
