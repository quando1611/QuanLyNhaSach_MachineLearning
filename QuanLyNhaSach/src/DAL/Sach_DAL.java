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
public class Sach_DAL {
    DBConnection connection;
    
    //Get All Sach
    public ArrayList<Sach> getAllSach()
    {
        connection = new DBConnection();
        ArrayList<Sach> result = new ArrayList<Sach>();
        String query = "select * from Sach";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTenTG(rs.getString("TenTacGia"));
                sach.setTenTheLoai(rs.getString("TenTheLoai"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGia(rs.getFloat("Gia"));
                result.add(sach);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Search Sach by ID
    public ArrayList<Sach> searchSachbyID(String ID)
    {
        connection = new DBConnection();
        ArrayList<Sach> result = new ArrayList<Sach>();
        String query = "select * from Sach where MaSach like '%" + ID +"%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTenTG(rs.getString("TenTacGia"));
                sach.setTenTheLoai(rs.getString("TenTheLoai"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGia(rs.getFloat("Gia"));
                result.add(sach);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Search Sach with Filter
    public ArrayList<Sach> searchSach(String name, String type, String author)
    {
        connection = new DBConnection();
        ArrayList<Sach> result = new ArrayList<Sach>();
        String query = "select * from Sach where TenSach like '%" + name + "%' or TenTheLoai like '%"+ type + "%' or TenTacGia like '%" + author +"%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTenTG(rs.getString("TenTacGia"));
                sach.setTenTheLoai(rs.getString("TenTheLoai"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGia(rs.getFloat("Gia"));
                result.add(sach);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Check Sach Exist for cretae new
    public ArrayList<Sach> searchSachExist(String name, String type, String author)
    {
        connection = new DBConnection();
        ArrayList<Sach> result = new ArrayList<Sach>();
        String query = "select * from Sach where TenSach like '%" + name + "%' and TenTheLoai like '%"+ type + "%' and TenTacGia like '%" + author +"%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTenTG(rs.getString("TenTacGia"));
                sach.setTenTheLoai(rs.getString("TenTheLoai"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGia(rs.getFloat("Gia"));
                result.add(sach);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Check Sach Exist for Update
    public ArrayList<Sach> searchSachExistNoUpdate(String name, String type, String author, int Amount, float Price)
    {
        connection = new DBConnection();
        ArrayList<Sach> result = new ArrayList<Sach>();
        String query = "select * from Sach where TenSach like '%" + name + "%' and TenTheLoai like '%"+ type + "%' and TenTacGia like '%" + author +"%' and SoLuong = '" + Amount + "' and Gia = '"+ Price + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTenTG(rs.getString("TenTacGia"));
                sach.setTenTheLoai(rs.getString("TenTheLoai"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGia(rs.getFloat("Gia"));
                result.add(sach);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Add Sach
    public boolean addSach(Sach sach)
    {
        connection = new DBConnection();
        String query = "insert into Sach values ('" + sach.getMaSach() + "','" + sach.getTenSach() + "','" + sach.getTenTG() + "','" + sach.getTenTheLoai() + "','" + sach.getSoLuong() + "','" + sach.getGia() + "')";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Update Sach
    public boolean updateSach(Sach sach)
    {
        connection = new DBConnection();
        String query = "update Sach set TenSach = '" + sach.getTenSach() + "', TenTacGia = '" + sach.getTenTG() + "', TenTheLoai = '" + sach.getTenTheLoai() + "', SoLuong = '" + sach.getSoLuong() + "', Gia = '" + sach.getGia() + "' where MaSach ='" +sach.getMaSach()+ "'";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete Sach
    public boolean deleteSach(String maSach)
    {
        connection = new DBConnection();
        String query = "delete from Sach where MaSach = '" + maSach + "'";
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
