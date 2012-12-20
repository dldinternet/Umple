/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

package cruise.statemachine.test;

// line 230 "../../../../src/TestHarnessStateMachine.ump"
public class CourseP
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseP State Machines
  enum One { On, Amber }
  private One one;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseP()
  {
    setOne(One.On);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getOneFullName()
  {
    String answer = one.toString();
    return answer;
  }

  public One getOne()
  {
    return one;
  }

  public boolean stay()
  {
    boolean wasEventProcessed = false;
    
    One aOne = one;
    switch (aOne)
    {
      case On:
        setOne(One.On);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean flip()
  {
    boolean wasEventProcessed = false;
    
    One aOne = one;
    switch (aOne)
    {
      case On:
        setOne(One.Amber);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private void setOne(One aOne)
  {
    one = aOne;
  }

  public void delete()
  {}

}