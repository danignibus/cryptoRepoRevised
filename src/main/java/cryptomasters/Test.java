/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import static cryptomasters.FileEncryption.encryptFile;
import java.io.IOException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author kdonahoe
 */
public class Test {
    
    public static void main(String args[]) throws NoSuchPaddingException, InvalidKeyException, IOException, Exception{
        FileEncryption fe = new FileEncryption();
        FileDecryption de = new FileDecryption();
        String path = "/Users/kdonahoe/Desktop/Crypto/encryptedFiles/toencrypt.txt";
        String pathEnc = fe.encryptFile(path);
        String pathDec = de.decryptFile(pathEnc);
        System.out.println("DECRYPTED FILE Path: " + pathDec);
    }
    
}
