/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kdonahoe
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ManageSecretKey {
   
//    public ManageSecretKey(String password){
//        
//    }
//    
    public static void main(String[] args) throws Exception {
        String password = args[0];
        
        SecretKey key = makeKey();
        storeKey(key, password);
        
        
    }
    /**
     * 
     * Generates a fresh secret key, and stores it into a Key File.
     * 
     * @throws NoSuchAlgorithmException 
     */
    public static SecretKey makeKey() throws NoSuchAlgorithmException, Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secKey = keyGen.generateKey();
        System.out.println("Generated Key: " + secKey.toString());
        return secKey;
    }
    
    /**
     * Actually creates an instance of a KeyStore, and stores it 
     * in the file (by calling createKeyStore).
     * Then stores the key into the KeyStore, with extra password protection. 
     *  
     */
    public static void storeKey(SecretKey secKey, String password) throws Exception{
        final String keyStoreFile = "/Users/kdonahoe/Desktop/KeyStore_File";
        KeyStore keyStore = createKeyStore(keyStoreFile);
        
        KeyStore.SecretKeyEntry ksEntry = new KeyStore.SecretKeyEntry(secKey);
        //additional level of security (other than just password) to storing the secret key
        String pwdSecret = "pw-secret";
        PasswordProtection keyPassword = new PasswordProtection(pwdSecret.toCharArray());
        
        keyStore.setEntry("kevSecretKey", ksEntry, keyPassword);
        keyStore.store(new FileOutputStream(keyStoreFile), password.toCharArray());
    }
    
     /**
     * Creates a KeyStore, which is a storage device for cryptographic keys and certificates,
     * and stores it into the key file. The password authenticates management of the KeyStore.
     *
     */
    
    private static KeyStore createKeyStore(String fileName) throws Exception{
        File f = new File(fileName);
        final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        
        if(f.exists()){
            ks.load((KeyStore.LoadStoreParameter) new FileInputStream(f));
        }
        else{
            ks.load(null, null);
            ks.store((KeyStore.LoadStoreParameter) new FileOutputStream(f));
        }
        return ks;
    }
    
    
    public static SecretKey retrieveKey(KeyStore keyStore, String password) throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException{
//        String pwdSecret = "pw-secret";
        PasswordProtection keyPassword = new PasswordProtection(password.toCharArray());
        
        KeyStore.Entry entry = keyStore.getEntry("kevSecretKey", keyPassword);
        SecretKey key = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
        System.out.println("Retrieved Key: " + key.toString());
        return key;
    }
}
  
    

