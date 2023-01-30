 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.*;
import DAL.*;
import DTO.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 19522
 */
public class ImportManagement extends javax.swing.JFrame {
    
    DBConnection connection;
    private String valueMaSach;
    public String valueMaNCC;
    public String dateCreateBill;
    private double total = 0.0;
    private int maximum = 0;
    private ArrayList<Sach> selectarr = new ArrayList<Sach>();
    private boolean exist = false;
    private String BillID;
    private String supplierSelectedID = "";
    /**
     * Creates new form Storage
     */
    public ImportManagement() {
        initComponents();
        loadAllData();
    }
    
    public void loadAllData() //Load all data from the begining
    {
        loadAllBook();
        loadAllSupplier();
        loadTypeCbData();
        loadAllBill();
        loadCurrentDate();
        loadSupplierCBData();
    }
    
    public void loadAllBook() //Load all book from the begining
    {
        DefaultTableModel table = (DefaultTableModel) SearchBookTable.getModel();
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
            System.err.println("Not thing to show!");
        }
        SearchBookTable.setModel(table);
    }
    
    private void loadAllBill() {
        //Load All bill to  BillShowTable
        DefaultTableModel table = (DefaultTableModel) BillShowTable.getModel();
        ArrayList<PhieuNhapKho> arr = new ArrayList<PhieuNhapKho>();
        PhieuNhapKho_BUS phieunhapkho_BUS = new PhieuNhapKho_BUS();
        arr = phieunhapkho_BUS.danhsachPhieuNhapKho();
        PhieuNhapKho phieunhapkho = new PhieuNhapKho();
        {
            try {
                    for (int i = 0; i < arr.size(); i++) {
                    phieunhapkho = arr.get(i);
                    String id = phieunhapkho.getMaPhieuNhap();
                    String nhacungcap = phieunhapkho.getMaNhaCC();
                    String maTK = phieunhapkho.getMaTK();
                    String ngaynhapkho = phieunhapkho.getNgayNhapKho();
                    String ngaytaophieu = phieunhapkho.getNgayTaoPhieu();
                    Double tongtien = phieunhapkho.getTongTien();     
                    Object[] row = {id, ngaynhapkho, ngaytaophieu, maTK ,nhacungcap, tongtien };
                    table.addRow(row);
                }
            } catch (Exception e) {
                System.err.println("No thing!");
            }
            BillShowTable.setModel(table);
        }
    }

    private void loadCurrentDate() {
        //get the current day by default for JDateChooser - DatePicker
        Calendar today = Calendar.getInstance();
        DateBox.setCalendar(today);
        dateCreateBill = ((JTextField) DateBox.getDateEditor().getUiComponent()).getText();
    }

    
    private void loadAllSupplier() //load all Supplier from the begining
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchSupplierTable.getModel();
        ArrayList<NhaCungCap> arr = new ArrayList<NhaCungCap>();
        NhaCungCap_BUS nhacungcap_BUS = new NhaCungCap_BUS();
        arr = nhacungcap_BUS.getNhaCungCap();
        NhaCungCap nhacungcap = new NhaCungCap();
        try {
            for(int i = 0; i< arr.size(); i++)
            {
                nhacungcap = arr.get(i);
                String id = nhacungcap.getMaNhaCC();
                String name = nhacungcap.getTenNhaCC();
                Object[] row = {id, name};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchSupplierTable.setModel(tableModel);
    }
    
    public void loadTypeCbData() //load type data to the Data ComboBox
    {
        connection = new DBConnection();
        String query = "select TenTheLoai from Sach";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while(rs.next())
            {
                String name = rs.getString("TenTheLoai");
                TypeCb.addItem(name);
            }
        } catch (Exception e) {
            System.err.println("Not thing to show!");
        }
    }
    
    public void loadSupplierCBData()
    {
        connection = new DBConnection();
        String query = "select TenNCC from NhaCungCap";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while(rs.next())
            {
                String name = rs.getString("TenNCC");
                SupplierNameCb.addItem(name);
                SuppliersID.addItem(name);
            }
        } catch (Exception e) {
            System.err.println("No thing to show!");
        }
    }
    public String getBookName(String id) {      //Get bookname by ID
        connection = new DBConnection();
        String name = "";
        String query = "select * from Sach where MaSach ='" + id + "'";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {
                name = rs.getString("TenSach");
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        return name;
    }
    
    public void resetSearchBookData() // reset SelectBookTable data to null
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchBookTable.getModel();
        tableModel.setRowCount(0);
        SearchBookTable.setModel(tableModel);
    }
    
    public void resetSupplier() // reset the Jtable to null
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchSupplierTable.getModel();
        tableModel.setRowCount(0);
        SearchSupplierTable.setModel(tableModel);
    }
    public void seacrhBookWithFilter() //Search books with filter
    {
        resetSearchBookData();
        String name = SupplySearchTxb.getText();
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
                String ma = sach.getMaSach();
                String ten = sach.getTenSach();
                String tacgia = sach.getTenTG();
                String loai = sach.getTenTheLoai();
                int soluong = sach.getSoLuong();
                double gia = sach.getGia();
                Object[] row = {ma, ten, tacgia, loai, soluong, gia};
                table.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchBookTable.setModel(table);
    }
    
     public void resetCbData() //Reset Combobox Data
    {
        TypeCb.removeAllItems();
        TypeCb.addItem("All");
        SupplierNameCb.removeAllItems();
        SupplierNameCb.addItem("All");
    }
    
    public void resetText() // reset textfield after event done!
    {
        SearchText.setText("");
        NameSupplierTxb.setText("");
        AuthorSearchTxb.setText("");
        ImportBillText.setText("");
        TotalText.setText("");
        total = 0;
    }
    
    public void resetSearchBookTable() { //reset SelectBookTable to Null
        DefaultTableModel tableModel = (DefaultTableModel) SearchBookTable.getModel();
        tableModel.setRowCount(0);
        SearchBookTable.setModel(tableModel);
        exist = false;
    }
    
    public void resetBillTable() { //reset SelectBookTable to Null
        DefaultTableModel tableModel = (DefaultTableModel) BillTable.getModel();
        tableModel.setRowCount(0);
        BillTable.setModel(tableModel);
        exist = false;
    }

    public void resetDetailBillTable() {    //reset DetailBillTable to Null
        DefaultTableModel tableModel = (DefaultTableModel) DetailBillTable.getModel();
        tableModel.setRowCount(0);
        DetailBillTable.setModel(tableModel);
    }

    public void resetBillShowTable() //reset BillShowTable to Null
    {
        DefaultTableModel tableModel = (DefaultTableModel) BillShowTable.getModel();
        tableModel.setRowCount(0);
        BillShowTable.setModel(tableModel);
    }

    
     public String randomID() // get random to generate ID for all of things
    {
        LocalDateTime local = LocalDateTime.now();
        long milis = local.getNano();
        String id = Long.toString(milis);
        System.err.println(id);
        return id;
    }
    
    public void SelectRow() //Select row from SearchBookTable to add into BillTable
    {
        total = 0; //when reset -> total = 0 & calculate again from the start
        DefaultTableModel choosetable = (DefaultTableModel) SearchBookTable.getModel();
        DefaultTableModel billtable = (DefaultTableModel) BillTable.getModel();     
        String id = choosetable.getValueAt(SearchBookTable.getSelectedRow(), 0).toString();
        String name = choosetable.getValueAt(SearchBookTable.getSelectedRow(), 1).toString();
        String author = choosetable.getValueAt(SearchBookTable.getSelectedRow(), 2).toString();
        String type = choosetable.getValueAt(SearchBookTable.getSelectedRow(), 3).toString();
        int amount = Integer.parseInt(AmountTxb.getText());   
        maximum = Integer.parseInt(choosetable.getValueAt(SearchBookTable.getSelectedRow(), 4).toString());
        Float price = Float.parseFloat(choosetable.getValueAt(SearchBookTable.getSelectedRow(), 5).toString());
        System.out.println(maximum);
            //insert in to SelectBookTable
            Sach sach = new Sach();
            sach.setMaSach(id);
            sach.setTenSach(name);
            sach.setTenTG(author);
            sach.setTenTheLoai(type);
            sach.setSoLuong(amount);
            sach.setGia(price * (float)PublicClass.ImportPrice);
            if (selectarr.size() > 0) {
                for (Sach existsach : selectarr) {
                    if (existsach.getMaSach().equals(id)) {
                        existsach.setSoLuong(existsach.getSoLuong() + amount);
                        exist = true;
                        break;
                    } else {
                        exist = false;
                    }
                }
                if (exist == false) {
                    selectarr.add(sach);
                }
            } else {
                selectarr.add(sach);
            }
            resetBillTable(); 
            //add to BillTable
            for (Sach sachFinal : selectarr) {
                String idFinal = sachFinal.getMaSach();
                String nameFinal = sachFinal.getTenSach();
                String authorFinal = sachFinal.getTenTG();
                String typeFinal = sachFinal.getTenTheLoai();
                int amountFinal = sachFinal.getSoLuong();
                double priceFinal = sachFinal.getGia();
                Object[] row = {idFinal, nameFinal, amountFinal, priceFinal};
                billtable.addRow(row);
                total += amountFinal * priceFinal;
            }
            BillTable.setModel(billtable);
            TotalText.setText(Double.toString(total));

    }
    
    public void selectImportBillRow()
    {
        DefaultTableModel tableModel = (DefaultTableModel) BillShowTable.getModel();
        DefaultTableModel tableDetailModel = (DefaultTableModel) DetailBillTable.getModel();
        //Get Data from Table
        String id = tableModel.getValueAt(BillShowTable.getSelectedRow(), 0).toString(); //Get MaHD from HoaDonTable to query and get data about ChiTietHoaDon in HoaDon
        ArrayList<ChiTietPhieuNhap> arr = new ArrayList<ChiTietPhieuNhap>();
        ChiTietPhieuNhap_BUS chiTietPhieuNhap_BUS = new ChiTietPhieuNhap_BUS();
        arr = chiTietPhieuNhap_BUS.danhsachChiTietPhieuNhap(id);
        ChiTietPhieuNhap chitiet = new ChiTietPhieuNhap();
        try {
            for (int i = 0; i < arr.size(); i++) {
                chitiet = arr.get(i);
                String chitietID = chitiet.getMaPhieuNhap();
                String chitietSachID = chitiet.getMaSach();
                String chitietTenSach = getBookName(chitietSachID);
                int chitietSoLuong = chitiet.getSoLuong();
                Object[] row = {chitietID, chitietTenSach, chitietSoLuong};
                tableDetailModel.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        DetailBillTable.setModel(tableDetailModel);
    }
    
    
   public void resetBillTableAfterConfirm() { //reset BillTable after confirm the bill
        DefaultTableModel tableModel = (DefaultTableModel) BillTable.getModel();
        tableModel.setRowCount(0);
        BillTable.setModel(tableModel);
        selectarr.clear();
        exist = false;
    }
   
   public String getSupplierID(String search) //Get Supplier ID by using Supplier's name
   {
       connection = new DBConnection();
       String query = "select * from NhaCungCap where TenNCC ='" + search + "'";
       try{
           ResultSet rs = connection.ExcuteQueryGetTable(query);
           while(rs.next())
           {
               supplierSelectedID = rs.getString("MaNCC");
           }
       }catch(Exception e)
       {
           System.err.println("No thing to show");
       }
       return supplierSelectedID;
   }
   
  /* public String getStaffID(String search)
   {
       connection = new DBConnection();
       String query = "select"
   }*/


    public void SelectRowSupplier() //Select row in the SearchSupplierTable
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchSupplierTable.getModel();
            //Get Data from Table
            valueMaNCC = tableModel.getValueAt(SearchSupplierTable.getSelectedRow(), 0).toString();
            String name = tableModel.getValueAt(SearchSupplierTable.getSelectedRow(), 1).toString();
            //Set TextField
            NameSupplierTxb.setText(name);  
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
        SearchSupplierTable = new javax.swing.JTable();
        SupplySearchTxb = new javax.swing.JTextField();
        SearchBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        EditChangeBtn = new javax.swing.JButton();
        NewChangeBtn = new javax.swing.JButton();
        AddTab = new javax.swing.JPanel();
        NameSupplierTxb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        AddSupplierBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        UpdateSupplierBtn = new javax.swing.JButton();
        Search = new javax.swing.JPanel();
        SearchBtn2 = new javax.swing.JButton();
        PlusBtn = new javax.swing.JButton();
        SubtractBtn = new javax.swing.JButton();
        AllRadio = new javax.swing.JRadioButton();
        FilterRadio = new javax.swing.JRadioButton();
        SelectBookBtn = new javax.swing.JButton();
        AmountTxb = new javax.swing.JTextField();
        TypeCb = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AuthorSearchTxb = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        SearchBookTable = new javax.swing.JTable();
        SearchText = new javax.swing.JTextField();
        CreateBillTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BillTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        ImportBillText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        DateBox = new com.toedter.calendar.JDateChooser();
        SupplierNameCb = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        CancelBillBtn = new javax.swing.JButton();
        TotalText = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        IDBtn = new javax.swing.JButton();
        ConfirmBillBtn = new javax.swing.JButton();
        DeleteBillBtn = new javax.swing.JButton();
        PurchasedBillTab = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DetailBillTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        BillShowTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        SuppliersID = new javax.swing.JComboBox<>();
        SearchPurchaseBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPORT MANAGEMENT");

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

        SearchSupplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SearchSupplierTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SearchSupplierTable.setGridColor(new java.awt.Color(0, 0, 0));
        SearchSupplierTable.setShowGrid(true);
        jScrollPane1.setViewportView(SearchSupplierTable);
        SearchSupplierTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        SearchTab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 935, 590));

        SupplySearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SupplySearchTxb.setText("Search here ...");
        SupplySearchTxb.setToolTipText("Search Here....");
        SupplySearchTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchTab.add(SupplySearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(953, 0, 250, 52));

        SearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn.setToolTipText("Search");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        SearchTab.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, -1, 52));

        DeleteBtn.setBackground(new java.awt.Color(255, 51, 51));
        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("DELETE");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        SearchTab.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 420, 116, 51));

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
        SearchTab.add(EditChangeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 330, 116, 51));

        NewChangeBtn.setBackground(new java.awt.Color(153, 255, 153));
        NewChangeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NewChangeBtn.setText("NEW");
        NewChangeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewChangeBtnActionPerformed(evt);
            }
        });
        SearchTab.add(NewChangeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 230, 116, 51));

        ParentPanel.addTab("Search Supplier", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png")), SearchTab); // NOI18N

        AddTab.setBackground(new java.awt.Color(255, 255, 255));
        AddTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NameSupplierTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NameSupplierTxb.setToolTipText("");
        NameSupplierTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(NameSupplierTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 431, 52));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Name :");
        AddTab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, -1, -1));

        AddSupplierBtn.setBackground(new java.awt.Color(153, 255, 153));
        AddSupplierBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddSupplierBtn.setText("ADD SUPPLIER");
        AddSupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSupplierBtnActionPerformed(evt);
            }
        });
        AddTab.add(AddSupplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 244, 51));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/istockphoto-1223671392-612x612-1.jpg"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddTab.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 99, -1, -1));

        UpdateSupplierBtn.setBackground(new java.awt.Color(255, 204, 204));
        UpdateSupplierBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateSupplierBtn.setText("UPDATE SUPPLIER");
        UpdateSupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateSupplierBtnActionPerformed(evt);
            }
        });
        AddTab.add(UpdateSupplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 300, 244, 51));

        ParentPanel.addTab("Add Supplier", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_Plus_+_35px.png")), AddTab); // NOI18N

        Search.setBackground(new java.awt.Color(255, 255, 255));
        Search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SearchBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn2.setToolTipText("Search");
        SearchBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtn2ActionPerformed(evt);
            }
        });
        Search.add(SearchBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 180, -1, 40));

        PlusBtn.setBackground(new java.awt.Color(153, 255, 153));
        PlusBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        PlusBtn.setText("+");
        PlusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlusBtnActionPerformed(evt);
            }
        });
        Search.add(PlusBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 440, 60, 51));

        SubtractBtn.setBackground(new java.awt.Color(255, 204, 204));
        SubtractBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SubtractBtn.setText("-");
        SubtractBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubtractBtnActionPerformed(evt);
            }
        });
        Search.add(SubtractBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 440, 60, 51));

        buttonGroup1.add(AllRadio);
        AllRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AllRadio.setSelected(true);
        AllRadio.setText("All");
        Search.add(AllRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 9, -1, -1));

        buttonGroup1.add(FilterRadio);
        FilterRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FilterRadio.setText("Filter");
        FilterRadio.setToolTipText("");
        Search.add(FilterRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 9, -1, -1));

        SelectBookBtn.setBackground(new java.awt.Color(153, 255, 153));
        SelectBookBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SelectBookBtn.setText("SELECT");
        SelectBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectBookBtnActionPerformed(evt);
            }
        });
        Search.add(SelectBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 530, 116, 51));

        AmountTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AmountTxb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AmountTxb.setText("0");
        AmountTxb.setToolTipText("Search Here....");
        AmountTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AmountTxb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AmountTxbKeyTyped(evt);
            }
        });
        Search.add(AmountTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 440, 70, 50));

        TypeCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        TypeCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Search.add(TypeCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1055, 116, 200, 45));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Type");
        Search.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(964, 127, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Author");
        Search.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(964, 190, -1, -1));

        AuthorSearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AuthorSearchTxb.setToolTipText("Search Here....");
        AuthorSearchTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Search.add(AuthorSearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 180, 160, 42));

        jScrollPane2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SearchBookTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jScrollPane2.setViewportView(SearchBookTable);

        Search.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 930, 590));

        SearchText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchText.setText("Search name ...");
        SearchText.setToolTipText("Search Here....");
        Search.add(SearchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, 310, 42));

        ParentPanel.addTab("Choose Book", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png")), Search); // NOI18N

        CreateBillTab.setBackground(new java.awt.Color(255, 255, 255));
        CreateBillTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BillTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BillTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amout", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BillTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        BillTable.setGridColor(new java.awt.Color(0, 0, 0));
        BillTable.setShowGrid(true);
        jScrollPane3.setViewportView(BillTable);
        BillTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        CreateBillTab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 930, 280));

        jLabel12.setText("Import Bill ID");
        CreateBillTab.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        ImportBillText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CreateBillTab.add(ImportBillText, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 160, 30));

        jLabel13.setText("Date");
        CreateBillTab.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, -1, -1));

        DateBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DateBox.setDateFormatString("yyyy-MM-dd");
        CreateBillTab.add(DateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 210, 30));

        SupplierNameCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CreateBillTab.add(SupplierNameCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 180, 30));

        jLabel14.setText("Supplier ID");
        CreateBillTab.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, -1));

        CancelBillBtn.setBackground(new java.awt.Color(255, 51, 102));
        CancelBillBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CancelBillBtn.setForeground(new java.awt.Color(255, 255, 255));
        CancelBillBtn.setText("CANCEL");
        CancelBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBillBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(CancelBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, 116, 51));

        TotalText.setEditable(false);
        TotalText.setText("0.0");
        TotalText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CreateBillTab.add(TotalText, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 420, 210, 50));

        jLabel15.setText("Total");
        CreateBillTab.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, 30, 30));

        IDBtn.setBackground(new java.awt.Color(153, 204, 255));
        IDBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        IDBtn.setText("GENERATE ID");
        IDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(IDBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 140, 30));

        ConfirmBillBtn.setBackground(new java.awt.Color(255, 51, 102));
        ConfirmBillBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ConfirmBillBtn.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmBillBtn.setText("CONFIRM");
        ConfirmBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmBillBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(ConfirmBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 470, 116, 51));

        DeleteBillBtn.setBackground(new java.awt.Color(255, 51, 102));
        DeleteBillBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBillBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBillBtn.setText("DELETE");
        DeleteBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBillBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(DeleteBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 350, 116, 51));

        ParentPanel.addTab("Create Bill", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_bill_35px_1.png")), CreateBillTab); // NOI18N

        PurchasedBillTab.setBackground(new java.awt.Color(255, 255, 255));
        PurchasedBillTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        DetailBillTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DetailBillTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DetailBillTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        DetailBillTable.setColumnSelectionAllowed(true);
        DetailBillTable.setGridColor(new java.awt.Color(0, 0, 0));
        DetailBillTable.setShowGrid(true);
        jScrollPane4.setViewportView(DetailBillTable);
        DetailBillTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        PurchasedBillTab.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 930, 260));

        jScrollPane5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BillShowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Import Date", "Create Date", "Staff ID", "Supplier ID", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BillShowTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        BillShowTable.setGridColor(new java.awt.Color(0, 0, 0));
        BillShowTable.setShowGrid(true);
        BillShowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillShowTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(BillShowTable);
        BillShowTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        PurchasedBillTab.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 280));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Supplier ID");
        PurchasedBillTab.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

        SuppliersID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        SuppliersID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PurchasedBillTab.add(SuppliersID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 160, 45));

        SearchPurchaseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchPurchaseBtn.setToolTipText("Search");
        SearchPurchaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchPurchaseBtnActionPerformed(evt);
            }
        });
        PurchasedBillTab.add(SearchPurchaseBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 10, 60, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Detail");
        PurchasedBillTab.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        ParentPanel.addTab("Purchased Bills", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_initiate_money_transfer_35px_1.png")), PurchasedBillTab); // NOI18N

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

    
    //Edit Supplier Button event handling
    private void EditChangeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditChangeBtnMouseClicked
        ParentPanel.setSelectedIndex(2);
    }//GEN-LAST:event_EditChangeBtnMouseClicked

    
    //Search button in SelectBookTable's event handling
    private void SearchBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtn2ActionPerformed
        // Search btn in Search Book Tab
        if(AllRadio.isSelected() == true)
        {
            resetSearchBookData();
            loadAllData();
        } else if (FilterRadio.isSelected() == true)
        {
            seacrhBookWithFilter();
            resetCbData();
            loadTypeCbData();
        }
        
    }//GEN-LAST:event_SearchBtn2ActionPerformed

    private void PlusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlusBtnActionPerformed
        // Plus Btn in SearchBook Tab
        int amount = Integer.parseInt(AmountTxb.getText());
        if (amount >= 0) {
            amount += 1;
        }
        AmountTxb.setText(Integer.toString(amount));
    }//GEN-LAST:event_PlusBtnActionPerformed

    private void SubtractBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubtractBtnActionPerformed
        // Subtraction Btn in SeacrhBook Tab
        int amount = Integer.parseInt(AmountTxb.getText());
        if (amount == 0) {

        } else {
            amount -= 1;
        }
        AmountTxb.setText(Integer.toString(amount));
    }//GEN-LAST:event_SubtractBtnActionPerformed

    private void SelectBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectBookBtnActionPerformed
        try {
            SelectRow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
        }
        
    }//GEN-LAST:event_SelectBookBtnActionPerformed

    private void BackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseClicked
    // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BackBtnMouseClicked

    private void IDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDBtnActionPerformed
        // TODO add your handling code here:
        BillID ="B" + randomID();
        ImportBillText.setText(BillID);
    }//GEN-LAST:event_IDBtnActionPerformed

    private void ConfirmBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmBillBtnActionPerformed
        // TODO add your handling code here:
        PhieuNhapKho nhap = new PhieuNhapKho();
        String date = ((JTextField) DateBox.getDateEditor().getUiComponent()).getText();
        System.err.println(date);
        if(ImportBillText.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please generate bill ID by cliking the button 'GENERATE ID'");
        }
        else 
        {
            nhap.setMaPhieuNhap(ImportBillText.getText());
            nhap.setNgayNhapKho(date);
            nhap.setNgayTaoPhieu(dateCreateBill);
            nhap.setMaTK(PublicClass.StaffID);
            nhap.setMaNhaCC(getSupplierID(SupplierNameCb.getSelectedItem().toString()));
            nhap.setTongTien(total);
            PhieuNhapKho_BUS phieunhapkho_BUS = new PhieuNhapKho_BUS();
            phieunhapkho_BUS.addPhieuNhapKho(nhap);
            
            for (Sach sach : selectarr)
            {
                ChiTietPhieuNhap chitietnhap = new ChiTietPhieuNhap();
                chitietnhap.setMaPhieuNhap(ImportBillText.getText());
                chitietnhap.setMaSach(sach.getMaSach());
                chitietnhap.setSoLuong(sach.getSoLuong());
                ChiTietPhieuNhap_BUS chitietphieunhap_BUS = new ChiTietPhieuNhap_BUS();
                chitietphieunhap_BUS.addChiTietPhieuNhap(chitietnhap);
                //GetOldAmount and update new amount for Sach
                Sach sachStorage = new Sach();
                int amount_old = 0;
                ArrayList<Sach> arrStorage = new ArrayList<Sach>();
                Sach_BUS sach_BUS = new Sach_BUS();
                arrStorage = sach_BUS.searchSachbyID(sach.getMaSach());
                            sachStorage = arrStorage.get(0);
                            String id = sachStorage.getMaSach();
                            String name1 = sachStorage.getTenSach();
                            String author1 = sachStorage.getTenTG();
                            String type1 = sachStorage.getTenTheLoai();
                            amount_old = sachStorage.getSoLuong();
                            float price = sachStorage.getGia();
                    //get new amount and update for Sach           
                    sach.setSoLuong(amount_old + sach.getSoLuong());
                    sach.setGia(price);
                    sach_BUS.updateSach(sach);
            }
            System.out.println("Succeed!");
            JOptionPane.showMessageDialog(this, "Successful!");
            resetBillTableAfterConfirm();
            resetSearchBookTable();
            resetBillShowTable();
            resetDetailBillTable();
            loadAllBill();
            loadAllBook();
            ParentPanel.setSelectedIndex(4);
        }
    }//GEN-LAST:event_ConfirmBillBtnActionPerformed
                               

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        // Search Supplier
        resetSupplier(); //reset to null table
        String search = SupplySearchTxb.getText();
        DefaultTableModel tableModel = (DefaultTableModel) SearchSupplierTable.getModel();
        ArrayList<NhaCungCap> arr = new ArrayList<NhaCungCap>();
        NhaCungCap_BUS nhacungcap_BUS = new NhaCungCap_BUS();
        arr = nhacungcap_BUS.seacrhNhaCungCap(search);
        NhaCungCap nhacungcap = new NhaCungCap();
        try {
            for(int i = 0; i< arr.size(); i++)
            {
                nhacungcap = arr.get(i);
                String id = nhacungcap.getMaNhaCC();
                String name = nhacungcap.getTenNhaCC();
                
                Object[] row = {id, name};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchSupplierTable.setModel(tableModel);    }//GEN-LAST:event_SearchBtnActionPerformed

    private void EditChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditChangeBtnActionPerformed
        // Move to Add Tab and set Textfield's text to update Customer
        try {
            SelectRowSupplier();
            ParentPanel.setSelectedIndex(1);
            UpdateSupplierBtn.setEnabled(true);
            AddSupplierBtn.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Supplier have been selected!");
        }
    }//GEN-LAST:event_EditChangeBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        // Delete Supplier
        try {
            SelectRowSupplier();
            NhaCungCap_BUS nhacungcap_BUS = new NhaCungCap_BUS();
            nhacungcap_BUS.deleteNhaCungCap(valueMaNCC);
            resetSupplier();
            resetText();
            loadAllSupplier();
            JOptionPane.showMessageDialog(this, "Delete Supplier successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Supplier have been selected!");
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void AddSupplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSupplierBtnActionPerformed
        // Add Supplier
        NhaCungCap nhacungcap = new NhaCungCap();
        if(NameSupplierTxb.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please fill atleast the name of the supplier...");
        }
        else
        {
            nhacungcap.setMaNhaCC("C" + randomID());
            nhacungcap.setTenNhaCC(NameSupplierTxb.getText());
            
        }
        NhaCungCap_BUS nhacungcap_BUS = new NhaCungCap_BUS();
        nhacungcap_BUS.addNhaCungCap(nhacungcap);
        JOptionPane.showMessageDialog(this, "Add Supplier successfully!");
        resetSupplier();
        loadAllSupplier();
        ParentPanel.setSelectedIndex(0);
        UpdateSupplierBtn.setEnabled(true);
    }//GEN-LAST:event_AddSupplierBtnActionPerformed

    private void NewChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewChangeBtnActionPerformed
        // Move to Add Tab, no thing to set
        ParentPanel.setSelectedIndex(1);
        UpdateSupplierBtn.setEnabled(false);
        AddSupplierBtn.setEnabled(true);
    }//GEN-LAST:event_NewChangeBtnActionPerformed

    private void CancelBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBillBtnActionPerformed
        // TODO add your handling code here:
        resetBillTableAfterConfirm();
        resetSearchBookData();
        resetBillShowTable();
        resetDetailBillTable();
        loadAllBill();
        loadAllBook();
        ParentPanel.setSelectedIndex(2);
        
    }//GEN-LAST:event_CancelBillBtnActionPerformed

    private void DeleteBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBillBtnActionPerformed
        // Delete row of BillTable
        try {
            int deleteRow = BillTable.getSelectedRow();
            System.out.println("remove at index" + deleteRow);
            selectarr.remove(deleteRow);
            total = 0; //after delete -> calculate again from the start
            DefaultTableModel selecttable = (DefaultTableModel) BillTable.getModel();
            resetBillTable();
            for (Sach sachFinal : selectarr) {
                String idFinal = sachFinal.getMaSach();
                String nameFinal = sachFinal.getTenSach();
                String authorFinal = sachFinal.getTenTG();
                String typeFinal = sachFinal.getTenTheLoai();
                int amountFinal = sachFinal.getSoLuong();
                double priceFinal = sachFinal.getGia();
                Object[] row = {idFinal, nameFinal, authorFinal, typeFinal, amountFinal, priceFinal };
                selecttable.addRow(row);
                total += amountFinal * priceFinal;
            }
            BillTable.setModel(selecttable);
            TotalText.setText(Double.toString(total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have been selected!");
        }
    }//GEN-LAST:event_DeleteBillBtnActionPerformed

    private void SearchPurchaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchPurchaseBtnActionPerformed
        // Search Bill with Supplier ID
        resetBillShowTable();
        resetDetailBillTable();
        DefaultTableModel billtable = (DefaultTableModel) BillShowTable.getModel();
        String search = SuppliersID.getSelectedItem().toString();
        if (search.equals("All")) {
            loadAllBill();
        } else {
            String seacrhid = getSupplierID(search); // get ID Supplier from name by function have created before
            ArrayList<PhieuNhapKho> arr = new ArrayList<PhieuNhapKho>();
            PhieuNhapKho_BUS phieunhapkho_BUS = new PhieuNhapKho_BUS();
            arr = phieunhapkho_BUS.searchPhieuNhapKhoWithSupplierID(seacrhid);
            System.out.println(arr.size());
            PhieuNhapKho phieunhapkho = new PhieuNhapKho();
            try {
                for (int i = 0; i < arr.size(); i++) {
                    phieunhapkho = arr.get(i);
                    String id = phieunhapkho.getMaPhieuNhap();
                    String nhacungcap = phieunhapkho.getMaNhaCC();
                    String maTK = phieunhapkho.getMaTK();
                    String ngaynhapkho = phieunhapkho.getNgayNhapKho();
                    String ngaytaophieu = phieunhapkho.getNgayTaoPhieu();
                    double tongtien = phieunhapkho.getTongTien();     
                    Object[] row = {id, ngaynhapkho, ngaytaophieu, maTK, nhacungcap, tongtien };
                    billtable.addRow(row);
                }
            } catch (Exception e) {
                System.out.println("No thing!");
            }
            BillShowTable.setModel(billtable);
        }

    }//GEN-LAST:event_SearchPurchaseBtnActionPerformed

    private void UpdateSupplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateSupplierBtnActionPerformed
        // TODO add your handling code here:
        if(NameSupplierTxb.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please fill atleast the name of the supplier...");
        }
        else
        {
            NhaCungCap nhacungcap = new NhaCungCap();
            nhacungcap.setMaNhaCC(valueMaNCC);
            nhacungcap.setTenNhaCC(NameSupplierTxb.getText());
            NhaCungCap_BUS nhaCungCap_BUS = new NhaCungCap_BUS();
            nhaCungCap_BUS.updateNhaCungCap(nhacungcap);
            JOptionPane.showMessageDialog(this, "Update Supplier successfully!");
            resetText();
            resetSupplier();
            loadAllSupplier();
            ParentPanel.setSelectedIndex(0);
            AddSupplierBtn.setEnabled(true);
        }
    }//GEN-LAST:event_UpdateSupplierBtnActionPerformed

    private void BillShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillShowTableMouseClicked
        // TODO add your handling code here:
        resetDetailBillTable();
        selectImportBillRow();
    }//GEN-LAST:event_BillShowTableMouseClicked

    private void AmountTxbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AmountTxbKeyTyped
        // AmountTxb Number only
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_AmountTxbKeyTyped

    

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
            java.util.logging.Logger.getLogger(ImportManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImportManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImportManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImportManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddSupplierBtn;
    private javax.swing.JPanel AddTab;
    private javax.swing.JRadioButton AllRadio;
    private javax.swing.JTextField AmountTxb;
    private javax.swing.JTextField AuthorSearchTxb;
    private javax.swing.JLabel BackBtn;
    private javax.swing.JTable BillShowTable;
    private javax.swing.JTable BillTable;
    private javax.swing.JButton CancelBillBtn;
    private javax.swing.JButton ConfirmBillBtn;
    private javax.swing.JPanel CreateBillTab;
    private com.toedter.calendar.JDateChooser DateBox;
    private javax.swing.JButton DeleteBillBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JTable DetailBillTable;
    private javax.swing.JButton EditChangeBtn;
    private javax.swing.JRadioButton FilterRadio;
    private javax.swing.JButton IDBtn;
    private javax.swing.JTextField ImportBillText;
    private javax.swing.JTextField NameSupplierTxb;
    private javax.swing.JButton NewChangeBtn;
    private javax.swing.JTabbedPane ParentPanel;
    private javax.swing.JButton PlusBtn;
    private javax.swing.JPanel PurchasedBillTab;
    private javax.swing.JPanel Search;
    private javax.swing.JTable SearchBookTable;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JButton SearchBtn2;
    private javax.swing.JButton SearchPurchaseBtn;
    private javax.swing.JTable SearchSupplierTable;
    private javax.swing.JPanel SearchTab;
    private javax.swing.JTextField SearchText;
    private javax.swing.JButton SelectBookBtn;
    private javax.swing.JButton SubtractBtn;
    private javax.swing.JComboBox<String> SupplierNameCb;
    private javax.swing.JComboBox<String> SuppliersID;
    private javax.swing.JTextField SupplySearchTxb;
    private javax.swing.JTextField TotalText;
    private javax.swing.JComboBox<String> TypeCb;
    private javax.swing.JButton UpdateSupplierBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

    

    
}
