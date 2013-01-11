/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package cruise.umple.compiler;
import cruise.umple.compiler.Position;

// line 97 "../../../../src/StateMachine.ump"
public class Guard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guard Attributes
  private Position position;
  private String condition;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetCondition;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guard(String aCondition)
  {
    cachedHashCode = -1;
    canSetCondition = true;
    condition = aCondition;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPosition(Position aPosition)
  {
    boolean wasSet = false;
    position = aPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setCondition(String aCondition)
  {
    boolean wasSet = false;
    if (!canSetCondition) { return false; }
    condition = aCondition;
    wasSet = true;
    return wasSet;
  }

  public Position getPosition()
  {
    return position;
  }

  public String getCondition()
  {
    return condition;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Guard compareTo = (Guard)obj;
  
    if (condition == null && compareTo.condition != null)
    {
      return false;
    }
    else if (condition != null && !condition.equals(compareTo.condition))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (condition != null)
    {
      cachedHashCode = cachedHashCode * 23 + condition.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetCondition = false;
    return cachedHashCode;
  }

  public void delete()
  {}

}