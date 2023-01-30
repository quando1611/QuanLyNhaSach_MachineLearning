/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.*;
import DTO.*;
import java.util.ArrayList;
/**
 *
 * @author 19522
 */
public class HoaDon_BUS {
    
    HoaDon_DAL hoaDon_DAL = new HoaDon_DAL();
    
    public ArrayList<HoaDon> danhSachHoaDon()
    {
        return hoaDon_DAL.danhSachHoaDon();
    }
    
    public ArrayList<HoaDon> danhSachHoaDonComplete()
    {
        return hoaDon_DAL.danhSachHoaDonComplete();
    }
    
    public ArrayList<HoaDon> danhSachHoaDonReturn()
    {
        return hoaDon_DAL.danhSachHoaDonReturn();
    }
    
    public ArrayList<HoaDon> danhSachHoaDonByDay(String date)
    {
        return hoaDon_DAL.danhSachHoaDonByDay(date);
    }
    
    public ArrayList<HoaDon> danhSachHoaDonCompletebyDate(String date)
    {
        return hoaDon_DAL.danhSachHoaDonCompleteByDate(date);
    }
    
    public ArrayList<HoaDon> danhSachHoaDonCompletebyMonth(String month, String year)
    {
        return hoaDon_DAL.danhSachHoaDonCompleteByMonth(month, year);
    }
    
    public ArrayList<HoaDon> searchHoaDonWithKhachHangID(String search)
    {
        return hoaDon_DAL.seacrhHoaDonByKhachHangID(search);
    }
    
    public boolean addHoaDon(HoaDon hoaDon)
    {
        return hoaDon_DAL.addHoaDon(hoaDon);
    }
    
    public boolean updateHoaDon(HoaDon hoaDon)
    {
        return hoaDon_DAL.updateHoaDon(hoaDon);
    }
    
    public boolean deleteHoaDon(String maHD)
    {
        return hoaDon_DAL.deleteHoaDon(maHD);
    }
    
    
}
