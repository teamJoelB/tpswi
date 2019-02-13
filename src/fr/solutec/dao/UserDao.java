/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.dao;

import fr.solutec.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joel B
 */
public class UserDao {
    
    public static User getByLoginPass(String login, String mdp)
    throws SQLException{
        User u = null;
        
        String sql = "SELECT * FROM personne WHERE mail=? AND mdp=?";
        
        Connection connexion = PhmDao.getConnection();
        PreparedStatement ordre = connexion.prepareStatement(sql);
        ordre.setString(1, login);
        ordre.setString(2, mdp);
        
        ResultSet rs = ordre.executeQuery();
        
        if(rs.next()){
            u = new User(rs.getInt("idpersonne"), 
            rs.getString("nom"), rs.getString("prenom"),
            rs.getString("mail"));
        }
        return u;
    }
    
    
    public static List<User> getAll() throws SQLException{
        List<User> users = new ArrayList<>();
        
        String sql = "SELECT * FROM Personne";
        
        Connection connexion = PhmDao.getConnection();
        
        Statement req = connexion.createStatement();
        
        ResultSet rs = req.executeQuery(sql);
        
        while(rs.next()){
            User u = new User(rs.getInt("idpersonne"), 
            rs.getString("nom"), rs.getString("prenom"),
            rs.getString("mail"));
            
            users.add(u);
        }
        
        return users;
    }
    
    public static void insert(User utilisateur) throws SQLException{
        String sql = "INSERT INTO personne "
                + "(nom, prenom, mail, mdp) VALUES (?, ?, ?, ?)";
        
        Connection connexion = PhmDao.getConnection();
        
        PreparedStatement ordre = connexion.prepareStatement(sql);
        ordre.setString(1, utilisateur.getNom());
        ordre.setString(1, utilisateur.getPrenom());
        ordre.setString(1, utilisateur.getMail());
        ordre.setString(1, utilisateur.getMdp());
        
        ordre.execute();
    }
    
}
