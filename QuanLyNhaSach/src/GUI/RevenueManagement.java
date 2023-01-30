/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.*;
import DTO.*;
import DAL.*;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 19522
 */
public class RevenueManagement extends javax.swing.JFrame {

    /**
     * Creates new form RevenueManagement
     */
    //Bill
    DBConnection connection;
    private String IDBill;
    private String CustomerID;
    private String dateString;
    private double totalBill;
    private double receive;
    //ImportBill
    private String IDimportBill;
    private String createDate;
    private String importDate;
    private String maTK;
    private String maNCC;
    private double totalImport;
    //SaleBill
    private String IDSaleBill;
    public RevenueManagement() {
        initComponents();
        loadCurrentDate();
        loadSaleBill();
        LoadStaffIDCb();
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

    public void reset()
    {
        //reset Data
        resetBill();
        resetImportBill();
        resetText();
    }
    
    public void resetBill()
    {
        //reset BillTable Data
        DefaultTableModel tableModel = (DefaultTableModel) BillShowTable.getModel();
        tableModel.setRowCount(0);
        BillShowTable.setModel(tableModel);
    }
    
    public void resetImportBill()
    {
        //reset ImportBillTable Data
        DefaultTableModel tableModel = (DefaultTableModel) ImportBillShowTable.getModel();
        tableModel.setRowCount(0);
        ImportBillShowTable.setModel(tableModel);
    }
    
    public void resetSaleBill()
    {
        //Reset SaleBillTable Data
        DefaultTableModel tableModel = (DefaultTableModel) SaleBillTable.getModel();
        tableModel.setRowCount(0);
        SaleBillTable.setModel(tableModel);
    }
    
    public void resetText()
    {
        //reset Text
    }
    
    private void loadCurrentDate() {
        //get the current day by default for JDateChooser - DatePicker
        Calendar today = Calendar.getInstance();
        DateBox.setCalendar(today);
        DateImportBox.setCalendar(today);
        DateSaleBillBox.setCalendar(today);
    }
    
    private void LoadStaffIDCb()
    {
        //Load StaffID to StaffID ComboBox
        connection = new DBConnection();
        String query = "select * from TaiKhoan";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {
                String id = rs.getString("MaTK");
                StaffIDCb.addItem(id);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
    }
    
    private void loadAllBillInDay()
    {
        //Load All bill to  BillShowTable
        DefaultTableModel table = (DefaultTableModel) BillShowTable.getModel();
        String date = ((JTextField) DateBox.getDateEditor().getUiComponent()).getText();
        ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
        HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
        arr = hoaDon_BUS.danhSachHoaDonCompletebyDate(date);
        HoaDon hoaDon = new HoaDon();
        {
            try {
                for (int i = 0; i < arr.size(); i++) {
                    hoaDon = arr.get(i);
                    String id = hoaDon.getMaHoaDon();
                    String customerID = hoaDon.getMaKH();
                    String billDay = hoaDon.getNgayNhap();
                    Double billToTal = hoaDon.getTongTien();
                    Double billReceive = hoaDon.getTienTra();
                    String billStatus = hoaDon.getTinhTrang();
                    Object[] row = {id, customerID, billDay, billToTal, billReceive, billStatus};
                    table.addRow(row);
                }
            } catch (Exception e) {
                System.err.println("No thing!");
            }
            BillShowTable.setModel(table);
        }
    }
    
    public void loadAllImportBillInDay()
    {
        //Load All bill to  BillShowTable
        DefaultTableModel table = (DefaultTableModel) ImportBillShowTable.getModel();
        String date = ((JTextField) DateImportBox.getDateEditor().getUiComponent()).getText();
        ArrayList<PhieuNhapKho> arr = new ArrayList<PhieuNhapKho>();
        PhieuNhapKho_BUS phieuNhapKho_BUS = new PhieuNhapKho_BUS();
        arr = phieuNhapKho_BUS.danhsachPhieuNhapKhoByDate(date);
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
                    Object[] row = {id, nhacungcap, maTK, ngaynhapkho, ngaytaophieu, tongtien };
                    table.addRow(row);
                }
            } catch (Exception e) {
                System.err.println("No thing!");
            }
            ImportBillShowTable.setModel(table);
    }
    
