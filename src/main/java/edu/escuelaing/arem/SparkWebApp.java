package edu.escuelaing.arem;

import static spark.Spark.*;

/**
 *
 * @author Nicole Montana
 */
public class SparkWebApp {
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/principal", (req, res) -> {
            res.redirect("/Forma.html");
            return null;    
            });
        Cache cache = new Cache();
        
        path("/search", ()->{   
            get("/:API/:value", (req,res)->{ 
                if(cache.containCache(req.params(":value")+" TIME_SERIES_DAILY "+req.params(":API"))){
                    return cache.getValueCache(req.params(":value")+" TIME_SERIES_DAILY "+req.params(":API"));
                }
                else{
                    cache.insertDataCache(req.params(":value")+" TIME_SERIES_DAILY "+req.params(":API"), new StringBuffer(HttpConnection.getData(req.params(":value"), req.params(":API"))));               
                    return new String(HttpConnection.getData(req.params(":value"), req.params(":API")));
                }
            });
        });
        
    }
    
    /*
    Función que genera un puerto por defecto para la conexión
    @return el número del puerto
    */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}