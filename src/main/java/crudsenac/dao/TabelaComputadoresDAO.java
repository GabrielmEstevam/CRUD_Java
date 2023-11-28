/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudsenac.dao;

import crudsenac.modelo.Computador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ftfer
 */
public class TabelaComputadoresDAO {

    static String url
            = "jdbc:mysql://localhost:3306/lojainformatica";

    static String login = "root";
    static String senha = "";

    public static boolean salvar(Computador obj) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //Receita de bolo JDBC
            //Passo 1 - Identificar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2  - Abrir conexao com o banco
            conexao = DriverManager.getConnection(url,
                    login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("INSERT INTO tabelacomputadores (marca, HD, processador) "
                    + "                             VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            comandoSQL.setString(1, "Gabriel Estevam");
            comandoSQL.setString(2, obj.getHD());
            comandoSQL.setString(3, obj.getProcessador());

            //Passo 4 - Finalmente... Executamos o comando SQL
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                //Sucesso! - Consegui gravar no banco!
                rs = comandoSQL.getGeneratedKeys();
                if (rs != null) {
                    while (rs.next()) {
                        int idGerado = rs.getInt(1);
                        //Atribuo o HD gerado ao objeto
                        obj.setIdComputador(idGerado);
                    }
                }

                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    } // Fim do Salvar

    public static ArrayList<Computador> buscarPorNumero(String numeroBuscar) {

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;
        ArrayList<Computador> lista = new ArrayList<Computador>();

        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexão
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT * FROM tabelacomputadores WHERE HD = ?");
            comandoSQL.setString(1, numeroBuscar);

            //Passo 4 - Executar o comando SQL
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Ler linha por linha do ResultSet
                while (rs.next()) {
                    int id = rs.getInt("idcomputadores");
                    String marca = rs.getString("marca");
                    String HD = rs.getString("HD");
                    String processador = rs.getString("processador");

                    //Passei os dados do rs para um objeto
                    Computador item = new Computador(id, marca, HD, processador);

                    lista.add(item);

                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;

    }

    public static ArrayList<Computador> listar() {

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;
        ArrayList<Computador> lista = new ArrayList<Computador>();

        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexão
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT * FROM tabelacomputadores;");

            //Passo 4 - Executar o comando SQL
            rs = comandoSQL.executeQuery();

            if (rs != null) {
                // Ler linha por linha do ResultSet
                while (rs.next()) {
                    Computador nota = new Computador();
                    nota.setIdComputador(rs.getInt("idcomputadores"));
                    nota.setMarcaNome(rs.getString("marca"));
                    nota.setHD(rs.getString("HD"));
                    nota.setProcessador(rs.getString("processador"));

                    //Passei os dados do rs para um objeto
                    

                    lista.add(nota);

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;

    }// Fim do listar

    public static boolean alterar(Computador obj) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //Receita de bolo JDBC
            //Passo 1 - Identificar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2  - Abrir conexao com o banco
            conexao = DriverManager.getConnection(url,
                    login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement(
                    "UPDATE tabelacomputadores SET marca =?, HD=?, processador =? WHERE idcomputadores = ? ");

            comandoSQL.setString(1, obj.getMarcaNome());
            comandoSQL.setString(2, obj.getHD());
           
            comandoSQL.setString(3, obj.getProcessador());
             comandoSQL.setInt(4, obj.getIdComputador());

            //Passo 4 - Finalmente... Executamos o comando SQL
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {

                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    } // Fim do Alterar

    public static boolean excluir(int idExcluir) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //Receita de bolo JDBC
            //Passo 1 - Identificar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2  - Abrir conexao com o banco
            conexao = DriverManager.getConnection(url,
                    login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement(
                    "DELETE FROM tabelacomputadores WHERE idcomputadores = ?");

            comandoSQL.setInt(1, idExcluir);

            //Passo 4 - Finalmente... Executamos o comando SQL
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {

                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TabelaComputadoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

}
