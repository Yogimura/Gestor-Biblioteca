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
public class Autor extends Utilidades implements Icud{
    
    private int id;
    private String nombre;
    private String genero;
    private String libro;
    
    public static int idcount = 0;
    
    public static ArrayList<Autor> autores = new ArrayList<>();
    
    public Autor(int id ,String nombre, String genero, String libro){
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.libro = libro;
        
    }
    
    public Autor(String nombre, String genero, String libro){
        idcount++;
        this.id = idcount;
        this.nombre = nombre;
        this.genero = genero;
        this.libro = libro;
    }
    
    public Autor(){
        
    }

    
    @Override
    public void crear(String[] set) {
        String nom = set[0];
        String gen = set[1];
        int idgenero = 0;
        
        autores.add(new Autor(nom, gen, "Sin libro"));
        
        for(int i = 0; i < Genero.generos.size(); i++){
            if(Genero.generos.get(i).getNombre().equals(gen)){
                idgenero = Genero.generos.get(i).getId();
                break;
            }
        }
        SQL.Conexion.Queries.offer(String.format("INSERT INTO autor VALUES (%d, '%s')", idgenero, nom));
        SQL.Buscar.autores.add(set[0]);
        JOptionPane.showMessageDialog(null, "Nuevo autor " + nom + " agregado");
    }

    @Override
    public void actualizar(String[] set) {
        int idautor = Integer.parseInt(set[0]);
        String nombrev = set[1];
        String nombren = set[2];
        String gen = set[3];
        
        int idgenero = 0;
        for(int i = 0; i < autores.size(); i++){
            if(autores.get(i).getId() == idautor){
                
                for(int y = 0; y < Libro.libros.size(); y++){
                    if(Libro.libros.get(y).getAutor().equals(nombrev)){
                        Libro.libros.get(y).setAutor(nombren);
                    }
                }
                
                for(int x = 0; x < SQL.Buscar.autores.size(); x++){
                    if(SQL.Buscar.autores.get(x).equals(nombrev)){
                        SQL.Buscar.autores.set(x, nombren);
                    }
                }
                
                Autor.autores.get(i).setNombre(nombren);
                Autor.autores.get(i).setGenero(gen);
                
                for(int z = 0; z < Genero.generos.size(); z++){
                    if(Genero.generos.get(z).getNombre().equals(gen)){
                        idgenero = Genero.generos.get(z).getId();
                    }
                }
                
                JOptionPane.showMessageDialog(null, String.format("El autor %s ha sido modificado a %s", nombrev, nombren));
                
                SQL.Conexion.Queries.offer(String.format("UPDATE autor SET Nombre = '%s', Id_genero = %d WHERE id_autor = %d", nombren, idgenero, idautor));
                break;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        
        for(int i = 0; i < autores.size(); i++){
            if(autores.get(i).getId() == id){
                for(int y = 0; y < Libro.libros.size(); y++){
                    if(Libro.libros.get(y).getAutor().equals(autores.get(i).getNombre())){
                        new Libro().eliminar(Libro.libros.get(y).getId());
                    }
                }
                SQL.Conexion.Queries.offer(String.format("DELETE FROM autor WHERE Id_autor = %d", autores.get(i).getId()));
                SQL.Buscar.autores.remove(autores.get(i).getNombre());
                JOptionPane.showMessageDialog(null, "Autor " + autores.get(i).getNombre() + " eliminado");
                autores.remove(i);
                break;
            }
        }
    }

    public static void cargar() {
        String[][] auth = Buscar.getAutores();
        autores.clear();
        for (String[] autore : auth) {
            autores.add(new Autor(Integer.parseInt(autore[0]), autore[1], autore[2], autore[3]));
            idcount = Integer.parseInt(autore[0]);
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

    public String getLibro() {
        return libro;
    }
    
    public void setLibro(String libro) {
        this.libro = libro;
    }
    
}
