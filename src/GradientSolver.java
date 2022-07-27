package calculator;

import static java.lang.Math.pow;

public class GradientSolver {
    public static int findGradient(String equation, int x) throws NumberFormatException {
        String eq = equation.replaceAll("\\s", "");        
        String[] tokens = eq.split(String.format("((?<=%1$s)|(?=%1$s))", "[\\+-]"));
        
        int sign = 1;
        String[] subTokens;
        String buffer = "";
        boolean isCoEff, isPower;
        char chr;
        
        double[] coEff = new double[10];
        double[] powers = new double[10];
        int k = 0;
        
        for (String token : tokens) {            
            if (token.equals("+"))
                sign = 1;
            else if (token.equals("-"))
                sign = -1;
            else if (!token.contains("x")) {
                coEff[k] = sign * Double.parseDouble(token);
                
                powers[k] = 1.0;
                ++k;
            }
            else {
              for (int i = 0; i < token.length(); ++i) {
                  chr = token.charAt(i);
                  
                  if (chr == 'x') {
                      if (buffer.equals(""))
                          coEff[k] = sign * 1.0;
                      else
                        coEff[k] = sign * Double.parseDouble(buffer);
                      buffer = "";
                  } else if (chr == '^')
                      continue;
                    else 
                        buffer += chr;
              }
              if (buffer.equals(""))
                  powers[k] = 1.0;
              else
                powers[k] = Double.parseDouble(buffer);
              buffer = "";
              ++k;
            }
        }
        
        int slope = 0;
        
        for (int i = 0; i < k; ++i) {
            if (powers[i] == 0)
                continue;
            
            slope += coEff[i] * powers[i] * pow(x, powers[i] - 1);
        }
        return slope;
    }
}
