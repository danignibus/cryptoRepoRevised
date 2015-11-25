/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;




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
       
       byte[] clearText = Files.readAllBytes(Paths.get(fileToEncryptPath));
       String clear = new String(clearText);
       System.out.println("clear: " +clear);

       byte[] cipherText = aesCipher.doFinal(clearText);
       String cipher = new String(cipherText);
       System.out.println("cipher: " + cipher);
       
       String encryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/encryptedFiles/encryptedFile.docx";
       
       FileOutputStream fileOS = new FileOutputStream(encryptedFilePath);
       fileOS.write(cipherText);
       fileOS.close();
       return encryptedFilePath;
   }
    
    
}
