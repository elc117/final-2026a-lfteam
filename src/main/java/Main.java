import io.javalin.Javalin;
import model.Atleta;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("INICIOU O MAIN");

        List<Atleta> atletas = new ArrayList<>();

        atletas.add(
                new Atleta(
                        1,
                        "Luis Felipe",
                        22,
                        "Branca",
                        75.0
                )
        );

        atletas.add(
                new Atleta(
                        2,
                        "João Silva",
                        25,
                        "Azul",
                        82.5
                )
        );

        var app = Javalin.create();

        app.get("/", ctx -> {
            ctx.result("Sistema de Gerenciamento de Equipes de Jiu-Jitsu");
        });

        app.get("/atletas", ctx -> {
            ctx.json(atletas);
        });

        System.out.println("VAI INICIAR O JAVALIN");

        app.start(7070);

        System.out.println("JAVALIN INICIADO");
    }
}