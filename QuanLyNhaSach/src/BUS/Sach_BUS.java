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
public class Sach_BUS {
    Sach_DAL sach_DAL = new Sach_DAL();
    
    public ArrayList<Sach> getAllSach()
    {
        return sach_DAL.getAllSach();
    }
    
    public ArrayList<Sach> searchSach(String name, String type, String author)
    {
        return sach_DAL.searchSach(name, type, author);
    }
    
    public ArrayList<Sach> searchSachExist(String name, String type, String author)
    {
        return sach_DAL.searchSachExist(name, type, author);
    }
    
    public ArrayList<Sach> searchSachExistNoUpdate(String name, String type, String author, int Amount, float Price)
    {
        return sach_DAL.searchSachExistNoUpdate(name, type, author, Amount, Price);
    }
    
    public ArrayList<Sach> searchSachbyID(String id)
    {
        return sach_DAL.searchSachbyID(id);
    }
    
    public boolean addSach(Sach sach)
    {
        return sach_DAL.addSach(sach);
    }
    
    public boolean  updateSach(Sach sach)
    {
        return sach_DAL.updateSach(sach);
    }
    
    public boolean deleteSach(String maSach)
    {
        return sach_DAL.deleteSach(maSach);
    }
}
