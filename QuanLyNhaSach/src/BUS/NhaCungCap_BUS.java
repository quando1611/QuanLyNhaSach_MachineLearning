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
public class NhaCungCap_BUS {
    NhaCungCap_DAL nhacungcap_DAL = new NhaCungCap_DAL();
    
    public ArrayList<NhaCungCap> getNhaCungCap()
    {
        return nhacungcap_DAL.getAllNhaCungCap();
    }
    
    public ArrayList<NhaCungCap> seacrhNhaCungCap(String search)
    {
        return nhacungcap_DAL.searchNhaCungCap(search); 
    }
    
    public boolean addNhaCungCap(NhaCungCap nhacungcap)
    {
        return nhacungcap_DAL.addNhaCungCap(nhacungcap);
    }
    
    public boolean updateNhaCungCap(NhaCungCap nhacungcap)
    {
        return nhacungcap_DAL.updateNhaCungCap(nhacungcap);
    }
    
    public boolean deleteNhaCungCap(String maNhaCC)
    {
        return nhacungcap_DAL.deleteNhaCungCap(maNhaCC);
    }
}
