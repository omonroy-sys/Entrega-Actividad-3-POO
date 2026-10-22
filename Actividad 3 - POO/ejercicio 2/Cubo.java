
package Figuras;

public class Cubo extends FiguraGeometrica {
    private final double lado;

    public Cubo(double lado) {
        if (lado <= 0) {
            throw new IllegalArgumentException("El lado debe ser mayor que cero.");
        }

        this.lado = lado;
        setVolumen(calcularVolumen());
        setSuperficie(calcularSuperficie());
    }

    @Override
    public double calcularVolumen() {
        return Math.pow(lado, 3);
    }

    @Override
    public double calcularSuperficie() {
        return 6 * Math.pow(lado, 2);
    }
}