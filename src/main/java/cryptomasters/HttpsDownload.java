/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import static cryptomasters.DownloadFileGUI.request;
import static cryptomasters.HttpsSendUpload.accountKey;
import static cryptomasters.HttpsSendUpload.accountName;
import static cryptomasters.HttpsSendUpload.blobName;
import static cryptomasters.HttpsSendUpload.containerName;
import static cryptomasters.HttpsSendUpload.imagePath;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author danignibus
 */
public class HttpsDownload {
    public static String accountName; 
    public static String accountKey;
    public static String containerName;
    public static String blobName;
    public static String downloadFileSaveName;
    public static String downloadDirectory;
    
    public static void runDownload(RequestData request) {
            HttpsDownload.accountName = request.userCredentials;
            HttpsDownload.accountKey = request.userGroupKey;
            HttpsDownload.blobName = request.downloadFileName;
            HttpsDownload.containerName = request.containerName;
            HttpsDownload.downloadFileSaveName = request.downloadFileSaveName;
            HttpsDownload.downloadDirectory = request.downloadDirectory;
            //TODO: take out hard coded account key!
            final String storageConnectionString =
        "DefaultEndpointsProtocol=https;"
        + "AccountName=" + accountName + ";" + "AccountKey=" + "Vb0bAhD9etqyVJlZnOxFIgOy4TvpQ1xQ6GKMuo7ymRD8SWUxyZYBPnV83UoDW3a/Gqfe0qlWwcCsULDXmLz9jA==";
          // For easy testing:
            //"DefaultEndpointsProtocol=https;"
        //+ "AccountName=cryptodaniandkev;"
        //+ "AccountKey=Vb0bAhD9etqyVJlZnOxFIgOy4TvpQ1xQ6GKMuo7ymRD8SWUxyZYBPnV83UoDW3a/Gqfe0qlWwcCsULDXmLz9jA==";
          System.out.println("ALL DATA:");
            System.out.println(containerName + " " + blobName + " " + accountName + " " + accountKey );

        try {
            
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
            CloudBlobContainer container = serviceClient.getContainerReference(containerName);
            
            String destFile = request.downloadDirectory+request.downloadFileSaveName;
            File f = new File(destFile);
            f.getParentFile().mkdirs();
            f.createNewFile();
            CloudBlob blob = request.downloadBlob;
            blob.download(new FileOutputStream(f));
            
            String filePathToDecrypt = request.downloadDirectory+request.downloadFileSaveName;
            FileDecryption fileDec = new FileDecryption();
            String decryptedFilePath = fileDec.decryptFile(filePathToDecrypt);
            
            System.out.println("Downloaded file: "+blob.getName() + " to: "+decryptedFilePath);
            
            
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            System.exit(-1);
        }
        catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        
        
        
    }
    
//    public static void main(String[] args) {
//        runUpload(request);
//    }

//    static void main(String accountName, String accountKey, String containerName, String blobName, String filePath) {
//        runUpload(request);
//    }
    
//        static void main(RequestData request) {
//        runUpload(request);
//    }

    HttpsDownload(RequestData request) {
        runDownload(request);
    }
}


