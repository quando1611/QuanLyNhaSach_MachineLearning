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
 * @author QUAN DO
 */
public class PhieuNhapKho_BUS {
    PhieuNhapKho_DAL phieunhapkho_DAL = new PhieuNhapKho_DAL();
    public ArrayList<PhieuNhapKho> danhsachPhieuNhapKho()
    {
        return phieunhapkho_DAL.danhsachPhieuNhap();
    }
    
    public ArrayList<PhieuNhapKho> danhsachPhieuNhapKhoByDate(String date)
    {
        return phieunhapkho_DAL.danhsachPhieuNhapByDate(date);
    }
    
    public ArrayList<PhieuNhapKho> danhsachPhieuNhapKhoByMonth(String month, String year)
    {
        return phieunhapkho_DAL.danhsachPhieuNhapByMonth(month, year);
    }
    
    public boolean addPhieuNhapKho(PhieuNhapKho phieunhap)
    {
        return phieunhapkho_DAL.addPhieuNhapKho(phieunhap);
    }
    
     public ArrayList<PhieuNhapKho> searchPhieuNhapKhoWithSupplierID(String search)
    {
        return phieunhapkho_DAL.seacrhPhieuNhapKhoBySupplierID(search);
    }
     
    public boolean updatePhieuNhapKho(PhieuNhapKho phieuNhapKho)
    {
        return phieunhapkho_DAL.updatePhieuNhapKho(phieuNhapKho);
    }
     public boolean deleteHoaDon(String maPNK)
    {
        return phieunhapkho_DAL.deletePhieuNhapKho(maPNK);
    }
}
