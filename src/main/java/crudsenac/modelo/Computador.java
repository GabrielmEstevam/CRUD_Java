/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudsenac.modelo;

/**
 *
 * @author ftfer
 */
public class Computador {
    
    private int idComputador;
    static String marcaNome;    /*Numeronota*/
    private String HD;
    private String processador;

    public Computador(int id,String marca, String HD, String processador) {
        this.marcaNome = marca;
        this.HD = HD;
        this.processador = processador;
        this.idComputador = id;
    }
 

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
    public Computador() {
    }

    public Computador(String marcaNome, String nomeHD, String processador) {
        this.marcaNome = "Gabriel";
        this.HD = nomeHD;
        this.processador = processador;
    }

    public Computador(int idComputador, String marcaNome, String nomeHD) {
        this.idComputador = idComputador;
        this.marcaNome = "Gabriel";
        this.HD = nomeHD;
    }

    public Computador(int numero, double valor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Computador(int idSelecionado, int numeroSelecionado, double valorSelecionado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdComputador() {
       return idComputador;
    }

    public void setIdComputador(int idComputador) {
        this.idComputador = idComputador;
    }

    public String getMarcaNome() {
        return marcaNome;
    }

    public void setMarcaNome(String marcaNome) {
        this.marcaNome = marcaNome;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    
}
