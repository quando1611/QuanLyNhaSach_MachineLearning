/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 19522
 */
public class TaiKhoan_DAL {
    DBConnection connection;

    //Check Login

    public ArrayList<TaiKhoan> getAllTaiKhoan() {
       connection = new DBConnection();
       ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
       String query = "select * from TaiKhoan";
       System.out.println(query);
       try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMaTK(rs.getString("MaTK"));
                taikhoan.setEmail(rs.getString("Email"));
                taikhoan.setMatKhau(rs.getString("MatKhau"));
                taikhoan.setHoTen(rs.getString("HoTen"));
                taikhoan.setMaPhanQuyen(rs.getString("MaPhanQuyen")); 
                taikhoan.setDiaChi(rs.getString("DiaChi"));
                taikhoan.setSDT(rs.getString("DienThoai"));
                result.add(taikhoan);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //add TaiKhoan
    public boolean addTaiKhoan( TaiKhoan taikhoan)
    {
        connection = new DBConnection();
        String query = "insert into TaiKhoan values('" + taikhoan.getMaTK() + "','" + taikhoan.getEmail() + "','" + taikhoan.getMatKhau() + "','" + taikhoan.getHoTen() + "','" + taikhoan.getMaPhanQuyen() + "','" + taikhoan.getDiaChi() + "','" + taikhoan.getSDT() + "')";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }    
    
    //update TaiKhoan
    public boolean updateTaiKhoan(TaiKhoan taikhoan)
    {
        connection = new DBConnection();
        String query = "update TaiKhoan set  DiaChi = '" + taikhoan.getDiaChi() + "', DienThoai = '" + taikhoan.getSDT() + "', Email = '" + taikhoan.getEmail() + "', MaPhanQuyen = '" + taikhoan.getMaPhanQuyen() + "' where MaTK = '" + taikhoan.getMaTK() + "'";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete TaiKhoan
    public boolean deleteTaiKhoan(String matk)
    {
        connection = new DBConnection();
        String query = "delete from TaiKhoan where MaTK = '" + matk + "'";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Search TaiKhoan
    public ArrayList<TaiKhoan> searchTaiKhoan(String search )
    {
        connection = new DBConnection();
        ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
        String query = "select * from TaiKhoan where HoTen like '%" + search + "%' or Email like '%" + search + "%' or DienThoai like '%" + search + "%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {  
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMaTK(rs.getString("MaTK"));
                taikhoan.setEmail(rs.getString("Email"));
                taikhoan.setMatKhau(rs.getString("MatKhau"));
                taikhoan.setHoTen(rs.getString("HoTen"));
                taikhoan.setMaPhanQuyen(rs.getString("MaPhanQuyen")); 
                taikhoan.setDiaChi(rs.getString("DiaChi"));
                taikhoan.setSDT(rs.getString("DienThoai"));
                result.add(taikhoan);
            }
    }catch(SQLException e)
    {
        System.out.println("Null Table");
    }
        return result;
    }
    
    //Get TaiKhoan with Role = Admin
    public ArrayList<TaiKhoan> getAllAdmin() {
       connection = new DBConnection();
       ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
       String query = "select * from TaiKhoan where MaPhanQuyen = 'Admin'";
       System.out.println(query);
       try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMaTK(rs.getString("MaTK"));
                taikhoan.setEmail(rs.getString("Email"));
                taikhoan.setMatKhau(rs.getString("MatKhau"));
                taikhoan.setHoTen(rs.getString("HoTen"));
                taikhoan.setMaPhanQuyen(rs.getString("MaPhanQuyen")); 
                taikhoan.setDiaChi(rs.getString("DiaChi"));
                taikhoan.setSDT(rs.getString("DienThoai"));
                result.add(taikhoan);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Get TaiKhoan with Role = Staff
    public ArrayList<TaiKhoan> getAllStaff() {
       connection = new DBConnection();
       ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
       String query = "select * from TaiKhoan where MaPhanQuyen = 'Sale'";
       System.out.println(query);
       try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMaTK(rs.getString("MaTK"));
                taikhoan.setEmail(rs.getString("Email"));
                taikhoan.setMatKhau(rs.getString("MatKhau"));
                taikhoan.setHoTen(rs.getString("HoTen"));
                taikhoan.setMaPhanQuyen(rs.getString("MaPhanQuyen")); 
                taikhoan.setDiaChi(rs.getString("DiaChi"));
                taikhoan.setSDT(rs.getString("DienThoai"));
                result.add(taikhoan);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Check TaiKhoan exist for create new/update TaiKhoan
    public ArrayList<TaiKhoan> checkExist(String email, String hoten, String diachi, String SDT)
    {
        connection = new DBConnection();
        ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
        String query = "select * from TaiKhoan where HoTen = '" + hoten + "' and Email = '" + email + "' and DienThoai = '" + SDT + "' and DiaChi = '" + diachi + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {  
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMaTK(rs.getString("MaTK"));
                taikhoan.setEmail(rs.getString("Email"));
                taikhoan.setMatKhau(rs.getString("MatKhau"));
                taikhoan.setHoTen(rs.getString("HoTen"));
                taikhoan.setMaPhanQuyen(rs.getString("MaPhanQuyen")); 
                taikhoan.setDiaChi(rs.getString("DiaChi"));
                taikhoan.setSDT(rs.getString("DienThoai"));
                result.add(taikhoan);
            }
    }catch(SQLException e)
    {
        System.out.println("Null Table");
    }
        return result;
    }
    
}
