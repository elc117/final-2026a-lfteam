import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        var app = Javalin.create();

        app.get("/", ctx -> {
            ctx.result("Sistema de Gerenciamento de Equipes de Jiu-Jitsu");
        });

        app.start(7070);
    }
}
