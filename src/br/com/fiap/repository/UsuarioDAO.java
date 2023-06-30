package br.com.fiap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

public class UsuarioDAO {

	private Connection conexao;

	public UsuarioDAO() throws SQLException {
		this.conexao = ConnectionFactory.conectar();
	}

	public void insert(Usuario usuario) {
		String sql = "insert into usuarios(nome, email, senha, data) values (?,?,?,?)"; //como não sei os valores do banco, informamos por meio de interrogações (???)
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql); //o statement é a query do sql 
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setDate(4, usuario.getData());
			stmt.execute();
			stmt.close();//sempre fechar a operação para liberar o recurso
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//SelectAll
	public List<Usuario> selectAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuarios order by nome"; //query
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); //ResultSet devolve uma tabela com dados do banco

			while (rs.next()) { //enquanto tiver um proximo registro dentro da tabela do banco de dados o while executa
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setData(rs.getDate("data"));

				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario selectById(int id) { //metodo que faz busca pelo ID
		Usuario usuario = null;
		String sql = "select * from usuarios where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setData(rs.getDate("data"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public void delete(int id) { //deleta pelo ID
		String sql = "delete from usuarios where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();//sempre fechar a operação para liberar o recurso
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Usuario usuario) {
		String sql = "update usuarios set nome=?, email=?, senha=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setLong(4, usuario.getId());

			stmt.execute();
			stmt.close(); //sempre fechar a operação para liberar o recurso
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
