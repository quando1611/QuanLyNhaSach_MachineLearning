/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.*;
import DTO.*;
import DAL.DBConnection;
import java.util.ArrayList;

/**
 *
 * @author 19522
 */
public class ChiTietHoaDon_BUS {
    
    ChiTietHoaDon_DAL chiTietHoaDon_DAL = new ChiTietHoaDon_DAL();
    
    public boolean addChiTietHoaDon(ChiTietHoaDon chitiet)
    {
        return chiTietHoaDon_DAL.addChiTietHoaDon(chitiet);
    }
  
    public ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon(String maHD)
    {
        return chiTietHoaDon_DAL.danhSachChiTietHoaDon(maHD);
    }
    
    public boolean deleteCHiTietHoaDon(String maHD)
    {
        return chiTietHoaDon_DAL.deleteChiTietHoaDon(maHD);
    }
    
    
}
