/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import SQL.Buscar;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author vladi
 */
public class Libro extends Utilidades implements Icud {
    
    private int id;
    private String nombre;
    private String genero;
    private String autor;
    
    public static int idcount;
    
    public static ArrayList<Libro> libros = new ArrayList<>();
    
    
    public Libro(int id, String nombre, String genero, String autor){
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.autor = autor;
    }
    
    public Libro(String nombre, String genero, String autor){
        idcount++;
        this.id = idcount;
        this.nombre = nombre;
        this.genero = genero;
        this.autor = autor;
    }
    
    public Libro(){
        
    }

    @Override
    public void crear(String[] set) {
        String nom = set[0];
        String gen = set[1];
        String auth = set[2];
        int idgenero = 0;
        int idautor = 0;
        libros.add(new Libro(nom, gen, auth));
        
        JOptionPane.showMessageDialog(null, "Nuevo libro " + nom + " agregado");
        
        for(int i = 0; i < Autor.autores.size(); i++){
            if(Autor.autores.get(i).getNombre().equals(auth)){
                idautor = Autor.autores.get(i).getId();
                Autor.autores.get(i).setLibro(nom);
                break;
            }
        }
        
        for(int i = 0; i < Genero.generos.size(); i++){
            if(Genero.generos.get(i).getNombre().equals(gen)){
                idgenero = Genero.generos.get(i).getId();
                break;
            }
        }
        
        SQL.Conexion.Queries.offer(String.format("INSERT INTO libro VALUES(%d, %d, '%s')", idautor, idgenero, nom));
    }

    @Override
    public void actualizar(String[] set) {
        
        String nombrev = set[1];
        String nombren = set[2];
        String gen = set[3];
        String auth = set[4];
        int idautor = 0;
        int idgenero = 0;
        
        for(int i = 0; i < libros.size(); i++){
            if(libros.get(i).getId() ==  Integer.parseInt(set[0])){
                
                for(int y = 0; y < Autor.autores.size(); y++){
                    if(Autor.autores.get(y).getNombre().equals(auth)){
                        idautor = Autor.autores.get(y).getId();
                    }
                }
                
                for(int x = 0; x < Genero.generos.size(); x++){
                    if(Genero.generos.get(x).getNombre().equals(gen)){
                        idgenero = Genero.generos.get(x).getId();
                    }
                }
                
                libros.get(i).setNombre(nombren);
                libros.get(i).setGenero(gen);
                libros.get(i).setAutor(auth);
                
                JOptionPane.showMessageDialog(null, String.format("El libro %s ha sido modificado a %s", nombrev, nombren));
                
                SQL.Conexion.Queries.offer(String.format("UPDATE libro SET Nombre = '%s', Id_genero = %d, Id_autor = %d WHERE Id_libro = %d", nombren, idgenero, idautor, libros.get(i).getId()));
                break;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        for(int i = 0; i < libros.size(); i++){
            if(libros.get(i).getId() == id){
                SQL.Conexion.Queries.offer(String.format("DELETE FROM libro WHERE Id_libro = %d", libros.get(i).getId()));
                JOptionPane.showMessageDialog(null, "Libro " + libros.get(i).getNombre() + " eliminado");
                libros.remove(i);
                break;
            }
        }
    }

    public static void cargar() {
        String[][] lib = Buscar.getLibros();
        libros.clear();
        for (String[] libro : lib) {
            libros.add(new Libro(Integer.parseInt(libro[0]), libro[1], libro[2], libro[3]));
            idcount = Integer.parseInt(libro[0]);
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
