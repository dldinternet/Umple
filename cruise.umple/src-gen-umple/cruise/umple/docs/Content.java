/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package cruise.umple.docs;
import java.util.*;

/**
 * Data for the user manual taken from one of the files in 
 * build/reference
 * @umplesource Documenter.ump 35
 * @umplesource Documenter_Code.ump 330
 */
// line 35 "../../../../src/Documenter.ump"
// line 330 "../../../../src/Documenter_Code.ump"
public class Content
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Content Attributes
  private String title;
  private boolean shouldIncludeReferences;
  private String description;
  private String syntax;
  private List<String> examples;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Content(String aTitle, String aDescription, String aSyntax)
  {
    title = aTitle;
    shouldIncludeReferences = true;
    description = aDescription;
    syntax = aSyntax;
    examples = new ArrayList<String>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setShouldIncludeReferences(boolean aShouldIncludeReferences)
  {
    boolean wasSet = false;
    shouldIncludeReferences = aShouldIncludeReferences;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setSyntax(String aSyntax)
  {
    boolean wasSet = false;
    syntax = aSyntax;
    wasSet = true;
    return wasSet;
  }

  public boolean addExample(String aExample)
  {
    boolean wasAdded = false;
    wasAdded = examples.add(aExample);
    return wasAdded;
  }

  public boolean removeExample(String aExample)
  {
    boolean wasRemoved = false;
    wasRemoved = examples.remove(aExample);
    return wasRemoved;
  }

  /**
   * Title of the user manual page
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * Legacy - to be deleted - used to represent whether cross-references should
   * Be created
   */
  public boolean getShouldIncludeReferences()
  {
    return shouldIncludeReferences;
  }

  /**
   * Html for the core of the manual page
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Grammar rules associated with this user manual page
   */
  public String getSyntax()
  {
    return syntax;
  }

  public String getExample(int index)
  {
    String aExample = examples.get(index);
    return aExample;
  }

  public String[] getExamples()
  {
    String[] newExamples = examples.toArray(new String[examples.size()]);
    return newExamples;
  }

  public int numberOfExamples()
  {
    int number = examples.size();
    return number;
  }

  public boolean hasExamples()
  {
    boolean has = examples.size() > 0;
    return has;
  }

  public int indexOfExample(String aExample)
  {
    int index = examples.indexOf(aExample);
    return index;
  }

  public boolean isShouldIncludeReferences()
  {
    return shouldIncludeReferences;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
	  
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "shouldIncludeReferences" + ":" + getShouldIncludeReferences()+ "," +
            "description" + ":" + getDescription()+ "," +
            "syntax" + ":" + getSyntax()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 333 ../../../../src/Documenter_Code.ump
  public String getTitleFilename()
  {
    return title.replace(" ","") + ".html";
  }
}