package loginscreen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import dao.UsuarioDao;
import modell.Usuario;

public class loginScreen extends JFrame implements ActionListener{
    private final JLabel erroMensagemLogin = new JLabel();
    private final JCheckBox lembrarSenha = new JCheckBox();
    private final JButton buttonLogin = new JButton();
    private final JButton buttonCadastro = new JButton();
    private final JTextField inputLogin = new JTextField();
    private final JPasswordField inputPassword = new JPasswordField();
    private final JLabel orLabel = new JLabel();
    private final JLabel labelTelaLogin = new JLabel();
    private final JLabel labelUsername = new JLabel();
    private final JLabel labelPassword = new JLabel();
    private final JLabel labelLembrarSenha = new JLabel();
    private final JFrame frame = new JFrame();

    public loginScreen() {

        labelTelaLogin.setForeground(Color.WHITE);
        labelTelaLogin.setFont(new Font("Inter", Font.BOLD, 30));
        labelTelaLogin.setText("LOGIN");
        labelTelaLogin.setBounds(143, 60, 100, 25);

        labelUsername.setForeground(Color.WHITE);
        labelUsername.setFont(new Font("Inter", Font.BOLD, 12));
        labelUsername.setText("USERNAME");
        labelUsername.setBounds(41, 110, 100, 25);

        labelPassword.setForeground(Color.WHITE);
        labelPassword.setFont(new Font("Inter", Font.BOLD, 12));
        labelPassword.setText("PASSWORD");
        labelPassword.setBounds(41, 190, 100, 25);

        labelLembrarSenha.setText("ESQUECEU A SENHA?");
        labelLembrarSenha.setBounds(220, 320, 300, 35);
        labelLembrarSenha.setFont(new Font("Inter", Font.BOLD, 10));
        labelLembrarSenha.setForeground(Color.WHITE);
        labelLembrarSenha.setCursor(new Cursor(HAND_CURSOR));

        orLabel.setText("OU");
        orLabel.setFont(new Font("Inter", Font.BOLD, 10));
        orLabel.setForeground(Color.WHITE);
        orLabel.setBounds(180, 360, 120, 35);

        lembrarSenha.setText("LEMBRAR DE MIM");
        lembrarSenha.setBounds(41, 320, 120, 35);
        lembrarSenha.setFont(new Font("Inter", Font.BOLD, 10));
        lembrarSenha.setBackground(new Color(22, 22, 22));
        lembrarSenha.setForeground(Color.WHITE);

        inputLogin.setFont(new Font("Inter", Font.PLAIN, 17));
        inputLogin.setForeground(Color.WHITE);
        inputLogin.setBackground(new Color(29, 29, 29));
        inputLogin.setBounds(39, 135, 300, 35);
        inputLogin.setCaretColor(Color.WHITE);
        inputLogin.setBorder(null);

        inputPassword.setFont(new Font("Inter", Font.PLAIN, 17));
        inputPassword.setForeground(Color.WHITE);
        inputPassword.setBackground(new Color(29, 29, 29));
        inputPassword.setBounds(39, 215, 300, 35);
        inputPassword.setCaretColor(Color.WHITE);
        inputPassword.setBorder(null);

             inputLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {//tecla desejada 
                    inputPassword.requestFocusInWindow();//input que eu quero ir :)
                }
            }
        });
        
        inputPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            buttonLogin.doClick();
            }
        });
                {
    }
        
        buttonLogin.setText("LOGIN");
        buttonLogin.setFont(new Font("Inter", Font.BOLD, 15));
        buttonLogin.setBackground(Color.WHITE);
        buttonLogin.setForeground(Color.BLACK);
        buttonLogin.setCursor(new Cursor(HAND_CURSOR));
        buttonLogin.setBorderPainted(false);
        buttonLogin.setBounds(39, 273, 300, 35);

        buttonLogin.addActionListener(this);

        buttonCadastro.setText("CRIAR CONTA");
        buttonCadastro.setFont(new Font("Inter", Font.BOLD, 15));
        buttonCadastro.setBackground(Color.BLUE);
        buttonCadastro.setForeground(Color.WHITE);
        buttonCadastro.setCursor(new Cursor(HAND_CURSOR));
        buttonCadastro.setBorderPainted(false);
        buttonCadastro.setBounds(39, 410, 300, 35);

        buttonCadastro.addActionListener(this);

        frame.add(orLabel);
        frame.add(labelLembrarSenha);
        frame.add(lembrarSenha);
        frame.add(buttonCadastro);
        frame.add(buttonLogin);
        frame.add(labelPassword);
        frame.add(inputPassword);
        frame.add(labelUsername);
        frame.add(labelTelaLogin);
        frame.add(inputLogin);

        frame.setSize(400, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(22, 22, 22));
        frame.setLocationRelativeTo(null);//se localizar no centro da tela
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource().equals(buttonCadastro)) {
            MostrartelaCadastro();

        } else if (e.getSource().equals(buttonLogin)) {
            verificarLogin();

        }

    }
    
    private void MostrartelaCadastro() {
        new RegistrationScreen();
    }

    private void verificarLogin() {

        Usuario user = new Usuario();
        user.setUsername(inputLogin.getText());
        user.setPassword(inputPassword.getText());

        UsuarioDao uDao = new UsuarioDao();

        if (inputLogin.getText().equals("") || inputPassword.getText().equals("")) {
            inputLogin.setBorder(BorderFactory.createLineBorder(Color.red));
            inputPassword.setBorder(BorderFactory.createLineBorder(Color.red));
            erroMensagemLogin.setText("Senha ou Username incorretos, tente novamente.");
            erroMensagemLogin.setFont(new Font("Inter", Font.PLAIN, 12));
            erroMensagemLogin.setForeground(Color.red);
            erroMensagemLogin.setBounds(39, 244, 300, 35);
            frame.add(erroMensagemLogin);
            frame.revalidate();
            frame.repaint();
        } else {
            try {
                uDao.buscarUser(user.getUsername(), user.getPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
