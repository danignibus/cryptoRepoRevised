/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptomasters;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author danignibus
 */
public class UploadFileGUI extends javax.swing.JFrame {
    public static RequestData request;

    /**
     * Creates new form UploadFileGUI
     * @param request
     */
    public UploadFileGUI(RequestData request) {
        UploadFileGUI.request = request;
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

        containersButtonGroup = new javax.swing.ButtonGroup();
        submitUploadButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fileNameInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        existingContainerRadioButton = new javax.swing.JRadioButton();
        newContainerRadioButton = new javax.swing.JRadioButton();
        newContainerNameInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        submitUploadButton.setText("Upload dat ish!!!");
        submitUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitUploadButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Name your file and select a container!");

        fileNameInput.setText("file.txt");
        fileNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileNameInputActionPerformed(evt);
            }
        });

        jLabel2.setText("File name: ");

        containersButtonGroup.add(existingContainerRadioButton);
        existingContainerRadioButton.setText("Upload to existing container");
        existingContainerRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existingContainerRadioButtonActionPerformed(evt);
            }
        });

        containersButtonGroup.add(newContainerRadioButton);
        newContainerRadioButton.setText("Create a new container");
        newContainerRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                newContainerRadioButtonItemStateChanged(evt);
            }
        });
        newContainerRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newContainerRadioButtonActionPerformed(evt);
            }
        });

        newContainerNameInput.setText("Enter new container name here");
        newContainerNameInput.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(submitUploadButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(existingContainerRadioButton)
                            .addComponent(newContainerRadioButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(83, 83, 83))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newContainerNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameInput)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addComponent(existingContainerRadioButton)
                .addGap(18, 18, 18)
                .addComponent(newContainerRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newContainerNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(submitUploadButton)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    
    private void submitUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitUploadButtonActionPerformed
        //submit request
        String containerChoice = getSelectedButtonText(containersButtonGroup);
        if (containerChoice == null) {
            JOptionPane.showMessageDialog(this, "Please select a radio button option.");

        }
        if (containerChoice.equals("Create a new container")) {
            //submit new container request
            System.out.println("got here");
            String uploadFileSaveName = fileNameInput.getText();
            request.setUploadFileSaveName(uploadFileSaveName);
            String newContainerName = newContainerNameInput.getText();
            newContainerName = newContainerName.toLowerCase();
            request.setContainerName(newContainerName);
            
            try {
                HttpsSendUpload sendIt = new HttpsSendUpload(request);

            }
            catch (Exception e) {
                System.out.println("Wrong credentials supplied");
            }
        }
        else if (containerChoice.equals("existingContainerRadioButton")) {
            //submit existing container request
        }
        
        this.setVisible(false);
        RequestData newRequest = new RequestData();
        newRequest.setUserCredentials(request.userCredentials);
        newRequest.setUserGroupKey(request.userGroupKey);
        UploadDownloadGUI startOverGUI = new UploadDownloadGUI(newRequest);
        startOverGUI.setVisible(true);
        
    }//GEN-LAST:event_submitUploadButtonActionPerformed

    private void fileNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileNameInputActionPerformed

    private void existingContainerRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existingContainerRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_existingContainerRadioButtonActionPerformed

    private void newContainerRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newContainerRadioButtonActionPerformed
        // TODO add your handling code here:
        newContainerNameInput.setVisible(true);
        revalidate();

    }//GEN-LAST:event_newContainerRadioButtonActionPerformed

    private void newContainerRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_newContainerRadioButtonItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_newContainerRadioButtonItemStateChanged

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
            java.util.logging.Logger.getLogger(UploadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadFileGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadFileGUI(request).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup containersButtonGroup;
    private javax.swing.JRadioButton existingContainerRadioButton;
    private javax.swing.JTextField fileNameInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField newContainerNameInput;
    private javax.swing.JRadioButton newContainerRadioButton;
    private javax.swing.JButton submitUploadButton;
    // End of variables declaration//GEN-END:variables
}
