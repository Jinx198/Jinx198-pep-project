package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    //constructor
    public AccountService(AccountDAO dao)
    {
        this.accountDAO= dao;
    }

    //method for creating an account
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

    //method for logging in, checks if username and password is empty first.
    public Account login(Account account)
    {
        if(account.getUsername()== null|| account.getPassword()== null)
        {
            return null;
        }
        return accountDAO.getAccountByUserNameandPassword(account.getUsername(), account.getPassword());
    }

}
