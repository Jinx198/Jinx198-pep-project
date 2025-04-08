package DAO;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class MessageDAO {

    public List <Message> getMessage(){
        Connection connection = ConnectionUtil.getConnection();
        List <Message> messages= new ArrayList<>();
        try{
            String sql= "SELECT * FROM Message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()){
            Message message = new Message(rs.getMesssage_id("message_id"))

            //messages.addMessage();
         }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        }
        return messages;
    }

}
