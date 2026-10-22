
package Figuras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaFigura extends JFrame {

    private final String tipo;
    private final List<JTextField> campos = new ArrayList<>();
    private final JLabel resultadoVolumen = new JLabel("Volumen: --");
    private final JLabel resultadoSuperficie = new JLabel("Superficie: --");

    public VentanaFigura(String tipo) {
        this.tipo = tipo;

        setTitle("Cálculo de " + tipo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 470);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel(tipo, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        panelPrincipal.add(new PanelImagen(tipo), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout(8, 8));
        JPanel panelCampos = new JPanel(new GridLayout(0, 2, 8, 8));

        String[] nombres = obtenerNombresCampos();

        for (String nombre : nombres) {
            panelCampos.add(new JLabel(nombre + ":"));
            JTextField campo = new JTextField();
            campos.add(campo);
            panelCampos.add(campo);
        }

        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.addActionListener(e -> calcular());

        JPanel panelResultados = new JPanel(new GridLayout(2, 1, 5, 5));
        panelResultados.add(resultadoVolumen);
        panelResultados.add(resultadoSuperficie);

        JPanel panelAcciones = new JPanel(new BorderLayout(8, 8));
        panelAcciones.add(botonCalcular, BorderLayout.NORTH);
        panelAcciones.add(panelResultados, BorderLayout.CENTER);

        panelInferior.add(panelCampos, BorderLayout.NORTH);
        panelInferior.add(panelAcciones, BorderLayout.CENTER);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private String[] obtenerNombresCampos() {
        switch (tipo) {
            case "Cilindro":
            case "Esfera":
                return tipo.equals("Cilindro")
                        ? new String[]{"Radio (cm)", "Altura (cm)"}
                        : new String[]{"Radio (cm)"};

            case "Pirámide":
                return new String[]{
                    "Base (cm)", "Altura (cm)", "Apotema (cm)"
                };

            case "Cubo":
                return new String[]{"Lado (cm)"};

            case "Prisma":
                return new String[]{
                    "Largo (cm)", "Ancho (cm)", "Altura (cm)"
                };

            default:
                throw new IllegalArgumentException("Figura desconocida.");
        }
    }

    private void calcular() {
        try {
            double[] valores = new double[campos.size()];

            for (int i = 0; i < campos.size(); i++) {
                valores[i] = Double.parseDouble(
                        campos.get(i).getText().trim()
                );

                if (!Double.isFinite(valores[i]) || valores[i] <= 0) {
                    throw new IllegalArgumentException(
                            "Ingresa dimensiones positivas y válidas."
                    );
                }
            }

            FiguraGeometrica figura;

            switch (tipo) {
                case "Cilindro":
                    figura = new Cilindro(valores[0], valores[1]);
                    break;

                case "Esfera":
                    figura = new Esfera(valores[0]);
                    break;

                case "Pirámide":
                    figura = new Piramide(
                            valores[0], valores[1], valores[2]
                    );
                    break;

                case "Cubo":
                    figura = new Cubo(valores[0]);
                    break;

                case "Prisma":
                    figura = new Prisma(
                            valores[0], valores[1], valores[2]
                    );
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Figura desconocida."
                    );
            }

            resultadoVolumen.setText(String.format(
                    java.util.Locale.US, "Volumen: %.2f cm³",
                    figura.calcularVolumen()
            ));

            resultadoSuperficie.setText(String.format(
                    java.util.Locale.US, "Superficie: %.2f cm²",
                    figura.calcularSuperficie()
            ));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa valores numéricos en todos los campos.",
                    "Dato incorrecto",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Dato incorrecto",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static class PanelImagen extends JPanel {

        private final String tipo;

        public PanelImagen(String tipo) {
            this.tipo = tipo;
            setPreferredSize(new Dimension(250, 150));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(new Color(45, 105, 170));
            g2.setStroke(new java.awt.BasicStroke(2.0f));

            int x = getWidth() / 2 - 45;
            int y = getHeight() / 2 - 35;

            switch (tipo) {
                case "Cilindro":
                    g2.drawOval(x, y, 90, 25);
                    g2.drawLine(x, y + 12, x, y + 75);
                    g2.drawLine(x + 90, y + 12, x + 90, y + 75);
                    g2.drawArc(x, y + 62, 90, 25, 180, 180);
                    g2.drawLine(x, y + 75, x, y + 75);
                    g2.drawArc(x, y + 62, 90, 25, 0, 180);
                    break;

                case "Esfera":
                    g2.drawOval(x, y - 5, 90, 90);
                    g2.drawOval(x + 15, y - 5, 60, 90);
                    g2.drawArc(x, y + 15, 90, 45, 0, 180);
                    g2.drawArc(x, y + 15, 90, 45, 180, 180);
                    break;

                case "Pirámide":
                    g2.drawLine(x + 45, y, x, y + 70);
                    g2.drawLine(x + 45, y, x + 90, y + 70);
                    g2.drawLine(x, y + 70, x + 90, y + 70);
                    g2.drawLine(x + 45, y, x + 45, y + 90);
                    g2.drawLine(x, y + 70, x + 45, y + 90);
                    g2.drawLine(x + 90, y + 70, x + 45, y + 90);
                    break;

                case "Cubo":
                    g2.drawRect(x, y + 20, 60, 60);
                    g2.drawRect(x + 25, y, 60, 60);
                    g2.drawLine(x, y + 20, x + 25, y);
                    g2.drawLine(x + 60, y + 20, x + 85, y);
                    g2.drawLine(x + 60, y + 80, x + 85, y + 60);
                    g2.drawLine(x + 25, y + 60, x, y + 80);
                    break;

                case "Prisma":
                    g2.drawRect(x, y + 15, 70, 55);
                    g2.drawRect(x + 25, y, 70, 55);
                    g2.drawLine(x, y + 15, x + 25, y);
                    g2.drawLine(x + 70, y + 15, x + 95, y);
                    g2.drawLine(x + 70, y + 70, x + 95, y + 55);
                    break;
            }

            g2.dispose();
        }
    }
}
