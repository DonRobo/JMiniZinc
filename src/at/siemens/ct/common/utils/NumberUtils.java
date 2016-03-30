package at.siemens.ct.common.utils;

/**
 * Provides utility funcions for {@link Number}s.
 * 
 * @author z003ft4a (Richard Taupe)
 *
 */
public class NumberUtils {

  private static final double EPSILON = 0.00000001;

  /**
   * Checks if the difference between two {@link Double}s is very small.
   * 
   * @param d1
   * @param d2
   * @return {@code true} iff the absolute difference between {@code d1} and {@code d2} is less than {@link #EPSILON}.
   */
  public static boolean equal(double d1, double d2) {
    return Math.abs(d1 - d2) < EPSILON;
  }

  /**
   * Provides utility funcions for optional {@link Number}s.
   * 
   * @author z003ft4a (Richard Taupe)
   *
   */
  public static class Opt {

    /**
     * Returns the sum of the two summands, if at least one of them has a value. If one is {@code null}, it is replaced
     * by {@code 0}. If both are {@code null}, {@code null} is returned.
     * 
     * @param n1
     *          the first summand
     * @param n2
     *          the second summand
     * @return the sum of the two summands, as described above.
     */
    public static Integer plus(Integer n1, Integer n2) {
      if (n1 != null || n2 != null)
        return defaultIfNull(n1, 0) + defaultIfNull(n2, 0);
      return null;
    }

    /**
     * @see #plus(Integer, Integer)
     */
    public static Long plus(Long n1, Long n2) {
      if (n1 != null || n2 != null)
        return defaultIfNull(n1, 0L) + defaultIfNull(n2, 0L);
      return null;
    }

    /**
     * @see #plus(Integer, Integer)
     */
    public static Float plus(Float n1, Float n2) {
      if (n1 != null || n2 != null)
        return defaultIfNull(n1, 0f) + defaultIfNull(n2, 0f);
      return null;
    }

    /**
     * @see #plus(Integer, Integer)
     */
    public static Double plus(Double n1, Double n2) {
      if (n1 != null || n2 != null)
        return defaultIfNull(n1, 0.0) + defaultIfNull(n2, 0.0);
      return null;
    }

    /**
     * Returns the result of {@code n1 -n2}, if {@code n1} has a value. If {@code n2} is {@code null}, it is replaced by
     * {@code 0}. If {@code n1} is {@code null}, {@code null} is returned.
     * 
     * @param n1
     *          the minuend
     * @param n2
     *          the subtrahend
     * @return the difference of the two operands, as described above.
     */
    public static Integer minus(Integer n1, Integer n2) {
      if (n1 != null)
        return n1 - defaultIfNull(n2, 0);
      return null;
    }

    /**
     * @see #minus(Integer, Integer)
     */
    public static Long minus(Long n1, Long n2) {
      if (n1 != null)
        return n1 - defaultIfNull(n2, 0L);
      return null;
    }

    /**
     * @see #minus(Integer, Integer)
     */
    public static Float minus(Float n1, Float n2) {
      if (n1 != null)
        return n1 - defaultIfNull(n2, 0f);
      return null;
    }

    /**
     * @see #minus(Integer, Integer)
     */
    public static Double minus(Double n1, Double n2) {
      if (n1 != null)
        return n1 - defaultIfNull(n2, 0.0);
      return null;
    }

    private static <N extends Number> N defaultIfNull(N number, N defaultValue) {
      return number != null ? number : defaultValue;
    }

  }

}
