/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ChiTietHoaDon;
import DTO.*;
import DAL.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author 19522
 */
public class ChiTietHoaDon_DAL {
    
    
    DBConnection connection;
    
    //Add chitietHoaDOn
    public boolean addChiTietHoaDon(ChiTietHoaDon chitiet)
    {
        connection = new DBConnection();
        String query = "insert into ChiTietHoaDon values('" + chitiet.getMaHD() + "', '" + chitiet.getMaSach() + "', '" + chitiet.getSoLuongMua() + "')";
         System.err.println(query);
        try {
            ResultSet rs = connection.ExcuteQueryUpdateDB(query);
            return true;
        } catch (Exception e) {
            System.err.println("Failed!");
            return false;
        }
    }
    
    //Get CHiTioetHoaDOn
    public ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon(String maHD)
    {
        connection = new DBConnection();
        ArrayList<ChiTietHoaDon> result = new ArrayList<ChiTietHoaDon>();
        String query = "select * from ChiTietHoaDon where MaHD = '" + maHD + "'";
        System.out.println(query);
        try{
            ResultSet rs = connection.ExcuteQueryGetTable(query);
            while (rs.next()) {                
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHD(rs.getString("MaHD"));
                chiTietHoaDon.setMaSach(rs.getString("MaSach"));
                chiTietHoaDon.setSoLuongMua(Integer.parseInt(rs.getString("SoLuongSachMua")));
                result.add(chiTietHoaDon);
            }
        }catch(SQLException e)
        {
            System.out.println("Null Table!");
        }
        return result;
    }
    
    //Delete CHiTietHoaDon
    public boolean deleteChiTietHoaDon(String maHD)
    {
       
        connection = new DBConnection();
        String query = "delete from ChiTietHoaDon where MaHD = '" + maHD + "'";
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
