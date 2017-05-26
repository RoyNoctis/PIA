/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author d13
 */
public class Rules extends javax.swing.JFrame {

    /**
     * Creates new form Rules
     */
    private JFrame MP;
    public Rules(JFrame MenuPrincipal) {
        initComponents();
        MP=MenuPrincipal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TbP_Rules = new javax.swing.JTabbedPane();
        Pnl_Add = new javax.swing.JPanel();
        Btn_Cancelar = new javax.swing.JButton();
        TxF_Conse = new javax.swing.JTextField();
        TxF_Ante = new javax.swing.JTextField();
        Lbl_Ante = new javax.swing.JLabel();
        Lbl_Conse = new javax.swing.JLabel();
        Btn_AddRule = new javax.swing.JButton();
        Pnl_Edit = new javax.swing.JPanel();
        Cbx_Rules = new javax.swing.JComboBox<String>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        Lbl_Edit_Ante = new javax.swing.JLabel();
        Lbl_Edit_Cpnse = new javax.swing.JLabel();
        Btn_Editar = new javax.swing.JButton();
        Pnl_Del = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_Rules = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar Nueva Regla");

        Btn_Cancelar.setText("Cancelar");
        Btn_Cancelar.setPreferredSize(new java.awt.Dimension(60, 24));
        Btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CancelarActionPerformed(evt);
            }
        });

        Lbl_Ante.setText("Antecedente");

        Lbl_Conse.setText("Consecuente");

        Btn_AddRule.setText("Agregar");
        Btn_AddRule.setPreferredSize(new java.awt.Dimension(60, 24));
        Btn_AddRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AddRuleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Pnl_AddLayout = new javax.swing.GroupLayout(Pnl_Add);
        Pnl_Add.setLayout(Pnl_AddLayout);
        Pnl_AddLayout.setHorizontalGroup(
            Pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_AddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pnl_AddLayout.createSequentialGroup()
                        .addComponent(Lbl_Ante)
                        .addGap(18, 18, 18)
                        .addComponent(TxF_Ante, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Pnl_AddLayout.createSequentialGroup()
                        .addComponent(Lbl_Conse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxF_Conse, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Pnl_AddLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(Btn_AddRule, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(Btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        Pnl_AddLayout.setVerticalGroup(
            Pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_AddLayout.createSequentialGroup()
                .addGroup(Pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_Ante)
                    .addComponent(TxF_Ante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(Pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_Conse)
                    .addComponent(TxF_Conse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_AddRule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        TbP_Rules.addTab("Agregar", Pnl_Add);

        Cbx_Rules.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Listado de Reglas", "Item 2", "Item 3", "Item 4" }));

        Lbl_Edit_Ante.setText("Antdecendente");

        Lbl_Edit_Cpnse.setText("Consecunete");

        Btn_Editar.setText("Editar");
        Btn_Editar.setPreferredSize(new java.awt.Dimension(60, 24));

        javax.swing.GroupLayout Pnl_EditLayout = new javax.swing.GroupLayout(Pnl_Edit);
        Pnl_Edit.setLayout(Pnl_EditLayout);
        Pnl_EditLayout.setHorizontalGroup(
            Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_EditLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pnl_EditLayout.createSequentialGroup()
                        .addGroup(Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lbl_Edit_Ante)
                            .addComponent(Lbl_Edit_Cpnse))
                        .addGap(34, 34, 34)
                        .addGroup(Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jTextField1)))
                    .addComponent(Cbx_Rules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pnl_EditLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        Pnl_EditLayout.setVerticalGroup(
            Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_EditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cbx_Rules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lbl_Edit_Ante))
                .addGap(18, 18, 18)
                .addGroup(Pnl_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lbl_Edit_Cpnse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        TbP_Rules.addTab("Editar", Pnl_Edit);

        Tbl_Rules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Tbl_Rules);

        javax.swing.GroupLayout Pnl_DelLayout = new javax.swing.GroupLayout(Pnl_Del);
        Pnl_Del.setLayout(Pnl_DelLayout);
        Pnl_DelLayout.setHorizontalGroup(
            Pnl_DelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pnl_DelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Pnl_DelLayout.setVerticalGroup(
            Pnl_DelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_DelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 76, Short.MAX_VALUE))
        );

        TbP_Rules.addTab("Eliminar", Pnl_Del);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TbP_Rules, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TbP_Rules, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CancelarActionPerformed
        MP.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Btn_CancelarActionPerformed

    private void Btn_AddRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AddRuleActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Regla agregada");
    }//GEN-LAST:event_Btn_AddRuleActionPerformed

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
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Rules().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_AddRule;
    private javax.swing.JButton Btn_Cancelar;
    private javax.swing.JButton Btn_Editar;
    private javax.swing.JComboBox<String> Cbx_Rules;
    private javax.swing.JLabel Lbl_Ante;
    private javax.swing.JLabel Lbl_Conse;
    private javax.swing.JLabel Lbl_Edit_Ante;
    private javax.swing.JLabel Lbl_Edit_Cpnse;
    private javax.swing.JPanel Pnl_Add;
    private javax.swing.JPanel Pnl_Del;
    private javax.swing.JPanel Pnl_Edit;
    private javax.swing.JTabbedPane TbP_Rules;
    private javax.swing.JTable Tbl_Rules;
    private javax.swing.JTextField TxF_Ante;
    private javax.swing.JTextField TxF_Conse;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
