/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import static cryptomasters.FileEncryption.key;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author kdonahoe
 */

public class FileDecryption {
    
    static Cipher aesCipher;
    static SecretKey key;
    
    public FileDecryption() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, DecoderException{
        key = ManageSecretKey.retrieveKey("/Users/kdonahoe/Desktop/KeyStore_File/passwords.txt");
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, key);
        
    }
    
    public static String decryptFile(String fileToDecryptPath) throws IOException, IllegalBlockSizeException, BadPaddingException{
        
        byte[] cipherText = Files.readAllBytes(Paths.get(fileToDecryptPath));
        byte[] decryptedText = aesCipher.doFinal(cipherText);
        
       String decryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/decryptedFiles/decryptedFile.docx";
       FileUtils.writeByteArrayToFile(new File(decryptedFilePath), decryptedText);

       return decryptedFilePath;                     
    }
    
    
}
