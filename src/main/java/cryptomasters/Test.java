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
        
        String path = fe.encryptFile("/Users/kdonahoe/Desktop/Crypto/encryptedFiles/kevin.docx");
        System.out.println("File Encryption Path: " + path);
    }
    
}
