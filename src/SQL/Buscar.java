/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author vladi
 */
public class Buscar extends Conexion {
    
    public static ArrayList<String> generos = new ArrayList<>();
    public static ArrayList<String> autores = new ArrayList<>();
    
    static public String[][] getLibros(){
        ArrayList<String[]> librosAp = new ArrayList<>();
        String[][] libros = new String[1][1];
        
        try{
            Statement consultar = getConexion().createStatement();
            
            String Query = "SELECT libro.Id_libro as Id_libro, libro.Nombre as Titulo, genero.Nombre as Genero, autor.Nombre as Autor FROM libro inner join autor on libro.Id_autor = autor.Id_autor inner join genero on libro.Id_genero = genero.Id_genero";
            
            ResultSet consulta = consultar.executeQuery(Query);
            
            while(consulta.next()){
                librosAp.add(new String[] {consulta.getString("Id_libro"), consulta.getString("Titulo"), consulta.getString("Genero"), consulta.getString("Autor")});
            }
            
            libros = new String[librosAp.size()][4];
            
            for(int i = 0; i < libros.length; i++){
                libros[i] = librosAp.get(i);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());            
        }
        
        return libros;
    }
    
    static public String[][] getAutores(){
       ArrayList<String[]> autoresAp = new ArrayList<>();
       String[][] auth = new String[1][1];
    
        try{
            Statement consultar = getConexion().createStatement();
            
            String Query = "SELECT libro.Nombre as 'Libro preferencial', autor.Id_autor as Id_autor, autor.Nombre as Nombre, genero.Nombre as Genero FROM autor inner join libro on libro.id_autor = autor.id_autor inner join genero on autor.id_genero = genero.id_genero";
            
            ResultSet consulta = consultar.executeQuery(Query);
            
            while(consulta.next()){
                autoresAp.add(new String[] {consulta.getString("Id_autor"), consulta.getString("Nombre"), consulta.getString("Genero"), consulta.getString("Libro preferencial")});
            }
            
            auth = new String[autoresAp.size()][4];
            
            for(int i = 0; i < auth.length; i++){
                auth[i] = autoresAp.get(i);
            }
            
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());            
        }
        
        return auth;
    }
    
    static public String[][] getGeneros(){
        ArrayList<String[]> generosBp = new ArrayList<>();
        String[][] generosAp = new String[1][1];
        try{
            Statement consultar = getConexion().createStatement();
            
            String Query = "SELECT * FROM genero";
            
            ResultSet consulta = consultar.executeQuery(Query);
            
            while(consulta.next()){
                generos.add(consulta.getString("nombre"));
                generosBp.add(new String[] {consulta.getString("Id_genero"), consulta.getString("Nombre")});
            }
            
            generosAp = new String[generosBp.size()][2];
            
            for(int i = 0; i < generosAp.length; i++){
                generosAp[i] = generosBp.get(i);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        return generosAp;
    }
    
    static public ArrayList<String> getAutoresSimple(){
  
        try{
            Statement consultar = getConexion().createStatement();
            
            String Query = "SELECT nombre FROM autor";
            
            ResultSet consulta = consultar.executeQuery(Query);
            
            while(consulta.next()){
                autores.add(consulta.getString("nombre"));
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        return autores;
    }
}
