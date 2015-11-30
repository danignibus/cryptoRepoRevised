/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;




/**
 *
 * @author kdonahoe
 */
public class FileEncryption { 
    static Cipher aesCipher;
    static SecretKey key; 
    
    public FileEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, Exception{
        key = ManageSecretKey.retrieveKey("/Users/kdonahoe/Desktop/KeyStore_File/passwords.txt");

        String password = "kev";
//        key = ManageSecretKey.makeAndStore(password);
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, key); 
       
    }
                    
   public static String encryptFile(String fileToEncryptPath) throws IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, Exception{   
        String password = "kev"; 
        key = ManageSecretKey.makeAndStore(password);
       
        aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] clearText = Files.readAllBytes(Paths.get(fileToEncryptPath));
        
        byte[] cipherText = aesCipher.doFinal(clearText);
       
       String encryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/encryptedFiles/encryptedFile.docx";
       
       FileUtils.writeByteArrayToFile(new File(encryptedFilePath), cipherText);

//        aesCipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] tempClearTextDec = aesCipher.doFinal(cipherText);
//        String tempDecrypted = new String(tempClearTextDec, "UTF-8");
//        
//        FileUtils.writeByteArrayToFile(new File("/Users/kdonahoe/Desktop/Crypto/DecryptedFiles/file"), tempClearTextDec);
                
        return encryptedFilePath;
   }
    
    
}
