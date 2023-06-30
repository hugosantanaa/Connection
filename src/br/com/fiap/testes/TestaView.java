package br.com.fiap.testes;

import java.sql.SQLException;

import javax.swing.JFrame;

import br.com.fiap.view.UsuarioFrame;

public class TestaView {

	public static void main(String[] args) {
		UsuarioFrame frame = null;
		try {
			frame = new UsuarioFrame();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
