package calculator;

import static java.lang.Math.acos;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
public class TriangleCalculator {
    public static double[] trangleValues(double s1, double s2, double s3, int precision) throws IllegalArgumentException {
        double precisionFactor = Math.pow(10, precision);

        double s1s = round(s1*s1 * precisionFactor) / precisionFactor;
        double s2s = round(s2*s2 * precisionFactor) / precisionFactor;
        double s3s = round(s3*s3 * precisionFactor) / precisionFactor;
        
        double area = 0, a1 = 0, a2 = 0, a3 = 0;

        double hypotenuse = max(max(s1, s2), s3);

        boolean isRightTriangle = s1s == s2s + s3s || s2s == s1s + s3s || s3s == s1s + s2s;

        if (isRightTriangle) {
            double finalA1 = 0, finalA2 = 0, finalA3 = 0;
            if (hypotenuse == s1) {
                area = 0.5 * s2*s3;

                finalA1 = 90.0;

                a2 = toDegrees(Math.atan(s2/s3));
                a3 = toDegrees(Math.atan(s3/s2));

                if (s2 > s3) {
                    finalA2 = max(a3, a2);
                    finalA3 = min(a3, a2);
                } else {
                    finalA2 = min(a3, a2);
                    finalA3 = max(a3, a2);
                }
            }
            else if (hypotenuse == s2) {
                area = 0.5 * s1*s3;

                finalA2 = 90.0;

                a1 = toDegrees(Math.atan(s1/s3));
                a3 = toDegrees(Math.atan(s3/s1));

                if (s1 > s3) {
                    finalA1 = max(a1, a3);
                    finalA3 = min(a1, a3);
                } else {
                    finalA1 = min(a1, a3);
                    finalA3 = max(a1, a3);
                }

            }
            else if (hypotenuse == s3) {
                area = 0.5 * s1*s2;

                finalA3 = 90.0;

                a1 = toDegrees(Math.atan(s2/s1));
                a2 = toDegrees(Math.atan(s1/s2));

                if (s1 > s2) {
                    finalA1 = max(a1, a2);
                    finalA2 = min(a1, a2);
                } else {
                    finalA1 = min(a1, a2);
                    finalA2 = max(a1, a2);
                }
            }
            
            area = round(area * precisionFactor) / precisionFactor;
            finalA1 = round(finalA1 * precisionFactor) / precisionFactor;
            finalA2 = round(finalA2 * precisionFactor) / precisionFactor;
            finalA3 = round(finalA3 * precisionFactor) / precisionFactor;

            return new double[] {area, finalA1, finalA2, finalA3};

        } else if (((s1 + s2) > s3) && ((s2 + s3) > s1) && ((s1 + s3) > s2)) {
            a1 = toDegrees(acos((s2*s2 + s3*s3 - s1*s1) / (2*s2*s3)));
            a2 = toDegrees(acos((s1*s1 + s3*s3 - s2*s2) / (2*s1*s3)));
            a3 = toDegrees(acos((s1*s1 + s2*s2 - s3*s3) / (2*s1*s2)));
            
            double s = (s1 + s2 + s3) / 2;
            
            area = sqrt(s * (s - s1) * (s - s2) * (s - s3));
            
            area = round(area * precisionFactor) / precisionFactor;
            a1 = round(a1 * precisionFactor) / precisionFactor;
            a2 = round(a2 * precisionFactor) / precisionFactor;
            a3 = round(a3 * precisionFactor) / precisionFactor;
            
            return new double[] {area, a1, a2, a3};   
        }
        throw new IllegalArgumentException("The dimensions do not form a triangle");
    }
}

