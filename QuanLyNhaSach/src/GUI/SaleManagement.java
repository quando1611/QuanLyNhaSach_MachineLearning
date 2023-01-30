/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.*;
import DAL.*;
import BUS.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import javax.swing.JTextField;

/**
 *
 * @author 19522
 */
public class SaleManagement extends javax.swing.JFrame {

    DBConnection connection;
    private String customerSelectID = "";
//        private int press = 0;
    private int maximum = 0; //maxsimum book in storage
    private double total = 0.0; // total money have to pay
    private boolean exist = false; //check exist from ArrayList selectarr below
    private String billID = ""; //generate billID with btn IDGenerate
    private ArrayList<Sach> selectarr = new ArrayList<Sach>(); //select arraylist for book selected
    private String phieuthutienID = "";
    /**
     * Creates new form SaleManagement
     */
    public SaleManagement() { //Call all the function what will load Data
        initComponents();
        loadAllTableData();
        loadTypeCbData();
        loadCustomerCbData();
    }

    public void loadAllTableData() { // All the function load Table Data from start
        loadAllBook();
        loadAllBill();
        loadCurrentDate();
        loadReturnBill();
    }

    //Get all data from Sach
    public void loadAllBook() {     //Load All book to SearchBookTable
        DefaultTableModel table = (DefaultTableModel) SearchBookTable.getModel();
        ArrayList<Sach> arr = new ArrayList<Sach>();
        Sach_BUS sach_BUS = new Sach_BUS();
        arr = sach_BUS.getAllSach();
        Sach sach = new Sach();
        try {
            for (int i = 0; i < arr.size(); i++) {
                sach = arr.get(i);
                String id = sach.getMaSach();
                String name = sach.getTenSach();
                String author = sach.getTenTG();
                String type = sach.getTenTheLoai();
                int amount = sach.getSoLuong();
                float price = sach.getGia();
                Object[] row = {id, name, author, type, amount, price};
                table.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchBookTable.setModel(table);
    }

    public void loadAllBill() {    
        //Load All bill to  BillShowTable
        DefaultTableModel table = (DefaultTableModel) BillShowTable.getModel();
        ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
        HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
        arr = hoaDon_BUS.danhSachHoaDonComplete();
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

    public void loadReturnBill() //Load all Return Bill in ReturnBillTable
    {
        DefaultTableModel table = (DefaultTableModel) ReturnBillTable.getModel();
        ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
        HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
        arr = hoaDon_BUS.danhSachHoaDonReturn();
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
            ReturnBillTable.setModel(table);
        }
    }

    public void loadTypeCbData() {          //Load TypeCB (Type of Book) for searching
        connection = new DBConnection();
        String query = "select TenTheLoai from Sach";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {
                String name = rs.getString("TenTheLoai");
                TypeCb.addItem(name);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
    }

    public void loadCustomerCbData() {          //Load CustomerIDCB & CustomerBillIDCB Data for create bill & Searching bill
        connection = new DBConnection();
        String query = "select * from KhachHang";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {
                String name = rs.getString("TenKH");
                CustomerID.addItem(name);
                CustomerBillIDCb.addItem(name);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
    }

    public void loadCurrentDate() {             //get the current day by default for JDateChooser - DatePicker
        Calendar today = Calendar.getInstance();
        DatePicker.setCalendar(today);
    }

    public String getCustomerID(String search) { //Get the customer ID by Customer name
        connection = new DBConnection();
        String query = "select * from KhachHang where TenKH ='" + search + "'";
        try {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {
                customerSelectID = rs.getString("MaKH");
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        return customerSelectID;
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

    public void seacrhBookWithFilter() {        //Search book with filter
        resetSearchData();
        String name = SearchTxb.getText();
        String type = TypeCb.getSelectedItem().toString();
        if (type == "All") {
            type = "";
        }
        String author = AuthorSearchTxb.getText();
        DefaultTableModel table = (DefaultTableModel) SearchBookTable.getModel();
        ArrayList<Sach> arr = new ArrayList<Sach>();
        Sach_BUS sach_BUS = new Sach_BUS();
        arr = sach_BUS.searchSach(name, type, author);
        Sach sach = new Sach();
        try {
            for (int i = 0; i < arr.size(); i++) {
                sach = arr.get(i);
                String id = sach.getMaSach();
                String name1 = sach.getTenSach();
                String author1 = sach.getTenTG();
                String type1 = sach.getTenTheLoai();
                int amount = sach.getSoLuong();
                double price = sach.getGia();
                Object[] row = {id, name1, author1, type1, amount, price};
                table.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        SearchBookTable.setModel(table);
    }

    public void resetSearchData() // reset the SearchBooktable to null
    {
        DefaultTableModel tableModel = (DefaultTableModel) SearchBookTable.getModel();
        tableModel.setRowCount(0);
        SearchBookTable.setModel(tableModel);
    }

    public void resetSelectTable() { //reset SelectBookTable to Null
        DefaultTableModel tableModel = (DefaultTableModel) SelectBookTable.getModel();
        tableModel.setRowCount(0);
        SelectBookTable.setModel(tableModel);
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

    public void resetReturnBillTable() //reset ReturnBillTable to Null
    {
        DefaultTableModel tableModel = (DefaultTableModel) ReturnBillTable.getModel();
        tableModel.setRowCount(0);
        ReturnBillTable.setModel(tableModel);
    }

    public void resetSelectTableAfterConfirm() { //reset SelectTable after confirm the bill
        DefaultTableModel tableModel = (DefaultTableModel) SelectBookTable.getModel();
        tableModel.setRowCount(0);
        SelectBookTable.setModel(tableModel);
        selectarr.clear();
        exist = false;
    }

    public void resetCbData() { //reset all the CustomerCombobox
        TypeCb.removeAllItems();
        TypeCb.addItem("All");
    }

    public void resetText() // reset textfield after event done!
    {
        IDBill.setText("");
        loadCurrentDate();
        TotalTxb.setText("");
        total = 0;
        ReceiveTxb.setText("");
        SearchTxb.setText("");
        AuthorSearchTxb.setText("");

    }

    public String randomID() // get random to generate ID for all of things
    {
        LocalDateTime local = LocalDateTime.now();
        long milis = local.getNano();
        String id = Long.toString(milis);
        System.err.println(id);
        return id;
    }

    public void SelectRow() { //SelectRow to add in searchBooktable to SelectBookTable
        total = 0; //if reset -> total = 0 & calculate again from the start
        DefaultTableModel selecttable = (DefaultTableModel) SelectBookTable.getModel();
        DefaultTableModel tableModel = (DefaultTableModel) SearchBookTable.getModel();
        //Get Data from Table
        String id = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 0).toString();
        String name = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 1).toString();
        String author = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 2).toString();
        String type = tableModel.getValueAt(SearchBookTable.getSelectedRow(), 3).toString();
        int amount = Integer.parseInt(AmountTxb.getText());
        maximum = Integer.parseInt(tableModel.getValueAt(SearchBookTable.getSelectedRow(), 4).toString());
        Float price = Float.parseFloat(tableModel.getValueAt(SearchBookTable.getSelectedRow(), 5).toString());
        System.out.println(maximum);
        if (amount >= maximum) {
            JOptionPane.showMessageDialog(this, "Exceeding : numbers want to buy must < numbers left in storage!");
        } else {
            //insert in to SelectBookTable
            Sach sach = new Sach();
            sach.setMaSach(id);
            sach.setTenSach(name);
            sach.setTenTG(author);
            sach.setTenTheLoai(type);
            sach.setSoLuong(amount);
            sach.setGia(price);
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
            resetSelectTable();
            for (Sach sachFinal : selectarr) {
                String idFinal = sachFinal.getMaSach();
                String nameFinal = sachFinal.getTenSach();
                String authorFinal = sachFinal.getTenTG();
                String typeFinal = sachFinal.getTenTheLoai();
                int amountFinal = sachFinal.getSoLuong();
                double priceFinal = sachFinal.getGia();
                Object[] row = {idFinal, nameFinal, amountFinal};
                selecttable.addRow(row);
                total += amountFinal * priceFinal;
            }
            SelectBookTable.setModel(selecttable);
            TotalTxb.setText(Double.toString(total));
        }
    }

    public void SelectBillRow() { //Select BillShowTable row to show detail Bill in DetailBillTable
        DefaultTableModel tableModel = (DefaultTableModel) BillShowTable.getModel();
        DefaultTableModel tableDetailModel = (DefaultTableModel) DetailBillTable.getModel();
        //Get Data from Table
        String id = tableModel.getValueAt(BillShowTable.getSelectedRow(), 0).toString(); //Get MaHD from HoaDonTable to query and get data about ChiTietHoaDon in HoaDon
        ArrayList<ChiTietHoaDon> arr = new ArrayList<ChiTietHoaDon>();
        ChiTietHoaDon_BUS chiTietHoaDon_BUS = new ChiTietHoaDon_BUS();
        arr = chiTietHoaDon_BUS.danhSachChiTietHoaDon(id);
        ChiTietHoaDon chitiet = new ChiTietHoaDon();
        try {
            for (int i = 0; i < arr.size(); i++) {
                chitiet = arr.get(i);
                String chitietID = chitiet.getMaHD();
                String chitietSachID = chitiet.getMaSach();
                String chitietTenSach = getBookName(chitietSachID);
                int chitietSoLuong = chitiet.getSoLuongMua();
                Object[] row = {chitietID, chitietTenSach, chitietSoLuong};
                tableDetailModel.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("No thing!");
        }
        DetailBillTable.setModel(tableDetailModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked") //Don't touch it.
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JLabel();
        ParentPanel = new javax.swing.JTabbedPane();
        SearchTab = new javax.swing.JPanel();
        SearchBtn = new javax.swing.JButton();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        SearchBookTable = new javax.swing.JTable();
        SearchTxb = new javax.swing.JTextField();
        CreateBillTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SelectBookTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        IDBill = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DatePicker = new com.toedter.calendar.JDateChooser();
        CustomerID = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        CancelBillBtn = new javax.swing.JButton();
        TotalTxb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ReceiveTxb = new javax.swing.JTextField();
        DeleteRowBtn = new javax.swing.JButton();
        ConfirmBillBtn = new javax.swing.JButton();
        IDGenerateBtn = new javax.swing.JButton();
        PurchasedBillTab = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DetailBillTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        BillShowTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        ReturnBillBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        CustomerBillIDCb = new javax.swing.JComboBox<>();
        SearchBillBtn = new javax.swing.JButton();
        ReturnTab = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ReturnBillTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SALE&BILL MANAGEMENT");

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

        SearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBtn.setToolTipText("Search");
        SearchBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        SearchTab.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 240, -1, 52));

        PlusBtn.setBackground(new java.awt.Color(153, 255, 153));
        PlusBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        PlusBtn.setText("+");
        PlusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlusBtnActionPerformed(evt);
            }
        });
        SearchTab.add(PlusBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 440, 60, 51));

        SubtractBtn.setBackground(new java.awt.Color(255, 204, 204));
        SubtractBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SubtractBtn.setText("-");
        SubtractBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubtractBtnActionPerformed(evt);
            }
        });
        SearchTab.add(SubtractBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 440, 60, 51));

