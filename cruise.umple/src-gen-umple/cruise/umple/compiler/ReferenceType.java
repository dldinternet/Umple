/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package cruise.umple.compiler;

/**
 * NOT used right now
 * @umplesource Umple.ump 211
 */
// line 211 "../../../../src/Umple.ump"
public class ReferenceType extends Type
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReferenceType Associations
  private UmpleClass umpleClass;
  private UmpleInterface umpleInterface;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReferenceType()
  {
    super();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public UmpleClass getUmpleClass()
  {
    return umpleClass;
  }

  public UmpleInterface getUmpleInterface()
  {
    return umpleInterface;
  }

  public boolean setUmpleClass(UmpleClass aNewUmpleClass)
  {
    boolean wasSet = false;
    umpleClass = aNewUmpleClass;
    wasSet = true;
    return wasSet;
  }

  public boolean setUmpleInterface(UmpleInterface aNewUmpleInterface)
  {
    boolean wasSet = false;
    umpleInterface = aNewUmpleInterface;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    umpleClass = null;
    umpleInterface = null;
    super.delete();
  }

}