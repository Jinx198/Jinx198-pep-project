package Service;
import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    //constructor
    public MessageService()
    {            
    messageDAO = new MessageDAO();
    }

    //constructor for when message DAO is provided
    public MessageService(MessageDAO messageDAO)
    {
        this.messageDAO=messageDAO;
    }

    //use the messageDAO to get the message
    public List <Message> getMessage()
    {
        return messageDAO.getMessage();
    }


}
