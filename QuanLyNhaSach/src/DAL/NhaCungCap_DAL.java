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
public class NhaCungCap_DAL {
   DBConnection connection;
    
    //Get All NCC
    public ArrayList<NhaCungCap> getAllNhaCungCap()
    {
        connection = new DBConnection();
        ArrayList<NhaCungCap> result = new ArrayList<NhaCungCap>();
        String query = "select * from NhaCungCap";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                NhaCungCap nhacungcap = new NhaCungCap();
                nhacungcap.setMaNhaCC(rs.getString("MaNCC"));
                nhacungcap.setTenNhaCC(rs.getString("TenNCC"));
                result.add(nhacungcap);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Search NCC
    public ArrayList<NhaCungCap> searchNhaCungCap(String search)
    {
        connection = new DBConnection();
        ArrayList<NhaCungCap> result = new ArrayList<NhaCungCap>();
        String query = "select * from NhaCungCap where MaNCC like '%" + search + "%' or TenNCC like '%" + search+ "%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                NhaCungCap nhacungcap = new NhaCungCap();
                nhacungcap.setMaNhaCC(rs.getString("MaNCC"));
                nhacungcap.setTenNhaCC(rs.getString("TenNCC"));
                result.add(nhacungcap);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Add NCC
    public boolean addNhaCungCap(NhaCungCap nhacungcap)
    {
        connection = new DBConnection();
        String query = "insert into NhaCungCap values ('" + nhacungcap.getMaNhaCC()+ "','" +nhacungcap.getTenNhaCC() + "')";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Update NCC
    public boolean updateNhaCungCap(NhaCungCap nhacungcap)
    {
        connection = new DBConnection();
        String query = "update NhaCungCap set TenNCC = '"  + nhacungcap.getTenNhaCC() + "' where MaNCC = '" + nhacungcap.getMaNhaCC() + "'" ;
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete NCC
    public boolean deleteNhaCungCap(String maNhaCC)
    {
        connection = new DBConnection();
        String query = "delete from NhaCungCap where MaNCC = '" + maNhaCC + "'";
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
