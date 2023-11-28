/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author vladi
 */
public class Utilidades {
    
    public static String[][] toTableL(ArrayList<Libro> set){
        
        String[][] tabla = new String[set.size()][4];
        
        for(int i = 0; i < set.size(); i++){
            tabla[i][0] = String.valueOf(set.get(i).getId());
            tabla[i][1] = set.get(i).getNombre();
            tabla[i][2] = set.get(i).getGenero();
            tabla[i][3] = set.get(i).getAutor();
        }
        return tabla;
    }
    
    public static String[][] toTableA(ArrayList<Autor>set ){
        
        String[][] tabla = new String[set.size()][4];
        
        for(int i = 0; i < set.size(); i++){
            tabla[i][0] = String.valueOf(set.get(i).getId());
            tabla[i][1] = set.get(i).getNombre();
            tabla[i][2] = set.get(i).getGenero();
            tabla[i][3] = set.get(i).getLibro();
        }
        return tabla;
    }
    
    public static String[][] FiltrarL(ArrayList<Libro> set, String filter, String value){
        
        String[][] Godvalley = new String[1][1];
        
        switch(filter){
            case "nombre" -> {
                var data = set.stream()
                        .filter(p -> p.getNombre().equals(value))
                        .map(p -> new Object[] {p.getId(), p.getNombre(), p.getGenero(), p.getAutor()})
                        .collect(Collectors.toList());

                Godvalley = new String[data.size()][4];
                
                for(int i = 0; i < Godvalley.length; i++){
                    Object[] datap = data.get(i);
                    String[] valley = {datap[0].toString(), datap[1].toString(), datap[2].toString(), datap[3].toString()};
                    Godvalley[i] = valley;
                }
            }
                
            case "genero" -> {
                var data1 = set.stream()
                        .filter(p -> p.getGenero().equals(value))
                        .map(p -> new Object[] {p.getId(), p.getNombre(), p.getGenero(), p.getAutor()})
                        .collect(Collectors.toList());

                Godvalley = new String[data1.size()][4];
                
                for(int i = 0; i < Godvalley.length; i++){
                    Object[] datap = data1.get(i);
                    String[] valley = {datap[0].toString(), datap[1].toString(), datap[2].toString(), datap[3].toString()};
                    Godvalley[i] = valley;
                }
            }
                
            case "autor" -> {
                var data3 = set.stream()
                        .filter(p -> p.getAutor().equals(value))
                        .map(p -> new Object[] {p.getId(), p.getNombre(), p.getGenero(), p.getAutor()})
                        .collect(Collectors.toList());

                Godvalley = new String[data3.size()][4];
                
                for(int i = 0; i < Godvalley.length; i++){
                    Object[] datap = data3.get(i);
                    String[] valley = {datap[0].toString(), datap[1].toString(), datap[2].toString(), datap[3].toString()};
                    Godvalley[i] = valley;
                }
            }
                
        }
        
        return Godvalley;
    }
    
    public static Object[][] FiltrarA(ArrayList<Autor> set, String filter, String value){
        
        //String[] valley;
        Object[][] Godvalley = new Object[1][1];
        switch(filter){
            case "nombre" -> {
                var data = set.stream()
                        .filter(p -> p.getNombre().equals(value))
                        .map(p -> new Object[] {p.getId(), p.getNombre(), p.getGenero(), p.getLibro()})
                        .collect(Collectors.toList());

                Godvalley = new String[data.size()][4];
                
                for(int i = 0; i < Godvalley.length; i++){
                    Object[] datap = data.get(i);
                    String[] valley = {datap[0].toString(), datap[1].toString(), datap[2].toString(), datap[3].toString()};
                    Godvalley[i] = valley;
                }
            }
   
            case "genero" -> {
                var data1 = set.stream()
                        .filter(p -> p.getGenero().equals(value))
                        .map(p -> new Object[] {p.getId(), p.getNombre(), p.getGenero(), p.getLibro()})
                        .collect(Collectors.toList());

                Godvalley = new String[data1.size()][4];
                
                for(int i = 0; i < Godvalley.length; i++){
                    Object[] datap = data1.get(i);
                    String[] valley = {datap[0].toString(), datap[1].toString(), datap[2].toString(), datap[3].toString()};
                    Godvalley[i] = valley;
                }
            }
                
            case "libro" -> {
                var data2 = set.stream()
                        .filter(p -> p.getNombre().equals(value))
                        .map(p -> new Object[] {p.getId(), p.getNombre(), p.getGenero(), p.getLibro()})
                        .collect(Collectors.toList());

                Godvalley = new String[data2.size()][4];
                
                for(int i = 0; i < Godvalley.length; i++){
                    Object[] datap = data2.get(i);
                    String[] valley = {datap[0].toString(), datap[1].toString(), datap[2].toString(), datap[3].toString()};
                    Godvalley[i] = valley;
                }
            }
                
        }
        
        return Godvalley;
    }
}
