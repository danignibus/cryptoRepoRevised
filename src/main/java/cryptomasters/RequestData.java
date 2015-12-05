/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import com.microsoft.azure.storage.blob.CloudBlob;

/**
 *
 * @author danignibus
 */
public class RequestData {
    String userCredentials;
    String userGroupKey;
    String uploadFileName;
    CloudBlob downloadBlob;
    String downloadFileName;
    String containerName;
    String uploadFileSaveName;
    String downloadFileSaveName;
    String downloadDirectory;

    public String getDownloadFileSaveName() {
        return downloadFileSaveName;
    }

    public void setDownloadFileSaveName(String downloadFileSaveName) {
        this.downloadFileSaveName = downloadFileSaveName;
    }
    
    public CloudBlob getDownloadBlob(){
        return downloadBlob;
    }
    
    public void setDownloadBlob(CloudBlob downloadBlob){
        this.downloadBlob = downloadBlob;
    }

    
    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    public void setDownloadDirectory(String downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    
    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getUploadFileSaveName() {
        return uploadFileSaveName;
    }

    public void setUploadFileSaveName(String uploadFileSaveName) {
        this.uploadFileSaveName = uploadFileSaveName;
    }

    public void setUserCredentials(String userCredentials) {
        this.userCredentials = userCredentials;
    }

    public void setUserGroupKey(String userGroupKey) {
        this.userGroupKey = userGroupKey;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUserCredentials() {
        return userCredentials;
    }

    public String getUserGroupKey() {
        return userGroupKey;
    }

    
}
