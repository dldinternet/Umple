/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package cruise.umple.compiler;
import java.io.*;
import java.util.*;
import cruise.umple.util.*;
import cruise.umple.compiler.exceptions.*;

/**
 * @umplesource Generator.ump 108
 * @umplesource Generator_CodeGvStateDiagram.ump 26
 */
// line 108 "../../../../src/Generator.ump"
// line 26 "../../../../src/Generator_CodeGvStateDiagram.ump"
public class GvStateDiagramGenerator implements CodeGenerator
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GvStateDiagramGenerator Attributes
  private UmpleModel model;
  private String output;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GvStateDiagramGenerator()
  {
    model = null;
    output = "";
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(UmpleModel aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutput(String aOutput)
  {
    boolean wasSet = false;
    output = aOutput;
    wasSet = true;
    return wasSet;
  }

  /**
   * Contains various aspects from an Umple file (.ump), such as classes, attributes, associations and methods.  Generated output is based
   * off of what's contained in here.
   */
  public UmpleModel getModel()
  {
    return model;
  }

  public String getOutput()
  {
    return output;
  }

  public void delete()
  {}

  @umplesourcefile(line={29},file={"Generator_CodeGvStateDiagram.ump"},javaline={77},length={45})
   public void generate(){
    StringBuilder code = new StringBuilder();
    StringBuilder transitions = new StringBuilder();
    String className;

    // Output basic gv file header
    code.append("// Code generated by Umple\n\n");
    code.append("digraph "+model.getUmpleFile().getSimpleFileName()+" {\n");
    code.append("  compound = true;\n");
    code.append("  size=\"80,10\"\n");

    // Determine if there are multiple classes with state machines
    // or multiple state machines in any class
    // If so, we will need to put boxes around the state machines
    int smCount = 0;
    for (UmpleClass uClass : model.getUmpleClasses()) {
      for (StateMachine sm : uClass.getStateMachines()) {
        smCount++;
      }
    }
    if(smCount == 0) {
      // Special case. No state machine.
      code.append("  node [shape=box, penwidth=0];\n");
      code.append("  message [label =\"No state machine found in the input Umple file\"];\n");
      terminateCode(code, transitions);
      return;
    }

    // We now know we have to output one or more state machines
    // Iterate through each class. 
    for (UmpleClass uClass : model.getUmpleClasses())
    {
      className = uClass.getName();
      code.append("\n  // Class: "+className+"\n");
      
      // Process the top level state machines in a class
      for (StateMachine sm : uClass.getStateMachines())
      {
        if(sm.getParentState() == null) { // This check shouldn't be needed
          appendStateMachineRecursively(code, transitions, sm, uClass, className, true, smCount, 4);
        }
      } // End iteration through state machines of a class
    } // End iteration through classes
    terminateCode(code, transitions);
  }

  @umplesourcefile(line={76},file={"Generator_CodeGvStateDiagram.ump"},javaline={124},length={8})
   private void terminateCode(StringBuilder code, StringBuilder transitions){
    code.append("\n  // All transitions\n");
    code.append(transitions);
    code.append("}\n");

    model.setCode(code.toString());
    writeModel();
  }


  /**
   * Append the standard format for a start state into the current graph context
   */
  @umplesourcefile(line={86},file={"Generator_CodeGvStateDiagram.ump"},javaline={134},length={8})
   private void appendStartState(StringBuilder code, int numSpaces, String clSmName){
    appendSpaces(code, numSpaces);
    code.append("// Start state black circle\n");
    appendSpaces(code, numSpaces);
    code.append("node [shape = point, fillcolor=\"black\", width=0.2 ];\n");
    appendSpaces(code, numSpaces);
    code.append("start_"+clSmName+";\n\n");
  }


  /**
   * Append the format for normal nodes.
   */
  @umplesourcefile(line={96},file={"Generator_CodeGvStateDiagram.ump"},javaline={148},length={6})
   private void appendNormalStateFormat(StringBuilder code, int numSpaces){
    appendSpaces(code, numSpaces); 
    code.append("// Format for normal states\n");
    appendSpaces(code, numSpaces);
    code.append("node [shape = rectangle, width=1,style=rounded];\n");
  }


  /**
   * Used to indent code
   */
  @umplesourcefile(line={104},file={"Generator_CodeGvStateDiagram.ump"},javaline={160},length={5})
   private void appendSpaces(StringBuilder code, int numSpaces){
    for(int i=0; i<numSpaces; i++) {
      code.append(" ");
    }
  }


  /**
   * The state qualified name incorporates the classname, the state machine name
   * and the hierarchy of state names
   * This is used as the internal graphviz label, and is not displayed.
   */
  @umplesourcefile(line={113},file={"Generator_CodeGvStateDiagram.ump"},javaline={171},length={5})
   private String getStateQualifiedName(State s, UmpleClass c){
    StateMachine sm = s.getStateMachine();
    String prepend = s.hasNestedStateMachines() ? "cluster" : "";
    return prepend+c.getName()+"_"+sm.getFullName()+"_"+s.getName();
  }


  /**
   * Return the name for the state to be used in transitions
   */
  @umplesourcefile(line={120},file={"Generator_CodeGvStateDiagram.ump"},javaline={184},length={4})
   private String getTransitionNameForState(State s, UmpleClass c, boolean isOrigin){
    State firstNonSuperstate = getFirstNestedNonClusterState(s);
    return getStateQualifiedName(firstNonSuperstate, c);
  }


  /**
   * return self if it does not have nested state, otherwise the first
   * nested state that does not itself have a nested state
   */
  @umplesourcefile(line={127},file={"Generator_CodeGvStateDiagram.ump"},javaline={194},length={8})
   private State getFirstNestedNonClusterState(State s){
    if(!s.hasNestedStateMachines()) {
      return s;
    }
    else {
      return getFirstNestedNonClusterState(s.getNestedStateMachine(0).getState(0));
    }
  }

  @umplesourcefile(line={136},file={"Generator_CodeGvStateDiagram.ump"},javaline={209},length={9})
   private String getTransitionHeadOrTailForState(State s, UmpleClass c, boolean isOrigin){
    if(s.hasNestedStateMachines()) {
      // Use lhead or ltail
      return (isOrigin? "ltail" : "lhead")+"="+getStateQualifiedName(s, c)+",";
    }
    else {
      return "";
    }
  }

  @umplesourcefile(line={146},file={"Generator_CodeGvStateDiagram.ump"},javaline={220},length={157})
   private void appendStateMachineRecursively(StringBuilder code, StringBuilder transitions, StateMachine sm, UmpleClass uClass, String className, boolean isTopLevel, int smCount, int indentLevel){
    String smName, clSmName, sLabel, sName;
    Event event;
    Action action;
    String transitionLabel, guardString, actionCode, transitionAction;
    Guard guard;
    List<StateMachine> allNestedStateMachines = sm.getImmediateNestedStateMachines();

    // We haven't processed any states yet in this statemachine
    Boolean isFirstState = true;

    // Determine whether bottom level
    Boolean isBottomLevel = allNestedStateMachines.isEmpty();
    String levelPhrase = "";
    if(isTopLevel) {
      if(isBottomLevel) levelPhrase ="Top and Bottom Level ";
      else levelPhrase = "Top Level ";
    }
    else if(isBottomLevel) levelPhrase = "Bottom Level ";
      
    smName = sm.getName();
    clSmName = className+"_"+smName;
    code.append("\n");
    appendSpaces(code,  indentLevel);
    code.append("// "+levelPhrase+"StateMachine: "+smName+"\n");        

    // If there are multiple state machines in the model
    // And this is a top level state machine
    // Then we need to put the state machine in a border
    if(smCount > 1 && isTopLevel) {
      appendSpaces(code,  indentLevel);

      code.append("subgraph cluster"+clSmName+" {\n");
      appendSpaces(code,  indentLevel+2);
      code.append("label=\"sm "+className+" "+smName+"\";\n");   
      appendSpaces(code,  indentLevel+2);
      code.append("penwidth=0.5;\n");        
    }
      
    // Every state machine has a start state
    appendStartState(code, indentLevel, clSmName);
    appendNormalStateFormat(code, indentLevel);
 
    // Iterate through the states of this state machine
    for (State s : sm.getStates())
    {
      sLabel=s.getName();  // This is what is displayed
      sName=getStateQualifiedName(s,uClass);  // Internal gv identifier
      
      code.append("\n");      
      appendSpaces(code,  indentLevel+2);
      code.append("// State: "+sLabel+"\n");   

      // Output the state (and possibly state machine) id
      if(!s.hasNestedStateMachines()) {
        // Simple state node
        appendSpaces(code,  indentLevel+2);
        code.append(sName+" [label = "+sLabel+"];\n");
      }
      else {
        // Start of a subgraph
        appendSpaces(code,  indentLevel+1);
        code.append("subgraph "+sName+" {\n");
        appendSpaces(code,  indentLevel+2);
        code.append("label = \""+sLabel+"\";\n");
        appendSpaces(code,  indentLevel+2);
        code.append("style = rounded;\n");
      }
      
      if(isFirstState) {
         // Output transition to first state
         isFirstState = false;
        appendSpaces(transitions,  indentLevel+2);
        String dest=getTransitionNameForState(s,uClass,false);
        String head=getTransitionHeadOrTailForState(s, uClass,false);
        transitions.append("start_"+clSmName+" -> "+dest+(head=="" ? "" : " ["+head+"]")+";\n");
      }
          
      // Output all the other transitions
      for (Transition t : s.getNextTransition()) {
        event = t.getEvent();
        action = t.getAction();
        
        if(event.isAutoTransition()) {
          transitionLabel = "";
        } 
        else {
          if (event.getIsTimer()) {
            transitionLabel = "after("+event.getTimerInSeconds()+")";
          }
          else {
            if(event.getArgs() == null || event.getArgs() == "") {
              transitionLabel = event.getName();
            }
            else {
              transitionLabel = event.getName()+"("+event.getArgs()+")";
            }
          }
        }
        
        if (action == null || action.getActionCode() == "") {
          transitionAction = "";
        }
        else {
          actionCode = action.getActionCode();
          if (actionCode.length() > 15) transitionAction = " / {...}";
          else transitionAction = " / "+actionCode;
        }
      
        guard = t.getGuard();
        if (guard == null) guardString = "";
        else {
          guardString = event.isAutoTransition() ? "[" : " ["
            + guard.getCondition(new JavaGenerator())+"]";
        }

        appendSpaces(transitions,  indentLevel+2);
        
        String orig = getTransitionNameForState(t.getFromState(),uClass,true);
        String dest = getTransitionNameForState(t.getNextState(),uClass,false);
        String origlh = getTransitionHeadOrTailForState(t.getFromState(),uClass,true);
        String destlt = getTransitionHeadOrTailForState(t.getNextState(),uClass,false);
        
        transitions.append(orig 
          + " -> "+ dest
          + " ["+origlh+destlt+" label = \""
          + transitionLabel + guardString + transitionAction + "\" ];\n");
      }  // End iteration through the transitions

      // Process nested state machines of this state
      allNestedStateMachines = s.getNestedStateMachines();
      for(StateMachine nestedSm: allNestedStateMachines) {
        appendStateMachineRecursively(code, transitions, nestedSm, uClass, className, false, smCount, indentLevel+2);
      } // End iteration through nested state machines

      // Mark end of subgraph
      if(s.hasNestedStateMachines()) {
        appendSpaces(code,  indentLevel+1);
        code.append("}\n");
      }

      appendSpaces(code,  indentLevel+2);
      code.append("// End State: "+sLabel+"\n"); 
     
    } // End iteration through states of a state machine


    appendSpaces(code,  indentLevel);
    code.append("// End "+levelPhrase+"StateMachine: "+smName+"\n");  

    // If there are multiple state machines in the model
    // And this is the top level state machine
    // Then we need to end the cluster border started earlier
    if(smCount > 1 && isTopLevel) {
      code.append("  }\n");
    }
  }


  /**
   * Output the graphviz file to a file with the .gv suffix
   */
  @umplesourcefile(line={309},file={"Generator_CodeGvStateDiagram.ump"},javaline={379},length={17})
   private void writeModel(){
    try
    {
      String path = model.getUmpleFile().getPath();
      File file = new File(path);
      file.mkdirs();
      String modelFilename = path + File.separator + model.getUmpleFile().getSimpleFileName() + ".gv";
      BufferedWriter bw = new BufferedWriter(new FileWriter(modelFilename));
      bw.write(model.getCode());
      bw.flush();
      bw.close();
    }
    catch (Exception e)
    {
      throw new UmpleCompilerException("There was a problem with generating GraphViz State Machine code." + e, e);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "output" + ":" + getOutput()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "model" + "=" + (getModel() != null ? !getModel().equals(this)  ? getModel().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}