

package Figuras;

public class FiguraGeometrica {

    private double volumen;
    private double superficie;

    public double getVolumen() {
        return volumen;
    }

    public double getSuperficie() {
        return superficie;
    }

    protected void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    protected void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double calcularVolumen() {
        return volumen;
    }

    public double calcularSuperficie() {
        return superficie;
    }
}
