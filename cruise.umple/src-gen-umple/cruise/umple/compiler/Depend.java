/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.16.0.2388 modeling language!*/

package cruise.umple.compiler;

/**
 * @umplesource Umple.ump 406
 * A depend relationship indicates a package on which this class depends
 * @umplesource Umple_Code.ump 404
 */
// line 409 "../../../../src/Umple.ump"
// line 406 "../../../../src/Umple_Code.ump"
public class Depend
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Depend Attributes
  private String name;
  private boolean isInternal;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Depend(String aName)
  {
    cachedHashCode = -1;
    canSetName = true;
    name = aName;
    isInternal = false;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    if (!canSetName) { return false; }
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsInternal(boolean aIsInternal)
  {
    boolean wasSet = false;
    isInternal = aIsInternal;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getIsInternal()
  {
    return isInternal;
  }

  public boolean isIsInternal()
  {
    return isInternal;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Depend compareTo = (Depend)obj;
  
    if (name == null && compareTo.name != null)
    {
      return false;
    }
    else if (name != null && !name.equals(compareTo.name))
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
    if (name != null)
    {
      cachedHashCode = cachedHashCode * 23 + name.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetName = false;
    return cachedHashCode;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
	  
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "isInternal" + ":" + getIsInternal()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 408 ../../../../src/Umple_Code.ump
  public String getPackageName()
  {
    if (name == null || "".equals(name))
    {
      return "";
    }
    int dotIndex = name.lastIndexOf(".");
    return dotIndex >= 0 ? name.substring(0,dotIndex) : name;
  }
}