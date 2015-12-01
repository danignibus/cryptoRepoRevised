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
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import static org.apache.commons.codec.binary.Hex.encodeHex;
import org.apache.commons.io.FileUtils;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import org.apache.commons.io.IOUtils;




/**
 *
 * @author kdonahoe
 */
public class FileEncryption { 
    static Cipher aesCipher;
    static SecretKey key; 
    final int AES_KEYLENGTH = 128;
    
    public FileEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, Exception{
//        key = ManageSecretKey.retrieveKey("/Users/kdonahoe/Desktop/KeyStore_File/passwords.txt");
        String password = "kev";
        key = ManageSecretKey.makeAndStore(password);
        
        SecureRandom prng = new SecureRandom();
        byte[] iv = new byte[AES_KEYLENGTH / 8];
        prng.nextBytes(iv);
       
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        aesCipher.init(Cipher.ENCRYPT_MODE, key, ivParam); 
       
    }
                    
   public static String encryptFile(String fileToEncryptPath) throws IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, Exception{   
      
       File fileToEncrypt = new File(fileToEncryptPath);
       FileInputStream inputStream = new FileInputStream(fileToEncrypt);
       byte[] clearTextBytes = new byte[(int) fileToEncrypt.length()];
       inputStream.read(clearTextBytes);
       
       Scanner input = new Scanner(fileToEncrypt);
       while(input.hasNext()){
           System.out.println("line: " +input.next());
       }
       input.close();

//       Set<Charset> newset = possibleCharsets(clearTextBytes);
//      new
//       
//       String str = new String(clearTextBytes, (Charset) newset);
//       System.out.println("set: " + str);
       
       byte[] cipherTextBytes = aesCipher.doFinal(clearTextBytes);
              
       String encryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/encryptedFiles/encryptedFile1.txt";
       
       FileOutputStream outputStream = new FileOutputStream(encryptedFilePath);
       outputStream.write(cipherTextBytes);
       inputStream.close();
       outputStream.close();
       
               
               
//        byte[] clearText = Files.readAllBytes(Paths.get(fileToEncryptPath));
//        
//        byte[] cipherText = aesCipher.doFinal(clearText);
//       
//        String encryptedFilePath = "/Users/kdonahoe/Desktop/Crypto/encryptedFiles/encryptedFile.docx";
//
//       FileUtils.writeByteArrayToFile(new File(encryptedFilePath), cipherText);

       
       
       
       //IF WANT TO CHECK DECRYPTION RIGHT AFTER ENCRYPTION - WAS WORKING EARLIER
//        aesCipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] tempClearTextDec = aesCipher.doFinal(cipherText);
//        String tempDecrypted = new String(tempClearTextDec, "UTF-8");
//        
//        FileUtils.writeByteArrayToFile(new File("/Users/kdonahoe/Desktop/Crypto/DecryptedFiles/file"), tempClearTextDec);
                
        return encryptedFilePath;
   }
    
   public static Set<Charset> possibleCharsets(byte[] bytes) {
    Set<Charset> charsets = new LinkedHashSet<>();
    for (Charset charset : Charset.availableCharsets().values()) {
        if (!new String(bytes, charset).contains("�"))
            charsets.add(charset);
    }
    return charsets;
} 
   
   
}
