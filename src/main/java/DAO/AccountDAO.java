package DAO;
import Model.Account;
import Util.ConnectionUtil;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.sql.*;

public class AccountDAO {

    //inserting an new account in the database
    public Account insertAccount(Account account){
       
        try( Connection conn = ConnectionUtil.getConnection()){
            //sql insert statement for an account
            String sql="INSERT INTO Account( username, password) VALUE (?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            //PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //prepared statement for sql string
            //preparedStatement.setInt(1,account.getAccount_id());
            ps.setString(1,account.getUsername());
            ps.setString(2,account.getPassword());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1)
            {
                ResultSet rs= ps.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    return new Account(id, account.getUsername(), account.getPassword());
                }
            }
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    public Account getAccountByUserNameandPassword(String username, String password)
    {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    public Account getAccountByUserName(String username)
    {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//method to get an account by it's ID
public Account getAccountById(int id) {
    try (Connection conn = ConnectionUtil.getConnection()) {
        String sql = "SELECT * FROM Account WHERE account_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Account(
                rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}