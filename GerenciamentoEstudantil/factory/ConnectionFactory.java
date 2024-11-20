package factory;
/*
Essa classe é responsável por criar a conexão com o banco de dados
 */

import java.sql.Connection;  //Importa a interface Connection
import java.sql.DriverManager; // Importa a classe para ler o driver do banco

public class ConnectionFactory {
    //Essa é a variável que representa o nome do administrador do banco de dados
    private static final String USERNAME = "root";  //Geralmente o padrão é o root, caso seja outro mudar depois

    //Variável da password
    private static final String PASSWORD = "";  // Deixei o campo password como vazio, mas caso houver password, alterar

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gerenciamentoestudantil";  // Coloquei a porta 3306, porque geralmente é a padrão, mudar caso usar uma diferente

    // Método responsável por fazer a conexão com o BD
    public static Connection createConnectionToMySql() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");  // Carrega o driver do banco

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);  // Cria a conexão com o BD
        return connection;
    }

    public static void main(String[] args) throws Exception{
        //Recupera uma conexão com o banco de dados, caso houver

        Connection con = createConnectionToMySql();

        //Testa se a conexão é null
        if (con != null) {
            System.out.println("Conexão sucedida!!");
            con.close();
        }
    }
}
