/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.Funcionario;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
        

public class FuncionarioDAO {
    
    Connection conn;
    Statement st;
    
    
    public boolean conectar(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdFunc", "root", "");
            st = conn.createStatement();
            return true;
        }
        catch (ClassNotFoundException | SQLException ex){
            return false;
        }
       
    }
    
    public boolean salvar(Funcionario funcionarioX){
        String inserir;
        
        inserir = "INSERT INTO tbfuncionario VALUES ('" + funcionarioX.getRegistro() + "', '" + funcionarioX.getNome() + "', '" + funcionarioX.getCargo() + "', '" + funcionarioX.getSalario() + "')";


        try{
            st.executeUpdate(inserir);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void update(Funcionario func){
        String update = "UPDATE tbfuncionario SET nome = ?, cargo = ?, salario = ? WHERE registro = ?";
        
        
        boolean conectar;
        conectar = conectar();
        
        if (conectar == true){
            try{
                PreparedStatement stmt = this.conn.prepareStatement(update);
                stmt.setString(1, func.getNome());
                stmt.setString(2, func.getCargo());
                stmt.setDouble(3, func.getSalario());
                stmt.setString(4, func.getRegistro());
                stmt.execute();
            } catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar dados ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro na conexao com o banco de dados ");
        }
    }
        
    public Funcionario pesquisar(String registro){
        String pesquisar = "SELECT * FROM tbfuncionario WHERE registro = ?";
        
        Funcionario funcionario = null;
        
        boolean conectar = conectar();
        
        
        if (conectar == true) {
            try (PreparedStatement pstmt = conn.prepareStatement(pesquisar)) {
            pstmt.setString(1, registro);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String registroFunc = rs.getString("registro");
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");

                funcionario = new Funcionario(registroFunc, nome, cargo, salario);
            }

            rs.close();
            } catch (SQLException ex) {
            return null;
            }    
        } else if (conectar == false) {
            JOptionPane.showMessageDialog(null, "Erro na conexao com o banco de dados ");
        }
        return funcionario;
    }
        
    public void excluir(String registro){
        String excluir = "DELETE FROM tbfuncionario WHERE registro = ?";
        
        boolean conectar = conectar();
        
        if (conectar == true){
            try{
                PreparedStatement stmt = this.conn.prepareStatement(excluir);
                stmt.setString(1, registro);
                stmt.execute();
                
                JOptionPane.showMessageDialog(null, "Registro excluido com sucesso");
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario: " + ex.getMessage());
            }
        } else if (conectar == false) {
            JOptionPane.showMessageDialog(null, "Erro na conexao com o banco de dados ");
        }
    }
    
    public void desconectar(){
        
        try{
            conn.close();
        }
        catch (SQLException ex){
            
        }
        
    }
    
}
