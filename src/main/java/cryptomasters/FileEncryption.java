/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import java.io.DataInputStream;
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
import javax.crypto.spec.SecretKeySpec;
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
        
        byte[] aesKeyEnc = key.getEncoded();
        SecretKey aeskeySpec = new SecretKeySpec(aesKeyEnc, "AES");
               
        String homeDir = System.getProperty("user.home");
        final String ivStoreFile = homeDir+"/Desktop/Crypto/ivSpec.txt";

        byte[] ivData = new byte[AES_KEYLENGTH /8];
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(ivStoreFile)));
        dis.readFully(ivData);
        if(dis != null){
            dis.close();
        }

        IvParameterSpec ivParam = new IvParameterSpec(ivData);
        
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        aesCipher.init(Cipher.ENCRYPT_MODE, aeskeySpec, ivParam); 
       
    }
                    
   public static String encryptFile(String fileToEncryptPath) throws IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, Exception{   
      
       File fileToEncrypt = new File(fileToEncryptPath);
       FileInputStream inputStream = new FileInputStream(fileToEncrypt);
       byte[] clearTextBytes = new byte[(int) fileToEncrypt.length()];
       inputStream.read(clearTextBytes);

       
       byte[] cipherTextBytes = aesCipher.doFinal(clearTextBytes);
              
       String homeDir = System.getProperty("user.home");
       String encryptedFilePath = homeDir+"/Desktop/Crypto/encryptedFiles/encryptedFile1.txt";
       
       FileOutputStream outputStream = new FileOutputStream(encryptedFilePath);
       outputStream.write(cipherTextBytes);
       inputStream.close();
       outputStream.close();

       return encryptedFilePath;
   }

   
}
