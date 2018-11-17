package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//
public class Conexao {
	
	private static Connection conn;
    private static final String USUARIO = "SYSTEM";
    private static final String SENHA = "123456";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DRIVER = "oracle.jdbc.OracleDriver";

    // Conectar ao banco
    public Connection abrir(){
        try {
        	Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Conectado!");
			return conn;
		}catch(SQLException ex) {
			System.out.println("ERRO: "+ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return conn;
    }
    
    public Connection fechar(){
    	try {
        	conn.close();
		}catch(SQLException ex) {
			System.out.println("ERRO: "+ex);
		}
        return conn;
    }

}