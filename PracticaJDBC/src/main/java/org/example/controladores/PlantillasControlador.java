package org.example.controladores;

import org.example.encapsulaciones.Estudiante;
import org.example.util.BaseControlador;
import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class PlantillasControlador extends BaseControlador {

    public PlantillasControlador(Javalin app) {
        super(app);
        registrandoPlantillas();
    }
    private void registrandoPlantillas(){
        //Registrando los templates. es necesario incluir la libería io.javalin:javalin-rendering:5.3.2
        JavalinRenderer.register(new JavalinThymeleaf(), ".html");
    }
    @Override
    public void aplicarRutas() {
        app.routes(() -> {

            path("/thymeleaf", () -> {

                get("/", ctx -> {
                    List<Estudiante> listaEstudiante = getEstudiantes();

                    Map<String, Object> modelo = new HashMap<>();
                        modelo.put("titulo", "Ejemplo de funcionalidad Thymeleaf");
                        modelo.put("listaEstudiante", listaEstudiante);

                        //
                        ctx.render("/templates/thymeleaf/funcionalidad.html", modelo);
                    });
            });
        });
    }
    @NotNull
    private List<Estudiante> getEstudiantes() {
        //listando los estudiantes..
        List<Estudiante> listaEstudiante = new ArrayList<>();
        listaEstudiante.add(new Estudiante(20011136, "Carlos Camacho", "ITT"));
        listaEstudiante.add(new Estudiante(20011137, "Otro Estudiante", "ISC"));
        listaEstudiante.add(new Estudiante(20011138, "Otro otro", "ISC"));
        return listaEstudiante;
    }
}
