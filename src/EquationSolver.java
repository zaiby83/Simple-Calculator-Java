package calculator;

public class EquationSolver {
    
    public static double[] equationSolver(double x1, double x2, double y1, double y2, double c1, double c2) {
        double detA = (x1*y2) - (x2*y1);
        if (detA == 0)
            throw new IllegalArgumentException("Trivial Equations");
        double detAx = (c1*y2) - (c2*y1);
        double detAy = (x1*c2) - (x2*c1);

        return new double[] {detAx / detA, detAy / detA};
    }
    
}
