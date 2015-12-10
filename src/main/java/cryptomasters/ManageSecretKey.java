package cryptomasters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kdonahoe
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.DecoderException;
import static org.apache.commons.codec.binary.Hex.encodeHex;
import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.io.FileUtils.readFileToByteArray;


public class ManageSecretKey {

    final static int AES_KEYLENGTH = 128;
    
    public static void main(String[] args) throws Exception {
        
    }
    
    public static SecretKey makeAndStore() throws Exception{
        SecretKey key = makeKey();
        storeKey(key); 
        return key;
    }
    
    /**
     * 
     * Generates a fresh secret key, and stores it into a Key File.
     * 
     * @throws NoSuchAlgorithmException 
     */
    public static SecretKey makeKey() throws NoSuchAlgorithmException, Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEYLENGTH);
        SecretKey secKey = keyGen.generateKey();
        return secKey;   
    }
    
    /**
     * Actually creates an instance of a KeyStore, and stores it 
     * in the file (by calling createKeyStore).
     * Then stores the key into the KeyStore, with extra password protection. 
     *  
     */
    public static void storeKey(SecretKey secKey) throws Exception{
        String homeDir = System.getProperty("user.home");
        boolean newCryptoFolder = new File(homeDir+"/Desktop/Crypto1/").mkdirs();

        final String keyStoreFile = homeDir+"/Desktop/Crypto1/passwords.txt";
   
        OutputStream outputstream = new FileOutputStream(keyStoreFile);
        ObjectOutputStream out = new ObjectOutputStream(outputstream);
        try{
            out.writeObject(secKey);
        } finally{
            out.close();
        }
        

        final String ivStoreFile = homeDir+"/Desktop/Crypto1/ivSpec.txt";
        
        SecureRandom prng = new SecureRandom();
        byte[] iv = new byte[AES_KEYLENGTH / 8];
        prng.nextBytes(iv);
               
        OutputStream ivoutputstream = new FileOutputStream(ivStoreFile);
        BufferedOutputStream ivout = new BufferedOutputStream(ivoutputstream);
        try{
            ivout.write(iv);
        } finally{
            ivout.close();
        }
       

//        //STORING USING KEY STORE
//        KeyStore keyStore = createKeyStore(keyStoreFile, password);
//        
//        KeyStore.SecretKeyEntry ksEntry = new KeyStore.SecretKeyEntry(secKey);
//        //additional level of security (other than just password) to storing the secret key
//        PasswordProtection keyPassword = new PasswordProtection("iowa-15K".toCharArray());
//        
//        keyStore.setEntry("kevSecretKey", ksEntry, keyPassword);
//        keyStore.store(new FileOutputStream(keyStoreFile), password.toCharArray());
////        System.out.println("The key: " + secKey.toString() +  "has been stored");
    }
    
     /**
     * Creates a KeyStore, which is a storage device for cryptographic keys and certificates,
     * and stores it into the key file. The password authenticates management of the KeyStore.
     *
     */
    
    private static KeyStore createKeyStore(String fileName, String password) throws Exception{
        File f = new File(fileName);
        final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        
        if(f.exists()){
            ks.load(new FileInputStream(f), password.toCharArray());
        }
        else{
            ks.load(null, null);
            ks.store(new FileOutputStream(f), password.toCharArray());
        }
        return ks;
    }
    
    
//    public static SecretKey retrieveKey(KeyStore keyStore, String password) throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException{
//        PasswordProtection keyPassword = new PasswordProtection("iowa-15K".toCharArray());
//        
//        KeyStore.Entry entry = keyStore.getEntry("kevSecretKey", keyPassword);
//        SecretKey key = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
//        
//        System.out.println("Retrieved Key: " + key.toString());
//        return key;
//    }
    
    public static SecretKey retrieveKey(String keyStoreFile) throws IOException, DecoderException, NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException, ClassNotFoundException{
    
        SecretKey key;
        InputStream inputStream = new FileInputStream(keyStoreFile);    
        ObjectInputStream in = new ObjectInputStream(inputStream);
        try{
            key = (SecretKey) in.readObject();
        }finally{
            in.close();
        }
        return key;
            

    }

}
  
    

