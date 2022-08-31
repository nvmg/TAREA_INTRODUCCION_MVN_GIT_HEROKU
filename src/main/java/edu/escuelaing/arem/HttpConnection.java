package edu.escuelaing.arem;

/**
 *
 * @author Nicole Montana
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    //private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";

    /*
    Función que abre la conexión hacia la URL solicitada por el usuario
    @param GET_URL, un string que indica la URL solicitada
    @return con, el canal
    @throws IOException 
    */
    public static HttpURLConnection conexion(String GET_URL) throws IOException{
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con;
    }
    
    /*
    Función que nos retorna la respuesta a la solicitud realizada.
    @param function, es un String que hace referencia al tiempo pedido en la URL
    @param symbol, es un String que hace referencia a la empresa
    @return un String con la información
    @throws IOException 
    */
    public static String getData(String function, String symbol) throws IOException {
        //Posible URL
        String URL = "https://www.alphavantage.co/query?function=" + function + "&symbol=" + symbol + "&apikey=demo";
       
        if (function == "TIME_SERIES_INTRADAY"){
            URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=demo";
        }
  
        HttpURLConnection con = conexion(URL);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            return getResponse(con);
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "{Error}";
    }
    
    /*
    Función que nos retorna la respuesta a la solicitud realizada.
    @param con, el canal por el cual se enviara la respuesta
    @return un String con la respuesta
    @throws IOException 
    */
    public static String getResponse(HttpURLConnection con) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    
    /*
    Función que indica los tipos de tiempo posibles
    @param date, un String con el tiempo solicitado en el API
    @return function, un String que indica la función correcta
    */
    public String getFunction(String date){
        String function = "";
        switch(date){
                case "Intraday":
                    function = "TIME_SERIES_INTRADAY";
                    break;
                case "Daily":
                    function = "TIME_SERIES_DAILY";
                    break;
                case "Monthly":
                    function = "TIME_SERIES_MONTHLY";
                    break;
                case "Weekly":
                    function = "TIME_SERIES_WEEKLY";
                    break;
        }
        return function;
    }
}