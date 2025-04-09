package Service;
import static org.mockito.ArgumentMatchers.nullable;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    //constructor
    public AccountService()
    {            
    accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO dao)
    {
        this.accountDAO= dao;
    }

    public Account register(Account account)
    {
        //if the username is empty then it returns null
        if(account.getUsername()==null || account.getUsername().isBlank())
        {
            return null;
        }
        
        //if password is empty and larger than 4 characters it returns null
        if(account.getPassword()==null || account.getPassword().length()<4)
        {
            return null;
        }

        Account existing = accountDAO.getAccountByUserName(account.getUsername());
        //returns null if username already exists
        if(existing != null)
        {
            return null;
        }
        
        return accountDAO.insertAccount(account);
    }

    public Account login(Account account)
    {
        if(account.getUsername()== null|| account.getPassword()== null);
        {
            return null;
        }
        return accountDAO.getAccountByUserNameandPassword(account.getUsername(), account.getPassword());
    }

}
