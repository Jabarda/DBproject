/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject.Forms;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jabarda
 */
public class FuncBuying extends javax.swing.JFrame {
    int[] price;
    public void SetPrice(int[] NewPrice)
    {
        price=NewPrice;
    }
    ClientWindow local;
    public void setClient(ClientWindow newLocal){
        local=newLocal;
    }
    /**
     * Creates new form FuncBuying
     */
    public FuncBuying() {
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

        CancelButton = new javax.swing.JButton();
        SubmitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FromField = new javax.swing.JTextField();
        ToField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        FuncField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        OutPriceField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Licience start date");

        jLabel2.setText("Licience over date");

        FromField.setText("dd/mm/yyyy");
        FromField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromFieldActionPerformed(evt);
            }
        });

        ToField.setText("dd/mm/yyyy");

        jLabel3.setText("Function");

        FuncField.setText("Insert number here...");
        FuncField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncFieldActionPerformed(evt);
            }
        });

        jButton1.setText("Calculate price");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        OutPriceField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SubmitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ToField)
                            .addComponent(FromField)
                            .addComponent(FuncField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(OutPriceField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(FromField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(ToField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(FuncField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(OutPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(SubmitButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
        // TODO add your handling code here:
        try{
        String from = FromField.getText();
        String to = ToField.getText();
        String number = FuncField.getText();
        int n = Integer.parseInt(number);
        Date f = null,t = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        try {
            f = (Date) df.parse(from);
        } catch (ParseException ex) {
            Logger.getLogger(FuncBuying.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            t = (Date) df.parse(to);
        } catch (ParseException ex) {
            Logger.getLogger(FuncBuying.class.getName()).log(Level.SEVERE, null, ex);
        }
        int delta = (int) ((t.getTime() - f.getTime())/86400000);
        if (delta<=0) throw new Exception("Wrong date");
        local.BuyFuncs(FromField.getText(), ToField.getText(), FuncField.getText(),Integer.toString(delta*price[n-1]));
        this.dispose();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error! Additional information: "+ e.getMessage());
        }
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private void FromFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FromFieldActionPerformed

    private void FuncFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuncFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FuncFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
        
        String from = FromField.getText();
        String to = ToField.getText();
        String number = FuncField.getText();
        int n = Integer.parseInt(number);
        Date f = null,t = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        try {
            f = (Date) df.parse(from);
        } catch (ParseException ex) {
            Logger.getLogger(FuncBuying.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            t = (Date) df.parse(to);
        } catch (ParseException ex) {
            Logger.getLogger(FuncBuying.class.getName()).log(Level.SEVERE, null, ex);
        }
        int delta = (int) ((t.getTime() - f.getTime())/86400000);
        if (delta<=0) throw new Exception("Wrong date");
        OutPriceField.setText(Integer.toString(delta*price[n-1]));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Additional information: "+ e.getMessage());
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FuncBuying.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncBuying.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncBuying.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncBuying.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncBuying().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JTextField FromField;
    private javax.swing.JTextField FuncField;
    private javax.swing.JTextField OutPriceField;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JTextField ToField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
