package com.nttdata.jdbc;
import java.sql.*;

public class NTTDataJDBCMain {
	
    public static void main( String[] args ) {
        stablishMDBConnection();
    }
    
    private static void stablishMDBConnection() {
    	
    	try {
    		
    		// Conexión y consulta con la base de datos.
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		final Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nttdata_javajdbc", "root", "rootroot");
    		final Statement sentence = dbConnection.createStatement();
    		final String query = "SELECT name, first_rol, second_rol "
					+ "FROM nttdata_mysql_soccer_player "
					+ "WHERE first_rol = 'MEP' || second_rol = 'MEP' "
					+ "ORDER BY name;";
			final ResultSet queryResult = sentence.executeQuery(query);
			
			// Se iteran los resultados de la consulta, en este caso la lista de jugadores que tienen la demarcación MEP.
			System.out.println("LISTA DE JUGADORES MEDIA PUNTA:");
			StringBuilder playerInfo = new StringBuilder();
			while (queryResult.next()) {
				playerInfo.append(" - ");
				playerInfo.append(queryResult.getString("name"));
				playerInfo.append(" [Demarcación principal: ");
				playerInfo.append(queryResult.getString("first_rol"));
				playerInfo.append("]");
				playerInfo.append("\n");
			}
			
			// Se muestran los resultados en consola.
			System.out.println(playerInfo.toString());
			
			// Se cierra la conexión con la base de datos.
			dbConnection.close();
			
    	} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }
}
