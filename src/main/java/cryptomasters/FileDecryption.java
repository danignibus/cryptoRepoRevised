/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import static cryptomasters.FileEncryption.key;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author kdonahoe
 */

public class FileDecryption {
    
    static Cipher aesCipherDec;
    static SecretKey key;
    final int AES_KEYLENGTH = 128;
    
    public FileDecryption() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, DecoderException, UnrecoverableEntryException, KeyStoreException, ClassNotFoundException, InvalidAlgorithmParameterException{
        key = ManageSecretKey.retrieveKey("/Users/kdonahoe/Desktop/KeyStore_File/passwords.txt");
     
        aesCipherDec = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        
        SecureRandom prng = new SecureRandom();
        byte[] iv = new byte[AES_KEYLENGTH / 8];
        prng.nextBytes(iv);
      
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        
        aesCipherDec.init(Cipher.DECRYPT_MODE, key, ivParam);
        
//         key = ManageSecretKey.retrieveKey("/Users/kdonahoe/Desktop/KeyStore_File/passwords.txt");
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//        aesCipher = Cipher.getInstance("AES/CBC/NoPadding");
//
//        byte[] iv = new byte[aesCipher.getBlockSize()];
//        random.nextBytes(iv);
//        IvParameterSpec ivSpec = new IvParameterSpec(iv);
//        aesCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
//        
        
    }
    
    public static String decryptFile(String fileToDecryptPath) throws IOException, IllegalBlockSizeException, BadPaddingException{
        
       File fileToDecrypt = new File(fileToDecryptPath);
       
       FileInputStream inputStream = new FileInputStream(fileToDecrypt);
       byte[] cipherTextBytes = new byte[(int) fileToDecrypt.length()];
       inputStream.read(cipherTextBytes);
       
       byte[] clearTextBytes = aesCipherDec.doFinal(cipherTextBytes);
       
       String decryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/decryptedFiles/decryptedFile1.txt";
       
       FileOutputStream outputStream = new FileOutputStream(decryptedFilePath);
       outputStream.write(clearTextBytes);
       inputStream.close();
       outputStream.close();
        
       
        
        
//        byte[] cipherText = Files.readAllBytes(Paths.get(fileToDecryptPath));
//        byte[] decryptedText = aesCipherDec.doFinal(cipherText);
//        
//       String decryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/decryptedFiles/decryptedFile.docx";
//       FileUtils.writeByteArrayToFile(new File(decryptedFilePath), decryptedText);

       return decryptedFilePath;                     
    }
    
    
}
