package com.estudiante.conexion;
import com.mysql.cj.jdbc.ConnectionGroup;

import java.sql.Connection;
public class Conexion {
    public static ConnectionGroup getConexion(){
        ConnectionGroup conexion = null;
        var baseDatos = "estudiantes_db";
        var url ="jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario ="root";
        var password ="";
        //cargamos la clase del driver en memoria
        class.formName("com.mysql.jdbc.Driver");
        conexion= Dri


    }
}
