package Service;
import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;


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
