package br.com.fiap.testes;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioDAO;

public class TestaUsuarioDAO {

	public static void main(String[] args) throws SQLException {

		UsuarioDAO dao = new UsuarioDAO();
		Usuario emerson = new Usuario("Emerson", "emerson@gmail.com", "123456");
		Usuario adriane = new Usuario("Adriane", "adriane@gmail.com", "654321");
		
		dao.insert(emerson);
		dao.insert(adriane);
		

		List<Usuario> usuarios = dao.selectAll();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getId());
			System.out.println(usuario.getNome());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getData());
		}
	
		Usuario usuario = dao.selectById(1);

			System.out.println(usuario.getId());
			System.out.println(usuario.getNome());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getData());
	}
}