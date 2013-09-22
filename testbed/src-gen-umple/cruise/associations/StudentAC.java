/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package cruise.associations;
import java.util.*;

// line 448 "../../../src/TestHarnessAssociations.ump"
public class StudentAC
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentAC Attributes
  private int number;

  //StudentAC Associations
  private ProgramAC program;
  private List<MentorAC> mentors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentAC(int aNumber)
  {
    number = aNumber;
    mentors = new ArrayList<MentorAC>();
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

  public ProgramAC getProgram()
  {
    return program;
  }

  public MentorAC getMentor(int index)
  {
    MentorAC aMentor = mentors.get(index);
    return aMentor;
  }

  public List<MentorAC> getMentors()
  {
    List<MentorAC> newMentors = Collections.unmodifiableList(mentors);
    return newMentors;
  }

  public int numberOfMentors()
  {
    int number = mentors.size();
    return number;
  }

  public boolean hasMentors()
  {
    boolean has = mentors.size() > 0;
    return has;
  }

  public int indexOfMentor(MentorAC aMentor)
  {
    int index = mentors.indexOf(aMentor);
    return index;
  }

  public boolean setProgram(ProgramAC aNewProgram)
  {
    boolean wasSet = false;
    if (aNewProgram == null)
    {
      ProgramAC existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getStudent() != null)
      {
        existingProgram.setStudent(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAC currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(aNewProgram))
    {
      currentProgram.setStudent(null);
    }

    program = aNewProgram;
    StudentAC existingStudent = aNewProgram.getStudent();

    if (!equals(existingStudent))
    {
      aNewProgram.setStudent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfMentorsValid()
  {
    boolean isValid = numberOfMentors() >= minimumNumberOfMentors() && numberOfMentors() <= maximumNumberOfMentors();
    return isValid;
  }

  public static int requiredNumberOfMentors()
  {
    return 2;
  }

  public static int minimumNumberOfMentors()
  {
    return 2;
  }

  public static int maximumNumberOfMentors()
  {
    return 2;
  }

  public boolean addMentor(MentorAC aMentor)
  {
    boolean wasAdded = false;
    if (mentors.contains(aMentor)) { return false; }
    if (numberOfMentors() >= maximumNumberOfMentors())
    {
      return wasAdded;
    }

    mentors.add(aMentor);
    if (aMentor.indexOfStudent(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMentor.addStudent(this);
      if (!wasAdded)
      {
        mentors.remove(aMentor);
      }
    }
    return wasAdded;
  }

  public boolean removeMentor(MentorAC aMentor)
  {
    boolean wasRemoved = false;
    if (!mentors.contains(aMentor))
    {
      return wasRemoved;
    }

    if (numberOfMentors() <= minimumNumberOfMentors())
    {
      return wasRemoved;
    }

    int oldIndex = mentors.indexOf(aMentor);
    mentors.remove(oldIndex);
    if (aMentor.indexOfStudent(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMentor.removeStudent(this);
      if (!wasRemoved)
      {
        mentors.add(oldIndex,aMentor);
      }
    }
    return wasRemoved;
  }

  public void delete()
  {
    if (program != null)
    {
      program.setStudent(null);
    }
    ArrayList<MentorAC> copyOfMentors = new ArrayList<MentorAC>(mentors);
    mentors.clear();
    for(MentorAC aMentor : copyOfMentors)
    {
      aMentor.removeStudent(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null")
     + outputString;
  }
}