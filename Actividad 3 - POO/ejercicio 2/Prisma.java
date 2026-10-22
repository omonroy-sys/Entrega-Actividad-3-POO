
package Figuras;

public class Prisma extends FiguraGeometrica {
    private final double largo;
    private final double ancho;
    private final double altura;

    public Prisma(double largo, double ancho, double altura) {
        if (largo <= 0 || ancho <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que cero.");
        }

        this.largo = largo;
        this.ancho = ancho;
        this.altura = altura;

        setVolumen(calcularVolumen());
        setSuperficie(calcularSuperficie());
    }

    @Override
    public double calcularVolumen() {
        return largo * ancho * altura;
    }

    @Override
    public double calcularSuperficie() {
        return 2 * (largo * ancho + largo * altura + ancho * altura);
    }
}
