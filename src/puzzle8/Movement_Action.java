
package puzzle8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABEDSSAMED
 */
public class Movement_Action extends javax.swing.JFrame {

    /**
     * Creates new form Movement_Action
     */
    int i = 0;

    private int[][] pathe;
    private List<TaquinNode> path;

    public Movement_Action(List<TaquinNode> pathG) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(0);
        txtBut.setText("");
        this.path = pathG;
        i = this.path.size() - 1;
        pathe = this.path.get(i).getMatrix();
        String x = "";

        txt00.setText(pathe[0][0] == 0 ? "" : pathe[0][0] + "");
        txt01.setText(pathe[0][1] == 0 ? "" : pathe[0][1] + "");
        txt02.setText(pathe[0][2] == 0 ? "" : pathe[0][2] + "");
        txt10.setText(pathe[1][0] == 0 ? "" : pathe[1][0] + "");
        txt11.setText(pathe[1][1] == 0 ? "" : pathe[1][1] + "");
        txt12.setText(pathe[1][2] == 0 ? "" : pathe[1][2] + "");
        txt20.setText(pathe[2][0] == 0 ? "" : pathe[2][0] + "");
        txt21.setText(pathe[2][1] == 0 ? "" : pathe[2][1] + "");
        txt22.setText(pathe[2][2] == 0 ? "" : pathe[2][2] + "");
    }

    private Movement_Action() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt00 = new javax.swing.JLabel();
        txt01 = new javax.swing.JLabel();
        txt02 = new javax.swing.JLabel();
        txt10 = new javax.swing.JLabel();
        txt11 = new javax.swing.JLabel();
        txt12 = new javax.swing.JLabel();
        txt22 = new javax.swing.JLabel();
        txt21 = new javax.swing.JLabel();
        txt20 = new javax.swing.JLabel();
        txtBut = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        txt00.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt00.setForeground(new java.awt.Color(0, 0, 0));
        txt00.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt00.setText("1");
        txt00.setToolTipText("");
        txt00.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt01.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt01.setForeground(new java.awt.Color(0, 0, 0));
        txt01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt01.setText("2");
        txt01.setToolTipText("");
        txt01.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt02.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt02.setForeground(new java.awt.Color(0, 0, 0));
        txt02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt02.setText("3");
        txt02.setToolTipText("");
        txt02.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt10.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt10.setForeground(new java.awt.Color(0, 0, 0));
        txt10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt10.setText("8");
        txt10.setToolTipText("");
        txt10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt11.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt11.setForeground(new java.awt.Color(0, 0, 0));
        txt11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt11.setText(" ");
        txt11.setToolTipText("");
        txt11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt12.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt12.setForeground(new java.awt.Color(0, 0, 0));
        txt12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt12.setText("4");
        txt12.setToolTipText("");
        txt12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt22.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt22.setForeground(new java.awt.Color(0, 0, 0));
        txt22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt22.setText("5");
        txt22.setToolTipText("");
        txt22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt21.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt21.setForeground(new java.awt.Color(0, 0, 0));
        txt21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt21.setText("6");
        txt21.setToolTipText("");
        txt21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txt20.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt20.setForeground(new java.awt.Color(0, 0, 0));
        txt20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt20.setText("7");
        txt20.setToolTipText("");
        txt20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        txtBut.setBackground(new java.awt.Color(255, 255, 255));
        txtBut.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        txtBut.setForeground(new java.awt.Color(0, 0, 0));
        txtBut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBut.setText("C'est le but");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/puzzle8/Windows_Close_Program_22531 (1).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/puzzle8/icons8-Previous-page-48.png"))); // NOI18N
        jButton4.setToolTipText("l'??tat pr??cident");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/puzzle8/icons8-next-page-48 -.png"))); // NOI18N
        jButton5.setToolTipText("l'??tat suivant");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt00, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt01, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt00, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt01, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(txtBut, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton4.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        i++;
        txtBut.setText("");
        if (i < this.path.size() - 1) {

            System.out.println("i=:" + i);
            pathe = this.path.get(i).getMatrix();
            txt00.setText(pathe[0][0] == 0 ? "" : pathe[0][0] + "");
            txt01.setText(pathe[0][1] == 0 ? "" : pathe[0][1] + "");
            txt02.setText(pathe[0][2] == 0 ? "" : pathe[0][2] + "");
            txt10.setText(pathe[1][0] == 0 ? "" : pathe[1][0] + "");
            txt11.setText(pathe[1][1] == 0 ? "" : pathe[1][1] + "");
            txt12.setText(pathe[1][2] == 0 ? "" : pathe[1][2] + "");
            txt20.setText(pathe[2][0] == 0 ? "" : pathe[2][0] + "");
            txt21.setText(pathe[2][1] == 0 ? "" : pathe[2][1] + "");
            txt22.setText(pathe[2][2] == 0 ? "" : pathe[2][2] + "");
        } else {
            i = 0;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtBut.setText("");
        i--;
        if (i >= 0) {
            //condition ? instruction1 : instruction2
            System.out.println("i=:" + i);
            pathe = this.path.get(i).getMatrix();
            txt00.setText(pathe[0][0] == 0 ? "" : pathe[0][0] + "");
            txt01.setText(pathe[0][1] == 0 ? "" : pathe[0][1] + "");
            txt02.setText(pathe[0][2] == 0 ? "" : pathe[0][2] + "");
            txt10.setText(pathe[1][0] == 0 ? "" : pathe[1][0] + "");
            txt11.setText(pathe[1][1] == 0 ? "" : pathe[1][1] + "");
            txt12.setText(pathe[1][2] == 0 ? "" : pathe[1][2] + "");
            txt20.setText(pathe[2][0] == 0 ? "" : pathe[2][0] + "");
            txt21.setText(pathe[2][1] == 0 ? "" : pathe[2][1] + "");
            txt22.setText(pathe[2][2] == 0 ? "" : pathe[2][2] + "");
        } else {

            i = this.path.size() - 1;
            txtBut.setText("C'est le but !!");

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Movement_Action.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Movement_Action.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Movement_Action.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Movement_Action.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Movement_Action().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txt00;
    private javax.swing.JLabel txt01;
    private javax.swing.JLabel txt02;
    private javax.swing.JLabel txt10;
    private javax.swing.JLabel txt11;
    private javax.swing.JLabel txt12;
    private javax.swing.JLabel txt20;
    private javax.swing.JLabel txt21;
    private javax.swing.JLabel txt22;
    private javax.swing.JLabel txtBut;
    // End of variables declaration//GEN-END:variables
}
