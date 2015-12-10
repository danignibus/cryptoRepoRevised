/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import static cryptomasters.DownloadFileGUI.request;
import static cryptomasters.FileEncryption.key;
import java.io.DataInputStream;
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
        
        String homeDir = System.getProperty("user.home");
        final String keyStoreFile = homeDir+"/Desktop/Crypto/passwords.txt";
        
        key = ManageSecretKey.retrieveKey(keyStoreFile);
     
        aesCipherDec = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        
        final String ivStoreFile = homeDir+"/Desktop/Crypto/ivSpec.txt";

        
        byte[] ivData = new byte[AES_KEYLENGTH /8];
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(ivStoreFile)));
        dis.readFully(ivData);
        if(dis != null){
            dis.close();
        }

        IvParameterSpec ivParam = new IvParameterSpec(ivData);
        
        aesCipherDec.init(Cipher.DECRYPT_MODE, key, ivParam);
                
    }
    
    public static String decryptFile(String fileToDecryptPath) throws IOException, IllegalBlockSizeException, BadPaddingException{
        
       File fileToDecrypt = new File(fileToDecryptPath);
       
       FileInputStream inputStream = new FileInputStream(fileToDecrypt);
       byte[] cipherTextBytes = new byte[(int) fileToDecrypt.length()];
       inputStream.read(cipherTextBytes);
       
       byte[] clearTextBytes = aesCipherDec.doFinal(cipherTextBytes);
       
       String decryptedFilePath = request.downloadDirectory + request.downloadFileSaveName;
       
       FileOutputStream outputStream = new FileOutputStream(decryptedFilePath);
       outputStream.write(clearTextBytes);
       inputStream.close();
       outputStream.close();

       return decryptedFilePath;                     
    }
    
    
}
