/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.ArrayList;
import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author QUAN DO
 */
public class PhieuNhapKho_DAL {
    
    DBConnection connection;
    
    //get All PhieuNhap
    public ArrayList<PhieuNhapKho> danhsachPhieuNhap()
    {
        connection = new DBConnection();
        ArrayList<PhieuNhapKho> result = new ArrayList<PhieuNhapKho>();
        String query = "select * from PhieuNhapKho";
        System.out.println(query);
        try 
        {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next())
            {
                PhieuNhapKho phieunhap = new PhieuNhapKho();
                phieunhap.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                phieunhap.setNgayNhapKho(rs.getString("NgayNhapKho"));
                phieunhap.setNgayTaoPhieu(rs.getString("NgayTaoPhieu"));
                phieunhap.setMaTK(rs.getString("MaTK"));
                phieunhap.setMaNhaCC(rs.getString("MaNCC"));
                phieunhap.setTongTien(Double.parseDouble(rs.getString("TongTien")));
                result.add(phieunhap);
            }
        }catch (SQLException e)
        {
            System.out.println("Null table");
        }
        return result;
    }
    
    //Get All PhieuNhap with date
    public ArrayList<PhieuNhapKho> danhsachPhieuNhapByDate(String date)
    {
        connection = new DBConnection();
        ArrayList<PhieuNhapKho> result = new ArrayList<PhieuNhapKho>();
        String query = "select * from PhieuNhapKho where NgayTaoPhieu ='" + date + "'";
        System.out.println(query);
        try 
        {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next())
            {
                PhieuNhapKho phieunhap = new PhieuNhapKho();
                phieunhap.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                phieunhap.setMaNhaCC(rs.getString("MaNCC"));
                phieunhap.setMaTK(rs.getString("MaTK"));
                phieunhap.setNgayTaoPhieu(rs.getString("NgayTaoPhieu"));
                phieunhap.setNgayNhapKho(rs.getString("NgayNhapKho"));
                phieunhap.setTongTien(Double.parseDouble(rs.getString("TongTien")));
                result.add(phieunhap);
            }
        }catch (SQLException e)
        {
            System.out.println("Null table");
        }
        return result;
    }
    
    //Get All phieu nhap by month
     public ArrayList<PhieuNhapKho> danhsachPhieuNhapByMonth(String month, String year)
    {
        connection = new DBConnection();
        ArrayList<PhieuNhapKho> result = new ArrayList<PhieuNhapKho>();
        String query = "select * from PhieuNhapKho where MONTH(NgayTaoPhieu) ='" + month + "' and YEAR(NgayTaoPhieu) = '" + year + "'";
        System.out.println(query);
        try 
        {
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next())
            {
                PhieuNhapKho phieunhap = new PhieuNhapKho();
                phieunhap.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                phieunhap.setMaNhaCC(rs.getString("MaNCC"));
                phieunhap.setMaTK(rs.getString("MaTK"));
                phieunhap.setNgayTaoPhieu(rs.getString("NgayTaoPhieu"));
                phieunhap.setNgayNhapKho(rs.getString("NgayNhapKho"));
                phieunhap.setTongTien(Double.parseDouble(rs.getString("TongTien")));
                result.add(phieunhap);
            }
        }catch (SQLException e)
        {
            System.out.println("Null table");
        }
        return result;
    }
    
     //Add Phieu Nhap
    public boolean addPhieuNhapKho(PhieuNhapKho phieunhap)
    {
        connection = new DBConnection();
        String query = "insert into PhieuNhapKho values('" + phieunhap.getMaPhieuNhap() + "', '" + phieunhap.getNgayNhapKho()+ "', '" + phieunhap.getNgayTaoPhieu()+ "', '" + phieunhap.getMaTK() + "', '" + phieunhap.getMaNhaCC() + "', '" + phieunhap.getTongTien() + "')";
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Search PhieuNhap with Supplier ID
    public ArrayList<PhieuNhapKho> seacrhPhieuNhapKhoBySupplierID(String search)
    {
        connection = new DBConnection();
        ArrayList<PhieuNhapKho> result = new ArrayList<PhieuNhapKho>();
        String query = "select * from PhieuNhapKho where MaNCC like '%" + search + "%'" ;
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) { 
                PhieuNhapKho phieunhap = new PhieuNhapKho();
                phieunhap.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                phieunhap.setNgayNhapKho(rs.getString("NgayNhapKho"));
                phieunhap.setNgayTaoPhieu(rs.getString("NgayTaoPhieu"));
                phieunhap.setMaTK(rs.getString("MaTK"));
                phieunhap.setMaNhaCC(rs.getString("MaNCC"));
                phieunhap.setTongTien(Double.parseDouble(rs.getString("TongTien")));
                result.add(phieunhap);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Update PhieuNhap
    public boolean updatePhieuNhapKho(PhieuNhapKho phieuNhapKho)
    {
        connection = new DBConnection();
        String query = "update PhieuNhapKho set MaPhieuNhap = '" + phieuNhapKho.getMaPhieuNhap()+ "', MaNCC = '" + phieuNhapKho.getMaNhaCC()+ "', NgayNhapKho = '" + phieuNhapKho.getNgayNhapKho()+ "', NgayTaoPhieu = '" + phieuNhapKho.getNgayTaoPhieu()+ "', TongTien = '" + phieuNhapKho.getTongTien()+ "'";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete PhieuNhap
    public boolean deletePhieuNhapKho(String maPNK)
    {
        connection = new DBConnection();
        String query = "delete from PhieuNhapKho where MaPhieuNhap = '" + maPNK + "'";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
}
