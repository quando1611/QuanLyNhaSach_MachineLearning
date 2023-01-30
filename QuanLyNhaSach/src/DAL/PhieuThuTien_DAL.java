/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.*;
import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author 19522
 */
public class PhieuThuTien_DAL {
    DBConnection connection;
    
    //Get ALl PhieuThuTien
    public ArrayList<PhieuThuTien> getAllPhieuThuTien()
    {
        connection = new DBConnection();
        ArrayList<PhieuThuTien> result = new ArrayList<PhieuThuTien>();
        String query = "select * from PhieuThuTien";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                PhieuThuTien phieuthutien = new PhieuThuTien();
                phieuthutien.setMaPhieuThu(rs.getString("MaPhieuThu"));
                phieuthutien.setMaKH(rs.getString("MaKH"));
                phieuthutien.setNgaythu(rs.getString("NgayThu"));
                phieuthutien.setSoTienThu(Float.parseFloat(rs.getString("SoTienThu")));
                phieuthutien.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                result.add(phieuthutien);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //GetAllPhieuThuTien with StaffID
    public ArrayList<PhieuThuTien> getAllPhieuThuTienbyStaffID(String staffID)
    {
        connection = new DBConnection();
        ArrayList<PhieuThuTien> result = new ArrayList<PhieuThuTien>();
        String query = "select * from PhieuThuTien where MaTaiKhoan ='" + staffID + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                PhieuThuTien phieuthutien = new PhieuThuTien();
                phieuthutien.setMaPhieuThu(rs.getString("MaPhieuThu"));
                phieuthutien.setMaKH(rs.getString("MaKH"));
                phieuthutien.setNgaythu(rs.getString("NgayThu"));
                phieuthutien.setSoTienThu(Float.parseFloat(rs.getString("SoTienThu")));
                phieuthutien.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                result.add(phieuthutien);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //GetAllPhieuThuTien with StaffID & Date
    public ArrayList<PhieuThuTien> getAllPhieuThuTienbyStaffIDAndDate(String staffID, String date)
    {
        connection = new DBConnection();
        ArrayList<PhieuThuTien> result = new ArrayList<PhieuThuTien>();
        String query = "select * from PhieuThuTien where MaTaiKhoan like '%" + staffID + "%' and NgayThu ='" + date + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                PhieuThuTien phieuthutien = new PhieuThuTien();
                phieuthutien.setMaPhieuThu(rs.getString("MaPhieuThu"));
                phieuthutien.setMaKH(rs.getString("MaKH"));
                phieuthutien.setNgaythu(rs.getString("NgayThu"));
                phieuthutien.setSoTienThu(Float.parseFloat(rs.getString("SoTienThu")));
                phieuthutien.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                result.add(phieuthutien);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Add PhieuThuTien
    public boolean addPhieuThuTien(PhieuThuTien phieuThuTien)
    {
        connection = new DBConnection();
        String query = "insert into PhieuThuTien values ('" + phieuThuTien.getMaPhieuThu() + "','" + phieuThuTien.getMaKH() + "','" + phieuThuTien.getNgaythu() + "','" + phieuThuTien.getSoTienThu() + "','" + phieuThuTien.getMaTaiKhoan() + "')";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete PhieuThuTien
     public boolean deletePhieuThuTien(String phieuthutienID)
    {
        connection = new DBConnection();
        String query = "delete from PhieuThuTien where MaPhieuThu = '" + phieuthutienID + "'";
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
