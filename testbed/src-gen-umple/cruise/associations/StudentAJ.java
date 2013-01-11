/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.16.0.2388 modeling language!*/

package cruise.associations;

// line 540 "../../../src/TestHarnessAssociations.ump"
public class StudentAJ
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentAJ Attributes
  private int number;

  //StudentAJ Associations
  private ProgramAJ program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentAJ(int aNumber)
  {
    number = aNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public ProgramAJ getProgram()
  {
    return program;
  }

  public boolean setProgram(ProgramAJ newProgram)
  {
    boolean wasSet = false;
    if (newProgram == null)
    {
      ProgramAJ existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getStudent() != null)
      {
        existingProgram.setStudent(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAJ currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(newProgram))
    {
      currentProgram.setStudent(null);
    }

    program = newProgram;
    StudentAJ existingStudent = newProgram.getStudent();

    if (!equals(existingStudent))
    {
      newProgram.setStudent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (program != null)
    {
      program.setStudent(null);
    }
  }

}