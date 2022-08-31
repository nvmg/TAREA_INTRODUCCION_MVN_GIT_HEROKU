package edu.escuelaing.arem.tarea_introduccion_mvn_git_heroku.src.main.java.edu.escuelaing.arep;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Nicole Montana
 */
public class Cache {
    
    ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    
    /*
    Función que retorna el valor de una llave especifica
    @param key, un string que representa la llave
    @return el valor
    */
    public String getValueCache(String key){
        return cache.get(key);
    }
    
    /*
    Función que inserta a la lista de cache las llaves y los valores nuevos
    @param key, un String que representa la llave
    @param value, un String que representa el valor que se desea insertar
    */
    public void insertDataCache(String key, String value){
        cache.put(key, value);
    }
}
