/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Notebook
 */
public class Funcionario {
    
    private String registro;
    private String nome;
    private String cargo;
    private double salario;
    
    public Funcionario(String registro, String nome, String cargo, double salario){
        
        this.registro = registro;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getRegistro() {
        return registro;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    
    
    
}
