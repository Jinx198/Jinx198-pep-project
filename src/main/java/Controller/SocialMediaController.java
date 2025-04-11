package Controller;

import Model.Message;
import Service.MessageService;
import DAO.MessageDAO;
import Model.Account;
import Service.AccountService;
import DAO.AccountDAO;
import io.javalin.Javalin;
import io.javalin.http.Context;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController() {
        this.accountService = new AccountService(new AccountDAO());
        this.messageService = new MessageService(new MessageDAO(), new AccountDAO());
        
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    
    private void registerHandler(Context ctx){
        Account account= ctx.bodyAsClass(Account.class);
        Account created = accountService.register(account);
        if(created != null){
            ctx.json(created);
        }
        else
        {
            ctx.status(400);
        }
    }

    //log in using account
    private void loginHandler(Context context) {
        Account loginRequest = context.bodyAsClass(Account.class);
        Account loggedIn = accountService.login(loginRequest);
        if (loggedIn != null) {
            context.json(loggedIn);
        } else {
            context.status(401);
        }
    }

    //creates a message 
    private void createMessageHandler(Context ctx) {
        Message message = ctx.bodyAsClass(Message.class);
        Message created = messageService.createMessage(message);
        if (created != null) {
            ctx.json(created);
        } else {
            ctx.status(400);
        }
    }

    //gets all messages 
    private void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }

    //gets messaged based on it's id
    private void getMessageByIdHandler(Context ctx) {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.result(""); 
        }
    }

    //deletes a message
    private void deleteMessageHandler(Context ctx) {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleted = messageService.deleteMessageById(messageId);
        if (deleted != null) {
            ctx.json(deleted);
        } else {
            ctx.result(""); 
        }
    }

    //updates a message 
    private void updateMessageHandler(Context ctx) {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message newMessage = ctx.bodyAsClass(Message.class);

        if(newMessage.getMessage_text()==null){
            ctx.status(400);
            return;
        }
        Message updated = messageService.updateMessage(messageId, newMessage.getMessage_text());
        if (updated != null) {
            ctx.json(updated);
        } else {
            ctx.status(400);
        }
    }

    //gets message based on the user's id 
    private void getMessagesByUserHandler(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(messageService.getMessagesByAccountId(accountId));
    }

}