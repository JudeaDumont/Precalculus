package MainSystem.GlobalSystem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Owner on 7/10/2017.
 */
public class SystemGlobal {
    public static final int EQUALITY_PRECISION = Integer.parseInt(ResourceBundle.getBundle("MainSystem.GlobalSystem.SystemGlobal", Locale.getDefault())
            .getString("System.Precision"));
    public static final double PI = Double.parseDouble(ResourceBundle.getBundle("MainSystem.GlobalSystem.SystemGlobal", Locale.getDefault())
            .getString("System.PI"));
    public static final int DEFAULT_CIRCLE_PRECISION = Integer.parseInt(ResourceBundle.getBundle("MainSystem.GlobalSystem.SystemGlobal", Locale.getDefault())
            .getString("System.DefaultCirclePrecision"));
    public static final int CALC_PRECISION = Integer.parseInt(ResourceBundle.getBundle("MainSystem.GlobalSystem.SystemGlobal", Locale.getDefault())
            .getString("System.CalcPrecision"));
    public static final double GRAVITY_CONSTANT = Double.parseDouble(ResourceBundle.getBundle("MainSystem.GlobalSystem.SystemGlobal", Locale.getDefault())
            .getString("System.GravityConstant"));
    public static final double XAXISRESISTANCE_CONSTANT = Double.parseDouble(ResourceBundle.getBundle("MainSystem.GlobalSystem.SystemGlobal", Locale.getDefault())
            .getString("System.XAxisResistance"));

    public static BigDecimal zero = new BigDecimal(0);
    public static MathContext calcPrecisionMathContext = new MathContext(SystemGlobal.CALC_PRECISION);
    public static MathContext equalityPrecisionMathContext = new MathContext(SystemGlobal.EQUALITY_PRECISION);
}
