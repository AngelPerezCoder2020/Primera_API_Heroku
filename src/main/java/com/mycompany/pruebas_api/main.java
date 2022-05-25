package com.mycompany.pruebas_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class main {
    public static void main(String[] args) throws IOException,MalformedURLException{
        URL url = new URL("https://springbootvalidations.herokuapp.com/Estudiantes");
        System.out.println(GETRequest(url));
    }
    public static int GETRequest(URL url)throws IOException{
        HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setConnectTimeout(5000);
        conexion.setReadTimeout(5000);
        int status = conexion.getResponseCode();
        String linea;
        if(status<299){
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            while((linea=lector.readLine())!=null){
                System.out.println(linea);
            }
            lector.close();
        }else{
            System.out.println("Damn bro, something went wrong :(");
        }
        return status;
    }
    public static int POSTRequest(URL url)throws IOException{
        HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
        conexion.setConnectTimeout(5000);
        String nuevo = "{\"id\":\"5\",\"nombre\":\"Jacobo Arbenz\",\"email\":\"jacobo1971@gmai.com\",\"carnet\":\"0907-20-1940\",\"promedio\":\"100\",\"creaciondate\":\"2022-05-24\"}";
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type","application/json");
        conexion.setDoOutput(true);
        OutputStream output = conexion.getOutputStream();
        output.write(nuevo.getBytes());
        output.flush();
        output.close();
        return conexion.getResponseCode();
    }
    public static int PUTRequest(int id)throws IOException{
        String u = "https://springbootvalidations.herokuapp.com/Estudiantes/"+id;
        URL url = new URL(u);
        HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
        conexion.setConnectTimeout(5000);
        String nuevo = "{\"id\":\"5\",\"nombre\":\"Jacobo Arbenz\",\"email\":\"jacobo1971@gmai.com\",\"carnet\":\"0907-20-1940\",\"promedio\":\"100\",\"creaciondate\":\"2022-05-24\"}";
        conexion.setRequestMethod("PUT");
        conexion.setRequestProperty("Content-Type","application/json");
        conexion.setDoOutput(true);
        OutputStream output = conexion.getOutputStream();
        output.write(nuevo.getBytes());
        output.flush();
        output.close();
        return conexion.getResponseCode();
    }
    public static int DELETERequest(int id)throws IOException{
        String u = "https://springbootvalidations.herokuapp.com/Estudiantes/"+id;
        URL url = new URL(u);
        HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
        conexion.setConnectTimeout(5000);
        conexion.setRequestMethod("DELETE");
        return conexion.getResponseCode();
    }
}