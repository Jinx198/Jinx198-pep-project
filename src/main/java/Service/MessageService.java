package Service;

import DAO.MessageDAO;
import Model.Message;
import DAO.AccountDAO;
import Model.Account;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    //constructor
    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO)
    {            
    this.messageDAO= messageDAO;
    this.accountDAO=accountDAO;
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

    //method to create a new message
    public Message createMessage(Message message)
    {
        if(message.getMessage_text()== null|| message.getMessage_text().isBlank() || message.getMessage_text().length()>255) {
            return null;
        } 
        Account account = accountDAO.getAccountById(message.getPosted_by());
        if(account == null){
            return null;
        }
        return messageDAO.insertMessage(message);
    }

    //method to get all messages
    public List<Message> getAllMessages()
    {
        return messageDAO.getAllMessages();
    }

    //method to get a message by it's id
    public Message getMessageById(int messageId){
        return messageDAO.getMessageById(messageId);
        
    }

    public Message deleteMessageById(int messageId)
    {
        return messageDAO.deleteMessageById(messageId);
    }

    public Message updateMessage(int messageId, String newText)
    {
        if(newText == null || newText.isBlank()|| newText.length()>255){
            return null;
        }
        return messageDAO.updateMessageText(messageId, newText);
    }

    public List<Message> getMessagesByAccountId(int accountid)
    {
        return messageDAO.getMessagesByAccountId(accountid);
    }

}
