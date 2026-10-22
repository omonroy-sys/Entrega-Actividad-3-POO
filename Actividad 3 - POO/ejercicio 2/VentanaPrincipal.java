

package Figuras;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Calculadora de Figuras Geométricas");
        setSize(430, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel(
                "Figuras Geométricas",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 23));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 5, 10, 5));

        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(0, 2, 12, 12));
        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(10, 25, 20, 25)
        );

        String[] figuras = {
            "Cilindro", "Esfera", "Pirámide", "Cubo", "Prisma"
        };

        for (String figura : figuras) {
            JButton boton = new JButton(figura);
            boton.setFont(new Font("Arial", Font.PLAIN, 15));

            boton.addActionListener(e -> {
                VentanaFigura ventana = new VentanaFigura(figura);
                ventana.setVisible(true);
            });

            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);
    }
}
