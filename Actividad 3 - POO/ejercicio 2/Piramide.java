
package Figuras;


public class Piramide extends FiguraGeometrica {
    private final double base;
    private final double altura;
    private final double apotema;

    public Piramide(double base, double altura, double apotema) {
        if (base <= 0 || altura <= 0 || apotema <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que cero.");
        }

        this.base = base;
        this.altura = altura;
        this.apotema = apotema;

        setVolumen(calcularVolumen());
        setSuperficie(calcularSuperficie());
    }

    @Override
    public double calcularVolumen() {
        return Math.pow(base, 2) * altura / 3.0;
    }

    @Override
    public double calcularSuperficie() {
        return Math.pow(base, 2) + 2 * base * apotema;
    }
}
