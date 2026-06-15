import io.javalin.Javalin;
import model.Atleta;
import model.Professor;
import model.Equipe;
import model.Graduacao;
import model.Treino;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("INICIOU O MAIN");

        List<Atleta> atletas = new ArrayList<>();
        List<Professor> professores = new ArrayList<>();
        List<Equipe> equipes = new ArrayList<>();
        List<Graduacao> graduacoes = new ArrayList<>();
        List<Treino> treinos = new ArrayList<>();

        // ATLETAS

        atletas.add(new Atleta(1, "Luis Felipe", 22, "Branca", 75.0));
        atletas.add(new Atleta(2, "João Silva",  25, "Azul",   82.5));

        // PROFESSORES

        professores.add(new Professor(1L, "Carlos Mendes", "Faixa Preta"));
        professores.add(new Professor(2L, "Marcos Silva",  "Faixa Marrom"));

        // EQUIPES

        equipes.add(new Equipe(1L, "Equipe Samurai",    "Santa Maria"));
        equipes.add(new Equipe(2L, "Equipe Guerreiros", "Ijuí"));

        // GRADUAÇÕES

        graduacoes.add(new Graduacao(1L, "Branca", "Azul",  "2026-06-14"));
        graduacoes.add(new Graduacao(2L, "Azul",   "Roxa",  "2026-05-10"));

        // TREINOS

        treinos.add(new Treino(1L, "2026-06-14", "Treino de raspagens e finalizações"));
        treinos.add(new Treino(2L, "2026-06-12", "Treino de passagem de guarda"));

        // Porta dinâmica: usa a variável PORT do Render, ou 7070 localmente
        int port = System.getenv("PORT") != null
                ? Integer.parseInt(System.getenv("PORT"))
                : 7070;

        var app = Javalin.create(config -> {
            // CORS: permite chamadas do GitHub Pages (e qualquer outro domínio)
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> {
                    rule.anyHost();
                });
            });
        });

        app.get("/", ctx -> {
            ctx.result("Sistema de Gerenciamento de Equipes de Jiu-Jitsu");
        });

        // ATLETAS

        app.get("/atletas", ctx -> {
            ctx.json(atletas);
        });

        app.get("/atletas/{id}", ctx -> {

            int id = Integer.parseInt(ctx.pathParam("id"));
            Atleta atletaEncontrado = null;

            for (Atleta atleta : atletas) {
                if (atleta.getId() == id) {
                    atletaEncontrado = atleta;
                    break;
                }
            }

            if (atletaEncontrado != null) {
                ctx.json(atletaEncontrado);
            } else {
                ctx.status(404);
                ctx.result("Atleta não encontrado");
            }
        });

        // PROFESSORES

        app.get("/professores", ctx -> {
            ctx.json(professores);
        });

        // EQUIPES

        app.get("/equipes", ctx -> {
            ctx.json(equipes);
        });

        // GRADUAÇÕES

        app.get("/graduacoes", ctx -> {
            ctx.json(graduacoes);
        });

        // TREINOS

        app.get("/treinos", ctx -> {
            ctx.json(treinos);
        });

        System.out.println("VAI INICIAR O JAVALIN NA PORTA " + port);

        app.start(port);

        System.out.println("JAVALIN INICIADO");
    }
}