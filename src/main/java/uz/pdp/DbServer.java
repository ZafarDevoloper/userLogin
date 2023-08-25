package uz.pdp;

import uz.pdp.model.Results;

import java.sql.*;

public class DbServer {
    private String url="jdbc:postgresql://localhost:5432/postgres";
    private String dbUser="postgres";
    private String dbPassword="2002";
    public Results saveData(User user)
    {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement=connection.createStatement();
            String query="select count(*) from user1 where email='"+user.getEmail()+"' and password='"+user.getPassword()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            int i=0;
            while (resultSet.next())
            {
                i=resultSet.getInt("count");
            }
            if (i <=0)
            {
                return new Results("AMAL BAJARILDI",true);
            }
            return new Results("BUNDAY USER MAVJUD EMAS",false);
        } catch (ClassNotFoundException e) {
           return new Results("Server error",false);
        } catch (SQLException e) {
            return new Results("Server error",false);

        }
    }
}
