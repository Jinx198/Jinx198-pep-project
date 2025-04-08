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

    public Message createMessage(Message message)
    {
        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages()
    {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId){
        return messageDAO.getMessageByID();
        
    }

    public Message deleteMessageById(int messageId)
    {
        return null;
    }

    public Message updateMessageText(int messageId, Message newMessage)
    {
        return null;
    }

    public Message getMessagesByAccountId(int accountid)
    {
        return null;
    }

}
