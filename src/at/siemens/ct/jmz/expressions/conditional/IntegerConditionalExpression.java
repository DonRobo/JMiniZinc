package at.siemens.ct.jmz.expressions.conditional;

import at.siemens.ct.jmz.expressions.bool.BooleanExpression;
import at.siemens.ct.jmz.expressions.integer.IntExpression;

public class IntegerConditionalExpression extends ConditionalExpression<IntExpression>
    implements IntExpression {

  public IntegerConditionalExpression(BooleanExpression condition, IntExpression thenBranch,
      IntExpression elseBranch) {
    super(condition, thenBranch, elseBranch);
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

}