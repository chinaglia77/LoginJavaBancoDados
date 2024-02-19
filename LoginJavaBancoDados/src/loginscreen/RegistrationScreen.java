package loginscreen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.UsuarioDao;
import modell.Usuario;

/**
 * RegistrationScreen
 */
public class RegistrationScreen  extends JFrame implements ActionListener{

    private final JLabel errorLabelCad = new JLabel();
    private final JButton buttonNovoCad = new JButton();
    private final JButton buttonPossuiCad = new JButton();
    private final JTextField inputEmailCad = new JTextField();
    private final JTextField inputUserCadastro = new JTextField();
    private final JTextField inputUserPass = new JTextField();
    private final JLabel labelCadastro = new JLabel();
    private final JLabel labelUserCadastro = new JLabel();
    private final JLabel labelPassCadastro = new JLabel();
    private final JLabel labelConfirmarPass = new JLabel();
    private final JFrame frameCad = new JFrame();

    public RegistrationScreen() {

        labelCadastro.setForeground(Color.WHITE);
        labelCadastro.setFont(new Font("Inter", Font.BOLD, 30));
        labelCadastro.setForeground(Color.WHITE);
        labelCadastro.setText("CADASTRO");
        labelCadastro.setBounds(107, 60, 170, 25);

        labelUserCadastro.setForeground(Color.WHITE);
        labelUserCadastro.setFont(new Font("Inter", Font.BOLD, 12));
        labelUserCadastro.setText("EMAIL");
        labelUserCadastro.setBounds(41, 110, 100, 25);

        labelPassCadastro.setForeground(Color.WHITE);
        labelPassCadastro.setFont(new Font("Inter", Font.BOLD, 12));
        labelPassCadastro.setText("USERNAME");
        labelPassCadastro.setBounds(41, 190, 100, 25);

        labelConfirmarPass.setForeground(Color.WHITE);
        labelConfirmarPass.setFont(new Font("Inter", Font.BOLD, 12));
        labelConfirmarPass.setText("SENHA");
        labelConfirmarPass.setBounds(41, 270, 100, 25);

        inputEmailCad.setFont(new Font("Inter", Font.PLAIN, 17));
        inputEmailCad.setForeground(Color.WHITE);
        inputEmailCad.setBackground(new Color(29, 29, 29));
        inputEmailCad.setBounds(39, 135, 300, 35);
        inputEmailCad.setCaretColor(Color.WHITE);
        inputEmailCad.setBorder(null);

        inputUserCadastro.setFont(new Font("Inter", Font.PLAIN, 17));
        inputUserCadastro.setForeground(Color.WHITE);
        inputUserCadastro.setBackground(new Color(29, 29, 29));
        inputUserCadastro.setBounds(39, 215, 300, 35);
        inputUserCadastro.setCaretColor(Color.WHITE);
        inputUserCadastro.setBorder(null);

        inputUserPass.setFont(new Font("Inter", Font.PLAIN, 17));
        inputUserPass.setForeground(Color.WHITE);
        inputUserPass.setBackground(new Color(29, 29, 29));
        inputUserPass.setBounds(39, 295, 300, 35);
        inputUserPass.setCaretColor(Color.WHITE);
        inputUserPass.setBorder(null);

        inputEmailCad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {//tecla desejada 
                    inputUserCadastro.requestFocusInWindow();//input que eu quero ir :)
                }
            }
        });

        
            inputUserCadastro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {//tecla desejada 
                    inputUserPass.requestFocusInWindow();//input que eu quero ir :)
                }
            }
        });
                
        buttonNovoCad.setText("LOGIN");
        buttonNovoCad.setFont(new Font("Inter", Font.BOLD, 15));
        buttonNovoCad.setBackground(Color.WHITE);
        buttonNovoCad.setForeground(Color.BLACK);
        buttonNovoCad.setCursor(new Cursor(HAND_CURSOR));
        buttonNovoCad.setBorderPainted(false);
        buttonNovoCad.setBounds(39, 355, 300, 35);

        buttonNovoCad.addActionListener(this);
        inputUserPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNovoCad.doClick();
            }
        });

        buttonPossuiCad.setText("JÁ TENHO LOGIN");
        buttonPossuiCad.setFont(new Font("Inter", Font.BOLD, 15));
        buttonPossuiCad.setBackground(Color.BLUE);
        buttonPossuiCad.setForeground(Color.WHITE);
        buttonPossuiCad.setCursor(new Cursor(HAND_CURSOR));
        buttonPossuiCad.setBorderPainted(false);
        buttonPossuiCad.setBounds(39, 411, 300, 35);

        buttonPossuiCad.addActionListener(this);

        frameCad.add(buttonPossuiCad);
        frameCad.add(buttonNovoCad);
        frameCad.add(labelConfirmarPass);
        frameCad.add(inputUserPass);
        frameCad.add(labelPassCadastro);
        frameCad.add(labelUserCadastro);
        frameCad.add(inputUserCadastro);
        frameCad.add(inputEmailCad);
        frameCad.add(labelCadastro);

        frameCad.setSize(400, 620);
        frameCad.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameCad.getContentPane().setBackground(new Color(22, 22, 22));
        frameCad.setLocationRelativeTo(null);//se localizar no centro da tela
        frameCad.setLayout(null);
        frameCad.setResizable(false);
        frameCad.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(buttonPossuiCad)) {
            frameCad.dispose();
        } else if (inputUserPass.getText().equals("") || inputUserCadastro.getText().equals("") || inputEmailCad.getText().equals("")) {

            inputUserPass.setBorder(BorderFactory.createLineBorder(Color.RED));
            inputUserCadastro.setBorder(BorderFactory.createLineBorder(Color.RED));
            inputEmailCad.setBorder(BorderFactory.createLineBorder(Color.RED));
            errorLabelCad.setText("Email invalido ou username e password não inseridos.");
            errorLabelCad.setFont(new Font("Inter", Font.PLAIN, 12));
            errorLabelCad.setForeground(Color.red);
            errorLabelCad.setBounds(39, 324, 310, 35);
            frameCad.add(errorLabelCad);
            frameCad.revalidate();
            frameCad.repaint();

        } else if (e.getSource().equals(buttonNovoCad)) {
            Usuario user = new Usuario();
            user.setUsername(inputUserCadastro.getText());
            user.setEmail(inputEmailCad.getText().toLowerCase());
            user.setPassword(inputUserPass.getText());

            UsuarioDao userDao = new UsuarioDao();
            userDao.inserirUser(user);
            
            frameCad.dispose();

        }

    }
}