/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author vladi
 */
public class Conexion {
    
    public static LinkedList<String> Queries = new LinkedList<>();
    
    public static Connection getConexion(){
        String conexionUrl = "jdbc:sqlserver://VLADI;databaseName=Biblioteca;integratedSecurity=true;encrypt=false";
        try{
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            return null;
        }
    }
}