        buttonGroup1.add(AllRadio);
        AllRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AllRadio.setSelected(true);
        AllRadio.setText("All");
        SearchTab.add(AllRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 9, -1, -1));

        buttonGroup1.add(FilterRadio);
        FilterRadio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FilterRadio.setText("Filter");
        FilterRadio.setToolTipText("");
        FilterRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterRadioActionPerformed(evt);
            }
        });
        SearchTab.add(FilterRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 9, -1, -1));

        SelectBookBtn.setBackground(new java.awt.Color(153, 255, 153));
        SelectBookBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SelectBookBtn.setText("SELECT");
        SelectBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectBookBtnActionPerformed(evt);
            }
        });
        SearchTab.add(SelectBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 530, 116, 51));

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
        SearchTab.add(AmountTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 440, 70, 50));

        TypeCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        TypeCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchTab.add(TypeCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 120, 200, 45));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Type");
        SearchTab.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(964, 127, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Author");
        SearchTab.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(964, 190, -1, -1));

        AuthorSearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AuthorSearchTxb.setToolTipText("Search Here....");
        AuthorSearchTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchTab.add(AuthorSearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 180, 200, 42));

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SearchBookTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Author", "Type", "Amount", "Price"
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

        SearchTab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 590));

        SearchTxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchTxb.setText("Search name ...");
        SearchTxb.setToolTipText("Search Here....");
        SearchTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchTab.add(SearchTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(942, 56, 310, 42));

        ParentPanel.addTab("Choose Book", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png")), SearchTab); // NOI18N

        CreateBillTab.setBackground(new java.awt.Color(255, 255, 255));
        CreateBillTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SelectBookTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SelectBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amout"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SelectBookTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SelectBookTable.setGridColor(new java.awt.Color(0, 0, 0));
        SelectBookTable.setShowGrid(true);
        jScrollPane3.setViewportView(SelectBookTable);
        SelectBookTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        CreateBillTab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 930, 280));

        jLabel2.setText("Bill ID");
        CreateBillTab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        IDBill.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDBill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        IDBill.setEnabled(false);
        CreateBillTab.add(IDBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 160, 30));

        jLabel3.setText("Date");
        CreateBillTab.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, -1, -1));

        DatePicker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DatePicker.setDateFormatString("yyyy-MM-dd");
        CreateBillTab.add(DatePicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 210, 30));

        CustomerID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CreateBillTab.add(CustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 180, 30));

        jLabel4.setText("Customer ID");
        CreateBillTab.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, -1));

        CancelBillBtn.setBackground(new java.awt.Color(255, 204, 204));
        CancelBillBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CancelBillBtn.setText("CANCEL");
        CancelBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBillBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(CancelBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 510, 116, 51));

        TotalTxb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TotalTxb.setEnabled(false);
        TotalTxb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalTxbActionPerformed(evt);
            }
        });
        CreateBillTab.add(TotalTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 210, 50));

        jLabel5.setText("Total");
        CreateBillTab.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 430, 30, 30));

        jLabel8.setText("Receive");
        CreateBillTab.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 430, 50, 30));

        ReceiveTxb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ReceiveTxb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ReceiveTxb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ReceiveTxbKeyTyped(evt);
            }
        });
        CreateBillTab.add(ReceiveTxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 420, 210, 50));

        DeleteRowBtn.setBackground(new java.awt.Color(255, 51, 102));
        DeleteRowBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteRowBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteRowBtn.setText("DELETE");
        DeleteRowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteRowBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(DeleteRowBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 360, 116, 51));

        ConfirmBillBtn.setBackground(new java.awt.Color(153, 255, 153));
        ConfirmBillBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ConfirmBillBtn.setText("CONFIRM");
        ConfirmBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmBillBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(ConfirmBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 510, 116, 51));

        IDGenerateBtn.setBackground(new java.awt.Color(153, 255, 153));
        IDGenerateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        IDGenerateBtn.setText("ID Generate");
        IDGenerateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDGenerateBtnActionPerformed(evt);
            }
        });
        CreateBillTab.add(IDGenerateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 120, 30));

        ParentPanel.addTab("Create Bill", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_bill_35px_1.png")), CreateBillTab); // NOI18N

        PurchasedBillTab.setBackground(new java.awt.Color(255, 255, 255));
        PurchasedBillTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        DetailBillTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amout"
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
        BillShowTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        PurchasedBillTab.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 280));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Detail");
        PurchasedBillTab.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        ReturnBillBtn.setBackground(new java.awt.Color(255, 51, 102));
        ReturnBillBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ReturnBillBtn.setForeground(new java.awt.Color(255, 255, 255));
        ReturnBillBtn.setText("RETURN");
        ReturnBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnBillBtnActionPerformed(evt);
            }
        });
        PurchasedBillTab.add(ReturnBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 500, 116, 51));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Customer ID");
        PurchasedBillTab.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, -1, -1));

        CustomerBillIDCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        CustomerBillIDCb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PurchasedBillTab.add(CustomerBillIDCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 150, 45));

        SearchBillBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_search_35px.png"))); // NOI18N
        SearchBillBtn.setToolTipText("Search");
        SearchBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBillBtnActionPerformed(evt);
            }
        });
        PurchasedBillTab.add(SearchBillBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 20, -1, 50));

        ParentPanel.addTab("Purchased Bills", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_initiate_money_transfer_35px_1.png")), PurchasedBillTab); // NOI18N

        ReturnTab.setBackground(new java.awt.Color(255, 255, 255));
        ReturnTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ReturnBillTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ReturnBillTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        ReturnBillTable.setGridColor(new java.awt.Color(0, 0, 0));
        ReturnBillTable.setShowGrid(true);
        jScrollPane6.setViewportView(ReturnBillTable);
        ReturnBillTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        ReturnTab.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 1120, 590));

        ParentPanel.addTab("Return Bills", new javax.swing.ImageIcon(getClass().getResource("/GUI/Component/Minisize/icons8_restart_35px.png")), ReturnTab); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        // Search btn in Search Book Tab
        if (AllRadio.isSelected() == true) {
            resetSearchData();
            loadAllTableData();
        } else if (FilterRadio.isSelected() == true) {
            seacrhBookWithFilter();
            resetCbData();
            loadTypeCbData();
        }
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void SelectBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectBookBtnActionPerformed
        // Select Button in SearchBook Tab
        try {
            SelectRow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have selected!");
        }
    }//GEN-LAST:event_SelectBookBtnActionPerformed

    private void SubtractBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubtractBtnActionPerformed
        // Subtraction Btn in SeacrhBook Tab
        int amount = Integer.parseInt(AmountTxb.getText());
        if (amount == 0) {

        } else {
            amount -= 1;
        }
        AmountTxb.setText(Integer.toString(amount));

    }//GEN-LAST:event_SubtractBtnActionPerformed

    private void PlusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlusBtnActionPerformed
        // Plus Btn in SearchBook Tab
        int amount = Integer.parseInt(AmountTxb.getText());
        if (amount >= 0) {
            amount += 1;
        }
        AmountTxb.setText(Integer.toString(amount));
    }//GEN-LAST:event_PlusBtnActionPerformed

    private void BackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseClicked
        // Back Btn in top panel
        this.dispose();
    }//GEN-LAST:event_BackBtnMouseClicked

    private void IDGenerateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDGenerateBtnActionPerformed
        //ID Generate Btn in CreateBill Tab
        billID = "B" + randomID();
        phieuthutienID = "M" + randomID();
        IDBill.setText(billID);
    }//GEN-LAST:event_IDGenerateBtnActionPerformed

    private void CancelBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBillBtnActionPerformed
        // CanCel Bill Btn in CreateBillTab
        resetSelectTableAfterConfirm();
        resetSearchData();
        resetBillShowTable();
        resetDetailBillTable();
        loadAllBill();
        loadAllBook();
        ParentPanel.setSelectedIndex(0);
    }//GEN-LAST:event_CancelBillBtnActionPerformed

    private void ConfirmBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmBillBtnActionPerformed
        // Confirm Bill
        HoaDon hoaDon = new HoaDon();
        String date = ((JTextField) DatePicker.getDateEditor().getUiComponent()).getText(); //Get String from DatePicker - JDateChooser
        System.err.println(date);
        if (IDBill.getText().equals("")) { //if BillID is null
            JOptionPane.showMessageDialog(this, "Please Generate Bill ID by clicking the button 'ID Generate'...");
        } else if (ReceiveTxb.getText().equals("") || Double.parseDouble(ReceiveTxb.getText()) < total) { //If receive < total
            JOptionPane.showMessageDialog(this, "Receive money doesn't match with Total cost!");
        } else 
        {
            //Start Create HoaDon
            hoaDon.setMaHoaDon(IDBill.getText()); 
            hoaDon.setMaKH(getCustomerID(CustomerID.getSelectedItem().toString()));
            hoaDon.setNgayNhap(date);
            hoaDon.setTongTien(total);
            hoaDon.setTienTra(Double.parseDouble(ReceiveTxb.getText()));
            hoaDon.setTinhTrang("Complete");
            HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
            hoaDon_BUS.addHoaDon(hoaDon); 
            //After create HoaDon-> Create each ChiTietHoaDon in HoaDon
            for (Sach sach : selectarr) {
                ChiTietHoaDon chitiet = new ChiTietHoaDon();
                chitiet.setMaHD(IDBill.getText());
                chitiet.setMaSach(sach.getMaSach());
                chitiet.setSoLuongMua(sach.getSoLuong());
                ChiTietHoaDon_BUS chiTietHoaDon_BUS = new ChiTietHoaDon_BUS();
                chiTietHoaDon_BUS.addChiTietHoaDon(chitiet);
                //add amount of book have boought into account 
                    String IDBuyBook = getCustomerID(CustomerID.getSelectedItem().toString());
                    KhachHang khachHangBuyBook = new KhachHang();
                    KhachHang_BUS khachHang_BUS = new KhachHang_BUS();
                    ArrayList<KhachHang> khachHangBuy = new ArrayList<KhachHang>();
                    khachHangBuy = khachHang_BUS.seacrhKhachHang(IDBuyBook);
                    khachHangBuyBook = khachHangBuy.get(0);
                    khachHangBuyBook.setSoSPDaMua(Integer.toString(Integer.parseInt(khachHangBuyBook.getSoSPDaMua()) + sach.getSoLuong()));
                    khachHang_BUS.updateKhachHang(khachHangBuyBook);
                //GetOldAmount and update new amount for Sach
                Sach sachInStorage = new Sach();
                int amountOld = 0;
                ArrayList<Sach> arrInStorage = new ArrayList<Sach>();
                Sach_BUS sach_BUS = new Sach_BUS();
                arrInStorage = sach_BUS.searchSachbyID(sach.getMaSach());
                    sachInStorage = arrInStorage.get(0);
                    String id = sachInStorage.getMaSach();
                    String name1 = sachInStorage.getTenSach();
                    String author1 = sachInStorage.getTenTG();
                    String type1 = sachInStorage.getTenTheLoai();
                    amountOld = sachInStorage.getSoLuong();
                    double price = sachInStorage.getGia();
                    //get new amount and update for Sach
                    sach.setSoLuong(amountOld - sach.getSoLuong());
                    sach_BUS.updateSach(sach);
            }
            //
            //Start Create PhieuThuTien
            PhieuThuTien phieuthutien = new PhieuThuTien();
            phieuthutien.setMaPhieuThu(phieuthutienID);
            phieuthutien.setMaKH(getCustomerID(CustomerID.getSelectedItem().toString()));
            phieuthutien.setNgaythu(date);
            phieuthutien.setSoTienThu((float)total);
            phieuthutien.setMaTaiKhoan(PublicClass.StaffID);
            PhieuThuTien_BUS phieuThuTien_BUS = new PhieuThuTien_BUS();
            phieuThuTien_BUS.addPhieuThuTien(phieuthutien);
            System.err.println("Succeed!");
            JOptionPane.showMessageDialog(this, "Create Bill Successful!");
            resetSelectTableAfterConfirm();
            resetSearchData();
            resetBillShowTable();
            resetDetailBillTable();
            resetText();
            loadAllBill();
            loadAllBook();
            ParentPanel.setSelectedIndex(2);
        }
        
    }//GEN-LAST:event_ConfirmBillBtnActionPerformed

    private void BillShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillShowTableMouseClicked
        // TODO add your handling code here:
        resetDetailBillTable();
        SelectBillRow();
    }//GEN-LAST:event_BillShowTableMouseClicked

    private void DeleteRowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteRowBtnActionPerformed
        // Delete row of selectBookTable
        try {
            int deleteRow = SelectBookTable.getSelectedRow();
            System.out.println("remove at index" + deleteRow);
            selectarr.remove(deleteRow);
            total = 0; //after delete -> calculate again from the start
            DefaultTableModel selecttable = (DefaultTableModel) SelectBookTable.getModel();
            resetSelectTable();
            for (Sach sachFinal : selectarr) {
                String idFinal = sachFinal.getMaSach();
                String nameFinal = sachFinal.getTenSach();
                String authorFinal = sachFinal.getTenTG();
                String typeFinal = sachFinal.getTenTheLoai();
                int amountFinal = sachFinal.getSoLuong();
                double priceFinal = sachFinal.getGia();
                Object[] row = {idFinal, nameFinal, amountFinal};
                selecttable.addRow(row);
                total += amountFinal * priceFinal;
            }
            SelectBookTable.setModel(selecttable);
            TotalTxb.setText(Double.toString(total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Row have been selected!");
        }
    }//GEN-LAST:event_DeleteRowBtnActionPerformed

    private void SearchBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBillBtnActionPerformed
        // Search Bill with Customer ID
        resetBillShowTable();
        resetDetailBillTable();
        DefaultTableModel billtable = (DefaultTableModel) BillShowTable.getModel();
        String search = CustomerBillIDCb.getSelectedItem().toString();
        if (search.equals("All")) {
            loadAllBill();
        } else {
            String seacrhid = getCustomerID(search); // get ID Customer from name by function have created before
            ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
            HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
            arr = hoaDon_BUS.searchHoaDonWithKhachHangID(seacrhid);
            System.out.println(arr.size());
            HoaDon hoaDon = new HoaDon();
            try {
                for (int i = 0; i < arr.size(); i++) {
                    hoaDon = arr.get(i);
                    String hoaDonID = hoaDon.getMaHoaDon();
                    String hoaDonMaKH = hoaDon.getMaKH();
                    String hoaDonNgayNhap = hoaDon.getNgayNhap();
                    Double hoaDonTongTien = hoaDon.getTongTien();
                    Double hoaDonTienTra = hoaDon.getTienTra();
                    String hoaDonTinhTrang = hoaDon.getTinhTrang();
                    Object[] row = {hoaDonID, hoaDonMaKH, hoaDonNgayNhap, hoaDonTongTien, hoaDonTienTra, hoaDonTinhTrang};
                    billtable.addRow(row);
                }
            } catch (Exception e) {
                System.out.println("No thing!");
            }
            BillShowTable.setModel(billtable);
        }

    }//GEN-LAST:event_SearchBillBtnActionPerformed

    private void ReturnBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnBillBtnActionPerformed
        // ReturnBill btn in Bill tab
        resetReturnBillTable();
        DefaultTableModel billtable = (DefaultTableModel) BillShowTable.getModel();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(billtable.getValueAt(BillShowTable.getSelectedRow(), 0).toString());
        hoaDon.setMaKH(billtable.getValueAt(BillShowTable.getSelectedRow(), 1).toString());
        hoaDon.setNgayNhap(billtable.getValueAt(BillShowTable.getSelectedRow(), 2).toString());
        hoaDon.setTongTien(Double.parseDouble(billtable.getValueAt(BillShowTable.getSelectedRow(), 3).toString()));
        hoaDon.setTienTra(Double.parseDouble(billtable.getValueAt(BillShowTable.getSelectedRow(), 4).toString()));
        hoaDon.setTinhTrang("Return");
        HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
        hoaDon_BUS.updateHoaDon(hoaDon);
        resetReturnBillTable();
        loadReturnBill();
        resetBillShowTable();
        loadAllBill();
        ParentPanel.setSelectedIndex(3);
        JOptionPane.showMessageDialog(this, "Return Successfully!");
    }//GEN-LAST:event_ReturnBillBtnActionPerformed

    private void FilterRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FilterRadioActionPerformed

    private void AllRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AllRadioActionPerformed

    private void TotalTxbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalTxbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalTxbActionPerformed

    private void AmountTxbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AmountTxbKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_AmountTxbKeyTyped

    private void ReceiveTxbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReceiveTxbKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_ReceiveTxbKeyTyped

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
            java.util.logging.Logger.getLogger(SaleManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AllRadio;
    private javax.swing.JTextField AmountTxb;
    private javax.swing.JTextField AuthorSearchTxb;
    private javax.swing.JLabel BackBtn;
    private javax.swing.JTable BillShowTable;
    private javax.swing.JButton CancelBillBtn;
    private javax.swing.JButton ConfirmBillBtn;
    private javax.swing.JPanel CreateBillTab;
    private javax.swing.JComboBox<String> CustomerBillIDCb;
    private javax.swing.JComboBox<String> CustomerID;
    private com.toedter.calendar.JDateChooser DatePicker;
    private javax.swing.JButton DeleteRowBtn;
    private javax.swing.JTable DetailBillTable;
    private javax.swing.JRadioButton FilterRadio;
    private javax.swing.JTextField IDBill;
    private javax.swing.JButton IDGenerateBtn;
    private javax.swing.JTabbedPane ParentPanel;
    private javax.swing.JButton PlusBtn;
    private javax.swing.JPanel PurchasedBillTab;
    private javax.swing.JTextField ReceiveTxb;
    private javax.swing.JButton ReturnBillBtn;
    private javax.swing.JTable ReturnBillTable;
    private javax.swing.JPanel ReturnTab;
    private javax.swing.JButton SearchBillBtn;
    private javax.swing.JTable SearchBookTable;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JPanel SearchTab;
    private javax.swing.JTextField SearchTxb;
    private javax.swing.JButton SelectBookBtn;
    private javax.swing.JTable SelectBookTable;
    private javax.swing.JButton SubtractBtn;
    private javax.swing.JTextField TotalTxb;
    private javax.swing.JComboBox<String> TypeCb;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}
