import io.javalin.Javalin;
import model.Atleta;
import model.Professor;
import model.Equipe;
import model.Graduacao;
import model.Treino;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static Connection conn;

    public static void main(String[] args) throws Exception {

        System.out.println("INICIOU O MAIN");

        // Monta a URL JDBC corretamente a partir da DATABASE_URL do Render
        String dbUrl = System.getenv("DATABASE_URL");
        // DATABASE_URL vem no formato: postgresql://user:pass@host/db
        // JDBC precisa de:             jdbc:postgresql://host:5432/db?user=user&password=pass
        String jdbcUrl = toJdbcUrl(dbUrl);
        conn = DriverManager.getConnection(jdbcUrl);
        System.out.println("CONECTADO AO BANCO!");

        criarTabelas();

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
            List<Atleta> lista = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM atletas");
            while (rs.next()) {
                lista.add(new Atleta(rs.getInt("id"), rs.getString("nome"),
                        rs.getInt("idade"), rs.getString("faixa"), rs.getDouble("peso")));
            }
            ctx.json(lista);
        });

        app.get("/atletas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM atletas WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ctx.json(new Atleta(rs.getInt("id"), rs.getString("nome"),
                        rs.getInt("idade"), rs.getString("faixa"), rs.getDouble("peso")));
            } else {
                ctx.status(404).result("Atleta não encontrado");
            }
        });

        app.post("/atletas", ctx -> {
            Atleta a = ctx.bodyAsClass(Atleta.class);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO atletas (nome, idade, faixa, peso) VALUES (?, ?, ?, ?) RETURNING id");
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getIdade());
            ps.setString(3, a.getFaixa());
            ps.setDouble(4, a.getPeso());
            ResultSet rs = ps.executeQuery();
            rs.next();
            a.setId(rs.getInt("id"));
            ctx.status(201).json(a);
        });

        app.put("/atletas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Atleta a = ctx.bodyAsClass(Atleta.class);
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE atletas SET nome=?, idade=?, faixa=?, peso=? WHERE id=?");
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getIdade());
            ps.setString(3, a.getFaixa());
            ps.setDouble(4, a.getPeso());
            ps.setInt(5, id);
            int rows = ps.executeUpdate();
            if (rows > 0) { a.setId(id); ctx.json(a); }
            else ctx.status(404).result("Atleta não encontrado");
        });

        app.delete("/atletas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("DELETE FROM atletas WHERE id=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) ctx.status(204);
            else ctx.status(404).result("Atleta não encontrado");
        });

        // -------------------------
        // PROFESSORES
        // -------------------------

        app.get("/professores", ctx -> {
            List<Professor> lista = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM professores");
            while (rs.next()) {
                lista.add(new Professor(rs.getLong("id"), rs.getString("nome"), rs.getString("graduacao")));
            }
            ctx.json(lista);
        });

        app.get("/professores/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM professores WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ctx.json(new Professor(rs.getLong("id"), rs.getString("nome"), rs.getString("graduacao")));
            } else ctx.status(404).result("Professor não encontrado");
        });

        app.post("/professores", ctx -> {
            Professor p = ctx.bodyAsClass(Professor.class);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO professores (nome, graduacao) VALUES (?, ?) RETURNING id");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getGraduacao());
            ResultSet rs = ps.executeQuery();
            rs.next();
            p.setId(rs.getLong("id"));
            ctx.status(201).json(p);
        });

        app.put("/professores/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Professor p = ctx.bodyAsClass(Professor.class);
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE professores SET nome=?, graduacao=? WHERE id=?");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getGraduacao());
            ps.setLong(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) { p.setId(id); ctx.json(p); }
            else ctx.status(404).result("Professor não encontrado");
        });

        app.delete("/professores/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("DELETE FROM professores WHERE id=?");
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) ctx.status(204);
            else ctx.status(404).result("Professor não encontrado");
        });

        // -------------------------
        // EQUIPES
        // -------------------------

        app.get("/equipes", ctx -> {
            List<Equipe> lista = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM equipes");
            while (rs.next()) {
                lista.add(new Equipe(rs.getLong("id"), rs.getString("nome"), rs.getString("cidade")));
            }
            ctx.json(lista);
        });

        app.get("/equipes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM equipes WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ctx.json(new Equipe(rs.getLong("id"), rs.getString("nome"), rs.getString("cidade")));
            } else ctx.status(404).result("Equipe não encontrada");
        });

        app.post("/equipes", ctx -> {
            Equipe e = ctx.bodyAsClass(Equipe.class);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO equipes (nome, cidade) VALUES (?, ?) RETURNING id");
            ps.setString(1, e.getNome());
            ps.setString(2, e.getCidade());
            ResultSet rs = ps.executeQuery();
            rs.next();
            e.setId(rs.getLong("id"));
            ctx.status(201).json(e);
        });

        app.put("/equipes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Equipe e = ctx.bodyAsClass(Equipe.class);
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE equipes SET nome=?, cidade=? WHERE id=?");
            ps.setString(1, e.getNome());
            ps.setString(2, e.getCidade());
            ps.setLong(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) { e.setId(id); ctx.json(e); }
            else ctx.status(404).result("Equipe não encontrada");
        });

        app.delete("/equipes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("DELETE FROM equipes WHERE id=?");
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) ctx.status(204);
            else ctx.status(404).result("Equipe não encontrada");
        });

        // -------------------------
        // GRADUAÇÕES
        // -------------------------

        app.get("/graduacoes", ctx -> {
            List<Graduacao> lista = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM graduacoes");
            while (rs.next()) {
                lista.add(new Graduacao(rs.getLong("id"), rs.getString("faixa_anterior"),
                        rs.getString("faixa_nova"), rs.getString("data_graduacao")));
            }
            ctx.json(lista);
        });

        app.get("/graduacoes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM graduacoes WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ctx.json(new Graduacao(rs.getLong("id"), rs.getString("faixa_anterior"),
                        rs.getString("faixa_nova"), rs.getString("data_graduacao")));
            } else ctx.status(404).result("Graduação não encontrada");
        });

        app.post("/graduacoes", ctx -> {
            Graduacao g = ctx.bodyAsClass(Graduacao.class);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO graduacoes (faixa_anterior, faixa_nova, data_graduacao) VALUES (?, ?, ?) RETURNING id");
            ps.setString(1, g.getFaixaAnterior());
            ps.setString(2, g.getFaixaNova());
            ps.setString(3, g.getDataGraduacao());
            ResultSet rs = ps.executeQuery();
            rs.next();
            g.setId(rs.getLong("id"));
            ctx.status(201).json(g);
        });

        app.put("/graduacoes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Graduacao g = ctx.bodyAsClass(Graduacao.class);
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE graduacoes SET faixa_anterior=?, faixa_nova=?, data_graduacao=? WHERE id=?");
            ps.setString(1, g.getFaixaAnterior());
            ps.setString(2, g.getFaixaNova());
            ps.setString(3, g.getDataGraduacao());
            ps.setLong(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) { g.setId(id); ctx.json(g); }
            else ctx.status(404).result("Graduação não encontrada");
        });

        app.delete("/graduacoes/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("DELETE FROM graduacoes WHERE id=?");
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) ctx.status(204);
            else ctx.status(404).result("Graduação não encontrada");
        });

        // -------------------------
        // TREINOS
        // -------------------------

        app.get("/treinos", ctx -> {
            List<Treino> lista = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM treinos");
            while (rs.next()) {
                lista.add(new Treino(rs.getLong("id"), rs.getString("data"), rs.getString("descricao")));
            }
            ctx.json(lista);
        });

        app.get("/treinos/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM treinos WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ctx.json(new Treino(rs.getLong("id"), rs.getString("data"), rs.getString("descricao")));
            } else ctx.status(404).result("Treino não encontrado");
        });

        app.post("/treinos", ctx -> {
            Treino t = ctx.bodyAsClass(Treino.class);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO treinos (data, descricao) VALUES (?, ?) RETURNING id");
            ps.setString(1, t.getData());
            ps.setString(2, t.getDescricao());
            ResultSet rs = ps.executeQuery();
            rs.next();
            t.setId(rs.getLong("id"));
            ctx.status(201).json(t);
        });

        app.put("/treinos/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            Treino t = ctx.bodyAsClass(Treino.class);
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE treinos SET data=?, descricao=? WHERE id=?");
            ps.setString(1, t.getData());
            ps.setString(2, t.getDescricao());
            ps.setLong(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) { t.setId(id); ctx.json(t); }
            else ctx.status(404).result("Treino não encontrado");
        });

        app.delete("/treinos/{id}", ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PreparedStatement ps = conn.prepareStatement("DELETE FROM treinos WHERE id=?");
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) ctx.status(204);
            else ctx.status(404).result("Treino não encontrado");
        });

        System.out.println("VAI INICIAR O JAVALIN NA PORTA " + port);
        app.start(port);
        System.out.println("JAVALIN INICIADO");
    }

    // Converte URL do Render para formato JDBC
    // De: postgresql://user:pass@host/db
    // Para: jdbc:postgresql://host:5432/db?user=user&password=pass
    static String toJdbcUrl(String url) {
        // Remove o prefixo "postgresql://"
        String sem = url.replace("postgresql://", "");
        // Separa credenciais do resto: user:pass@host/db
        String[] partes = sem.split("@");
        String[] credenciais = partes[0].split(":");
        String usuario = credenciais[0];
        String senha = credenciais[1];
        // host/db
        String hostDb = partes[1];
        return "jdbc:postgresql://" + hostDb + "?user=" + usuario + "&password=" + senha + "&sslmode=require";
    }

    static void criarTabelas() throws SQLException {
        Statement st = conn.createStatement();
        st.execute("""
            CREATE TABLE IF NOT EXISTS atletas (
                id    SERIAL PRIMARY KEY,
                nome  VARCHAR(100) NOT NULL,
                idade INT NOT NULL,
                faixa VARCHAR(50) NOT NULL,
                peso  DOUBLE PRECISION NOT NULL
            )
        """);
        st.execute("""
            CREATE TABLE IF NOT EXISTS professores (
                id        SERIAL PRIMARY KEY,
                nome      VARCHAR(100) NOT NULL,
                graduacao VARCHAR(50) NOT NULL
            )
        """);
        st.execute("""
            CREATE TABLE IF NOT EXISTS equipes (
                id     SERIAL PRIMARY KEY,
                nome   VARCHAR(100) NOT NULL,
                cidade VARCHAR(100) NOT NULL
            )
        """);
        st.execute("""
            CREATE TABLE IF NOT EXISTS graduacoes (
                id              SERIAL PRIMARY KEY,
                faixa_anterior  VARCHAR(50) NOT NULL,
                faixa_nova      VARCHAR(50) NOT NULL,
                data_graduacao  VARCHAR(20) NOT NULL
            )
        """);
        st.execute("""
            CREATE TABLE IF NOT EXISTS treinos (
                id        SERIAL PRIMARY KEY,
                data      VARCHAR(20) NOT NULL,
                descricao TEXT NOT NULL
            )
        """);
        System.out.println("TABELAS CRIADAS/VERIFICADAS!");
    }
}