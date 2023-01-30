/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 19522
 */
public class DBConnection {
    String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=QLNS;user=sa;password=sa;";
    Connection conn;
    public DBConnection(){
        //Get COnnection
        try {
             conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) 
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //function SELECT - get data from table in Database
    public ResultSet ExcuteQueryGetTable(String querySQL){
        //Object that get the result
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();           
            rs = state.executeQuery(querySQL);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    //funtion INSERT, DELETE, UPDATE -- 3 of them have the same frame
    public ResultSet ExcuteQueryUpdateDB(String querySQL){
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(querySQL);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
}
