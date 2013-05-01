/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.${svn.Revision} modeling language!*/

package cruise.associations;

// line 262 "../../../src/TestHarnessAssociations.ump"
public class ProgramQ
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgramQ Associations
  private MentorQ mentor;
  private StudentQ student;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgramQ()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public MentorQ getMentor()
  {
    return mentor;
  }

  public StudentQ getStudent()
  {
    return student;
  }

  public boolean setMentor(MentorQ aNewMentor)
  {
    boolean wasSet = false;
    if (aNewMentor == null)
    {
      MentorQ existingMentor = mentor;
      mentor = null;
      
      if (existingMentor != null && existingMentor.getProgram() != null)
      {
        existingMentor.setProgram(null);
      }
      wasSet = true;
      return wasSet;
    }

    MentorQ currentMentor = getMentor();
    if (currentMentor != null && !currentMentor.equals(aNewMentor))
    {
      currentMentor.setProgram(null);
    }

    mentor = aNewMentor;
    ProgramQ existingProgram = aNewMentor.getProgram();

    if (!equals(existingProgram))
    {
      aNewMentor.setProgram(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setStudent(StudentQ aNewStudent)
  {
    boolean wasSet = false;
    if (aNewStudent == null)
    {
      StudentQ existingStudent = student;
      student = null;
      
      if (existingStudent != null && existingStudent.getProgram() != null)
      {
        existingStudent.setProgram(null);
      }
      wasSet = true;
      return wasSet;
    }

    StudentQ currentStudent = getStudent();
    if (currentStudent != null && !currentStudent.equals(aNewStudent))
    {
      currentStudent.setProgram(null);
    }

    student = aNewStudent;
    ProgramQ existingProgram = aNewStudent.getProgram();

    if (!equals(existingProgram))
    {
      aNewStudent.setProgram(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (mentor != null)
    {
      mentor.setProgram(null);
    }
    if (student != null)
    {
      student.setProgram(null);
    }
  }

}