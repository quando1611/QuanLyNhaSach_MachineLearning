/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.PublicClass;
import java.awt.Panel;
import javax.swing.JPanel;
import GUI.*;
import javax.swing.JOptionPane;
/**
 *
 * @author 19522
 */
public class Home extends javax.swing.JFrame {

    //Tranmision variable 'role' from Login
    private String role;
    public static Home home1;
    public void setData(String s)
    {
        role = s;
        RoleLb.setText(role);
    }
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        home1 = this;
        WelcomeLb.setText("Welcome Back, " + PublicClass.name);
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
        jPanel2 = new javax.swing.JPanel();
        LogoLb = new javax.swing.JLabel();
        WelcomeLb = new javax.swing.JLabel();
        ExitBtn = new javax.swing.JLabel();
        RoleLb = new javax.swing.JLabel();
        StoragePn = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CustomerPn = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        RevenuePn = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        SalePn = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LogOutPn = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ImportPn = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        StaffPn = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_book_250px.png"))); // NOI18N
        LogoLb.setText(" ");
        jPanel2.add(LogoLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 0, -1, -1));

        WelcomeLb.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        WelcomeLb.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeLb.setText("Welcome Back, ..........................................");
        jPanel2.add(WelcomeLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

        ExitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_close_window_70px.png"))); // NOI18N
        ExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitBtnMouseClicked(evt);
            }
        });
        jPanel2.add(ExitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, -1, 82));

        RoleLb.setFont(new java.awt.Font("Impact", 0, 50)); // NOI18N
        RoleLb.setForeground(new java.awt.Color(255, 204, 0));
        RoleLb.setText("ROLE");
        jPanel2.add(RoleLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 57, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        StoragePn.setBackground(new java.awt.Color(251, 251, 251));
        StoragePn.setBorder(new javax.swing.border.MatteBorder(null));
        StoragePn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StoragePnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StoragePnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StoragePnMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 189, 239));
        jLabel4.setText("    Storage");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_book_60px.png"))); // NOI18N

        javax.swing.GroupLayout StoragePnLayout = new javax.swing.GroupLayout(StoragePn);
        StoragePn.setLayout(StoragePnLayout);
        StoragePnLayout.setHorizontalGroup(
            StoragePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoragePnLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(StoragePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(StoragePnLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115))
        );
        StoragePnLayout.setVerticalGroup(
            StoragePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StoragePnLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(21, 21, 21))
        );

        jPanel1.add(StoragePn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 163, -1));

        CustomerPn.setBackground(new java.awt.Color(251, 251, 251));
        CustomerPn.setBorder(new javax.swing.border.MatteBorder(null));
        CustomerPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerPnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CustomerPnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CustomerPnMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 189, 239));
        jLabel6.setText("  Customers");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_people_60px.png"))); // NOI18N

        javax.swing.GroupLayout CustomerPnLayout = new javax.swing.GroupLayout(CustomerPn);
        CustomerPn.setLayout(CustomerPnLayout);
        CustomerPnLayout.setHorizontalGroup(
            CustomerPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPnLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(CustomerPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CustomerPnLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115))
        );
        CustomerPnLayout.setVerticalGroup(
            CustomerPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerPnLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(21, 21, 21))
        );

        jPanel1.add(CustomerPn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 163, -1));

        RevenuePn.setBackground(new java.awt.Color(251, 251, 251));
        RevenuePn.setBorder(new javax.swing.border.MatteBorder(null));
        RevenuePn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RevenuePnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RevenuePnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RevenuePnMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 189, 239));
        jLabel8.setText("    Revenue");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_cost_60px.png"))); // NOI18N

        javax.swing.GroupLayout RevenuePnLayout = new javax.swing.GroupLayout(RevenuePn);
        RevenuePn.setLayout(RevenuePnLayout);
        RevenuePnLayout.setHorizontalGroup(
            RevenuePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RevenuePnLayout.createSequentialGroup()
                .addGroup(RevenuePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RevenuePnLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(RevenuePnLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(120, 120, 120))
        );
        RevenuePnLayout.setVerticalGroup(
            RevenuePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RevenuePnLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(21, 21, 21))
        );

        jPanel1.add(RevenuePn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 163, -1));

        SalePn.setBackground(new java.awt.Color(251, 251, 251));
        SalePn.setBorder(new javax.swing.border.MatteBorder(null));
        SalePn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalePnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalePnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalePnMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 189, 239));
        jLabel10.setText("  Sale & Bill");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_bill_60px_1.png"))); // NOI18N

        javax.swing.GroupLayout SalePnLayout = new javax.swing.GroupLayout(SalePn);
        SalePn.setLayout(SalePnLayout);
        SalePnLayout.setHorizontalGroup(
            SalePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SalePnLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(115, 115, 115))
            .addGroup(SalePnLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SalePnLayout.setVerticalGroup(
            SalePnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalePnLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(22, 22, 22))
        );

        jPanel1.add(SalePn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 163, -1));

        LogOutPn.setBackground(new java.awt.Color(251, 251, 251));
        LogOutPn.setBorder(new javax.swing.border.MatteBorder(null));
        LogOutPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOutPnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogOutPnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogOutPnMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 189, 239));
        jLabel12.setText("    Log Out");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_Logout_60px.png"))); // NOI18N

        javax.swing.GroupLayout LogOutPnLayout = new javax.swing.GroupLayout(LogOutPn);
        LogOutPn.setLayout(LogOutPnLayout);
        LogOutPnLayout.setHorizontalGroup(
            LogOutPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogOutPnLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(LogOutPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(LogOutPnLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel13)))
                .addGap(120, 120, 120))
        );
        LogOutPnLayout.setVerticalGroup(
            LogOutPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogOutPnLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(21, 21, 21))
        );

        jPanel1.add(LogOutPn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 500, 163, -1));

        ImportPn.setBackground(new java.awt.Color(251, 251, 251));
        ImportPn.setBorder(new javax.swing.border.MatteBorder(null));
        ImportPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImportPnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ImportPnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ImportPnMouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 189, 239));
        jLabel14.setText("     Import");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_shopping_cart_60px.png"))); // NOI18N

        javax.swing.GroupLayout ImportPnLayout = new javax.swing.GroupLayout(ImportPn);
        ImportPn.setLayout(ImportPnLayout);
        ImportPnLayout.setHorizontalGroup(
            ImportPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImportPnLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(ImportPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ImportPnLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel15)))
                .addGap(120, 120, 120))
        );
        ImportPnLayout.setVerticalGroup(
            ImportPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ImportPnLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(21, 21, 21))
        );

        jPanel1.add(ImportPn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 310, 163, -1));

        StaffPn.setBackground(new java.awt.Color(251, 251, 251));
        StaffPn.setBorder(new javax.swing.border.MatteBorder(null));
        StaffPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StaffPnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StaffPnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StaffPnMouseExited(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 189, 239));
        jLabel18.setText("       Staff");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_microsoft_admin_60px.png"))); // NOI18N

        javax.swing.GroupLayout StaffPnLayout = new javax.swing.GroupLayout(StaffPn);
        StaffPn.setLayout(StaffPnLayout);
        StaffPnLayout.setHorizontalGroup(
            StaffPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffPnLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(288, 288, 288))
            .addGroup(StaffPnLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StaffPnLayout.setVerticalGroup(
            StaffPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffPnLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(21, 21, 21))
        );

        jPanel1.add(StaffPn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 498, 163, 140));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setText("Prediction");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/symbole-du-livre-rose.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 170, 140));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //Function to handle BG ưhen mouse hover
    private void setBGWhenMouseEnter(JPanel panel)
    {
       panel.setBackground( new java.awt.Color(197 ,197 , 197));
    }
    private void setBGWhenMouseExit(JPanel panel)
    {
       panel.setBackground( new java.awt.Color(251,251,251));
    }
    
    //Call Event Enter & Exit Mouse
    private void ExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitBtnMouseClicked
                System.exit(0);
    }//GEN-LAST:event_ExitBtnMouseClicked

    private void CustomerPnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(CustomerPn);
    }//GEN-LAST:event_CustomerPnMouseEntered

    private void CustomerPnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(CustomerPn);
    }//GEN-LAST:event_CustomerPnMouseExited

    private void StoragePnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StoragePnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(StoragePn);
    }//GEN-LAST:event_StoragePnMouseEntered

    private void StoragePnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StoragePnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(StoragePn);
    }//GEN-LAST:event_StoragePnMouseExited

    private void SalePnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalePnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(SalePn);
    }//GEN-LAST:event_SalePnMouseEntered

    private void SalePnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalePnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(SalePn);
    }//GEN-LAST:event_SalePnMouseExited

    private void ImportPnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportPnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(ImportPn);
    }//GEN-LAST:event_ImportPnMouseEntered

    private void ImportPnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportPnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(ImportPn);
    }//GEN-LAST:event_ImportPnMouseExited

    private void RevenuePnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RevenuePnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(RevenuePn);
    }//GEN-LAST:event_RevenuePnMouseEntered

    private void RevenuePnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RevenuePnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(RevenuePn);
    }//GEN-LAST:event_RevenuePnMouseExited

    private void StaffPnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffPnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(StaffPn);
    }//GEN-LAST:event_StaffPnMouseEntered

    private void StaffPnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffPnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(StaffPn);
    }//GEN-LAST:event_StaffPnMouseExited

    private void LogOutPnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutPnMouseEntered
        // TODO add your handling code here:
        setBGWhenMouseEnter(LogOutPn);
    }//GEN-LAST:event_LogOutPnMouseEntered

    private void LogOutPnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutPnMouseExited
        // TODO add your handling code here:
        setBGWhenMouseExit(LogOutPn);
    }//GEN-LAST:event_LogOutPnMouseExited

    private void ImportPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportPnMouseClicked
        // TODO add your handling code here:
        ImportManagement importManagement = new ImportManagement();
        importManagement.show();
    }//GEN-LAST:event_ImportPnMouseClicked

    private void SalePnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalePnMouseClicked
        // TODO add your handling code here:
        SaleManagement saleManagement = new SaleManagement();
        saleManagement.show();
    }//GEN-LAST:event_SalePnMouseClicked

    private void StoragePnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StoragePnMouseClicked
        // TODO add your handling code here:
        StorageManagement storageManagement = new StorageManagement();
        storageManagement.show();
    }//GEN-LAST:event_StoragePnMouseClicked

    private void CustomerPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPnMouseClicked
        // TODO add your handling code here:
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.show();
        
    }//GEN-LAST:event_CustomerPnMouseClicked

    private void RevenuePnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RevenuePnMouseClicked

        // TODO add your handling code here:
        RevenueManagement revenueManagement = new RevenueManagement();
        revenueManagement.show();
    }//GEN-LAST:event_RevenuePnMouseClicked

    //Check Account Role
    private void StaffPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffPnMouseClicked
        // TODO add your handling code here:
        if(role.equals("Admin"))
        {
            StaffManagement staffManagement = new StaffManagement();
            staffManagement.show();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "You don't have permission to use this feature...");
        }
        
        
    }//GEN-LAST:event_StaffPnMouseClicked

    //Account LogOut
    private void LogOutPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutPnMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
            System.out.println("Yes!");
                Login login = new Login();
                this.dispose();
                login.show();
          } else if (response == JOptionPane.NO_OPTION) {
            System.out.println("No!");
          } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("Cancel!");
          }
    }//GEN-LAST:event_LogOutPnMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        Estimate prediction = new Estimate();
        prediction.show();
    }//GEN-LAST:event_jPanel3MouseClicked

    
    
    
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CustomerPn;
    private javax.swing.JLabel ExitBtn;
    private javax.swing.JPanel ImportPn;
    private javax.swing.JPanel LogOutPn;
    private javax.swing.JLabel LogoLb;
    private javax.swing.JPanel RevenuePn;
    private javax.swing.JLabel RoleLb;
    private javax.swing.JPanel SalePn;
    private javax.swing.JPanel StaffPn;
    private javax.swing.JPanel StoragePn;
    private javax.swing.JLabel WelcomeLb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
