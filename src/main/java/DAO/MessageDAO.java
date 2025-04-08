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
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));

                messages.add(message);
            }
            }catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
        
        return messages;
    }




}
