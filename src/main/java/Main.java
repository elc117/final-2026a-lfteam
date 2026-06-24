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

        // dados iniciais
        atletas.add(new Atleta(1, "Luis Felipe", 22, "Branca", 75.0));
        atletas.add(new Atleta(2, "João Silva",  25, "Azul",   82.5));

        professores.add(new Professor(1L, "Carlos Mendes", "Faixa Preta"));
        professores.add(new Professor(2L, "Marcos Silva",  "Faixa Marrom"));

        equipes.add(new Equipe(1L, "Equipe Samurai",    "Santa Maria"));
        equipes.add(new Equipe(2L, "Equipe Guerreiros", "Ijuí"));

        graduacoes.add(new Graduacao(1L, "Branca", "Azul", "2026-06-14"));
        graduacoes.add(new Graduacao(2L, "Azul",   "Roxa", "2026-05-10"));

        treinos.add(new Treino(1L, "2026-06-14", "Treino de raspagens e finalizações"));
        treinos.add(new Treino(2L, "2026-06-12", "Treino de passagem de guarda"));

        int port = System.getenv("PORT") != null
                ? Integer.parseInt(System.getenv("PORT"))
                : 7070;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> rule.anyHost());
            });
        });

        app.get("/", ctx -> ctx.result("Sistema de Gerenciamento de Equipes de Jiu-Jitsu"));

        // -------------------------
        // ATLETAS
        // -------------------------

        app.get("/atletas", ctx -> {
            ctx.json(atletas);
        });

        app.get("/atletas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            atletas.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                    ctx::json,
                    () -> ctx.status(404).result("Atleta não encontrado")
                );
        });

        app.post("/atletas", ctx -> {
            Atleta atleta = ctx.bodyAsClass(Atleta.class);
            atleta.setId(atletas.size() + 1);
            atletas.add(atleta);
            ctx.status(201).json(atleta);
        });

        app.put("/atletas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Atleta dados = ctx.bodyAsClass(Atleta.class);
            atletas.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .ifPresentOrElse(a -> {
                    a.setNome(dados.getNome());
                    a.setIdade(dados.getIdade());
                    a.setFaixa(dados.getFaixa());
                    a.setPeso(dados.getPeso());
                    ctx.json(a);
                }, () -> ctx.status(404).result("Atleta não encontrado"));
        });

        app.delete("/atletas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean removido = atletas.removeIf(a -> a.getId() == id);
            if (removido) ctx.status(204);
            else ctx.status(404).result("Atleta não encontrado");
        });

        // -------------------------
        // PROFESSORES
        // -------------------------

        app.get("/professores", ctx -> {
            ctx.json(professores);
        });

        app.get("/professores/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            professores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                    ctx::json,
                    () -> ctx.status(404).result("Professor não encontrado")
                );
        });

        app.post("/professores", ctx -> {
            Professor professor = ctx.bodyAsClass(Professor.class);
            professor.setId((long) (professores.size() + 1));
            professores.add(professor);
            ctx.status(201).json(professor);
        });

        app.put("/professores/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Professor dados = ctx.bodyAsClass(Professor.class);
            professores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(p -> {
                    p.setNome(dados.getNome());
                    p.setGraduacao(dados.getGraduacao());
                    ctx.json(p);
                }, () -> ctx.status(404).result("Professor não encontrado"));
        });

        app.delete("/professores/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            boolean removido = professores.removeIf(p -> p.getId().equals(id));
            if (removido) ctx.status(204);
            else ctx.status(404).result("Professor não encontrado");
        });

        // -------------------------
        // EQUIPES
        // -------------------------

        app.get("/equipes", ctx -> {
            ctx.json(equipes);
        });

        app.get("/equipes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            equipes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                    ctx::json,
                    () -> ctx.status(404).result("Equipe não encontrada")
                );
        });

        app.post("/equipes", ctx -> {
            Equipe equipe = ctx.bodyAsClass(Equipe.class);
            equipe.setId((long) (equipes.size() + 1));
            equipes.add(equipe);
            ctx.status(201).json(equipe);
        });

        app.put("/equipes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Equipe dados = ctx.bodyAsClass(Equipe.class);
            equipes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(e -> {
                    e.setNome(dados.getNome());
                    e.setCidade(dados.getCidade());
                    ctx.json(e);
                }, () -> ctx.status(404).result("Equipe não encontrada"));
        });

        app.delete("/equipes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            boolean removido = equipes.removeIf(e -> e.getId().equals(id));
            if (removido) ctx.status(204);
            else ctx.status(404).result("Equipe não encontrada");
        });

        // -------------------------
        // GRADUAÇÕES
        // -------------------------

        app.get("/graduacoes", ctx -> {
            ctx.json(graduacoes);
        });

        app.get("/graduacoes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            graduacoes.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                    ctx::json,
                    () -> ctx.status(404).result("Graduação não encontrada")
                );
        });

        app.post("/graduacoes", ctx -> {
            Graduacao graduacao = ctx.bodyAsClass(Graduacao.class);
            graduacao.setId((long) (graduacoes.size() + 1));
            graduacoes.add(graduacao);
            ctx.status(201).json(graduacao);
        });

        app.put("/graduacoes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Graduacao dados = ctx.bodyAsClass(Graduacao.class);
            graduacoes.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(g -> {
                    g.setFaixaAnterior(dados.getFaixaAnterior());
                    g.setFaixaNova(dados.getFaixaNova());
                    g.setDataGraduacao(dados.getDataGraduacao());
                    ctx.json(g);
                }, () -> ctx.status(404).result("Graduação não encontrada"));
        });

        app.delete("/graduacoes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            boolean removido = graduacoes.removeIf(g -> g.getId().equals(id));
            if (removido) ctx.status(204);
            else ctx.status(404).result("Graduação não encontrada");
        });

        // -------------------------
        // TREINOS
        // -------------------------

        app.get("/treinos", ctx -> {
            ctx.json(treinos);
        });

        app.get("/treinos/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            treinos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                    ctx::json,
                    () -> ctx.status(404).result("Treino não encontrado")
                );
        });

        app.post("/treinos", ctx -> {
            Treino treino = ctx.bodyAsClass(Treino.class);
            treino.setId((long) (treinos.size() + 1));
            treinos.add(treino);
            ctx.status(201).json(treino);
        });

        app.put("/treinos/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Treino dados = ctx.bodyAsClass(Treino.class);
            treinos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(t -> {
                    t.setData(dados.getData());
                    t.setDescricao(dados.getDescricao());
                    ctx.json(t);
                }, () -> ctx.status(404).result("Treino não encontrado"));
        });

        app.delete("/treinos/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            boolean removido = treinos.removeIf(t -> t.getId().equals(id));
            if (removido) ctx.status(204);
            else ctx.status(404).result("Treino não encontrado");
        });

        System.out.println("VAI INICIAR O JAVALIN NA PORTA " + port);
        app.start(port);
        System.out.println("JAVALIN INICIADO");
    }
}