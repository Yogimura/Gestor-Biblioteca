/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author vladi
 */
public class Genero implements Icud{

    private int id;
    private String nombre;
    
    static int idcount = 0;
    
    public Genero(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    
    public Genero(String nombre){
        idcount++;
        this.id = idcount;
        this.nombre = nombre;
    }
    
    public Genero(){
        
    }
    
    public static ArrayList<Genero> generos = new ArrayList<>();
    
    @Override
    public void crear(String[] set) {
        SQL.Buscar.generos.add(set[0]);
        generos.add(new Genero(set[0]));
        JOptionPane.showMessageDialog(null, "Nuevo genero " + set[0] + " creado");
        SQL.Conexion.Queries.offer(String.format("INSERT INTO genero VALUES('%s')", set[0]));
    }

    @Override
    public void actualizar(String[] set) {
        String nombrev = set[0];
        String nombren = set[1];
        
        for(int i = 0; i < Autor.autores.size(); i++){
            if(Autor.autores.get(i).getGenero().equals(nombrev)){
                Autor.autores.get(i).setGenero(nombren);
            }
        }
        
        for(int i = 0; i < Libro.libros.size(); i++){
            if(Libro.libros.get(i).getGenero().equals(nombrev)){
                Libro.libros.get(i).setGenero(nombren);
            }
        }
        
        for(int i = 0; i < generos.size(); i++){
            if(generos.get(i).getNombre().equals(nombrev)){
                generos.get(i).setNombre(nombren);
                for(int y = 0; y < SQL.Buscar.generos.size(); y++){
                    if(SQL.Buscar.generos.get(y).equals(nombrev)){
                        SQL.Buscar.generos.set(y, nombren);
                    }
                }
                
                JOptionPane.showMessageDialog(null, String.format("El genero %s ha sido modificado a %s", nombrev, nombren));
                
                SQL.Conexion.Queries.offer(String.format("UPDATE genero SET Nombre = '%s' WHERE Id_genero = %d", nombren, generos.get(i).getId()));
                break;
            }
        }
        
        
        
    }

    @Override
    public void eliminar(int id) {
        for(int i = 0; i < Autor.autores.size(); i++){
                if(Autor.autores.get(i).getGenero().equals(SQL.Buscar.generos.get(id))){
                    new Autor().eliminar(Autor.autores.get(i).getId());
                }
            }
        SQL.Conexion.Queries.offer(String.format("DELETE FROM genero WHERE Id_genero = %d", id));
        JOptionPane.showMessageDialog(null, "Genero " + SQL.Buscar.generos.get(id) + " Eliminado");
        SQL.Buscar.generos.remove(id);
    }
    
    public static void cargar(){
        String[][] gens = SQL.Buscar.getGeneros();
        generos.clear();
        for (String[] genero : gens) {
            generos.add(new Genero(Integer.parseInt(genero[0]), genero[1]));
            idcount = Integer.parseInt(genero[0]);
        }
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
