package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.controladores.CrudTradicionalControlador;
import org.example.controladores.PlantillasControlador;
import org.example.servicios.BootStrapServices;
import org.example.servicios.DataBaseServices;

import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Javalin app = Javalin.create(config -> {
            //configurando los documentos estaticos.
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress = false;
                staticFileConfig.aliasCheck = null;
            });
        });
        app.start(7000);
        app.get("/", ctx -> {
            ctx.result("Esta es la segunda aplicacion");
        });
        BootStrapServices.startDb();
        DataBaseServices.getInstancia().testConexion();
        BootStrapServices.crearTablas();
        new CrudTradicionalControlador(app).aplicarRutas();
        new PlantillasControlador(app).aplicarRutas();
        BootStrapServices.stopDb();
    }

}
