/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.*;
import DAL.*;
import java.util.ArrayList;
/**
 *
 * @author 19522
 */
public class KhachHang_BUS {
    KhachHang_DAL khachHang_DAL = new KhachHang_DAL();
    
    public ArrayList<KhachHang> getAllKhachHang()
    {
        return khachHang_DAL.getAllKhachHang();
    }
    
    public ArrayList<KhachHang> seacrhKhachHang(String search)
    {
        return khachHang_DAL.searchKhachHang(search); 
    }
    
    public ArrayList<KhachHang> seacrhKhachHangExist(String name, String phone)
    {
        return khachHang_DAL.searchKhachHangExist(name,phone); 
    }
    
    public ArrayList<KhachHang> seacrhKhachHangExistNoUpdate(String name, String address, String phone, String email, String SoSPDaMua)
    {
        return khachHang_DAL.searchKhachHangExistNoUpdate(name,address,phone,email,SoSPDaMua); 
    }
    
    public boolean addKhachHang(KhachHang khachHang)
    {
        return khachHang_DAL.addKhachHang(khachHang);
    }
    
    public boolean updateKhachHang(KhachHang khachHang)
    {
        return khachHang_DAL.updateKhachHang(khachHang);
    }
    
    public boolean updateAmountProductKhachHang(String idString, int amount)
    {
        return khachHang_DAL.updateAmountProductKhachHang(idString, amount);
    }
    
    public boolean deleteKhachHang(String maKH)
    {
        return khachHang_DAL.deleteKhachHang(maKH);
    }
}
