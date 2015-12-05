/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.ListBlobItem;
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author kdonahoe
 */
public class PickDownloadFileGUI extends javax.swing.JFrame {

    public static RequestData request;
    public static String accountName; 
    public static String containerName;
    public static String storageConnectionString;
    public static DefaultListModel model;
            
    /**
     * Creates new form PickDownloadFileGUI
     * @param request
     */
    public PickDownloadFileGUI(RequestData request) {
        PickDownloadFileGUI.request = request;
        accountName = request.userCredentials;
//        String containerName = "newcont";
        storageConnectionString =
        "DefaultEndpointsProtocol=https;"
        + "AccountName=" + accountName + ";" + "AccountKey=" + "jHt9Ewu5ujL154JkA/bGarKAeKGCwVkfmls5FI5OGlfAeFugCWq1MMVAgCQn2h9LttwAnAlYKZFLqedMCzU71Q==";
          // For easy testing:
        model = new DefaultListModel();
       
        
          
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerLabel = new javax.swing.JLabel();
        containerInputText = new javax.swing.JTextField();
        listBlobsButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        blobList = new javax.swing.JList<>();
        downloadBlobButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        containerLabel.setText("Which container would you like to download from:");

        containerInputText.setText("container name ...");
        containerInputText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                containerInputTextMousePressed(evt);
            }
        });

        listBlobsButton.setText("List blobs in that container");
        listBlobsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listBlobsButtonActionPerformed(evt);
            }
        });

        blobList.setModel(model);
        blobList.setVisible(false);
        jScrollPane1.setViewportView(blobList);

        downloadBlobButton.setText("Download");
        downloadBlobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadBlobButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(containerInputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(containerLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(listBlobsButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(downloadBlobButton)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(containerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(containerInputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listBlobsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(downloadBlobButton)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void containerInputTextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_containerInputTextMousePressed
        // TODO add your handling code here:
        containerInputText.setText("");
    }//GEN-LAST:event_containerInputTextMousePressed

    private void listBlobsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listBlobsButtonActionPerformed
        // TODO add your handling code here:
        if(!containerInputText.equals("")){
            containerName = containerInputText.getText();
            blobList.setVisible(true);
             try
            {
                // Retrieve storage account from connection-string.
                CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

                // Create the blob client.
                CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

                // Retrieve reference to a previously created container.
                CloudBlobContainer container = blobClient.getContainerReference(containerName);

                // Loop over blobs within the container and output the URI to each of them.
                for (ListBlobItem blobItem : container.listBlobs()) {
                    model.addElement(blobItem);
                    CloudBlob blob  = (CloudBlob) blobItem;
                    System.out.println(blob.getName());
               }
            }
            catch (Exception e)
            {
        // Output the stack trace.
            e.printStackTrace();
            } 
        }
    }//GEN-LAST:event_listBlobsButtonActionPerformed

    private void downloadBlobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadBlobButtonActionPerformed
        // TODO add your handling code here:
        if(model.getElementAt(blobList.getSelectedIndex()) instanceof CloudBlob){
            CloudBlob blob = (CloudBlob) model.getElementAt(blobList.getSelectedIndex());
            request.downloadBlob = blob;
            try {
                request.downloadFileName = blob.getName();
            } catch (URISyntaxException ex) {
                Logger.getLogger(PickDownloadFileGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                System.out.println("Downloading this blob: "+ blob.getName());
            } catch (URISyntaxException ex) {
                Logger.getLogger(PickDownloadFileGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.containerName = containerName;
        
        this.setVisible(false);
        DownloadFileGUI downloadFileGUI = new DownloadFileGUI(request);
        downloadFileGUI.setVisible(true);
    }//GEN-LAST:event_downloadBlobButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PickDownloadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PickDownloadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PickDownloadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PickDownloadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PickDownloadFileGUI(request).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> blobList;
    private javax.swing.JTextField containerInputText;
    private javax.swing.JLabel containerLabel;
    private javax.swing.JButton downloadBlobButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listBlobsButton;
    // End of variables declaration//GEN-END:variables
}
