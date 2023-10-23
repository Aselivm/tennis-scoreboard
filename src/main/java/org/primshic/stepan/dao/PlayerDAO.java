package org.primshic.stepan.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDAO extends BaseDAO {
    public PlayerDAO() {
        init();
    }

    public static void init(){
        try(Connection connection = connectionBuilder.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table Player(id int)");
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("INSERT INTO PLAYER values (1)");
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement2.executeQuery("select * from Player");
            resultSet.next();
            System.out.println(resultSet.getString("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        init();
    }

}
