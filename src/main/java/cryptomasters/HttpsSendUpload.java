/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;
import java.io.*;
import javax.swing.JOptionPane;


/**
 *
 * @author danignibus
 */
public class HttpsSendUpload {
    public static String accountName; 
    public static String accountKey;
    public static String containerName;
    public static String blobName;
    public static String imagePath;
    public static boolean returnVal =true;
//For accessing Azure Blob through HTTPS is quite simple if you use the Azure SDK: 
    //you need to use URL of the blob with [https://yourblob.....com]https://yourblob.....com 
    //instead of [http://yourblob....com]http://yourblob....com, and SDK will do the rest. 
    //If you use Curl, or something else, it might be a bit more complex.
//Here is how to access Azure Blob: https://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/


    public boolean getReturnVal(){
        return returnVal;
    }
    public static void runUpload(RequestData request) throws StorageException, Exception {
            HttpsSendUpload.accountName = request.userCredentials;
            HttpsSendUpload.accountKey = request.userGroupKey;
            HttpsSendUpload.blobName = request.uploadFileSaveName;
            HttpsSendUpload.containerName = request.containerName;
            HttpsSendUpload.imagePath = request.uploadFileName;
            //TODO: take out hard coded account key!
            final String storageConnectionString =
        "DefaultEndpointsProtocol=https;"
        + "AccountName=" + accountName + ";" + "AccountKey=" + "jHt9Ewu5ujL154JkA/bGarKAeKGCwVkfmls5FI5OGlfAeFugCWq1MMVAgCQn2h9LttwAnAlYKZFLqedMCzU71Q==";
          // For easy testing:
            //"DefaultEndpointsProtocol=https;"
        //+ "AccountName=cryptodaniandkev;"
        //+ "AccountKey=Vb0bAhD9etqyVJlZnOxFIgOy4TvpQ1xQ6GKMuo7ymRD8SWUxyZYBPnV83UoDW3a/Gqfe0qlWwcCsULDXmLz9jA==";
          System.out.println("ALL DATA:");
            System.out.println(containerName + " " + imagePath + " " + blobName + " " + accountName + " " + accountKey );

        try {
            
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
            CloudBlobContainer container = serviceClient.getContainerReference(containerName);
            boolean created = container.createIfNotExists();
            System.out.println(created);

            // Upload an image file.
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);

            FileEncryption fe = new FileEncryption();
            String encryptedFilePath = fe.encryptFile(imagePath);
            System.out.println(request.uploadFileSaveName + " has been encrypted and uploaded.");
            System.out.println("To view the encrypted version, check out: "+ encryptedFilePath);
            
            File sourceFile = new File(encryptedFilePath);
            blob.upload(new FileInputStream(sourceFile), sourceFile.length());
            returnVal = true;


        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            returnVal= false;
        }
        catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            returnVal= false;
            throw storageException;
            //System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            returnVal= false;
            throw e;
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

    HttpsSendUpload(RequestData request) throws StorageException, Exception {
        runUpload(request);
    }
}
