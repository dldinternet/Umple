/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

package cruise.associations;

// line 3 "../../../src/TestHarnessAssociations.ump"
public class MentorA
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorA Associations
  private StudentA student;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorA()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public StudentA getStudent()
  {
    return student;
  }

  public boolean setStudent(StudentA newStudent)
  {
    boolean wasSet = false;
    if (newStudent == null)
    {
      StudentA existingStudent = student;
      student = null;
      
      if (existingStudent != null && existingStudent.getMentor() != null)
      {
        existingStudent.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    StudentA currentStudent = getStudent();
    if (currentStudent != null && !currentStudent.equals(newStudent))
    {
      currentStudent.setMentor(null);
    }

    student = newStudent;
    MentorA existingMentor = newStudent.getMentor();

    if (!equals(existingMentor))
    {
      newStudent.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (student != null)
    {
      student.setMentor(null);
    }
  }

}