    public void loadSaleBill()
    {
        try {
                String date = ((JTextField) DateSaleBillBox.getDateEditor().getUiComponent()).getText(); //Get String from DatePicker - JDateChooser
                DefaultTableModel tableModel = (DefaultTableModel) SaleBillTable.getModel();
                PhieuThuTien phieuthutien = new PhieuThuTien();
                PhieuThuTien_BUS bus = new PhieuThuTien_BUS();
                ArrayList<PhieuThuTien> arr = new ArrayList<PhieuThuTien>();
                if(OnlyStaffRadio.isSelected() == true)
                {
                    if(StaffIDCb.getSelectedItem().toString().equals("All"))
                    {
                        arr = bus.getAllPhieuThuTien();
                    }
                    else
                    {
                        arr = bus.getAllPhieuThuTienByStaffID(StaffIDCb.getSelectedItem().toString());
                    }
                }
                else if (BothRadio.isSelected() == true)
                {
                    if(StaffIDCb.getSelectedItem().toString().equals("All"))
                    {
                        arr = bus.getAllPhieuThuTienByStaffIDAndDate("",date);
                    }
                    else
                    {
                        arr = bus.getAllPhieuThuTienByStaffIDAndDate(StaffIDCb.getSelectedItem().toString(),date);
                    }
                }
                try {
                    for(int i=0; i < arr.size(); i++)
                    {
                        phieuthutien = arr.get(i);
                        String id = phieuthutien.getMaPhieuThu();
                        String khachid = phieuthutien.getMaKH();
                        String ngaythu = phieuthutien.getNgaythu();
                        float tienthu = phieuthutien.getSoTienThu();
                        String staff = phieuthutien.getMaTaiKhoan();
                        Object[] row = {id, khachid, ngaythu, tienthu, staff};
                        tableModel.addRow(row);
                    }
                } catch (Exception e) {
                }
                SaleBillTable.setModel(tableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Haven't choose Day!");
        }
    }
    
    private void selectBillRow() //select BillTable row to get Data
    {
        DefaultTableModel selecttable = (DefaultTableModel) BillShowTable.getModel();
        IDBill = selecttable.getValueAt(BillShowTable.getSelectedRow(), 0).toString();
        CustomerID = selecttable.getValueAt(BillShowTable.getSelectedRow(), 1).toString();
        dateString = selecttable.getValueAt(BillShowTable.getSelectedRow(), 2).toString();
        totalBill = Double.parseDouble(selecttable.getValueAt(BillShowTable.getSelectedRow(), 3).toString());
        receive = Double.parseDouble(selecttable.getValueAt(BillShowTable.getSelectedRow(), 4).toString());
    }
    
    private void selectImportBillRow() //select ImportBillTable Row to get Data
    {
        DefaultTableModel selecttable = (DefaultTableModel) ImportBillShowTable.getModel();
        IDimportBill = selecttable.getValueAt(ImportBillShowTable.getSelectedRow(), 0).toString();
        createDate = selecttable.getValueAt(ImportBillShowTable.getSelectedRow(), 1).toString();
        importDate = selecttable.getValueAt(ImportBillShowTable.getSelectedRow(), 2).toString();
        maTK = selecttable.getValueAt(ImportBillShowTable.getSelectedRow(), 3).toString();
        maNCC = selecttable.getValueAt(ImportBillShowTable.getSelectedRow(), 4).toString();
        totalImport = Double.parseDouble(selecttable.getValueAt(ImportBillShowTable.getSelectedRow(), 5).toString());
    }
    
    private void selectSaleBillRow() //select SaleBillTable row to get Data
    {
        DefaultTableModel selecttable = (DefaultTableModel) SaleBillTable.getModel();
        IDSaleBill = selecttable.getValueAt(SaleBillTable.getSelectedRow(), 0).toString();
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
        DailyTurnOver = new javax.swing.JPanel();
        SearchBtn = new javax.swing.JButton();
        DateBox = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        BillShowTable = new javax.swing.JTable();
        DeleteBtn = new javax.swing.JButton();
        PreviewBtn = new javax.swing.JButton();
        PrintBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PrintPreview = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        ImportReportTab = new javax.swing.JPanel();
        DateImportBox = new com.toedter.calendar.JDateChooser();
        SearchBtn1 = new javax.swing.JButton();
        PreviewImportBtn = new javax.swing.JButton();
        PrintImportBtn = new javax.swing.JButton();
        DeleteBtn1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ImportPrintPreview = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        ImportBillShowTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        MonthlyRevenue = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        RevenueCal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ImportTotalLb = new javax.swing.JLabel();
        SaleTotalLb = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        RevenueLb = new javax.swing.JLabel();
        SearchBtn2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        RevenuePrintPreview = new javax.swing.JTextArea();
        MonthCb = new javax.swing.JComboBox<>();
        YearTxb = new javax.swing.JTextField();
        PrintImportBtn1 = new javax.swing.JButton();
        StaffBillTab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SaleBillTable = new javax.swing.JTable();
        DateSaleBillBox = new com.toedter.calendar.JDateChooser();
        SearchBtn3 = new javax.swing.JButton();
        StaffIDCb = new javax.swing.JComboBox<>();
        BothRadio = new javax.swing.JRadioButton();
        OnlyStaffRadio = new javax.swing.JRadioButton();
        DeleteBtn2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REVENUE MANAGEMENT");

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

        DailyTurnOver.setBackground(new java.awt.Color(255, 255, 255));
        DailyTurnOver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn.setToolTipText("Search");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        DailyTurnOver.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 40));

        DateBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DateBox.setDateFormatString("yyyy-MM-dd");
        DailyTurnOver.add(DateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 280, 40));

        jScrollPane5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BillShowTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BillShowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CustomerID", "Date", "Total", "Receive", "Status"
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

        DailyTurnOver.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 800, 430));

        DeleteBtn.setBackground(new java.awt.Color(255, 51, 102));
        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn.setText("DELETE");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        DailyTurnOver.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 530, 116, 51));

        PreviewBtn.setBackground(new java.awt.Color(153, 255, 153));
        PreviewBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PreviewBtn.setText("PREVIEW");
        PreviewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviewBtnActionPerformed(evt);
            }
        });
        DailyTurnOver.add(PreviewBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 116, 51));

        PrintBtn.setBackground(new java.awt.Color(255, 204, 204));
        PrintBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintBtn.setText("PRINT");
        PrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintBtnActionPerformed(evt);
            }
        });
        DailyTurnOver.add(PrintBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 116, 51));

        PrintPreview.setColumns(20);
        PrintPreview.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintPreview.setRows(5);
        jScrollPane1.setViewportView(PrintPreview);

        DailyTurnOver.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 410, 500));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Preview Before Print");
        DailyTurnOver.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, -1));

        ParentPanel.addTab("Daily Turnover", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_today_35px.png")), DailyTurnOver); // NOI18N

        ImportReportTab.setBackground(new java.awt.Color(255, 255, 255));
        ImportReportTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DateImportBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DateImportBox.setDateFormatString("yyyy-MM-dd");
        ImportReportTab.add(DateImportBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 280, 40));

        SearchBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn1.setToolTipText("Search");
        SearchBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtn1ActionPerformed(evt);
            }
        });
        ImportReportTab.add(SearchBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 40));

        PreviewImportBtn.setBackground(new java.awt.Color(153, 255, 153));
        PreviewImportBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PreviewImportBtn.setText("PREVIEW");
        PreviewImportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviewImportBtnActionPerformed(evt);
            }
        });
        ImportReportTab.add(PreviewImportBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 116, 51));

        PrintImportBtn.setBackground(new java.awt.Color(255, 204, 204));
        PrintImportBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintImportBtn.setText("PRINT");
        PrintImportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintImportBtnActionPerformed(evt);
            }
        });
        ImportReportTab.add(PrintImportBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 116, 51));

        DeleteBtn1.setBackground(new java.awt.Color(255, 51, 102));
        DeleteBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn1.setText("DELETE");
        DeleteBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtn1ActionPerformed(evt);
            }
        });
        ImportReportTab.add(DeleteBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 530, 116, 51));

        ImportPrintPreview.setColumns(20);
        ImportPrintPreview.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ImportPrintPreview.setRows(5);
        jScrollPane3.setViewportView(ImportPrintPreview);

        ImportReportTab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 410, 500));

        jScrollPane6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ImportBillShowTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ImportBillShowTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ImportBillShowTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        ImportBillShowTable.setGridColor(new java.awt.Color(0, 0, 0));
        ImportBillShowTable.setShowGrid(true);
        ImportBillShowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImportBillShowTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(ImportBillShowTable);

        ImportReportTab.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 800, 430));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Preview Before Print");
        ImportReportTab.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, -1));

        ParentPanel.addTab("Import Report", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_add_list_35px.png")), ImportReportTab); // NOI18N

        MonthlyRevenue.setBackground(new java.awt.Color(255, 255, 255));
        MonthlyRevenue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RevenueCal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        RevenueCal.setText("REVENUE");
        jPanel3.add(RevenueCal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        MonthlyRevenue.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 520, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("IMPORT");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        MonthlyRevenue.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 520, 50));

        ImportTotalLb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ImportTotalLb.setForeground(new java.awt.Color(255, 102, 102));
        ImportTotalLb.setText("IMPORT");
        MonthlyRevenue.add(ImportTotalLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, -1, -1));

        SaleTotalLb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SaleTotalLb.setForeground(new java.awt.Color(0, 153, 0));
        SaleTotalLb.setText("SALE");
        MonthlyRevenue.add(SaleTotalLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 204, 51));
        jLabel14.setText("SALE");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        MonthlyRevenue.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 520, 50));

        RevenueLb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        RevenueLb.setText("REVENUE");
        MonthlyRevenue.add(RevenueLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, -1, -1));

        SearchBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn2.setToolTipText("Search");
        SearchBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtn2ActionPerformed(evt);
            }
        });
        MonthlyRevenue.add(SearchBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 60, 40));

        RevenuePrintPreview.setColumns(20);
        RevenuePrintPreview.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RevenuePrintPreview.setRows(5);
        jScrollPane4.setViewportView(RevenuePrintPreview);

        MonthlyRevenue.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 690, 480));

        MonthCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        MonthlyRevenue.add(MonthCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 50, 40));

        YearTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        YearTxb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        YearTxb.setToolTipText("Search Here....");
        YearTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        YearTxb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                YearTxbKeyTyped(evt);
            }
        });
        MonthlyRevenue.add(YearTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 110, 42));

        PrintImportBtn1.setBackground(new java.awt.Color(255, 204, 204));
        PrintImportBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintImportBtn1.setText("PRINT");
        PrintImportBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintImportBtn1ActionPerformed(evt);
            }
        });
        MonthlyRevenue.add(PrintImportBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 530, 116, 51));

        ParentPanel.addTab("Monthly Revenue", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_schedule_35px.png")), MonthlyRevenue); // NOI18N

        StaffBillTab.setBackground(new java.awt.Color(255, 255, 255));
        StaffBillTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SaleBillTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SaleBillTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MaKhachHang", "NgayThu", "SoTienThu", "MaTaiKhoan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SaleBillTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SaleBillTable.setGridColor(new java.awt.Color(0, 0, 0));
        SaleBillTable.setShowGrid(true);
        SaleBillTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaleBillTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(SaleBillTable);
        SaleBillTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        StaffBillTab.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 916, 430));

        DateSaleBillBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DateSaleBillBox.setDateFormatString("yyyy-MM-dd");
        StaffBillTab.add(DateSaleBillBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 310, 40));

        SearchBtn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn3.setToolTipText("Search");
        SearchBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtn3ActionPerformed(evt);
            }
        });
        StaffBillTab.add(SearchBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 60, 40));

        StaffIDCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        StaffIDCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        StaffBillTab.add(StaffIDCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 310, 40));

        buttonGroup1.add(BothRadio);
        BothRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BothRadio.setText("StaffAndDate");
        StaffBillTab.add(BothRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, -1, -1));

        buttonGroup1.add(OnlyStaffRadio);
        OnlyStaffRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        OnlyStaffRadio.setSelected(true);
        OnlyStaffRadio.setText("OnlyStaff");
        StaffBillTab.add(OnlyStaffRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        DeleteBtn2.setBackground(new java.awt.Color(255, 51, 102));
        DeleteBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn2.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBtn2.setText("DELETE");
        DeleteBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtn2ActionPerformed(evt);
            }
        });
        StaffBillTab.add(DeleteBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 500, 116, 51));

        ParentPanel.addTab("Staff Bill", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_staff_35px.png")), StaffBillTab); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ParentPanel)
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

    private void BillShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillShowTableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BillShowTableMouseClicked

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        // Delete Bill
        try {
            selectBillRow();
            if(IDBill.equals(""))
            {
                System.out.println("Nothing to delete!");
            }
            else
            {
                ChiTietHoaDon_BUS chiTietHoaDon_BUS = new ChiTietHoaDon_BUS();
                chiTietHoaDon_BUS.deleteCHiTietHoaDon(IDBill);
                HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
                hoaDon_BUS.deleteHoaDon(IDBill);
                reset();
                loadAllBillInDay();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
        }
        
       
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void PreviewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviewBtnActionPerformed
        // Preview Bill before Print
        
        try {
            selectBillRow();
            ArrayList<ChiTietHoaDon> arr = new ArrayList<ChiTietHoaDon>();
            ChiTietHoaDon_BUS chitiethoadon_BUS = new ChiTietHoaDon_BUS();
            arr = chitiethoadon_BUS.danhSachChiTietHoaDon(IDBill);
            ChiTietHoaDon chitiet = new ChiTietHoaDon();
            PrintPreview.setText("                                 BOOK-KEEPER STORE\n");
            PrintPreview.setText(PrintPreview.getText() + "                                __for a bright feature__  \n");
            PrintPreview.setText(PrintPreview.getText() + "-----------------------------------------------------------------\n");
            PrintPreview.setText(PrintPreview.getText() + "                                           -INVOICE-                                 \n");
            PrintPreview.setText(PrintPreview.getText() + "                                 Address : KTX Khu B \n");
            PrintPreview.setText(PrintPreview.getText() + "                                      - ĐHQG TPHCM\n");
            PrintPreview.setText(PrintPreview.getText() + "  Phone :     0345304407                   Web :  fb.com/12345    \n");
            PrintPreview.setText(PrintPreview.getText() + "-----------------------------------------------------------------\n");
            PrintPreview.setText(PrintPreview.getText() + "  InvoiceID :     " + IDBill + "         CustomerID :" + CustomerID + "\n");
            PrintPreview.setText(PrintPreview.getText() + "                                   Date : " + dateString + "\n" );
            PrintPreview.setText(PrintPreview.getText() + "-----------------------------------------------------------------\n");
            for(int i = 0; i< arr.size();i++)
            {
                chitiet = arr.get(i);
                String bookID = chitiet.getMaSach();
                String bookName = getBookName(bookID);
                int amount = chitiet.getSoLuongMua();
                PrintPreview.setText(PrintPreview.getText() + "                      " + bookName + "      x " + amount + "\n");
            }
            PrintPreview.setText(PrintPreview.getText() + "                      ------------  ------------                " + "\n");
            PrintPreview.setText(PrintPreview.getText() + "\n");
            PrintPreview.setText(PrintPreview.getText() + "                      Total :          " + totalBill + "\n");
            PrintPreview.setText(PrintPreview.getText() + "                      ------------  ------------                " + "\n");
            PrintPreview.setText(PrintPreview.getText() + "                      Receive :      " + receive + "\n");
            PrintPreview.setText(PrintPreview.getText() + "                      ------------  ------------                " + "\n");
            PrintPreview.setText(PrintPreview.getText() + "                      Return   :      " + (receive - totalBill) + "\n");
            PrintPreview.setText(PrintPreview.getText() + "\n");
            PrintPreview.setText(PrintPreview.getText() + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            PrintPreview.setText(PrintPreview.getText() + "* *                      THANK YOU! COME AGAIN <3                * *\n");
            PrintPreview.setText(PrintPreview.getText() + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
        }
    }//GEN-LAST:event_PreviewBtnActionPerformed

    private void PrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintBtnActionPerformed
        // Print Bill
        try {
            PrintPreview.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Information!");
        }
    }//GEN-LAST:event_PrintBtnActionPerformed

    private void PreviewImportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviewImportBtnActionPerformed
        // Preview Import Bill before print
        try {
            selectImportBillRow();
            ArrayList<ChiTietPhieuNhap> arr = new ArrayList<ChiTietPhieuNhap>();
            ChiTietPhieuNhap_BUS chiTietPhieuNhap_BUS = new ChiTietPhieuNhap_BUS();
            arr = chiTietPhieuNhap_BUS.danhsachChiTietPhieuNhap(IDimportBill);
            ChiTietPhieuNhap phieunhap = new ChiTietPhieuNhap();
            ImportPrintPreview.setText("                                 BOOK-KEEPER STORE\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "                                __for a bright feature__  \n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "-----------------------------------------------------------------\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "                                    -IMPORT BOOK-                                 \n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "                                 Address : KTX Khu B \n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "                                      - ĐHQG TPHCM\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "  Phone :     0345304407                   Web :  fb.com/12345    \n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "-----------------------------------------------------------------\n");
            for(int i = 0; i< arr.size();i++)
            {
                phieunhap = arr.get(i);
                String bookID = phieunhap.getMaSach();
                String bookName = getBookName(bookID);
                int amount = phieunhap.getSoLuong();
                ImportPrintPreview.setText(ImportPrintPreview.getText() + "                      " + bookName + "      x " + amount + "\n");
            }
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "                      ------------  ------------                " + "\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "                      Total :          " + totalImport + "\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "* *                      PLEASE KEEP THIS BILL!!!                * *\n");
            ImportPrintPreview.setText(ImportPrintPreview.getText() + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
        }
    }//GEN-LAST:event_PreviewImportBtnActionPerformed

    private void PrintImportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintImportBtnActionPerformed
        // print Import Bill
        try {
            ImportPrintPreview.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Information!");
        }
    }//GEN-LAST:event_PrintImportBtnActionPerformed

    private void DeleteBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtn1ActionPerformed
        // Delete Import Bill
        try {
            selectImportBillRow();
            if(IDimportBill.equals(""))
            {
                System.out.println("Nothing to delete!");
            }
            else
            {
                ChiTietPhieuNhap_BUS chiTietPhieuNhap_BUS = new ChiTietPhieuNhap_BUS();
                chiTietPhieuNhap_BUS.deleteChiTietPhieuNhap(IDimportBill);
                PhieuNhapKho_BUS phieuNhapKho_BUS = new PhieuNhapKho_BUS();
                phieuNhapKho_BUS.deleteHoaDon(IDimportBill);
                reset();
                loadAllImportBillInDay();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
        }
    }//GEN-LAST:event_DeleteBtn1ActionPerformed

    private void ImportBillShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportBillShowTableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ImportBillShowTableMouseClicked

    private void SearchBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtn1ActionPerformed
        // Search Import Bill By Date
        resetImportBill();
        loadAllImportBillInDay();
       
    }//GEN-LAST:event_SearchBtn1ActionPerformed

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        // Search Bill By Date
        resetBill();
        loadAllBillInDay();
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void BackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BackBtnMouseClicked

    private void SearchBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtn2ActionPerformed
        // Preview Revenue before print
        try {
                String month = MonthCb.getSelectedItem().toString();
                String year = YearTxb.getText();
                double saleTotal = loadRevenue_Sale(month, year);
                double importTotal = loadRevenue_Import(month, year);
                calculateRevenue(saleTotal, importTotal);
                RevenueCal.setText("REVENUE     ~     " + ((saleTotal-importTotal)/(saleTotal+importTotal)*100 ) + " %"); 


                //Print Preview
                RevenuePrintPreview.setText("******************************************************************************************************************\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "**                                                              BOOK-KEEPER STORE                                                                    **\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "******************************************************************************************************************\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                                                        - REVENUE -\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                                      __Month : " + month +"                   Year : " + year + "__\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                                      Address : KTX Khu B - ĐHQG TPHCM\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "           Phone :     0345304407                                                                             Web :  fb.com/12345\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "------------------------------------------------------------------------------------------------------------------\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                   SALE                                                                                IMPORT\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                         "+ saleTotal +"                                                            " + importTotal + "\n");

                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                                                      ---------------------\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                                                               INTEREST\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "                                                                       " + (saleTotal - importTotal) + "\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "------------------------------------------------------------------------------------------------------------------\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "            - In this month, we get the revenue ~ " +(((saleTotal-importTotal)/(saleTotal+importTotal))*100) + " %\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "------------------------------------------------------------------------------------------------------------------\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "\n");
                RevenuePrintPreview.setText( RevenuePrintPreview.getText() + "------------------------------------------------------------------------------------------------------------------\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fill the YearTextbox!");
        }
    }//GEN-LAST:event_SearchBtn2ActionPerformed

    private void SearchBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtn3ActionPerformed
        // load SaleBill Data
        resetSaleBill();
        loadSaleBill();
    }//GEN-LAST:event_SearchBtn3ActionPerformed

    private void PrintImportBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintImportBtn1ActionPerformed
        // print Revenue
        try {
            RevenuePrintPreview.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Information!");
        }
    }//GEN-LAST:event_PrintImportBtn1ActionPerformed

    private void DeleteBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtn2ActionPerformed
        // Delete Sale Bill+
        try {
            PhieuThuTien_BUS bus = new PhieuThuTien_BUS();
            bus.deletePhieuThuTien(IDSaleBill);
            JOptionPane.showMessageDialog(this, "Delete SaleBill success!");
            resetSaleBill();
            loadSaleBill();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
            
        }
    }//GEN-LAST:event_DeleteBtn2ActionPerformed

    private void SaleBillTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaleBillTableMouseClicked
        // select row from SaleBillTable
        selectSaleBillRow();
    }//GEN-LAST:event_SaleBillTableMouseClicked

    private void YearTxbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YearTxbKeyTyped
        // YearTxb Number only
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_YearTxbKeyTyped

    
    //
    //Calculate Revenue Sale in #Monthly Revenue Tab
    private double loadRevenue_Sale(String month, String year)
    {
        double saleTotal = 0;
        ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
        HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
        arr = hoaDon_BUS.danhSachHoaDonCompletebyMonth(month, year);
        HoaDon hoaDon = new HoaDon();
        {
            try {
                for (int i = 0; i < arr.size(); i++) {
                    hoaDon = arr.get(i);
                    saleTotal +=hoaDon.getTongTien();
                }
            } catch (Exception e) {
                System.err.println("No thing!");
            }
        }
        SaleTotalLb.setText(Double.toString(saleTotal));
        return saleTotal;
    }
    
    //Calculate Revenue Import in #Monthly Revenue Tab
     private double loadRevenue_Import(String month, String year)
    {
        double importTotal = 0;
        ArrayList<PhieuNhapKho> arr = new ArrayList<PhieuNhapKho>();
        PhieuNhapKho_BUS phieunhapkho_BUS = new PhieuNhapKho_BUS();
        arr = phieunhapkho_BUS.danhsachPhieuNhapKhoByMonth(month, year);
        PhieuNhapKho phieunhapkho = new PhieuNhapKho();
        {
            try {
                for (int i = 0; i < arr.size(); i++) {
                    phieunhapkho = arr.get(i);
                    importTotal +=phieunhapkho.getTongTien();
                }
            } catch (Exception e) {
                System.err.println("No thing!");
            }
        }
        ImportTotalLb.setText(Double.toString(importTotal)); 
        return importTotal;
    }
     
     private void calculateRevenue(double saleTotal, double importTotal)
     {
         //Calculate revenue in month
         RevenueLb.setText(Double.toString(saleTotal - importTotal));
     }
     
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(RevenueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RevenueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RevenueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RevenueManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RevenueManagement().setVisible(true);
            }
        });
    }
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackBtn;
    private javax.swing.JTable BillShowTable;
    private javax.swing.JRadioButton BothRadio;
    private javax.swing.JPanel DailyTurnOver;
    private com.toedter.calendar.JDateChooser DateBox;
    private com.toedter.calendar.JDateChooser DateImportBox;
    private com.toedter.calendar.JDateChooser DateSaleBillBox;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton DeleteBtn1;
    private javax.swing.JButton DeleteBtn2;
    private javax.swing.JTable ImportBillShowTable;
    private javax.swing.JTextArea ImportPrintPreview;
    private javax.swing.JPanel ImportReportTab;
    private javax.swing.JLabel ImportTotalLb;
    private javax.swing.JComboBox<String> MonthCb;
    private javax.swing.JPanel MonthlyRevenue;
    private javax.swing.JRadioButton OnlyStaffRadio;
    private javax.swing.JTabbedPane ParentPanel;
    private javax.swing.JButton PreviewBtn;
    private javax.swing.JButton PreviewImportBtn;
    private javax.swing.JButton PrintBtn;
    private javax.swing.JButton PrintImportBtn;
    private javax.swing.JButton PrintImportBtn1;
    private javax.swing.JTextArea PrintPreview;
    private javax.swing.JLabel RevenueCal;
    private javax.swing.JLabel RevenueLb;
    private javax.swing.JTextArea RevenuePrintPreview;
    private javax.swing.JTable SaleBillTable;
    private javax.swing.JLabel SaleTotalLb;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JButton SearchBtn1;
    private javax.swing.JButton SearchBtn2;
    private javax.swing.JButton SearchBtn3;
    private javax.swing.JPanel StaffBillTab;
    private javax.swing.JComboBox<String> StaffIDCb;
    private javax.swing.JTextField YearTxb;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}
