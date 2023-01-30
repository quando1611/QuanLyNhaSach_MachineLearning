/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.*;
import DAL.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author QUAN DO
 */
public class ChiTietPhieuNhap_DAL{
        DBConnection connection;
        
        //Add CHiTietPhieuNhap
        public boolean addChiTietPhieuNhap(ChiTietPhieuNhap chitietnhap)
        {
            connection = new DBConnection();
            String query = "insert into ChiTietPhieuNhap values('" + chitietnhap.getMaPhieuNhap() + "', '" + chitietnhap.getMaSach() + "', '" + chitietnhap.getSoLuong() + "')";
            System.err.println(query);
            try
            {
                ResultSet rs = connection.ExcuteQueryUpdateDB(query);
                return true;
        }catch(Exception e)
        { 
            System.out.println("Failed!");
            return false;
        }
        }
        
        //Get ChitietphieuNhap
        public ArrayList<ChiTietPhieuNhap> danhSachChiTietPhieuNhap(String id)
    {
        connection = new DBConnection();
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<ChiTietPhieuNhap>();
        String query = "select * from ChiTietPhieuNhap where MaPhieuNhap = '" + id + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
                chiTietPhieuNhap.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                chiTietPhieuNhap.setMaSach(rs.getString("MaSach"));
                chiTietPhieuNhap.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                result.add(chiTietPhieuNhap);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
        
        //Search CHiTietPhieuNhap With SupplierID
        public ArrayList<ChiTietPhieuNhap> seacrChiTietPhieuNhapBySupplierID(String search)
    {
        connection = new DBConnection();
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<ChiTietPhieuNhap>();
        String query = "select * from ChiTietPhieuNhap where MaNCC like '%" + search + "%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) { 
                ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
                chiTietPhieuNhap.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                chiTietPhieuNhap.setMaSach(rs.getString("MaSach"));
                chiTietPhieuNhap.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                result.add(chiTietPhieuNhap);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
        //Delete ChiTietPhieuNhap
        public boolean deleteChiTietPhieuNhap(String manhap)
    {
       
        connection = new DBConnection();
        String query = "delete from ChiTietPhieuNhap where MaPhieuNhap = '" + manhap + "'";
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
