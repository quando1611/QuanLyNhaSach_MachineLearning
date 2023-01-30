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
 * @author 19522
 */
public class HoaDon_DAL {
    
    DBConnection connection;
    
    //Get All HoaDOn
    public ArrayList<HoaDon> danhSachHoaDon()
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Get All HoaDOn with status = COmplete
    public ArrayList<HoaDon> danhSachHoaDonComplete()
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon where TinhTrang = 'Complete'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Get All HoaDOn with Status = Return
    public ArrayList<HoaDon> danhSachHoaDonReturn()
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon where TinhTrang = 'Return'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Get All HoaDon with Date
    public ArrayList<HoaDon> danhSachHoaDonByDay(String date)
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon where NgayLap = '" + date + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //get ALl HoaDOn with Day & Status = COmplete
    public ArrayList<HoaDon> danhSachHoaDonCompleteByDate(String date)
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon where TinhTrang = 'Complete' and NgayLap = '" + date + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //get ALl HoaDOn with Month & Status = COmplete
    public ArrayList<HoaDon> danhSachHoaDonCompleteByMonth(String month,String year)
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon where TinhTrang = 'Complete' and MONTH(NgayLap) = '" + month + "' and YEAR(NgayLap) = '" + year + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Search HoaDOn by KhachHangID
    public ArrayList<HoaDon> seacrhHoaDonByKhachHangID(String search)
    {
        connection = new DBConnection();
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        String query = "select * from HoaDon where MaKH like '%" + search + "%'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) { 
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setNgayNhap(rs.getString("NgayLap"));
                hoaDon.setTongTien(Float.parseFloat(rs.getString("TongTien")));
                hoaDon.setTienTra(Float.parseFloat(rs.getString("TienTra")));
                hoaDon.setTinhTrang(rs.getString("TinhTrang"));
                result.add(hoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Add HoaDOn
    public boolean addHoaDon(HoaDon hoaDon)
    {
        connection = new DBConnection();
        String query = "insert into HoaDon values('" + hoaDon.getMaHoaDon() + "', '" + hoaDon.getMaKH() + "', '" + hoaDon.getNgayNhap() + "', '" + hoaDon.getTongTien() + "', '" + hoaDon.getTienTra() + "', '" + hoaDon.getTinhTrang() + "')";
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Update HoaDOn
    public boolean updateHoaDon(HoaDon hoaDon)
    {
        connection = new DBConnection();
        String query = "update HoaDon set MaKH = '" + hoaDon.getMaKH()+ "', NgayLap = '" + hoaDon.getNgayNhap() + "', TongTien = '" + hoaDon.getTongTien() + "', TienTra = '" + hoaDon.getTienTra() + "', TinhTrang = '" + hoaDon.getTinhTrang() + "' where MaHoaDon ='" + hoaDon.getMaHoaDon() +"'";
        System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Delete HoaDon
    public boolean deleteHoaDon(String maHD)
    {
        connection = new DBConnection();
        String query = "delete from HoaDon where MaHoaDon = '" + maHD + "'";
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
