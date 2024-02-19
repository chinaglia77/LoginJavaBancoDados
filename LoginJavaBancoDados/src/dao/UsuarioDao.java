package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modell.Usuario;

/**
 * UsuarioDao
 */
public class UsuarioDao {

    public void inserirUser(Usuario user) {

        String sql = "INSERT INTO NEW_TABLE(USERNAME, EMAIL, PASSWORD) VALUE(?, ?, ?)";

        PreparedStatement ps;

        try {

            ps = ConexaoDao.getConexaoDao().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Usuario buscarUser(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConexaoDao.getConexaoDao();
            ps = conn.prepareStatement(
                    "SELECT ID, USERNAME, EMAIL, PASSWORD FROM NEW_TABLE WHERE USERNAME = ? AND PASSWORD = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            boolean usuarioEncontrado = false;

            rs = ps.executeQuery();

            while (rs.next()) {
                String nomeLogin = rs.getString(2);
                String nomeSenha = rs.getString(4);

                if (nomeLogin.equals(username) && nomeSenha.equals(password)) {
                    usuarioEncontrado = true;
                } else {
                    usuarioEncontrado = false;
                    break;
                }
            }

            if (usuarioEncontrado) {
                JOptionPane.showMessageDialog(null, "ACESSADO COM SUCESSO");
            } else {
                JOptionPane.showMessageDialog(null, "Login ou Senha invalidos", "INVALIDO", JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
