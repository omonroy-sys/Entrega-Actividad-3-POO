


package Figuras;

public class Cilindro extends FiguraGeometrica {
    private final double radio;
    private final double altura;

    public Cilindro(double radio, double altura) {
        if (radio <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que cero.");
        }

        this.radio = radio;
        this.altura = altura;
        setVolumen(calcularVolumen());
        setSuperficie(calcularSuperficie());
    }

    @Override
    public double calcularVolumen() {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    @Override
    public double calcularSuperficie() {
        return 2 * Math.PI * radio * (radio + altura);
    }
}
