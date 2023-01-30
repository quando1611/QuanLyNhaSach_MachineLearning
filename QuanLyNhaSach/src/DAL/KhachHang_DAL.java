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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 19522
 */
public class KhachHang_DAL {
    DBConnection connection;
    
    //Get All KhachHang
    public ArrayList<KhachHang> getAllKhachHang()
    {
        connection = new DBConnection();
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        String query = "select * from KhachHang";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getString("MaKH"));
                khachHang.setTenKH(rs.getString("TenKH"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setDienThoai(rs.getString("DienThoai"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoSPDaMua(rs.getString("SoSPDaMua"));
                result.add(khachHang);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Search KhachHang
    public ArrayList<KhachHang> searchKhachHang(String search)
    {
        connection = new DBConnection();
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        String query = "select * from KhachHang where MaKH like '%" + search + "%' or TenKH like '%" + search+ "%' or DienThoai like '%" + search +"%' or Email like '%" + search + "%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getString("MaKH"));
                khachHang.setTenKH(rs.getString("TenKH"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setDienThoai(rs.getString("DienThoai"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoSPDaMua(rs.getString("SoSPDaMua"));
                result.add(khachHang);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Check KhachHang Exist
    public ArrayList<KhachHang> searchKhachHangExist(String name , String phone )
    {
        connection = new DBConnection();
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        String query = "select * from KhachHang where TenKH like '%" + name + "%' and DienThoai like '%" + phone +"%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getString("MaKH"));
                khachHang.setTenKH(rs.getString("TenKH"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setDienThoai(rs.getString("DienThoai"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoSPDaMua(rs.getString("SoSPDaMua"));
                result.add(khachHang);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Check KhachHang Exist when update
    public ArrayList<KhachHang> searchKhachHangExistNoUpdate(String name , String Address, String phone, String Email, String SoSPDaMua )
    {
        connection = new DBConnection();
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        String query = "select * from KhachHang where TenKH like '%" + name + "%' and DienThoai like '%" + phone + "%' and DiaChi like '%" + Address + "%' and Email like '%" + Email + "%' and SoSPDaMua = '" + SoSPDaMua + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getString("MaKH"));
                khachHang.setTenKH(rs.getString("TenKH"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setDienThoai(rs.getString("DienThoai"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoSPDaMua(rs.getString("SoSPDaMua"));
                result.add(khachHang);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Add KhachHang
    public boolean addKhachHang(KhachHang khachHang)
    {
        connection = new DBConnection();
        String query = "insert into KhachHang values ('" + khachHang.getMaKH() + "','" +khachHang.getTenKH()+ "','" + khachHang.getDiaChi() + "','" + khachHang.getDienThoai() + "','" + khachHang.getEmail() + "','" +khachHang.getSoSPDaMua() + "')";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Updater KhachHang
    public boolean updateKhachHang(KhachHang khachHang)
    {
        connection = new DBConnection();
        String query = "update KhachHang set TenKH = '" + khachHang.getTenKH() + "', DiaChi = '" + khachHang.getDiaChi() + "', DienThoai = '" +khachHang.getDienThoai() +"', Email = '" +khachHang.getEmail() +"', SoSPDaMua = '" + khachHang.getSoSPDaMua() + "' where MaKH = '" +khachHang.getMaKH() + "'" ;
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete AmountProduct KhachHang
     public boolean updateAmountProductKhachHang(String id, int amount)
    {
        connection = new DBConnection();
        String query = "update KhachHang set SoSPDaMua = SoSPDaMua + " + amount + " where TenKH = '" + id + "'" ;
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
     //Delete KhachHang
    public boolean deleteKhachHang(String maKH)
    {
        connection = new DBConnection();
        String query = "delete from KhachHang where MaKH = '" + maKH + "'";
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
