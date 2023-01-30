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
public class PhieuThuTien_BUS {
    PhieuThuTien_DAL phieuThuTien_DAL = new PhieuThuTien_DAL();
    
    public ArrayList<PhieuThuTien> getAllPhieuThuTien()
    {
        return phieuThuTien_DAL.getAllPhieuThuTien();
    }
    
    public ArrayList<PhieuThuTien> getAllPhieuThuTienByStaffID(String staffID)
    {
        return phieuThuTien_DAL.getAllPhieuThuTienbyStaffID(staffID);
    }
    
    public ArrayList<PhieuThuTien> getAllPhieuThuTienByStaffIDAndDate(String staffID, String date)
    {
        return phieuThuTien_DAL.getAllPhieuThuTienbyStaffIDAndDate(staffID,date);
    }
    
    public boolean addPhieuThuTien(PhieuThuTien phieuthutien)
    {
        return phieuThuTien_DAL.addPhieuThuTien(phieuthutien);
    }
    
    public boolean deletePhieuThuTien(String phieuthutienID)
    {
        return phieuThuTien_DAL.deletePhieuThuTien(phieuthutienID);
    }
    
}
