import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cronometro {

    JLabel contagem;

    JButton btIniciar = new JButton("Go!");
    JButton btParar = new JButton("<< Stop >>");

    Timer timer;
    int contador = 0;
    boolean executando = false;

    void init() {
        JPanel painel = new JPanel();
        JFrame janela = new JFrame("Cronometro");
        JFrame.setDefaultLookAndFeelDecorated(true);

        contagem = new JLabel("00:00:00");

        janela.setSize(200, 500);
        janela.setAlwaysOnTop(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());
        // janela.setFont(new Font(contagem.getName(), Font.PLAIN, 00));


        btIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!executando) {
                    timer = new Timer();
                    executando = true;
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            contador++;
                            int segundo = contador % 60;
                            int minuto = (contador / 60) % 60;
                            int hora = minuto / 60;
                            contagem.setText(String.format("%02d:%02d:%02d", hora, minuto, segundo));
                        }
                    }, 1000, 1000);
                }
            }
        });
        btParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador = 0;
                timer.cancel();
                executando = false;
                contagem.setText("00:00:00");

            }
        });

        painel.setLayout(new GridLayout(2, 1));

        janela.add(painel);
       // janela.pack();
        janela.setVisible(true);

        janela.add(contagem, BorderLayout.CENTER);
        painel.add(btIniciar, BorderLayout.EAST);
        painel.add(btParar, BorderLayout.SOUTH);



    }

    public static void main(String[] args) {
        Cronometro crono = new Cronometro();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                crono.init();
            }
        });
        crono.init();
    }

}
