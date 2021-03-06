/**
 * Copyright Siemens AG, 2016
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package at.siemens.ct.jmz.elements;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.siemens.ct.common.utils.ListUtils;
import at.siemens.ct.jmz.expressions.array.IntegerArray;
import at.siemens.ct.jmz.expressions.set.RangeExpression;

/**
 * Tests {@link Array#parseValue(String)
 *
 * @author Copyright Siemens AG, 2016
 */
public class TestParsers {

	@Test
	public void testParseCorrectIntArray1d() {
		RangeExpression range = new RangeExpression(1, 3);
		RangeExpression type = new RangeExpression(1, 3);
		IntegerArray var = IntegerArray.createVariable("a", range, type);
		Integer[] parsedValue = var.parseValue("array1d(1..3, [1, 2, 3])");
		Assert.assertEquals("Unexpected array length", 3, parsedValue.length);
		Assert.assertArrayEquals("Unexpected value", new Integer[] { 1, 2, 3 }, parsedValue);
	}

	@Test
	public void testParseCorrectIntArray2d() {
		List<RangeExpression> range = ListUtils.fromElements(new RangeExpression(1, 3), new RangeExpression(4, 6));
		RangeExpression type = new RangeExpression(1, 9);
		IntegerArray var = IntegerArray.createVariable("a", range, type);
		Integer[] parsedValue = var.parseValue("array2d(1..3, 4..6, [1, 2, 3, 4, 5, 6, 7, 8, 9])");
		Assert.assertEquals("Unexpected array length", 9, parsedValue.length);
		Assert.assertArrayEquals("Unexpected value", new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				parsedValue);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseIntArrayWrongDimensions() {
		List<RangeExpression> range = ListUtils.fromElements(new RangeExpression(1, 3), new RangeExpression(4, 6));
		RangeExpression type = new RangeExpression(1, 3);
		IntegerArray var = IntegerArray.createVariable("a", range, type);
		var.parseValue("array1d(1..3, [1, 2, 3])");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseIntArrayElementNotInDomain() {
		RangeExpression range = new RangeExpression(1, 3);
		RangeExpression type = new RangeExpression(1, 3);
		IntegerArray var = IntegerArray.createVariable("a", range, type);
		var.parseValue("array1d(1..3, [2, 3, 4])");
	}

}
