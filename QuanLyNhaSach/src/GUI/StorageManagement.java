/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAL.DBConnection;
import DAL.*;
import BUS.*;
import DTO.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author 19522
 */
public class StorageManagement extends javax.swing.JFrame {

    
    DBConnection connection;
    public String valueMaSach;
    public int TypeCbindex;
    /**
     * Creates new form StorageManagement
     */
    public StorageManagement() {
        initComponents();
        loadAllData();
    }
    
    public void loadAllData() //load All Data from beginning and when update/add s.t
    {
        loadAllBook();
        loadTypeCbData();
    }
    //Get all data from Sach
    public void loadAllBook()
    {
        //Load All Book
        DefaultTableModel table = (DefaultTableModel)SearchBookTable.getModel();
        ArrayList<Sach> arr = new ArrayList<Sach>();
        Sach_BUS sach_BUS= new Sach_BUS();
        arr = sach_BUS.getAllSach();
        Sach sach = new Sach();
        try {
            for(int i=0; i< arr.size();i++)
            {
                sach = arr.get(i);
                String id = sach.getMaSach();
                String name = sach.getTenSach();
                String author = sach.getTenTG();
                String type = sach.getTenTheLoai();
                int amount = sach.getSoLuong();
                float price = sach.getGia();
                Object[] row = {id, name, author,type,amount,price};
                table.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchBookTable.setModel(table);
    }
    
    public void loadTypeCbData()
    {
        //Load TypeComboBox Data
        connection = new DBConnection();
        String query = "select TenTheLoai from Sach";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while(rs.next())
            {
                String name = rs.getString("TenTheLoai");
                TypeCb.addItem(name);
                TypeSelectCb.addItem(name);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        
    }
    
    public void seacrhBookWithFilter()
    {
        //Search Book wwith FIlter
        resetData();
        String name = SearchTxb.getText();
        String type = TypeCb.getSelectedItem().toString();
        if(type == "All")
        {
            type = "";
        }
        String author = AuthorSearchTxb.getText();
        DefaultTableModel table = (DefaultTableModel) SearchBookTable.getModel();
        ArrayList<Sach> arr = new ArrayList<Sach>();
        Sach_BUS sach_BUS = new Sach_BUS();
        arr = sach_BUS.searchSach(name, type, author);
        Sach sach = new Sach();
        try {
            for(int i=0; i< arr.size();i++)
            {
                sach = arr.get(i);
                String id = sach.getMaSach();
                String name1 = sach.getTenSach();
                String author1 = sach.getTenTG();
                String type1 = sach.getTenTheLoai();
                int amount = sach.getSoLuong();
                double price = sach.getGia();
                Object[] row = {id, name1, author1,type1,amount,price};
                table.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchBookTable.setModel(table);
    }
    
    
    public void resetData() // reset the Jtable to null
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchBookTable.getModel();
        tableModel.setRowCount(0);
        SearchBookTable.setModel(tableModel);
    }
    
    public void resetCbData() //Reset Combobox Data
    {
        TypeCb.removeAllItems();
        TypeCb.addItem("All");
        TypeSelectCb.removeAllItems();
        TypeSelectCb.addItem("All");
    }
    
    public void resetText() // reset textfield after event done!
    {
        SearchTxb.setText("");
        NameTxb.setText("");
        NameTxb.enable(true);
        TypeTxb.setText("");
        PriceTxb.setText("");
        TypeTxb.setText("");
        AuthorTxb.setText("");
        AuthorSearchTxb.setText("");
        AmountTxb.setText("");
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
        DefaultTableModel tableModel = (DefaultTableModel) SearchBookTable.getModel();
            //Get Data from Table
            valueMaSach = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 0).toString();
            String name = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 1).toString();
            String author = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 2).toString();
            String type = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 3).toString();
            int amount = Integer.parseInt(tableModel.getValueAt(SearchBookTable.getSelectedRow(), 4).toString());
            String price = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 5).toString();
            //Set TextField
            NameTxb.setText(name);
            AuthorTxb.setText(author);
            TypeSelectCb.setSelectedItem(type);
            AmountTxb.setText(String.valueOf(amount));
            PriceTxb.setText(price);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JLabel();
        ParentPanel = new javax.swing.JTabbedPane();
        SearchTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SearchBookTable = new javax.swing.JTable();
        SearchBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        EditChangeBtn = new javax.swing.JButton();
        AllRadio = new javax.swing.JRadioButton();
        FilterRadio = new javax.swing.JRadioButton();
        NewChangeBtn = new javax.swing.JButton();
        AuthorSearchTxb = new javax.swing.JTextField();
        TypeCb = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SearchTxb = new javax.swing.JTextField();
        AddTab = new javax.swing.JPanel();
        NameTxb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AuthorTxb = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TypeTxb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PriceTxb = new javax.swing.JTextField();
        AddBookBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        UpdateBookBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        TypeSelectCb = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        AmountTxb = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STORAGE MANAGEMENT");

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

        SearchTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SearchBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Author", "Type", "Amout", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SearchBookTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SearchBookTable.setGridColor(new java.awt.Color(0, 0, 0));
        SearchBookTable.setShowGrid(true);
        jScrollPane1.setViewportView(SearchBookTable);
        SearchBookTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        SearchTab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1000, 490));

        SearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn.setToolTipText("Search");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        SearchTab.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 40, 110, 50));

        DeleteBtn.setBackground(new java.awt.Color(255, 51, 102));
        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("DELETE");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        SearchTab.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 520, 116, 51));

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
        SearchTab.add(EditChangeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 440, 116, 51));

        buttonGroup1.add(AllRadio);
        AllRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AllRadio.setSelected(true);
        AllRadio.setText("All");
        SearchTab.add(AllRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        buttonGroup1.add(FilterRadio);
        FilterRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FilterRadio.setText("Filter");
        FilterRadio.setToolTipText("");
        SearchTab.add(FilterRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        NewChangeBtn.setBackground(new java.awt.Color(153, 255, 153));
        NewChangeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NewChangeBtn.setText("NEW");
        NewChangeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NewChangeBtnMouseClicked(evt);
            }
        });
        NewChangeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewChangeBtnActionPerformed(evt);
            }
        });
        SearchTab.add(NewChangeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 360, 116, 51));

        AuthorSearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AuthorSearchTxb.setToolTipText("Search Here....");
        SearchTab.add(AuthorSearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 170, 42));

        TypeCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        SearchTab.add(TypeCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 182, 42));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Type");
        SearchTab.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Author");
        SearchTab.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, -1));

        SearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchTxb.setText("Search name ...");
        SearchTxb.setToolTipText("Search Here....");
        SearchTab.add(SearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 321, 42));

        ParentPanel.addTab("Search Book", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png")), SearchTab); // NOI18N

        AddTab.setBackground(new java.awt.Color(255, 255, 255));
        AddTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NameTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NameTxb.setToolTipText("");
        NameTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(NameTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 431, 52));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Name :");
        AddTab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Author(s) :");
        AddTab.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, -1, -1));

        AuthorTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AuthorTxb.setToolTipText("");
        AuthorTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(AuthorTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 260, 52));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Amount :");
        AddTab.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, -1, -1));

        TypeTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TypeTxb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TypeTxb.setToolTipText("");
        TypeTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(TypeTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 210, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Price :");
        AddTab.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, -1, -1));

        PriceTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PriceTxb.setToolTipText("");
        PriceTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(PriceTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 200, 50));

        AddBookBtn.setBackground(new java.awt.Color(153, 255, 153));
        AddBookBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddBookBtn.setText("ADD BOOK");
        AddBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBookBtnActionPerformed(evt);
            }
        });
        AddTab.add(AddBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 471, 244, 51));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/icons8_open_book_250px.png"))); // NOI18N
        AddTab.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 99, -1, -1));

        UpdateBookBtn.setBackground(new java.awt.Color(255, 204, 204));
        UpdateBookBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateBookBtn.setText("UPDATE BOOK");
        UpdateBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBookBtnActionPerformed(evt);
            }
        });
        AddTab.add(UpdateBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 471, 244, 51));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Or");
        AddTab.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 240, -1, -1));

        TypeSelectCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        TypeSelectCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(TypeSelectCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 200, 50));

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Type :");
        AddTab.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, -1, -1));

        AmountTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AmountTxb.setToolTipText("");
        AmountTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(AmountTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 200, 50));

        ParentPanel.addTab("Add Book", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_Plus_+_35px.png")), AddTab); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void EditChangeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditChangeBtnMouseClicked
        ParentPanel.setSelectedIndex(1);
    }//GEN-LAST:event_EditChangeBtnMouseClicked

    private void NewChangeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewChangeBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NewChangeBtnMouseClicked

    private void BackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseClicked
        // TODO add your handling code here:
        this.dispose(); 
    }//GEN-LAST:event_BackBtnMouseClicked

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        // Search function
        if(AllRadio.isSelected() == true) //Search All
        {
            resetData();
            loadAllData();
        }
        else if(FilterRadio.isSelected() == true) //Search wwith Filter
        {
            seacrhBookWithFilter();
            resetCbData();
            loadTypeCbData();
        }
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void NewChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewChangeBtnActionPerformed
        // Move to panel Add Book wwith function Add
        ParentPanel.setSelectedIndex(1);
        UpdateBookBtn.setEnabled(false);
        AddBookBtn.setEnabled(true);
    }//GEN-LAST:event_NewChangeBtnActionPerformed

    private void EditChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditChangeBtnActionPerformed
        // Move to panel Add Book wwith function Edit
        try {
            SelectRow();
            ParentPanel.setSelectedIndex(1);
            UpdateBookBtn.setEnabled(true);
            AddBookBtn.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Book have selected!");
        }
    }//GEN-LAST:event_EditChangeBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        // Delete
        try {
            SelectRow();
            Sach_BUS sach_BUS = new Sach_BUS();
            sach_BUS.deleteSach(valueMaSach);
            JOptionPane.showMessageDialog(this, "Delete Book success!");
            resetData();
            resetCbData();
            resetText();
            loadAllBook();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Book have selected!");
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void AddBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBookBtnActionPerformed
        // Add Book
        Sach sach = new Sach();
        String theloaiString;
        //getTheLoai
            if(TypeTxb.getText().equals("")) //Check Null
            {
                if(TypeSelectCb.getSelectedItem().toString().equals("All"))
                {
                    sach.setTenTheLoai("");
                    theloaiString = "";
                }
                else
                {
                    sach.setTenTheLoai(TypeSelectCb.getSelectedItem().toString());
                    theloaiString = TypeSelectCb.getSelectedItem().toString();
                }
            }
            else
            {
                sach.setTenTheLoai(TypeTxb.getText());
                theloaiString = TypeTxb.getText();
            }
        Sach_BUS sach_BUS = new Sach_BUS();
        ArrayList<Sach> existarr = new ArrayList<Sach>();
        existarr = sach_BUS.searchSachExist(NameTxb.getText(), theloaiString , AuthorTxb.getText());
        if(NameTxb.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please fill atleast the name of book...");
        }
        else if(existarr.size()>0)
        {
            JOptionPane.showMessageDialog(this, "Already Exist...");
        }
        else
        {
            sach.setMaSach("b" + randomID());
            sach.setTenSach(NameTxb.getText());
            sach.setTenTG(AuthorTxb.getText());
            sach.setSoLuong(Integer.parseInt(AmountTxb.getText()));
            sach.setGia(Float.parseFloat(PriceTxb.getText()));
            sach_BUS.addSach(sach);
            JOptionPane.showMessageDialog(this, "Add Book success!");
            resetData();
            resetCbData();
            resetText();
            loadAllBook();
            ParentPanel.setSelectedIndex(0);
            UpdateBookBtn.setEnabled(true);
        }
        
        
        
    }//GEN-LAST:event_AddBookBtnActionPerformed

    private void UpdateBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBookBtnActionPerformed
        // Update Book
        Sach sach = new Sach();
        String theloaiString;
        //getTheLoai
            if(TypeTxb.getText().equals("")) //Check Null
            {
                if(TypeSelectCb.getSelectedItem().toString().equals("All"))
                {
                    sach.setTenTheLoai("");
                    theloaiString = "";
                }
                else
                {
                    sach.setTenTheLoai(TypeSelectCb.getSelectedItem().toString());
                    theloaiString = TypeSelectCb.getSelectedItem().toString();
                }
            }
            else
            {
                sach.setTenTheLoai(TypeTxb.getText());
                theloaiString = TypeTxb.getText();
            }
            Sach_BUS sach_BUS = new Sach_BUS();
            ArrayList<Sach> existarr = new ArrayList<Sach>();
            existarr = sach_BUS.searchSachExistNoUpdate(NameTxb.getText(), theloaiString , AuthorTxb.getText(), Integer.parseInt(AmountTxb.getText()), Float.parseFloat(PriceTxb.getText()));
            
            if(NameTxb.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please fill atleast the name of book...");
            }
            else if(existarr.size()>0)
            {
                JOptionPane.showMessageDialog(this, "No Update...");
            }
            else
            {
                sach.setMaSach(valueMaSach);
                sach.setTenSach(NameTxb.getText());
                sach.setTenTG(AuthorTxb.getText());
                sach.setSoLuong(Integer.parseInt(AmountTxb.getText()));
                sach.setGia(Float.parseFloat(PriceTxb.getText()));
                sach_BUS.updateSach(sach);
                JOptionPane.showMessageDialog(this, "Update Book success!");
                resetData();
                resetCbData();
                resetText();
                loadAllData();
                ParentPanel.setSelectedIndex(0);
                AddBookBtn.setEnabled(true);
            }
            
    }//GEN-LAST:event_UpdateBookBtnActionPerformed

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
            java.util.logging.Logger.getLogger(StorageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StorageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StorageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StorageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StorageManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBookBtn;
    private javax.swing.JPanel AddTab;
    private javax.swing.JRadioButton AllRadio;
    private javax.swing.JTextField AmountTxb;
    private javax.swing.JTextField AuthorSearchTxb;
    private javax.swing.JTextField AuthorTxb;
    private javax.swing.JLabel BackBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton EditChangeBtn;
    private javax.swing.JRadioButton FilterRadio;
    private javax.swing.JTextField NameTxb;
    private javax.swing.JButton NewChangeBtn;
    private javax.swing.JTabbedPane ParentPanel;
    private javax.swing.JTextField PriceTxb;
    private javax.swing.JTable SearchBookTable;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JPanel SearchTab;
    private javax.swing.JTextField SearchTxb;
    private javax.swing.JComboBox<String> TypeCb;
    private javax.swing.JComboBox<String> TypeSelectCb;
    private javax.swing.JTextField TypeTxb;
    private javax.swing.JButton UpdateBookBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
