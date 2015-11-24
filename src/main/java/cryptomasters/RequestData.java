/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

/**
 *
 * @author danignibus
 */
public class RequestData {
    String userCredentials;
    String userGroupKey;
    String uploadFileName;
    String downloadFileName;
    String containerName;
    String uploadFileSaveName;

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
