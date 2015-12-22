package at.siemens.ct.jmz;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import at.siemens.ct.jmz.elements.Element;
import at.siemens.ct.jmz.elements.IntArrayConstant;
import at.siemens.ct.jmz.elements.IntConstant;
import at.siemens.ct.jmz.elements.IntSet;
import at.siemens.ct.jmz.elements.IntVar;

/**
 * A MiniZinc model builder.
 * 
 * @author z003ft4a (Richard Taupe)
 *
 */
public class ModelBuilder implements IModelBuilder {

  private List<Element> elements = new LinkedList<>();

  @Override
  public IntConstant createIntConstant(String name, int value) {
    IntConstant c = new IntConstant(name, value);
    addElement(c);
    return c;
  }

  @Override
  public IntArrayConstant createIntArrayConstant(String name, IntSet range, IntSet type,
      int[] values) {
    IntArrayConstant iac = new IntArrayConstant(name, range, type, values);
    addElement(iac);
    return iac;
  }

  @Override
  public IntSet createIntSet(String name, int lb, int ub) {
    return createIntSet(new IntSet(name, lb, ub));
  }

  @Override
  public IntSet createIntSet(String name, int lb, IntConstant ub) {
    return createIntSet(new IntSet(name, lb, ub));
  }

  @Override
  public IntSet createIntSet(String name, IntConstant lb, IntConstant ub) {
    return createIntSet(new IntSet(name, lb, ub));
  }

  private IntSet createIntSet(IntSet is) {
    addElement(is);
    return is;
  }

  @Override
  public IntVar createIntVar(String name, IntSet type) {
    IntVar v = new IntVar(name, type);
    addElement(v);
    return v;
  }

  private void addElement(Element element) {
    elements.add(element);
  }

  @Override
  public Stream<Element> elements() {
    return elements.stream();
  }

  @Override
  public void reset() {
    elements.clear();
  }

}