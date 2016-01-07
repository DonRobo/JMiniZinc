package at.siemens.ct.jmz.elements;

import at.siemens.ct.jmz.expressions.integer.IntExpression;
import at.siemens.ct.jmz.expressions.integer.SumExpression;

public class IntVar extends Variable {

  private IntSet type;
  private IntExpression value;

  public IntVar(String name, IntSet type) {
    this(name, type, null);
  }

  public IntVar(String name, IntSet type, IntExpression value) {
    super(name);
    this.type = type;
    this.value = value;
  }

  @Override
  public String declare() {
    StringBuilder declaration = new StringBuilder();
    declaration.append(String.format("var %s: %s", type.getName(), name));

    if (value != null) {
      declaration.append(" = ");
      declaration.append(value);
    }

    declaration.append(";");
    return declaration.toString();
  }

  /**
   * Creates an integer variable named {@code name} and assigns the sum of {@code summands} to it.
   * 
   * @param name
   * @param summands
   * @return a reference to the created variable.
   */
  public static IntVar createSum(String name, IntArrayVar summands) {
    return new IntVar(name, IntSet.ALL_INTEGERS, new SumExpression(summands)); // TODO: tighter domain bounds?
  }

}
