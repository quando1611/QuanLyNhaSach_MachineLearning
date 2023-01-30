/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.*;
import BUS.*;
import DAL.DBConnection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 19522
 */
public class StaffManagement extends javax.swing.JFrame {
    DBConnection connection;
    public String valueMaTK;
    public int RoleCbIndex;

    /**
     * Creates new form StaffManagement
     */
    public StaffManagement() {
        initComponents();
        loadAllTaiKhoan();
        loadRoleCbData();
    }
    
    //Load all Account wwith All role
    public void loadAllTaiKhoan()
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchTable.getModel();
        ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
        TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
        arr = taikhoan_BUS.getAllTaiKhoan();
        TaiKhoan taikhoan = new TaiKhoan();
        try
        {
            for(int i = 0; i < arr.size(); i++)
            {
                taikhoan = arr.get(i);
                String id = taikhoan.getMaTK();
                String email = taikhoan.getEmail();
                String matkhau = taikhoan.getMatKhau();
                String hoten = taikhoan.getHoTen();
                String diachi = taikhoan.getDiaChi();
                String sdt = taikhoan.getSDT();
                Object[]row = {id, email, matkhau, hoten, diachi, sdt };
                tableModel.addRow(row);
            }
        }catch (Exception e)
        {
            System.err.println("Not thing to show");
        }
        SearchTable.setModel(tableModel);
        
    }
    
    //load All role Admin Account
    public void loadAllAdmin()
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchTable.getModel();
        ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
        TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
        arr = taikhoan_BUS.getAllAdmin();
        TaiKhoan taikhoan = new TaiKhoan();
        try
        {
            for(int i = 0; i < arr.size(); i++)
            {
                taikhoan = arr.get(i);
                String id = taikhoan.getMaTK();
                String email = taikhoan.getEmail();
                String matkhau = taikhoan.getMatKhau();
                String hoten = taikhoan.getHoTen();
                String diachi = taikhoan.getDiaChi();
                String sdt = taikhoan.getSDT();
                Object[]row = {id, email, matkhau, hoten, diachi, sdt };
                tableModel.addRow(row);
            }
        }catch (Exception e)
        {
            System.err.println("No thing to show");
        }
        SearchTable.setModel(tableModel);
    }
    
    //load All role Staff Account
    public void loadAllStaff()
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchTable.getModel();
        ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
        TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
        arr = taikhoan_BUS.getAllStaff();
        TaiKhoan taikhoan = new TaiKhoan();
        try
        {
            for(int i = 0; i < arr.size(); i++)
            {
                taikhoan = arr.get(i);
                String id = taikhoan.getMaTK();
                String email = taikhoan.getEmail();
                String matkhau = taikhoan.getMatKhau();
                String hoten = taikhoan.getHoTen();
                String diachi = taikhoan.getDiaChi();
                String sdt = taikhoan.getSDT();
                Object[]row = {id, email, matkhau, hoten, diachi, sdt };
                tableModel.addRow(row);
            }
        }catch (Exception e)
        {
            System.err.println("Not thing to show");
        }
        SearchTable.setModel(tableModel);
    }
    
    //Load Role COmbobox Data
    public void loadRoleCbData()
    {
        connection = new DBConnection();
        String query = "select MaPhanQuyen from PhanQuyen";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while(rs.next())
            {
                String name = rs.getString("MaPhanQuyen");
                RoleCb.addItem(name);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        
    }
    
    public void reset() // reset the Jtable to null
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchTable.getModel();
        tableModel.setRowCount(0);
        SearchTable.setModel(tableModel);
    }
    
    public void ResetText() //reset Text affter done function
    {
        SearchTxb.setText("");
        EmailText.setText("");
        PassText.setText("");
        NameText.setText("");
        AddressText.setText("");
        PhoneText.setText("");
    }
    
    
    
    
    public String randomID() // get random to generate ID for all of things
    {
        LocalDateTime local = LocalDateTime.now();
        long milis = local.getNano();
        String id = Long.toString(milis);
        System.err.println(id);
        return id;
    }
    
    public void SelectRow() //function get information wwhen select row from table
    {
        //Get data from table
        DefaultTableModel tableModel = (DefaultTableModel) SearchTable.getModel();
        valueMaTK = tableModel.getValueAt(SearchTable.getSelectedRow(), 0).toString();
        String email = tableModel.getValueAt(SearchTable.getSelectedRow(), 1).toString();
        String pass = tableModel.getValueAt(SearchTable.getSelectedRow(), 2).toString();
        String name = tableModel.getValueAt(SearchTable.getSelectedRow(), 3).toString(); 
        String address = tableModel.getValueAt(SearchTable.getSelectedRow(), 4).toString();
        String phone = tableModel.getValueAt(SearchTable.getSelectedRow(), 5).toString();
        //Set TextField
        EmailText.setText(email);
        PassText.setText(pass);
        NameText.setText(name);
        AddressText.setText(address);
        PhoneText.setText(phone);
        RoleCb.setSelectedItem("Sale");
    }
       
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FilterStaffGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JLabel();
        ParentPanel = new javax.swing.JTabbedPane();
        SearchTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SearchTable = new javax.swing.JTable();
        SearchTxb = new javax.swing.JTextField();
        SearchBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        EditChangeBtn = new javax.swing.JButton();
        AllRadio = new javax.swing.JRadioButton();
        AdminRadio = new javax.swing.JRadioButton();
        StaffRadio = new javax.swing.JRadioButton();
        NewChangeButton = new javax.swing.JButton();
        AddTab = new javax.swing.JPanel();
        EmailText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PhoneText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NameText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        AddStaffBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        UpdateStaffBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        RoleCb = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PassText = new javax.swing.JTextField();
        AddressText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STAFF MANAGEMENT");

        BackBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_previous_70px.png"))); // NOI18N
        BackBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackBtn)
                .addGap(373, 373, 373)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BackBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        ParentPanel.setBackground(new java.awt.Color(153, 153, 255));
        ParentPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        SearchTab.setBackground(new java.awt.Color(255, 255, 255));
        SearchTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SearchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Email", "Pass", "Name", "Address", "PhoneNumber"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SearchTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SearchTable.setGridColor(new java.awt.Color(0, 0, 0));
        SearchTable.setShowGrid(true);
        jScrollPane1.setViewportView(SearchTable);
        SearchTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        SearchTab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 590));

        SearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchTxb.setText("Search here ...");
        SearchTxb.setToolTipText("Search Here....");
        SearchTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchTab.add(SearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, 250, 42));

        SearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn.setToolTipText("Search");
        SearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchBtnMouseClicked(evt);
            }
        });
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        SearchTab.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 60, 60, 40));

        DeleteBtn.setBackground(new java.awt.Color(255, 51, 102));
        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("DELETE");
        DeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteBtnMouseClicked(evt);
            }
        });
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        SearchTab.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 510, 116, 51));

        EditChangeBtn.setBackground(new java.awt.Color(255, 204, 204));
        EditChangeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditChangeBtn.setText("EDIT");
        EditChangeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditChangeBtnMouseClicked(evt);
            }
        });
        EditChangeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditChangeBtnActionPerformed(evt);
            }
        });
        SearchTab.add(EditChangeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 438, 116, 51));

        FilterStaffGroup.add(AllRadio);
        AllRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AllRadio.setSelected(true);
        AllRadio.setText("All");
        AllRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllRadioActionPerformed(evt);
            }
        });
        SearchTab.add(AllRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(953, 9, -1, -1));

        FilterStaffGroup.add(AdminRadio);
        AdminRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AdminRadio.setText("Admin");
        AdminRadio.setToolTipText("");
        AdminRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminRadioActionPerformed(evt);
            }
        });
        SearchTab.add(AdminRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        FilterStaffGroup.add(StaffRadio);
        StaffRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StaffRadio.setText("Staff");
        StaffRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffRadioActionPerformed(evt);
            }
        });
        SearchTab.add(StaffRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, -1, -1));

        NewChangeButton.setBackground(new java.awt.Color(153, 255, 153));
        NewChangeButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NewChangeButton.setText("NEW");
        NewChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewChangeButtonActionPerformed(evt);
            }
        });
        SearchTab.add(NewChangeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 349, 116, 51));

        ParentPanel.addTab("Search Staff", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png")), SearchTab); // NOI18N

        AddTab.setBackground(new java.awt.Color(255, 255, 255));
        AddTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        EmailText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmailText.setToolTipText("");
        EmailText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(EmailText, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 431, 52));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Email :");
        AddTab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Password :");
        AddTab.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 130, -1));

        PhoneText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PhoneText.setToolTipText("");
        PhoneText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PhoneText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PhoneTextKeyTyped(evt);
            }
        });
        AddTab.add(PhoneText, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 310, 430, 52));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Phone Number :");
        AddTab.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, -1, -1));

        NameText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NameText.setToolTipText("");
        NameText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NameTextKeyTyped(evt);
            }
        });
        AddTab.add(NameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 470, 52));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Role :");
        AddTab.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, -1, -1));

        AddStaffBtn.setBackground(new java.awt.Color(153, 255, 153));
        AddStaffBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddStaffBtn.setText("ADD STAFF");
        AddStaffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStaffBtnActionPerformed(evt);
            }
        });
        AddTab.add(AddStaffBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 244, 51));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/istockphoto-1223671392-612x612-1.jpg"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 99, -1, -1));

        UpdateStaffBtn.setBackground(new java.awt.Color(255, 204, 204));
        UpdateStaffBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateStaffBtn.setText("UPDATE STAFF");
        UpdateStaffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateStaffBtnActionPerformed(evt);
            }
        });
        AddTab.add(UpdateStaffBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 480, 244, 51));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        AddTab.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 15, 3, 490));

        RoleCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(RoleCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, 240, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Full Name :");
        AddTab.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Address :");
        AddTab.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        PassText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PassText.setToolTipText("");
        PassText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(PassText, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 430, 52));

        AddressText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AddressText.setToolTipText("");
        AddressText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(AddressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 430, 52));

        ParentPanel.addTab("Add Staff", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_Plus_+_35px.png")), AddTab); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ParentPanel)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        //Search Staff
        reset();
        String search = SearchTxb.getText();
        DefaultTableModel tableModel = (DefaultTableModel) SearchTable.getModel();
        ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
        TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
        arr = taikhoan_BUS.searchTaiKhoan(search);
        TaiKhoan taikhoan = new TaiKhoan();
        try {
            for(int i = 0; i < arr.size(); i++)
            {
                taikhoan = arr.get(i);
                String id = taikhoan.getMaTK();
                String hoten = taikhoan.getHoTen();
                String diachi = taikhoan.getDiaChi();
                String sdt = taikhoan.getSDT();
                String email = taikhoan.getEmail();
                Object[]row = {id, hoten, diachi, sdt, email};
                tableModel.addRow(row);        
            }
        }catch (Exception e) {
            System.err.println("Not thing!");
        }
        SearchTable.setModel(tableModel);
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void AddStaffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStaffBtnActionPerformed
         // Add Staff
        TaiKhoan taikhoan = new TaiKhoan();
        ArrayList<TaiKhoan> existarr = new ArrayList<TaiKhoan>();
        TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
        existarr = taikhoan_BUS.checkExist(EmailText.getText(), NameText.getText(), AddressText.getText(), PhoneText.getText());
        if(NameText.getText().equals("")) //Check Null
        {
            JOptionPane.showMessageDialog(this, "Please fill atleast the name of the staff...");
        }
        else if(existarr.size() >0) //Check Staff exist already
        {
            JOptionPane.showMessageDialog(this, "Already exist this staff/admin...");
        }
        else
        {
            taikhoan.setMaTK("A" + randomID());
            taikhoan.setEmail(EmailText.getText());
            taikhoan.setMatKhau(PassText.getText());
            taikhoan.setHoTen(NameText.getText());
            taikhoan.setDiaChi(AddressText.getText());
            taikhoan.setSDT(PhoneText.getText());
            taikhoan.setMaPhanQuyen(RoleCb.getSelectedItem().toString());
            taikhoan_BUS.addTaiKhoan(taikhoan);
            JOptionPane.showMessageDialog(this, "Add Staff successfully!");
            reset();
            ResetText();
            loadAllStaff();
            ParentPanel.setSelectedIndex(0);
            UpdateStaffBtn.setEnabled(true);
        }
        
    }//GEN-LAST:event_AddStaffBtnActionPerformed

    private void UpdateStaffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateStaffBtnActionPerformed
        // Update Staff
            TaiKhoan taikhoan = new TaiKhoan();
            ArrayList<TaiKhoan> existarr = new ArrayList<TaiKhoan>();
            TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
            existarr = taikhoan_BUS.checkExist(EmailText.getText(), NameText.getText(), AddressText.getText(), PhoneText.getText());
            if(NameText.getText().equals("")) //check Null
            {
                JOptionPane.showMessageDialog(this, "Please fill atleast the name of staff...");
            }
            else if(existarr.size() > 0) //Check exist staff already
            {
                JOptionPane.showMessageDialog(this, "Already existed this staff/admin...");
            }
            else
            {
                taikhoan.setMaTK(valueMaTK);
                taikhoan.setEmail(EmailText.getText());
                taikhoan.setMatKhau(PassText.getText());
                taikhoan.setHoTen(NameText.getText());
                taikhoan.setMaPhanQuyen(RoleCb.getSelectedItem().toString());
                taikhoan.setDiaChi(AddressText.getText());
                taikhoan.setSDT(PhoneText.getText());
                taikhoan_BUS.updateTaiKhoan(taikhoan);
                JOptionPane.showMessageDialog(this, "Update Staff successfully!");
                reset();
                ResetText();
                loadAllStaff();
                StaffRadio.setSelected(true);
                ParentPanel.setSelectedIndex(0);
                AddStaffBtn.setEnabled(true);
            }

    }//GEN-LAST:event_UpdateStaffBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        //Delete Staff
        try {
            SelectRow();
            TaiKhoan_BUS taikhoan_BUS = new TaiKhoan_BUS();
            taikhoan_BUS.deleteTaiKhoan(valueMaTK);
            JOptionPane.showMessageDialog(this, "Delete Staff successfully!");
            reset();
            ResetText();
            loadAllTaiKhoan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Staff have been selected!");
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void NewChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewChangeButtonActionPerformed
        // Move to panel Add Staff with function Add
        ParentPanel.setSelectedIndex(1);
        AddStaffBtn.setEnabled(true);
        UpdateStaffBtn.setEnabled(false);
    }//GEN-LAST:event_NewChangeButtonActionPerformed

    private void EditChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditChangeBtnActionPerformed
        // Move to panel Add Staff with function Edit
        try {
            SelectRow();
            AddStaffBtn.setEnabled(false);
            UpdateStaffBtn.setEnabled(true);
            ParentPanel.setSelectedIndex(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Staff have been selected!");
        }
    }//GEN-LAST:event_EditChangeBtnActionPerformed

    private void AdminRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminRadioActionPerformed
        // Search Admin Radio 
        if(AdminRadio.isSelected() == true);
        {
            reset();
            loadAllAdmin();
        }
    }//GEN-LAST:event_AdminRadioActionPerformed

    private void BackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BackBtnMouseClicked

    private void AllRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllRadioActionPerformed

        // Search All Radio
        if(AllRadio.isSelected() == true)
        {
            reset();
            loadAllTaiKhoan();
        }
    }//GEN-LAST:event_AllRadioActionPerformed

    private void StaffRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffRadioActionPerformed
        // Search Staff Radio
        if(StaffRadio.isSelected() == true)
        {
            reset();
            loadAllStaff();
        }
    }//GEN-LAST:event_StaffRadioActionPerformed

    private void SearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBtnMouseClicked

    private void EditChangeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditChangeBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EditChangeBtnMouseClicked

    private void DeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteBtnMouseClicked

    private void NameTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameTextKeyTyped
        // NameTxb only Text
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_NameTextKeyTyped

    private void PhoneTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneTextKeyTyped
        // PhoneTxb only Number
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_PhoneTextKeyTyped

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
            java.util.logging.Logger.getLogger(StaffManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddStaffBtn;
    private javax.swing.JPanel AddTab;
    private javax.swing.JTextField AddressText;
    private javax.swing.JRadioButton AdminRadio;
    private javax.swing.JRadioButton AllRadio;
    private javax.swing.JLabel BackBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton EditChangeBtn;
    private javax.swing.JTextField EmailText;
    private javax.swing.ButtonGroup FilterStaffGroup;
    private javax.swing.JTextField NameText;
    private javax.swing.JButton NewChangeButton;
    private javax.swing.JTabbedPane ParentPanel;
    private javax.swing.JTextField PassText;
    private javax.swing.JTextField PhoneText;
    private javax.swing.JComboBox<String> RoleCb;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JPanel SearchTab;
    private javax.swing.JTable SearchTable;
    private javax.swing.JTextField SearchTxb;
    private javax.swing.JRadioButton StaffRadio;
    private javax.swing.JButton UpdateStaffBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
