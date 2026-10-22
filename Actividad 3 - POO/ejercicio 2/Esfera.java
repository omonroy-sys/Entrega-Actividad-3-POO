
package Figuras;

public class Esfera extends FiguraGeometrica {
    private final double radio;

    public Esfera(double radio) {
        if (radio <= 0) {
            throw new IllegalArgumentException("El radio debe ser mayor que cero.");
        }

        this.radio = radio;
        setVolumen(calcularVolumen());
        setSuperficie(calcularSuperficie());
    }

    @Override
    public double calcularVolumen() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
    }

    @Override
    public double calcularSuperficie() {
        return 4 * Math.PI * Math.pow(radio, 2);
    }
}