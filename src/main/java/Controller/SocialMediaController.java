package Controller;

import Model.Message;
import Service.MessageService;
import DAO.MessageDAO;
import Model.Account;
import Service.AccountService;
import DAO.AccountDAO;
import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.nullable;

import java.util.List;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.get("/register",this::getAccountHandler);
        app.post("/register", this::registerHandler);
        //app.get("/messages", this::getMessageHandler);
        //app.post("/messages", this::postMessageHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    //registration handler
    // private void getAccountHandler(Context context){
        
    //    context.json("");
   // }
    
    private void registerHandler(Context context){
        Account account= context.bodyAsClass(Account.class);
        Account created = accountService.register(account);
        if(created != null){
            context.json(created);
        }
        else
        {
            context.status(400);
        }
    }
    
    //get message handler
    private void getMessageHandler(Context context) {
        //List <Message> messages = MessageService.getMessage();
        context.json("");
    }

    private void postMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class );
        context.json(mapper.writeValueAsString(message));
    }


}