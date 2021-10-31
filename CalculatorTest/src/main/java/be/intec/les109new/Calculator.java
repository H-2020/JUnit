package be.intec.les109new;

public class Calculator {

    public double add(double n1, double n2) {
        return (n1 + n2);
    }

    public double min(double n1, double n2) {
        return (n1 - n2);
    }

    public double div(double n1, double n2) {
        if (n2 != 0) {
            return (n1 / n2);
        } else {
            return 0;
        }
    }

    public double mul(double n1, double n2) {
        return (n1 * n2 );
    }

}
