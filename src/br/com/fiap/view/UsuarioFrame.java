package br.com.fiap.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioDAO;

public class UsuarioFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel labelNome, labelEmail, labelSenha;
	private JTextField textoNome, textoEmail, textoSenha;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar, botaoSair;
	private JTable tabela;
	private DefaultTableModel modelo;
	private UsuarioDAO dao = new UsuarioDAO();

	public UsuarioFrame() throws SQLException {
		Container container = getContentPane();
		setLayout(null);

		labelNome = new JLabel("Nome");
		labelEmail = new JLabel("Email");
		labelSenha = new JLabel("Senha");

		labelNome.setBounds(10, 10, 240, 15);
		labelEmail.setBounds(10, 50, 240, 15);
		labelSenha.setBounds(10, 100, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelEmail.setForeground(Color.BLACK);
		labelSenha.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelEmail);
		container.add(labelSenha);

		textoNome = new JTextField();
		textoEmail = new JTextField();
		textoSenha = new JTextField();

		textoNome.setBounds(10, 25, 265, 20);
		textoEmail.setBounds(10, 70, 265, 20);
		textoSenha.setBounds(10, 120, 265, 20);

		container.add(textoNome);
		container.add(textoEmail);
		container.add(textoSenha);

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 150, 80, 20);
		botaoLimpar.setBounds(100, 150, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Id do Usuario");
		modelo.addColumn("Nome do Usuario");
		modelo.addColumn("Email do Usuario");
		modelo.addColumn("Senha do Usuario");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");
		botaoSair = new JButton("Sair");

		botarApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);
		botaoSair.setBounds(690, 500, 80, 20);

		container.add(botarApagar);
		container.add(botaoEditar);
		container.add(botaoSair);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		botarApagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
		});
		
		botaoSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String email = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String senha = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			Usuario usuario = new Usuario(nome, email, senha);
			usuario.setId(id);
			this.dao.update(usuario);
			JOptionPane.showMessageDialog(this, "Item alterado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			this.dao.delete(id);
			modelo.removeRow(tabela.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item exclu�do com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void preencherTabela() {
		List<Usuario> usuarios = dao.selectAll();
		try {
			for (Usuario usuario : usuarios) {
				modelo.addRow(new Object[] { usuario.getId(), usuario.getNome(), usuario.getEmail() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvar() {
		if (!textoNome.getText().equals("") && !textoEmail.getText().equals("") && !textoSenha.getText().equals("")) {
			Usuario usuario = new Usuario(textoNome.getText(), textoEmail.getText(), textoSenha.getText());
			this.dao.insert(usuario);
			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Nome e Descri��o devem ser informados.");
		}
	}

	private void limpar() {
		this.textoNome.setText("");
		this.textoEmail.setText("");
		this.textoSenha.setText("");
	}
}
