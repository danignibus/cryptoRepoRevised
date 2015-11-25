/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author kdonahoe
 */
public class FileEncryption {
    
    Cipher aesCipher;
    
    
    public FileEncryption(SecretKey aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
        aesCipher = Cipher.getInstance("AES/CBC?PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
    }
            
            
   public static void encryptFile(){    
   }
    
    
}
