/**
 * Copyright Siemens AG, 2016-2017
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package at.siemens.ct.jmz.expressions.set;

import at.siemens.ct.jmz.elements.Set;
import at.siemens.ct.jmz.expressions.integer.IntegerConstant;
import at.siemens.ct.jmz.expressions.integer.IntegerExpression;

/**
 * Represents a MiniZinc integer set type with an implicit null value that is an element of this set. In contrast to
 * {@link OptionalIntSet}, which uses the support for option types built in to MiniZinc (but not well supported yet by
 * MiniZinc itself), we use an integer to represent nulls here. This element can be obtained by
 * {@link #getNullElement()}.
 *
 * @author Copyright Siemens AG, 2016-2017
 */
public class PseudoOptionalIntSet implements IntegerSetExpression {

  private SetExpression<Integer> innerSet;
	private IntegerExpression nullElement;

	public PseudoOptionalIntSet(RangeExpression originalSet) {
    this.nullElement = originalSet.getLb().add(-1);
    this.innerSet = new RangeExpression(nullElement, originalSet.getUb());
	}

  public PseudoOptionalIntSet(Set<Integer> intSet) {
    IntegerConstant nullElement = determineNullElement(intSet);
    this.nullElement = nullElement;
    this.innerSet = new Union(intSet, SetLiteral.singleton(nullElement));
	}

  private PseudoOptionalIntSet(SetExpression<Integer> innerSet, IntegerExpression nullElement) {
    this.innerSet = innerSet;
    this.nullElement = nullElement;
  }

  private IntegerConstant determineNullElement(Set<Integer> intSet) {
		// TODO: determine least element of intSet in a more intelligent way (set must be constant anyway)
		int nullElement = 0;
    while (intSet.contains(nullElement)) {
      nullElement--;
    }
		return new IntegerConstant(nullElement);
	}

	/**
	 * @return the element of this set that represents null values.
	 */
  public IntegerExpression getNullElement() {
		return nullElement;
  }

	@Override
	public Boolean contains(Integer value) {
		return innerSet.contains(value);
	}

	@Override
	public String use() {
    return innerSet.use();
	}

  @Override
  public String toString() {
    return use();
  }
  
  @Override
  public PseudoOptionalIntSet substitute(String name, Object value) {
    return new PseudoOptionalIntSet(innerSet.substitute(name, value), nullElement.substitute(name, value));
  }

}
