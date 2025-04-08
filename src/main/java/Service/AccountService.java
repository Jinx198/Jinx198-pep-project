package Service;
import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;

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
        return null;
    }

    public Account login(Account account)
    {
        return null;
    }

}
