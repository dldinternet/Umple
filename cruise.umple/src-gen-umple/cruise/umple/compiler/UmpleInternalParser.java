/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.2716 modeling language!*/

package cruise.umple.compiler;
import java.io.*;
import cruise.umple.util.*;
import java.util.regex.*;
import java.util.*;

/**
 * Copyright: All contributers to the Umple Project
 * 
 * This file is made available subject to the open source license found at:
 * http://umple.org/license
 * This is our internal parser implementation for the Umple language.  It uses
 * a generic Parser that can read an external EBNF grammar file, and then populate
 * an abstract syntax tree.
 * 
 * The work of the UmpleInternalParser is 
 * 
 * a) The grammar definition (defined externally in *.grammar files)
 * b) Analyzing the AST to populate an Umple meta model instance
 * c) Delegating to our code generator to produce the necessary artifacts (i.e. Java / PHP / Ruby code)
 * 
 * Please refer to UmpleInternalParser_Code.ump for implementation details.
 * @umplesource UmpleInternalParser.ump 23
 * @umplesource UmpleInternalParser_Code.ump 33
 * @umplesource UmpleInternalParser_CodeCore.ump 17
 * @umplesource UmpleInternalParser_CodeClass.ump 17
 * @umplesource UmpleInternalParser_CodeConstraints.ump 17
 * @umplesource UmpleInternalParser_CodeStateMachine.ump 17
 * @umplesource UmpleInternalParser_CodeTrace.ump 17
 * @umplesource UmpleInternalParser_CodeLayout.ump 17
 */
// line 23 "../../../../src/UmpleInternalParser.ump"
// line 33 "../../../../src/UmpleInternalParser_Code.ump"
// line 17 "../../../../src/UmpleInternalParser_CodeCore.ump"
// line 17 "../../../../src/UmpleInternalParser_CodeClass.ump"
// line 17 "../../../../src/UmpleInternalParser_CodeConstraints.ump"
// line 17 "../../../../src/UmpleInternalParser_CodeStateMachine.ump"
// line 17 "../../../../src/UmpleInternalParser_CodeTrace.ump"
// line 17 "../../../../src/UmpleInternalParser_CodeLayout.ump"
public class UmpleInternalParser extends Parser implements UmpleParser
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int line();String file();int javaline();int length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UmpleInternalParser Attributes
  private String currentPackageName;
  private boolean packageNameUsed;
  private boolean outputUmpleSource;
  private UmpleModel model;
  private List<String> unparsedUmpleFiles;
  private List<String> parsedUmpleFiles;
  private boolean shouldProcessAgain;
  private boolean shouldProcessClassAgain;
  private List<AssociationVariable> unlinkedAssociationVariables;
  private List<Association> unlinkedAssociations;
  private Map<Position,String> positionToClassNameReference;
  private Map<UmpleClassifier,List<String>> unlinkedExtends;
  private Map<UmpleClassifier,List<Token>> unlinkedExtendsTokens;
  private Map<UmpleClassifier,List<Token>> unlinkedExtendsTokensInterface;
  private Map<UmpleClassifier,List<String>> unlinkedInterfaceExtends;
  private List<Comment> lastComments;
  private StateMachine placeholderStateMachine;
  private Map<String,Token> stateMachineNameToToken;
  private Map<State,List<Token>> possiblyUnknownStates;
  private int traceFlagId;

  //UmpleInternalParser State Machines
  enum Strictness { none, modelOnly, noExtraCode }
  private Strictness strictness;

  //UmpleInternalParser Associations
  private List<ErrorType> messagesToExpect;
  private List<ErrorType> warningsToIgnore;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UmpleInternalParser(String aName, UmpleModel aModel)
  {
    super(aName);
    currentPackageName = "";
    packageNameUsed = true;
    outputUmpleSource = false;
    model = aModel;
    unparsedUmpleFiles = new ArrayList<String>();
    parsedUmpleFiles = new ArrayList<String>();
    shouldProcessAgain = false;
    shouldProcessClassAgain = false;
    unlinkedAssociationVariables = new ArrayList<AssociationVariable>();
    unlinkedAssociations = new ArrayList<Association>();
    positionToClassNameReference = new HashMap<Position, String>();
    unlinkedExtends = new HashMap<UmpleClassifier,List<String>>();
    unlinkedExtendsTokens = new HashMap<UmpleClassifier,List<Token>>();
    unlinkedExtendsTokensInterface = new HashMap <UmpleClassifier, List<Token>>();
    unlinkedInterfaceExtends = new HashMap<UmpleClassifier,List<String>>();
    lastComments = new ArrayList<Comment>();
    placeholderStateMachine = null;
    stateMachineNameToToken = new HashMap<String, Token>();
    possiblyUnknownStates = new HashMap<State,List<Token>>();
    traceFlagId = 0;
    messagesToExpect = new ArrayList<ErrorType>();
    warningsToIgnore = new ArrayList<ErrorType>();
    setStrictness(Strictness.none);
    // line 41 "../../../../src/UmpleInternalParser_Code.ump"
    init();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentPackageName(String aCurrentPackageName)
  {
    boolean wasSet = false;
    currentPackageName = aCurrentPackageName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPackageNameUsed(boolean aPackageNameUsed)
  {
    boolean wasSet = false;
    packageNameUsed = aPackageNameUsed;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutputUmpleSource(boolean aOutputUmpleSource)
  {
    boolean wasSet = false;
    outputUmpleSource = aOutputUmpleSource;
    wasSet = true;
    return wasSet;
  }

  public boolean setModel(UmpleModel aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    // line 47 "../../../../src/UmpleInternalParser.ump"
    if(model != null && model.getUmpleFile() != null) { super.setFilename(model.getUmpleFile().getFileName()); super.setRootToken(reset());}
    return wasSet;
  }

  public boolean setTraceFlagId(int aTraceFlagId)
  {
    boolean wasSet = false;
    traceFlagId = aTraceFlagId;
    wasSet = true;
    return wasSet;
  }

  public String getCurrentPackageName()
  {
    return currentPackageName;
  }

  public boolean getPackageNameUsed()
  {
    return packageNameUsed;
  }

  public boolean getOutputUmpleSource()
  {
    return outputUmpleSource;
  }

  /**
   * The Umple meta model which will be populated based on what was parsed.
   */
  public UmpleModel getModel()
  {
    return model;
  }

  public int getTraceFlagId()
  {
    // line 20 "../../../../src/UmpleInternalParser_CodeTrace.ump"
    traceFlagId++;
    return traceFlagId;
  }

  public boolean isPackageNameUsed()
  {
    return packageNameUsed;
  }

  public boolean isOutputUmpleSource()
  {
    return outputUmpleSource;
  }

  public String getStrictnessFullName()
  {
    String answer = strictness.toString();
    return answer;
  }

  public Strictness getStrictness()
  {
    return strictness;
  }

  public boolean setStrictness(Strictness aStrictness)
  {
    strictness = aStrictness;
    return true;
  }

  public ErrorType getMessagesToExpect(int index)
  {
    ErrorType aMessagesToExpect = messagesToExpect.get(index);
    return aMessagesToExpect;
  }

  /**
   * List of errors or warnings to expect. It is an error if they do not occur.
   * This is used to set up test cases for the presence of certain messages
   */
  public List<ErrorType> getMessagesToExpect()
  {
    List<ErrorType> newMessagesToExpect = Collections.unmodifiableList(messagesToExpect);
    return newMessagesToExpect;
  }

  public int numberOfMessagesToExpect()
  {
    int number = messagesToExpect.size();
    return number;
  }

  public boolean hasMessagesToExpect()
  {
    boolean has = messagesToExpect.size() > 0;
    return has;
  }

  public int indexOfMessagesToExpect(ErrorType aMessagesToExpect)
  {
    int index = messagesToExpect.indexOf(aMessagesToExpect);
    return index;
  }

  public ErrorType getWarningsToIgnore(int index)
  {
    ErrorType aWarningsToIgnore = warningsToIgnore.get(index);
    return aWarningsToIgnore;
  }

  /**
   * List of warnings to allow and ignore
   * Used to suppress warnings that we do not care about
   */
  public List<ErrorType> getWarningsToIgnore()
  {
    List<ErrorType> newWarningsToIgnore = Collections.unmodifiableList(warningsToIgnore);
    return newWarningsToIgnore;
  }

  public int numberOfWarningsToIgnore()
  {
    int number = warningsToIgnore.size();
    return number;
  }

  public boolean hasWarningsToIgnore()
  {
    boolean has = warningsToIgnore.size() > 0;
    return has;
  }

  public int indexOfWarningsToIgnore(ErrorType aWarningsToIgnore)
  {
    int index = warningsToIgnore.indexOf(aWarningsToIgnore);
    return index;
  }

  public static int minimumNumberOfMessagesToExpect()
  {
    return 0;
  }

  public boolean addMessagesToExpect(ErrorType aMessagesToExpect)
  {
    boolean wasAdded = false;
    if (messagesToExpect.contains(aMessagesToExpect)) { return false; }
    messagesToExpect.add(aMessagesToExpect);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMessagesToExpect(ErrorType aMessagesToExpect)
  {
    boolean wasRemoved = false;
    if (messagesToExpect.contains(aMessagesToExpect))
    {
      messagesToExpect.remove(aMessagesToExpect);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMessagesToExpectAt(ErrorType aMessagesToExpect, int index)
  {  
    boolean wasAdded = false;
    if(addMessagesToExpect(aMessagesToExpect))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessagesToExpect()) { index = numberOfMessagesToExpect() - 1; }
      messagesToExpect.remove(aMessagesToExpect);
      messagesToExpect.add(index, aMessagesToExpect);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMessagesToExpectAt(ErrorType aMessagesToExpect, int index)
  {
    boolean wasAdded = false;
    if(messagesToExpect.contains(aMessagesToExpect))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessagesToExpect()) { index = numberOfMessagesToExpect() - 1; }
      messagesToExpect.remove(aMessagesToExpect);
      messagesToExpect.add(index, aMessagesToExpect);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMessagesToExpectAt(aMessagesToExpect, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfWarningsToIgnore()
  {
    return 0;
  }

  public boolean addWarningsToIgnore(ErrorType aWarningsToIgnore)
  {
    boolean wasAdded = false;
    if (warningsToIgnore.contains(aWarningsToIgnore)) { return false; }
    warningsToIgnore.add(aWarningsToIgnore);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWarningsToIgnore(ErrorType aWarningsToIgnore)
  {
    boolean wasRemoved = false;
    if (warningsToIgnore.contains(aWarningsToIgnore))
    {
      warningsToIgnore.remove(aWarningsToIgnore);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addWarningsToIgnoreAt(ErrorType aWarningsToIgnore, int index)
  {  
    boolean wasAdded = false;
    if(addWarningsToIgnore(aWarningsToIgnore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWarningsToIgnore()) { index = numberOfWarningsToIgnore() - 1; }
      warningsToIgnore.remove(aWarningsToIgnore);
      warningsToIgnore.add(index, aWarningsToIgnore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWarningsToIgnoreAt(ErrorType aWarningsToIgnore, int index)
  {
    boolean wasAdded = false;
    if(warningsToIgnore.contains(aWarningsToIgnore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWarningsToIgnore()) { index = numberOfWarningsToIgnore() - 1; }
      warningsToIgnore.remove(aWarningsToIgnore);
      warningsToIgnore.add(index, aWarningsToIgnore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWarningsToIgnoreAt(aWarningsToIgnore, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    messagesToExpect.clear();
    warningsToIgnore.clear();
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
    @umplesourcefile(line=0,file="",javaline=412,length=3)
 public UmpleInternalParser()  {
this("UmpleInternalParser", new UmpleModel(null));
  }
  @umplesourcefile(line=3,file="",javaline=416,length=3)
 public UmpleInternalParser(UmpleModel aModel)  {
this("UmpleInternalParser", aModel);
  }
// line 53 ../../../../src/UmpleInternalParser_Code.ump
  @umplesourcefile(line=53,file="UmpleInternalParser_Code.ump",javaline=421,length=18)
  private void init()
  {
    if(model.getUmpleFile() != null)
    {
      setFilename(model.getUmpleFile().getFileName());
      setRootToken(reset()); // Makes sure the root token position has the filename
    }
    
    addCouple(new Couple("\"","\""));
    addCouple(new Couple("{","}"));
    addRulesInFile("/umple_core.grammar");
    addRulesInFile("/umple_classes.grammar");
    addRulesInFile("/umple_patterns.grammar");
    addRulesInFile("/umple_state_machines.grammar");
    addRulesInFile("/umple_traces.grammar");
    addRulesInFile("/umple_constraints.grammar");  // TODO Under development
    addRulesInFile("/umple_layout.grammar");
  }

  @umplesourcefile(line=72,file="UmpleInternalParser_Code.ump",javaline=441,length=4)
  public ParseResult parse(String ruleName, String input)
  {
    return super.parse(ruleName,input);
  }

  @umplesourcefile(line=77,file="UmpleInternalParser_Code.ump",javaline=447,length=11)
  public ParseResult analyze(boolean shouldGenerate)
  {
    parseAllFiles();
    analyzeAllTokens(getRootToken());
    postTokenAnalysis();
    if (shouldGenerate && getParseResult().getWasSuccess())
    {
      model.generate();
    }
    return getParseResult();
  }

  //------------------------
  // PRIVATE METHODS
  //------------------------

  // When an error occurs, set the failed position and mark the compile as NOT successful
  @umplesourcefile(line=94,file="UmpleInternalParser_Code.ump",javaline=465,length=6)
  private void setFailedPosition(Position position, int errorCode, String... messages)
  {
    //getParseResult().setWasSuccess(false);
    getParseResult().setPosition(position);
    getParseResult().addErrorMessage(new ErrorMessage(errorCode,position,messages));
  }

  // Analyze all child tokens of the "root" token.  This delegates to a individual
  // Each token is analyzed as long as "shouldProcessAgain" is set to true during the analysis
  // analyzeToken and quits early if a problem arises
  @umplesourcefile(line=104,file="UmpleInternalParser_Code.ump",javaline=476,length=19)
  private void analyzeAllTokens(Token rootToken)
  {
    int analysisStep = 0;
    shouldProcessAgain = true;
    do
    {
      analysisStep += 1;
      shouldProcessAgain = false;
      for(Token t : rootToken.getSubTokens())
      {
        analyzeToken(t,analysisStep);
        if (!getParseResult().getWasSuccess())
        {
          return;
        }
      }
    }
    while (shouldProcessAgain);
  }

  // Delegate function to analyze a token within the context of a class
  // Each token is analyzed as long as "shouldProcessClassAgain" is set to true during the analysis
  // "1" is for the first round of analysis and "2" for the second.  The "2" is used for chicken-and-egg initialization problems, otherwise
  // put everything under the "1"
  @umplesourcefile(line=128,file="UmpleInternalParser_Code.ump",javaline=501,length=19)
  private void analyzeAllTokens(Token rootToken, UmpleClass aClass)
  {
    int analysisStep = 0;
    shouldProcessClassAgain = true;
    do
    {
      analysisStep += 1;
      shouldProcessClassAgain = false;
      for(Token token : rootToken.getSubTokens())
      {
        analyzeToken(token,aClass,analysisStep);
        if (!getParseResult().getWasSuccess())
        {
          return;
        }
      }
    }
    while (shouldProcessClassAgain);
  }

  // Delegate function to analyze a token and send it to the write
  @umplesourcefile(line=149,file="UmpleInternalParser_Code.ump",javaline=523,length=8)
  private void analyzeToken(Token t, int analysisStep)
  {
    analyzeCoreToken(t,analysisStep);
    analyzeClassToken(t,analysisStep);
    analyzeStateMachineToken(t,analysisStep);
    analyzeTraceToken(t,analysisStep);
    analyzeLayoutToken(t,analysisStep);
  }

  // Analyze an individual token, delegates to the various components in Umple
  @umplesourcefile(line=159,file="UmpleInternalParser_Code.ump",javaline=534,length=8)
  private void analyzeToken(Token t, UmpleClass aClass, int analysisStep)
  {
    analyzeCoreToken(t,aClass,analysisStep);
    analyzeClassToken(t,aClass,analysisStep);
    analyzeStateMachineToken(t,aClass,analysisStep);
    analyzeTraceToken(t,aClass,analysisStep);
    analyzeLayoutToken(t,aClass,analysisStep);
  }
  
  // Once you have analyze all tokens, you allowed a second 'pass' to apply any additional checks
  // Each step in the process might "fail", so we check the status before calling each delegate
  // token post token analysis method
  @umplesourcefile(line=171,file="UmpleInternalParser_Code.ump",javaline=547,length=32)
  private void postTokenAnalysis()
  {
    
    if (getParseResult().getWasSuccess())
    {
      postTokenCoreAnalysis();
    }
    if (getParseResult().getWasSuccess())
    {
      postTokenInterfaceAnalysis();
    }
    if (getParseResult().getWasSuccess())
    {
      postTokenClassAnalysis();  
    }

    if (getParseResult().getWasSuccess())
    {
      postTokenStateMachineAnalysis();
    }
    
    if (getParseResult().getWasSuccess())
    {
      postTokenTraceAnalysis();  
    }

    if (getParseResult().getWasSuccess())
    {
      postTokenLayoutAnalysis();
    }

  }


  // Locate all 'use *.ump' references and add those files if not already parsed 
  @umplesourcefile(line=206,file="UmpleInternalParser_Code.ump",javaline=583,length=46)
  private void addNecessaryFiles()
  {
    for(Token t : getRootToken().getSubTokens())
    {
      if (t.is("use"))
      {
        // A file included with a use statement could be in the same directory
        String filename = model.getUmpleFile().getPath() + File.separator + t.getValue();
        
        // Or it could be in the parent directory
        String filenamePD = model.getUmpleFile().getParent() + File.separator + t.getValue();
        
        // Or it could be in a lib directory of the current directory
        String filenameLD = model.getUmpleFile().getPath() + File.separator + "lib" + File.separator + t.getValue();
        
        // TO DO: Or it could be on the web as a standard Umple file
        // This should ideally also be turned into a 'search path' based mechanism

        if (!parsedUmpleFiles.contains(filename) && !unparsedUmpleFiles.contains(filename) && !parsedUmpleFiles.contains(filenamePD) && !unparsedUmpleFiles.contains(filenamePD) && !parsedUmpleFiles.contains(filenameLD) && !unparsedUmpleFiles.contains(filenameLD) )
        {
          // We have not encountered this 'use' file yet
          // Check if file name exists - if not raise error message        
          if((new File(filename)).exists()) {
            // We have found the file and it needs to be queued for processing
            unparsedUmpleFiles.add(filename);
          }
          else if((new File(filenamePD)).exists()) {
            // We have found the file and it needs to be queued for processing
            unparsedUmpleFiles.add(filenamePD);
          }
          else if((new File(filenameLD)).exists()) {
            // We have found the file and it needs to be queued for processing
            unparsedUmpleFiles.add(filenameLD);
          }
          else {
            getParseResult().addErrorMessage(new ErrorMessage(1510, t.getPosition(), t.getValue()));

          }
        }  
        
        // To do: If file doesn't exist, then t.getValue may be referring to a
        // library file in ../ump or on the web. Try these before raising error
        
      }
    }
  }
  
  // Loop through all unparsed files, parse them, and add any missing references
  @umplesourcefile(line=254,file="UmpleInternalParser_Code.ump",javaline=632,length=17)
  private void parseAllFiles()
  {
    addNecessaryFiles();
    while (!unparsedUmpleFiles.isEmpty())
    {
      String nextFile = unparsedUmpleFiles.get(0);
      unparsedUmpleFiles.remove(0);
      parsedUmpleFiles.add(nextFile);
      String input = SampleFileWriter.readContent(new File(nextFile));
      //TODO: parse() should probably be responsible for
      // reading file data, if we refactor it to be as such,
      // then we can get rid of this ugly bit of code
      setFilename(nextFile);
      parse("program", input);
      addNecessaryFiles();
    }    
  }
// line 21 ../../../../src/UmpleInternalParser_CodeCore.ump
  @umplesourcefile(line=21,file="UmpleInternalParser_CodeCore.ump",javaline=651,length=20)
  private void analyzeCoreToken(Token t, int analyzeCoreToken)
  {
    if (analyzeCoreToken != 1)
    {
      return;
    }
  
    if (t.is("generate") || t.is("generate_path"))
    {
    	analyzeGenerate(t);
    }
    else if (t.is("glossary"))
    {
      analyzeGlossary(t);
    }
    else if (t.is("debug"))
    {
    	model.setDebugMode(true);
    }
  }

  // There are currently no core tokens of concern in the context of an UmpleClass
  // This method is available if needed
  @umplesourcefile(line=44,file="UmpleInternalParser_CodeCore.ump",javaline=675,length=4)
  private void analyzeCoreToken(Token t, UmpleClass aClass, int analysisStep)
  {

  }

  // Perform post token analysis on core elements of the Umple language
  @umplesourcefile(line=50,file="UmpleInternalParser_CodeCore.ump",javaline=682,length=51)
  private void postTokenCoreAnalysis()
  {
 	  boolean overrode_all = false;
		
	  List<GenerateTarget> gen = new ArrayList<GenerateTarget>(Arrays.asList(model.getGenerates()));
	  HashMap<String, Boolean> overrideMap = new HashMap<String,Boolean>();
		
	  for(GenerateTarget target : model.getGenerates())
	  {
	  	if(target.getOverride() && !overrideMap.containsKey(target.getLanguage()))
	  	{
	  	   // Target is based on key {language} so this 
	  	   // should remove everything with the same key
	  	   while(gen.remove(target));
	  	   overrideMap.put(target.getLanguage(), true);
	  	   gen.add(target);
	  	}
	  	else
	  	{
	  		// Issue a warning that a generate statement has been issued 
	  		// with the override keyword twice
	  	}
	  		
	  	if(target.getOverrideAll() && overrideMap.containsKey(target.getLanguage()))
	  	{
	  		// issue warning
	  	}

	  	if(target.getOverrideAll() && overrode_all)
	  	{
	  		// issue warning
	  		continue;
	  	}
	  		
	  	if(target.getOverrideAll())
	  	{
	  		gen.clear();
	  		gen.add(target);
	  		overrideMap.put(target.getLanguage(), true);
	  		overrode_all = true;
	  	}
	}
	
	model.clearGenerates();
	model.addGenerate(gen);
	
    if (model.getDefaultGenerate() == null)
    {
      model.addGenerate("Java");
    }
  }  


  // Add singular / plural forms of words to the glossary to be used by the code generator
  @umplesourcefile(line=104,file="UmpleInternalParser_CodeCore.ump",javaline=737,length=12)
  private void analyzeGlossary(Token glossaryToken)
  {
    for(Token wordToken : glossaryToken.getSubTokens())
    {
      if (!wordToken.is("word"))
      {
        continue;
      }
      Word word = new Word(wordToken.getValue("singular"),wordToken.getValue("plural"));
      model.getGlossary().addWord(word);
    }
  }
  
  @umplesourcefile(line=117,file="UmpleInternalParser_CodeCore.ump",javaline=751,length=21)
  private void analyzeGenerate(Token genToken)
  {
  	if(genToken.is("generate_path"))
  	{
  	  String language = genToken.getValue("language");
  	  String path = genToken.getValue("output");
  	  GenerateTarget target = new GenerateTarget(language, path);
  	      
  	  if(genToken.getValue("override") != null && genToken.getValue("override").equals("--override"))
  	  	target.setOverride(true);
  	  
  	  if(genToken.getValue("override") != null && genToken.getValue("override").equals("--override-all"))
  	  	target.setOverrideAll(true);
  	  
  	   model.addGenerate(target);
  	}
  	else
  	{
      model.addGenerate(genToken.getValue());
  	}
  }
// line 38 ../../../../src/UmpleInternalParser_CodeClass.ump
  @umplesourcefile(line=38,file="UmpleInternalParser_CodeClass.ump",javaline=774,length=74)
  private void analyzeClassToken(Token t, int analysisStep)
  {
    if (analysisStep != 2)
    {
      shouldProcessAgain = shouldProcessAgain || (analysisStep == 1);
      return;
    }

    // Only need to clear comments if there actually was comments.
    boolean shouldConsumeComment = lastComments.size() > 0;

    // Determine what the current token is primarily, and based on that the analysis procedure is determined.
    if (t.isStatic("//") || t.isStatic("/*") || t.isStatic("*/"))
    {
      shouldConsumeComment = false;
    }
    else if (t.is("strictness") || t.is("message"))
    {
      // unimplemented feature. Issue a warning that it is currently not fully implemented
      setFailedPosition(t.getPosition(), 9999, t.getName(), t.toString());
    }      
    else if (t.is("namespace"))
    {
      if(!packageNameUsed && !t.getValue().equals(currentPackageName))
    		setFailedPosition(t.getPosition(),31,currentPackageName,t.getValue());
      currentPackageName = t.getValue();
      if (model.getDefaultNamespace() == null)
      {
        model.setDefaultNamespace(currentPackageName);  
      }
      packageNameUsed = false;
    }
    else if (t.is("inlineComment"))
    {
      analyzeComment(t);
      shouldConsumeComment = false;
    } 
    else if (t.is("multilineComment"))
    {
      analyzeMultilineComment(t);
      shouldConsumeComment = false;
    }
    else if (t.is("classDefinition"))
    {
      analyzeClass(t);
    }
    else if (t.is("externalDefinition"))
    {
      if (t.getValue("interface")!=null)
        analyzeExternalInterface(t);
      else
        analyzeExternal(t);
    }
    else if (t.is("interfaceDefinition"))
    {
      analyzeInterface(t);
    }
    else if (t.is("associationClassDefinition"))
    {
      analyzeAssociationClass(t);
    }
    else if (t.is("associationDefinition"))
    {
      analyzeAllAssociations(t);
    }

    // This essentially "clears" the comments in the list so that new comments, when parsed, will be the ones appearing above
    // classes, methods, attributes, etc (whichever comes next) rather than old comments propogating everywhere.
    if (shouldConsumeComment)
    {
      lastComments.clear();
    }

  }  

  /*
   * Analyze class content tokens.
   * 
   * @param token The current token that will be analyzed to determine how to further make use of it (is it a method, comment, 
   * attribute, etc?)
   * @param aClass The Umple class used such that parsed content such as methods, attributes, comments, etc may be added to
   * it.
   * @param analysisStep Used to determine whether or not things should be analyzed more than once (multi-pass).
   */
  @umplesourcefile(line=122,file="UmpleInternalParser_CodeClass.ump",javaline=859,length=102)
  private void analyzeClassToken(Token token, UmpleClass aClass, int analysisStep)
  {
    if (analysisStep != 1)
    {
      return;
    }

    // Only need to clear comments if there actually was comments.
    boolean shouldConsumeComment = lastComments.size() > 0;
    


    // Determine what the current token is primarily, and based on that the analysis procedure is determined.
    if (token.isStatic("//") || token.isStatic("/*") || token.isStatic("*/"))
    {
      shouldConsumeComment = false;
    }
    else if (token.is("inlineComment"))
    {
      analyzeComment(token);
      shouldConsumeComment = false;
    }
    else if (token.is("multilineComment"))
    {
      analyzeMultilineComment(token);
      shouldConsumeComment = false;
    }
    // TODO Under development
    
    else if (token.is("classDefinition"))
    {
      UmpleClass childClass = analyzeClass(token);
      boolean wasSet = childClass.setExtendsClass(aClass);
      if (!wasSet)
      {
      	setFailedPosition(token.getPosition(), 16, childClass.getName(), aClass.getName());
      }
    }
    else if (token.is("attribute"))
    {
      analyzeAttribute(token,aClass);
    }
    
    else if (token.is("invariant"))
    {
      analyzeInvariant(token,aClass);
      // unimplemented feature. Issue a warning that it is currently not fully implemented
      // setFailedPosition(token.getPosition(), 9999, token.getName(), token.toString());
    } 
    else if (token.is("beforeCode") || token.is("afterCode"))
    {
    	
      analyzeInjectionCode(token,aClass);
    }
    else if (token.is("key") || token.is("defaultKey")) 
    {
      analyzeKey(token,aClass);
    }
    else if (token.is("extraCode"))
    {
      //This is a catch all and will be used less often as the grammar gets updated.
      if(extraCodeIsMalformedStateMachine(token)) setFailedPosition(token.getPosition(), 1006, "");
      //Append #line comment to indicate line and position of source
      if (token.getPosition() != null)
      {
      	aClass.appendExtraCode("// line " + token.getPosition().getLineNumber() + " " + token.getPosition().getRelativePath(aClass, "Java"));
      	aClass.appendExtraCode("  " + token.getValue());
      }
      else
      {
      	aClass.appendExtraCode(token.getValue());
      }
    }
    else if (token.is("constantDeclaration"))
    {
      analyzeConstant(token,aClass);
    }
    else if (token.is("concreteMethodDeclaration"))
    {
      analyzeMethod(token,aClass);
    }
    else if (token.is("depend"))
    {
      Depend d = new Depend(token.getValue());
      aClass.addDepend(d);
    }
    else if (token.is("inlineAssociation"))
    {
      analyzeinlineAssociation(token,aClass);
    }
    else if (token.is("symmetricReflexiveAssociation"))
    {
      analyzeSymmetricReflexiveAssociation(token,aClass);
    }

    // This essentially "clears" the comments in the list so that new comments, when parsed, will be the ones appearing above
    // classes, methods, attributes, etc (whichever comes next) rather than old comments propogating everywhere.
    if (shouldConsumeComment)
    {
      lastComments.clear();
    }
  }    

  /**
   * Analyzes a comment to determine if it should be added into the list of currently parsed comments waiting to be added to
   * a class, attribute, association, method or otherwise.
   * 
   * Note that this is for an inline comment rather than a multiline comment.
   * 
   * @param token The current token which has been flagged to be a comment to analyze, containing its value.
   */
  @umplesourcefile(line=233,file="UmpleInternalParser_CodeClass.ump",javaline=971,length=15)
  private void analyzeComment(Token token)
  {
    String theValue = "";
    // Special comment directive to force umpleoutput directives to be added
    // In every class
    if (token.getValue().startsWith("@outputumplesource")) { 
      outputUmpleSource = true;
      return;
    }
    if (!token.getValue().equals("$?[End_of_model]$?")) 
    {
      theValue = token.getValue();
      lastComments.add(new Comment(theValue));
    }
  }

  /**
   * Analyzes a comment to determine if it should be added into the list of currently parsed comments waiting to be added to
   * a class, attribute, association, method or otherwise.
   * 
   * Note that this is for a multiline comment, which essentially means the possibility of multiple inline comments (1 per line)
   * that will be concatenated together.
   * 
   * @param token The current token which has been flagged to be a comment to analyze, containing its value.
   */
  @umplesourcefile(line=258,file="UmpleInternalParser_CodeClass.ump",javaline=997,length=19)
  private void analyzeMultilineComment(Token token)
  {
    String inlineComments[] = token.getValue().split("\n");

    // Go through the inline comments and add them to the list of comments waiting to be applied
    String theComment = "";
    for (int i = 0; i < inlineComments.length; i++) 
    {
      theComment = inlineComments[i];
      if(theComment.startsWith("@outputumplesource")) {
        outputUmpleSource = true;
      }
      else {
        Comment comment = new Comment(theComment);
        comment.isInline = false;
        lastComments.add(comment);
      }
    }
  }

  // Link associations, association variables and extends that were "defined" after their use
  @umplesourcefile(line=279,file="UmpleInternalParser_CodeClass.ump",javaline=1019,length=14)
  private void postTokenClassAnalysis()
  {
    if (verifyClassesInUse())
    {
      checkSingletonAssociations();
      addUnlinkedAssociationVariables();
      addUnlinkedAssociations();
      addUnlinkedExtends();
      checkDuplicateAssociationNames();
      checkExtendsForCycles();
      checkSortedAssociations();
      checkClassInterfaceAssocations();
    }
  }

  @umplesourcefile(line=294,file="UmpleInternalParser_CodeClass.ump",javaline=1035,length=5)
  private void postTokenInterfaceAnalysis()
  {
    addUnlinkedInterfaceExtends();
    checkExtendsForCyclesInterface();
  }
  
  @umplesourcefile(line=300,file="UmpleInternalParser_CodeClass.ump",javaline=1042,length=28)
   private List <UmpleInterface> recursiveCycleCheckInterface(List <UmpleInterface> extend,UmpleInterface parent, HashMap<UmpleInterface, Boolean> map)
  {

	  List <UmpleInterface> temp = new ArrayList <UmpleInterface>();

	  if(extend == null|| extend.isEmpty())
		  return temp;


	  for (UmpleInterface I: extend){
		  if(map.containsKey(I)){
			  temp.add(I);
			  return temp;
		  }
		  map.put(I, true);	  

		  for (UmpleInterface EI: I.getExtendsInterface()){
			  if(parent.equals(EI)){
				  temp.add(EI);
				  return temp;
			  }  
		  }
		  List <UmpleInterface> temptemp = recursiveCycleCheckInterface(I.getExtendsInterface(), parent, map);
		  temp.addAll(temptemp);
	  }  

	  return temp;
  }

  @umplesourcefile(line=329,file="UmpleInternalParser_CodeClass.ump",javaline=1072,length=17)
  private void checkExtendsForCyclesInterface()
  {
	  for(UmpleInterface I : model.getUmpleInterfaces())
	  {
		  HashMap<UmpleInterface, Boolean> vistedMap = new HashMap<UmpleInterface, Boolean>();
		  if(I.getExtendsInterface() != null)
		  {
			  if(recursiveCycleCheckInterface(I.getExtendsInterface(), I, vistedMap).contains(I)){
				  Token t = I.getExtendsToken();
		          if(t.getValue().equals(I.getName()))
		            getParseResult().addErrorMessage(new ErrorMessage(11,t.getPosition(),"Interface",I.getName()));
		          else
		            getParseResult().addErrorMessage(new ErrorMessage(12,t.getPosition(),"Interface",t.getValue(),I.getName()));
			  }
		  }
	  }
  }

  @umplesourcefile(line=347,file="UmpleInternalParser_CodeClass.ump",javaline=1091,length=18)
  private UmpleClass recursiveCycleCheck(UmpleClass extend, UmpleClass parent, HashMap<UmpleClass, Boolean> map)
  {
    UmpleClass temp = null;

    if(extend == null)
      return null;

    if(map.containsKey(extend))
      return extend;

    map.put(extend, true);

    if(parent.equals(extend.getExtendsClass()))
      return extend.getExtendsClass();

    temp = recursiveCycleCheck(extend.getExtendsClass(), parent, map);
    return temp;
  }

  @umplesourcefile(line=366,file="UmpleInternalParser_CodeClass.ump",javaline=1111,length=18)
  private void checkExtendsForCycles()
  {
    for(UmpleClass C : model.getUmpleClasses())
    {
      HashMap<UmpleClass, Boolean> vistedMap = new HashMap<UmpleClass, Boolean>();
      if(C.getExtendsClass() != null)
      {
        if(C.equals(recursiveCycleCheck(C.getExtendsClass(), C, vistedMap))) 
        {
          Token t = C.getExtendsToken();
          if(t.getValue().equals(C.getName()))
            getParseResult().addErrorMessage(new ErrorMessage(11,t.getPosition(),"Class",C.getName()));
          else
            getParseResult().addErrorMessage(new ErrorMessage(12,t.getPosition(),"Class",t.getValue(),C.getName()));
        }
      }
    }
  }


  /*
   * Analyzes all associations that are part of the given token indicated to be related to an association.
   * 
   * @param associationToken The token indicated to be an association where sub tokens will be analyzed from to further
   * analyze the individual associations.
   */
  @umplesourcefile(line=392,file="UmpleInternalParser_CodeClass.ump",javaline=1138,length=21)
  private void analyzeAllAssociations(Token associationToken)
  {
    String name = associationToken.getValue("name");
    
    // Go through every token that is a child of the current token (all associations part of this association).
    for(Token token : associationToken.getSubTokens())
    {
      if (token.is("association"))
      {
        // Analyze the individual association.
        Association association = analyzeAssociation(token, "");
        if (!getParseResult().getWasSuccess()) 
        {
          return;
        }
        
        association.setName(name);
        unlinkedAssociations.add(association);
      }
    }
  }  

  /*
   * Analyzes a class token to populate an Umple class.
   * 
   * This is also where the list of currently parsed comments will be added to the Umple class.
   * 
   * @param classToken The token which contains the data to be analyzed to populate an Umple class.
   * 
   * @return An Umple class populated with data based on the analysis of the class token.
   */
  @umplesourcefile(line=423,file="UmpleInternalParser_CodeClass.ump",javaline=1170,length=10)
  private UmpleClass analyzeClass(Token classToken)
  {
    String className = classToken.getValue("name");
    //Check to ensure the name is valid (starts with a letter, and only contains letters, numbers, or underscores
    if (Token.isValidIdentifier(className, "[A-Za-z]") != true) {
      setFailedPosition(classToken.getPosition(), 100, className);
    }
    else if ( className.matches("[a-z].*") ){ // Warn when class name does not start with a capital letter.
      setFailedPosition(classToken.getPosition(), 101, className);
    }
    
    UmpleClass aClass = model.addUmpleClass(classToken.getValue("name"));
    if ( classToken.is("classDefinition") && "external".equals(aClass.getModifier()) )
      aClass.setModifier(""); // Remove the external modifier if a non-external specification of this class is found.

    Position thePosition = classToken.getPosition();

    // Set the original .ump file and line number
    aClass.addPosition(thePosition);

    // Add all the comments in the comment list to the Umple class.
    // But add them before any umplesource special comments
    int regularCommentCountEnd = 0;
    for (Comment c : aClass.getComments()) {
      if(c.getText().startsWith("@umplesource")) break;
      regularCommentCountEnd++;
    }

    for (Comment c : lastComments)
    {
      aClass.addCommentAt(c,regularCommentCountEnd);
      regularCommentCountEnd++;
    }
    
    // Add special position comment at the end if @outputumplesource had been 
    // detected earlier in a comment
    if(outputUmpleSource == true) {  
      aClass.addComment(new Comment("@umplesource " + thePosition.getRelativePath(null,"Java")+" "+thePosition.getLineNumber()));
    }

	// If the "abstract" keyword is parsed, make the Umple class an abstract class.
    if (classToken.getValue("abstract") != null)
    {
    	boolean wasSet = aClass.setIsAbstract(true);
    	
    	// Ensure the value was set.
    	if (wasSet == false)
    	{
    		setFailedPosition(classToken.getPosition(), 0, "Unable to make class abstract!");
    	}
    }

    addExtendsTo(classToken, aClass, unlinkedExtends, unlinkedExtendsTokens);
    
    // If the "singleton" keyword is parsed, make the Umple class a singleton.
    if (classToken.getValue("singleton") != null)
    {
      aClass.setIsSingleton(true);
    }
    if(!"".equals(aClass.getPackageName()) && !currentPackageName.equals(aClass.getPackageName()) && !packageNameUsed){
    	setFailedPosition(classToken.getPosition(), 30, aClass.getName(), currentPackageName);
    	aClass.setPackageName(currentPackageName);		
    }		
    if("".equals(aClass.getPackageName())){
    	aClass.setPackageName(currentPackageName);
	}
	packageNameUsed = true;
    if (aClass.getIsSingleton()) 
    {
      classToken.setName(classToken.getName());	
    }

    if (classToken.getValue("immutable") != null)
    {
      boolean wasSet = aClass.setImmutable();
      if (!wasSet)
      {
        // Future-proofing: currently all paths cause wasSet to be true
        setFailedPosition(classToken.getPosition(), 14, classToken.getName());
      }
    }

    analyzeAllTokens(classToken,aClass);
    return aClass;
  }

  /*
   * Takes an Umple classifier and analyzes a classifier token to add classifiers which extend it.
   * 
   * @param classifierToken The token to be analyzed to add subclasses to the specified Umple classifier.
   * @param aClassifier The Umple classifier for which subclasses will be added.
   */
  @umplesourcefile(line=515,file="UmpleInternalParser_CodeClass.ump",javaline=1263,length=18)
  private void addExtendsTo(Token classifierToken, UmpleClassifier aClassifier, Map <UmpleClassifier,List <String>> unlinkedExtends, Map <UmpleClassifier, List<Token>> unlinkedExtendsTokens)
  //private void addExtendsTo(Token classToken, UmpleClassifier aClassifier)
  {
    List<String> extendsList = new ArrayList<String>();
    List<Token> extendsTokenList = new ArrayList<Token>();

    // Go through all sub-tokens of the class token to add subclasses related to the Umple class.
    for (Token extendsToken : classifierToken.getSubTokens())
    {
      if (extendsToken.getValue("extendsName") != null)
      { 
        extendsList.add(extendsToken.getValue("extendsName"));
        extendsTokenList.add(extendsToken); // With this line we really don't need the above, todo: refactor
        unlinkedExtends.put(aClassifier, extendsList);
        unlinkedExtendsTokens.put(aClassifier, extendsTokenList);
      }  
    }
  }

  

  @umplesourcefile(line=536,file="UmpleInternalParser_CodeClass.ump",javaline=1285,length=10)
  private UmpleClass analyzeExternal(Token externalToken)
  {
    // Check to see if there is an existing class
    UmpleClass existingClass = model.getUmpleClass(externalToken.getValue("name"));
    UmpleClass aClass = analyzeClass(externalToken);
    // Only set the modifier to external if there is not a class defined with the same name
    if ( existingClass == null )
      aClass.setModifier("external");
    return aClass;
  }

  @umplesourcefile(line=547,file="UmpleInternalParser_CodeClass.ump",javaline=1297,length=6)
  private UmpleInterface analyzeExternalInterface(Token externalToken)
  {
    UmpleInterface anInterface = analyzeInterface(externalToken);
    anInterface.setModifier("external");
    return anInterface;
  }

  @umplesourcefile(line=554,file="UmpleInternalParser_CodeClass.ump",javaline=1305,length=10)
  private UmpleInterface analyzeInterface(Token t)
  {
    String interfaceName = t.getValue("name");   
    //Check to ensure the name is valid (starts with a letter, and only contains letters, numbers, or underscores
    if (Token.isValidIdentifier(interfaceName, "[A-Za-z|@]") != true) {
      setFailedPosition(t.getPosition(), 110, interfaceName);
    }
    else if ( interfaceName.matches("[a-z].*") ){ // Warn when interface name doesn't start with a capital letter.
       setFailedPosition(t.getPosition(), 111, interfaceName);
    }
  
    UmpleInterface newInterface = new UmpleInterface(t.getValue("name"));
    model.addUmpleInterface(newInterface);
    if(!"".equals(newInterface.getPackageName()) && !currentPackageName.equals(newInterface.getPackageName()) && !packageNameUsed){
    	setFailedPosition(t.getPosition(), 30, newInterface.getName(), currentPackageName);
    	newInterface.setPackageName(currentPackageName);		
    }		
    if("".equals(newInterface.getPackageName())){
    	newInterface.setPackageName(currentPackageName);
	}
	packageNameUsed = true;
    analyzeInterface(t,newInterface);
    return newInterface;
  }

  @umplesourcefile(line=579,file="UmpleInternalParser_CodeClass.ump",javaline=1331,length=26)
  private void analyzeInterface(Token interfaceToken, UmpleInterface aInterface)
  {
    for(Token token : interfaceToken.getSubTokens())
    {
      if (token.is("depend"))
      {
        Depend d = new Depend(token.getValue());
        aInterface.addDepend(d);
      }
      if (token.is("interfaceMemberDeclaration"))
      {
        analyzeInterfaceMembers(token, aInterface);
      }
      else if (token.is("elementPosition"))
      {
        aInterface.setCoordinates(new Coordinate(token.getIntValue("x"),token.getIntValue("y"), token.getIntValue("width"), token.getIntValue("height")));
      }
      else if (token.is("displayColor"))
      {  // Note: See near clone in UmpleInternalParser_CodeLayout.ump
        String theColor = token.getValue("colorValue");
        if(theColor.startsWith("=")) theColor=theColor.substring(1,theColor.length());
        if(!theColor.startsWith("\"")) theColor= "\""+theColor;
        if(!theColor.endsWith("\"")) theColor= theColor+"\"";         
        aInterface.setDisplayColor(theColor);
      }
    }
  }

  @umplesourcefile(line=607,file="UmpleInternalParser_CodeClass.ump",javaline=1360,length=47)
  private void addUnlinkedInterfaceExtends()
  {  
    for (UmpleClassifier c : unlinkedInterfaceExtends.keySet())
    {
      UmpleInterface child = null; //unlinkedInterfaceExtends guaranteed to contain only UmpleInterfaces
      if (c instanceof UmpleInterface){
    	  child = (UmpleInterface) c;
      }
      List<String> extendsNames = unlinkedInterfaceExtends.get(child);
      List<Token>  extendsToken = unlinkedExtendsTokensInterface.get(child);
      

      if (extendsNames == null)
      {
        continue;
      }
      

      for (int i=0; i < extendsNames.size();i++)
      {
        String extendName= extendsNames.get(i);
        UmpleInterface uInterface=  model.getUmpleInterface(extendName);
        boolean wasSet = child.addExtendsInterface(uInterface);
        
        if (!wasSet)
        {
          Position pos;
          try
          {
            pos = extendsToken.get(i).getPosition();
          }
          catch(Exception e)
          {
            pos = new Position("",0,0,0);
          }
          setFailedPosition(pos, 16, child.getName(), uInterface.getName());
          return;
        }
        try
        {
          child.setExtendsToken(extendsToken.get(i));
        }
        
        catch(Exception e){}
      }
    }
  }  

  @umplesourcefile(line=655,file="UmpleInternalParser_CodeClass.ump",javaline=1409,length=19)
  private void analyzeInterfaceMembers(Token interfaceMemberToken, UmpleInterface aInterface)
  {
    for(Token childToken : interfaceMemberToken.getSubTokens())
    {
      addExtendsTo(interfaceMemberToken, aInterface, unlinkedInterfaceExtends, unlinkedExtendsTokensInterface);
      if(childToken.is("abstractMethodDeclaration"))
      {
        analyzeMethod(childToken, aInterface);   
      }  
      else if (childToken.is("constantDeclaration"))
      {
        analyzeConstant(childToken, aInterface);    
      }
      else if (childToken.is("extraCode"))
      {
        aInterface.appendExtraCode(childToken.getValue("extraCode"));
      }
    }
  }

  @umplesourcefile(line=675,file="UmpleInternalParser_CodeClass.ump",javaline=1430,length=85)
  private void analyzeAssociationClass(Token classToken)
  {
    AssociationClass aClass = model.addAssociationClass(classToken.getValue("name"));
    addExtendsTo(classToken, aClass, unlinkedExtends, unlinkedExtendsTokens);
    analyzeAllTokens(classToken,aClass);

	if(!"".equals(aClass.getPackageName()) && !currentPackageName.equals(aClass.getPackageName()) && !packageNameUsed){
    	setFailedPosition(classToken.getPosition(), 30, aClass.getName(), currentPackageName);
    	aClass.setPackageName(currentPackageName);
    }		
    if("".equals(aClass.getPackageName())){
    	aClass.setPackageName(currentPackageName);
	}
	packageNameUsed = true;
    Token leftAssociationToken = null;
    Token rightAssociationToken = null;

    for(Token token : classToken.getSubTokens())
    {
      if (token.is("singleAssociationEnd"))
      {
        if (leftAssociationToken == null)
        {
          leftAssociationToken = token;
        }
        else
        {
          rightAssociationToken = token;
        }
      }
    }

    if (leftAssociationToken == null || rightAssociationToken == null)
    {
      setFailedPosition(classToken.getPosition(), 8, classToken.getValue("name"));
      return;
    }

    String innerName = null;
    String innerType = aClass.getName();
    String innerModifier = null;
    Multiplicity innerMult = new Multiplicity();
    innerMult.setRange("0","1"); 

    String leftName = leftAssociationToken.getValue("roleName");
    String leftType = leftAssociationToken.getValue("type");
    String leftModifier = leftAssociationToken.getValue("modifier");
    String leftBound = leftAssociationToken.getValue("bound");
    String leftLowerBound = leftAssociationToken.getValue("lowerBound");
    String leftUpperBound = leftAssociationToken.getValue("upperBound");
    Multiplicity leftMult = new Multiplicity();
    leftMult.setBound(leftBound);
    leftMult.setRange(leftLowerBound,leftUpperBound);

    String rightName = rightAssociationToken.getValue("roleName");
    String rightType = rightAssociationToken.getValue("type");
    String rightModifier = rightAssociationToken.getValue("modifier");
    String rightBound = rightAssociationToken.getValue("bound");
    String rightLowerBound = rightAssociationToken.getValue("lowerBound");
    String rightUpperBound = rightAssociationToken.getValue("upperBound");
    Multiplicity rightMult = new Multiplicity();
    rightMult.setBound(rightBound);
    rightMult.setRange(rightLowerBound,rightUpperBound);

    AssociationEnd leftFirstEnd = new AssociationEnd(innerName,innerType,innerModifier,leftType,leftMult);
    AssociationEnd leftSecondEnd = new AssociationEnd(leftName,leftType,leftModifier,innerType,innerMult);

    AssociationEnd rightFirstEnd = new AssociationEnd(innerName,innerType,innerModifier,rightType,rightMult);
    AssociationEnd rightSecondEnd = new AssociationEnd(rightName,rightType,rightModifier,innerType,innerMult);    

    updateAssociationEnds(leftFirstEnd,leftSecondEnd);
    updateAssociationEnds(rightFirstEnd,rightSecondEnd);

    Association leftAssociation = new Association(true,true,leftFirstEnd,leftSecondEnd);
    leftAssociation.setTokenPosition(leftAssociationToken.getPosition());

    Association rightAssociation = new Association(true,true,rightFirstEnd,rightSecondEnd);
    rightAssociation.setTokenPosition(rightAssociationToken.getPosition());

    model.addAssociation(leftAssociation);
    model.addAssociation(rightAssociation);

    unlinkedAssociations.add(leftAssociation);
    unlinkedAssociations.add(rightAssociation);
  }
  
  @umplesourcefile(line=761,file="UmpleInternalParser_CodeClass.ump",javaline=1517,length=18)
   private boolean verifyClassesInUse()
  {
    for(Map.Entry<Position, String> e : positionToClassNameReference.entrySet())
    {
      boolean isAClass = model.getUmpleClass(e.getValue()) != null;
      boolean isAInterface = model.getUmpleInterface(e.getValue()) != null;

      if (!isAClass && !isAInterface) //item referenced not a class or interface
      {
        UmpleClass aClass = model.addUmpleClass(e.getValue());
        aClass.setPackageName(model.getDefaultNamespace());
        setFailedPosition(e.getKey(), 5, e.getValue());
        return false;
      }
    }
    
    return true;
  }
  
  @umplesourcefile(line=780,file="UmpleInternalParser_CodeClass.ump",javaline=1537,length=10)
    private boolean associationIsBetweenClassAndInterface (Association a){
	   AssociationEnd myEnd = a.getEnd(0);
       AssociationEnd yourEnd = a.getEnd(1);
       
       UmpleClass myClass = model.getUmpleClass(myEnd.getClassName());
       UmpleInterface yourClass = model.getUmpleInterface(yourEnd.getClassName());
       
       if (myClass != null && yourClass != null ){ //association is between class and interface
    	   return true;
       }
       
       return false;
      
   }
  


  @umplesourcefile(line=797,file="UmpleInternalParser_CodeClass.ump",javaline=1555,length=38)
  private void addUnlinkedAssociationVariables()
  {
    for (AssociationVariable av : unlinkedAssociationVariables)
    {
       
      UmpleClass aClass = model.getUmpleClass(av.getType());
      UmpleClass bClass = model.getUmpleClass(av.getRelatedAssociation().getType());   
      
      if (aClass == null || bClass == null){ //Association is between Class and Interface
    	  continue;
      }   

      Association assoc = bClass.getAssociation(bClass.indexOfAssociationVariable(av));

      boolean added = aClass.addAssociationVariable(av.getRelatedAssociation());
      if (!added)
      {
        if ((!aClass.isImmutable() && !av.getRelatedAssociation().getIsNavigable()) || (!bClass.isImmutable() && !av.getIsNavigable())) 
        { 
          setFailedPosition(assoc.getTokenPosition(),13);
        }
        else { setFailedPosition(assoc.getTokenPosition(),18); }
        return;
      }

      aClass.addAssociation(assoc);

      if (av.getIsNavigable())
      {
        bClass.addReferencedPackage(aClass.getPackageName());
      }

      if (av.getRelatedAssociation().getIsNavigable())
      {
        aClass.addReferencedPackage(bClass.getPackageName());
      }

    }
  }

  @umplesourcefile(line=837,file="UmpleInternalParser_CodeClass.ump",javaline=1596,length=4)
  private boolean isUmpleClass(String elementName)
  {
    return (model.getUmpleInterface(elementName) != null) ? false: true;
  }

  @umplesourcefile(line=842,file="UmpleInternalParser_CodeClass.ump",javaline=1602,length=51)
  private void addUnlinkedExtends()
  {  
    for (UmpleClassifier c : unlinkedExtends.keySet())
    {
      UmpleClass child = null; // unlinkedExtends guaranteed to contain only UmpleClasses
      if (c instanceof UmpleClass){
    	  child = (UmpleClass) c;
      }	
    
      List<String> extendsNames = unlinkedExtends.get(child);    
      List<Token>  extendsToken = unlinkedExtendsTokens.get(child);

      if (extendsNames == null)
      {
        continue;
      }

      for (int i=0; i < extendsNames.size();i++){
        String extendName= extendsNames.get(i);
        if (isUmpleClass(extendName))
        {
          UmpleClass parent = model.getUmpleClass(extendName);     
          boolean wasSet = child.setExtendsClass(parent);
          if (!wasSet)
          {
            Position pos;
            try
            {
              pos = extendsToken.get(i).getPosition();
            }
            catch(Exception e)
            {
              pos = new Position("",0,0,0);
            }
            setFailedPosition(pos, 16, child.getName(), parent.getName());
            return;
          }
          try
          {
            child.setExtendsToken(extendsToken.get(i));
          }
          catch(Exception e){}
        }
        else {
          UmpleInterface uInterface=  model.getUmpleInterface(extendName);
          child.addParentInterface(uInterface);
          addImplementedMethodsFromInterface(uInterface, child);
        }
      }
    }
  }  

  @umplesourcefile(line=894,file="UmpleInternalParser_CodeClass.ump",javaline=1655,length=16)
  private void addImplementedMethodsFromInterface(UmpleInterface parentInterface, UmpleClass uClass)
  {
    //GET AND SET METHODS CHECK?
    if (parentInterface.hasMethods())
    {
      for (Method aMethod : parentInterface.getMethods())
      {
        boolean shouldAddMethod = verifyIfMethodIsConstructorOrGetSet(uClass, aMethod);
        if (!(uClass.hasMethod(aMethod)) && shouldAddMethod)
        {
          aMethod.setIsImplemented(true);
          uClass.addMethod(aMethod);
        }
      }
    }
  }

  /*
   * Used to determine if a method is a contructor or a getter/setter.
   * 
   * @param uClass The Umple class for which the method is contained.
   * @param aMethod The method which is contained within the Umple class.
   * 
   * @return True if the method is a constructor, getter/setter, false otherwise.
   */
  @umplesourcefile(line=919,file="UmpleInternalParser_CodeClass.ump",javaline=1681,length=34)
  private boolean verifyIfMethodIsConstructorOrGetSet(UmpleClass uClass, Method aMethod)
  {
    String methodName = aMethod.getName();

    // Have to check for short method names.
    if (methodName.length() >= 3)
    {
      //1. Verify if method to be added is a setter or a getter
      String accessorName = methodName.substring(0,3);
      if ((accessorName.equals("get")) || (accessorName.equals("set")))
      {
        String possibleAttributeName =   methodName.substring(3,methodName.length()).toLowerCase();
        Attribute attr = uClass.getAttribute(possibleAttributeName);
        if (attr != null)
        {
          return false;
        }
      }
      //2. Verify if method to be added is a constructor
      if (aMethod.getType().equals("public"))
      {
        uClass.appendExtraCode(aMethod.toString());
        return false;
      }  
      //3. Verify if method from interface is already part of the Class extracode
      String match = "public " + aMethod.getType() + " " + aMethod.getName();    
      if (uClass.getExtraCode().contains(match))
      {
        return false;
      }
    }

    return true;
  }
  
  @umplesourcefile(line=954,file="UmpleInternalParser_CodeClass.ump",javaline=1717,length=109)
  private void checkDuplicateAssociationNames()
  {
    for(UmpleClass C : model.getUmpleClasses())
    {
      // Create the list of attribute names (for issue 272)
      List<String> existingAttributeNames = new ArrayList<String>();
      for (Attribute attr : C.getAttributes())
      {
      	      existingAttributeNames.add(attr.getName());
      }
      
      Boolean roleMatchesClassName, hasMultipleAssocToSameClass;
      List<String> classesWithAssociationsToCurrClass = new ArrayList<String>();
      List<String> roleNameSameAsClassName = new ArrayList<String>();
      List<String> existingNames = new ArrayList<String>();
      List<Association> visitedAssociations = new ArrayList<Association>();
      for(Association assoc : C.getAssociations())
      {  
   		roleMatchesClassName = false;
   		hasMultipleAssocToSameClass = false;
   		
        if (visitedAssociations.contains(assoc))
        {
          continue;
        }
        
      	AssociationEnd firstEnd = assoc.getEnd(0);
      	AssociationEnd secondEnd = assoc.getEnd(1);	
      	
      	Boolean checkFirstEnd = !firstEnd.getClassName().equals(C.getName());
      	Boolean checkSecondEnd = !secondEnd.getClassName().equals(C.getName());
      	Boolean associationIsReflexive = !checkFirstEnd && !checkSecondEnd;
      	
      	//issue 288: firstEnd of association does not indicate current (this) class being analyzed.
      	//If association is NOT reflexive, must check the differing class.  Check if role name
      	//matches class name, but only if it is a user entered role name.  Current class must
      	//also have multiple associations to the same class to cause java compile errors.  
      	if(!associationIsReflexive && C.numberOfAssociations() > 1)
      	{ 
      	  //check the differing class
      	  if(checkFirstEnd)
      	  {
      	    if(roleNameSameAsClassName.contains(firstEnd.getClassName().toLowerCase()))
      	    {
      	      hasMultipleAssocToSameClass = true; 	//flag error 19
      	    }
      	    //is a user-defined role name and rolename matches class name
      	    else if(firstEnd.getRoleName().toLowerCase().equals(firstEnd.getClassName().toLowerCase()) && !firstEnd.getIsDefaultRoleName())
      	    {    
      	      roleNameSameAsClassName.add(firstEnd.getRoleName().toLowerCase());
      	    }
      	    
      	    classesWithAssociationsToCurrClass.add(firstEnd.getClassName());
      	  }
      	  //check the differing class
      	  if(checkSecondEnd)
      	  {
      	    if(roleNameSameAsClassName.contains(secondEnd.getClassName().toLowerCase()))
      	    {
      	      hasMultipleAssocToSameClass = true;	//flag error 19
      	    }
      	    //is a user-defined role name and rolename matches class name
      	    else if(secondEnd.getRoleName().toLowerCase().equals(secondEnd.getClassName().toLowerCase()) && !secondEnd.getIsDefaultRoleName())
      	    {    
      	      roleNameSameAsClassName.add(secondEnd.getRoleName().toLowerCase());
      	    }
      	    
      	    classesWithAssociationsToCurrClass.add(secondEnd.getClassName());    	    
      	  }
      	}

      	// check names on other-class end of associations to other classes
        if ((checkFirstEnd || associationIsReflexive) && assoc.getIsLeftNavigable())
        { 
          if (existingNames.contains(firstEnd.getRoleName()) || hasMultipleAssocToSameClass)
          {
            getParseResult().addErrorMessage(new ErrorMessage(19,assoc.getTokenPosition(),C.getName(),firstEnd.getRoleName()));
          }
          else if (existingAttributeNames.contains(firstEnd.getRoleName()))
          {  // Check if the association name is the same as an attribute name
             getParseResult().addErrorMessage(new ErrorMessage(23,assoc.getTokenPosition(),C.getName(),firstEnd.getRoleName()));
          }
          else
          {
            existingNames.add(firstEnd.getRoleName());
          }
        }
        if ((checkSecondEnd || associationIsReflexive) && assoc.getIsRightNavigable())
        {
          if (existingNames.contains(secondEnd.getRoleName()) || hasMultipleAssocToSameClass)
          {
            getParseResult().addErrorMessage(new ErrorMessage(19,assoc.getTokenPosition(),C.getName(),secondEnd.getRoleName()));
          }
          else if (existingAttributeNames.contains(secondEnd.getRoleName()))
          {  // Check if the association name is the same as an attribute name
             getParseResult().addErrorMessage(new ErrorMessage(23,assoc.getTokenPosition(),C.getName(),secondEnd.getRoleName()));
          }
          else
          {
            existingNames.add(secondEnd.getRoleName());
          }
        }
        
        if (associationIsReflexive)
        { 
          // The UmpleClass is only expected to have duplicate references to reflexive associations
          visitedAssociations.add(assoc);
        }
      }
    }
  }

  @umplesourcefile(line=1066,file="UmpleInternalParser_CodeClass.ump",javaline=1830,length=37)
  private void checkSingletonAssociations() 
  {
    for (Association association : model.getAssociations()) 
    {  
      if (associationIsBetweenClassAndInterface (association)){continue;}	
      
      AssociationEnd myEnd = association.getEnd(0);
      AssociationEnd yourEnd = association.getEnd(1);

      UmpleClass myClass = model.getUmpleClass(myEnd.getClassName());
      UmpleClass yourClass = model.getUmpleClass(yourEnd.getClassName());

      if (myClass.getIsSingleton() && (yourEnd.getMultiplicity().getRangeParts()[0].equals("1") && yourEnd.getMultiplicity().getRangeParts()[1].equals("1"))) 
      {
        yourEnd.getMultiplicity().setRange("0", "1");
        yourEnd.getMultiplicity().setBound(null);
        setFailedPosition(association.getTokenPosition(), 2, association.getName());  
      }

      if (yourClass.getIsSingleton() && (myEnd.getMultiplicity().getRangeParts()[0].equals("1") && myEnd.getMultiplicity().getRangeParts()[1].equals("1"))) 
      {
        myEnd.getMultiplicity().setRange("0", "1");
        myEnd.getMultiplicity().setBound(null);
        setFailedPosition(association.getTokenPosition(), 2, association.getName());
      }

      if(myClass.getIsSingleton() && (myEnd.getMultiplicity().getUpperBound() < 0 || myEnd.getMultiplicity().getUpperBound() > 1)) 
      {
        setFailedPosition(association.getTokenPosition(), 10, myEnd.getClassName());
      }

      if(yourClass.getIsSingleton() && (yourEnd.getMultiplicity().getUpperBound() < 0 || yourEnd.getMultiplicity().getUpperBound() > 1)) 
      {
        setFailedPosition(association.getTokenPosition(), 10, yourEnd.getClassName());
      }
    }
  }

  @umplesourcefile(line=1104,file="UmpleInternalParser_CodeClass.ump",javaline=1869,length=60)
  private void addUnlinkedAssociations()
  {
    for (Association association : unlinkedAssociations)
    {      	 
      if (associationIsBetweenClassAndInterface (association)){continue;}	
      
      AssociationEnd myEnd = association.getEnd(0);
      AssociationEnd yourEnd = association.getEnd(1);

      UmpleClass myClass = model.getUmpleClass(myEnd.getClassName());
      UmpleClass yourClass = model.getUmpleClass(yourEnd.getClassName());
      
      AssociationVariable myAs = new AssociationVariable(myEnd.getRoleName(),myEnd.getClassName(),myEnd.getModifier(),null,myEnd.getMultiplicity(),association.getIsLeftNavigable());
      AssociationVariable yourAs = new AssociationVariable(yourEnd.getRoleName(),yourEnd.getClassName(),yourEnd.getModifier(),null,yourEnd.getMultiplicity(),association.getIsRightNavigable());
      myAs.setRelatedAssociation(yourAs);
      
      if(!"".equals(myEnd.getPriority())) { myAs.setPriority(myEnd.getPriority()); }
      if(!"".equals(yourEnd.getPriority())) { yourAs.setPriority(yourEnd.getPriority()); }
      
      if (association.isImmutable())
      {
        boolean set = myAs.setImmutable();
        if (set) { yourAs.setImmutable(); }
        else
        {
      	  setFailedPosition(association.getTokenPosition(),17);
        }
      }
      
      boolean added = myClass.addAssociationVariable(yourAs);
      if (!added)
      {
      	if (myClass.isImmutable()) { setFailedPosition(association.getTokenPosition(),17); }
      	else { setFailedPosition(association.getTokenPosition(),13); }
        return;
      }
      myClass.addAssociation(association);

      added = yourClass.addAssociationVariable(myAs);
      if (!added)
      {
        if (myClass == yourClass) { setFailedPosition(association.getTokenPosition(),18); }
        else { setFailedPosition(association.getTokenPosition(),13); }
        return;
      }
      
      yourClass.addAssociation(association);

      if (myAs.getIsNavigable())
      {
        yourClass.addReferencedPackage(myClass.getPackageName());
      }

      if (yourAs.getIsNavigable())
      {
        myClass.addReferencedPackage(yourClass.getPackageName());
      }      

    }
  }

  @umplesourcefile(line=1165,file="UmpleInternalParser_CodeClass.ump",javaline=1931,length=95)
  private void checkSortedAssociations()
  {
    for (Association association : model.getAssociations()) 
    {
      if (associationIsBetweenClassAndInterface (association)){continue;}
      AssociationEnd myEnd = association.getEnd(0);
      AssociationEnd yourEnd = association.getEnd(1);

      UmpleClass myClass = model.getUmpleClass(myEnd.getClassName());
      UmpleClass yourClass = model.getUmpleClass(yourEnd.getClassName());
      
      String value;
      
      if(!"".equals(yourEnd.getPriority())){
      	Attribute temp = yourClass.getAttribute(yourEnd.getPriority());
      	if(temp != null)
		{
      	  if(Pattern.matches("Integer|Short|Float|Double|String", temp.getType())) 
		  {
		  	String attributeType = yourClass.getName();
		  	String priorityType = temp.getType();
		  	String sortedName = yourEnd.getPriority().substring(0,1).toUpperCase() + yourEnd.getPriority().substring(1);
      		String php_codeblock = 
      	     "\n      function($x, $y)\n"+
                "      {\n"+
                "        return $x->get"+ sortedName +"() -\n"+ 
                "               $y->get"+ sortedName +"();\n"+
                "      }";  
             String java_codeblock = 
		      "\n      new Comparator<" + attributeType +">(){\n"+
		         "        @Override\n"+
                 "        public int compare("+attributeType+" arg0, "+attributeType+" arg1)\n"+ 
                 "        {\n"+
                 "          return (("+priorityType+")arg0.get"+sortedName+"()).compareTo(\n"+
                 "                 (("+priorityType+")arg1.get"+sortedName+"()));\n"+ 
                 "        }\n"+
                 "      }";    
             
             Attribute priority = new Attribute(yourEnd.getRoleName()+"Priority","Comparator<" + attributeType +">", "", "", false, yourClass);
             CodeBlock cb = new CodeBlock();
             cb.setCode("Php", php_codeblock);
             cb.setCode("Java", java_codeblock);
             cb.setCode("Ruby", "\"\"");
             priority.setCodeblock(cb); 
      		 myClass.addAttribute(priority);
      		 
		  } 
      	  else
      		setFailedPosition(association.getTokenPosition(), 24, yourEnd.getPriority(), myClass.getName());
      	}
		else
		  setFailedPosition(association.getTokenPosition(), 25, yourClass.getName(), yourEnd.getPriority());
      }
      
      if(!"".equals(myEnd.getPriority())){
      	Attribute temp = myClass.getAttribute(myEnd.getPriority());
      	if(temp != null)
		{
      	  if(Pattern.matches("Integer|Short|Float|Double|String", temp.getType()))
      	  {
      	  	String attributeType = myClass.getName() ;
      	  	String priorityType = temp.getType();
      	  	String sortedName = myEnd.getPriority().substring(0,1).toUpperCase() + myEnd.getPriority().substring(1);
      		String php_codeblock = 
      	     "\n      function($x, $y)\n"+
                "      {\n"+
                "        return $x->get"+ sortedName +"() -\n"+ 
                "               $y->get"+ sortedName +"();\n"+
                "      }";  
             String java_codeblock = 
		      "\n      new Comparator<" + attributeType +">(){\n"+
		         "        @Override\n"+
                 "        public int compare("+attributeType+" arg0, "+attributeType +" arg1)\n"+ 
                 "        {\n"+
                 "          return (("+priorityType+")arg0.get"+sortedName+"()).compareTo(\n"+
                 "                 (("+priorityType+")arg1.get"+sortedName+"()));\n"+ 
                 "        }\n"+
                 "      }";    
             
             Attribute priority = new Attribute(myEnd.getRoleName()+"Priority","Comparator<" + attributeType +">", "", "", false, myClass);
             CodeBlock cb = new CodeBlock();
             cb.setCode("Php", php_codeblock);
             cb.setCode("Java", java_codeblock);
             cb.setCode("Ruby", "\"\"");
             priority.setCodeblock(cb); 
      		 yourClass.addAttribute(priority);
		  }  
      	  else
      		setFailedPosition(association.getTokenPosition(), 24, myEnd.getPriority(), yourClass.getName());
		}
		else
		  setFailedPosition(association.getTokenPosition(), 25, myClass.getName(), myEnd.getPriority());
      }
    }
  }
  
  @umplesourcefile(line=1261,file="UmpleInternalParser_CodeClass.ump",javaline=2028,length=10)
   private void checkClassInterfaceAssocations(){
	  for (Association a : model.getAssociations()){
		  if (associationIsBetweenClassAndInterface(a)){
			  boolean hasCorrectArrow = !a.getIsLeftNavigable()&&a.getIsRightNavigable(); // Assocation has "->" arrow
			  if (!hasCorrectArrow){
				  setFailedPosition(a.getTokenPosition(), 20, a.getEnd(0).getClassName());
			  }
		  }
	  }
  }
  
  /*
   * Analyzes a token flagged to be a method in which case the data that makes up the method will be populated into a
   * method instance and added to an Umple element (which could be an Umple class).
   * 
   * @param method The token flagged to be the method.
   * @param uElement The Umple element for which the method will be added.
   */
  @umplesourcefile(line=1279,file="UmpleInternalParser_CodeClass.ump",javaline=2047,length=79)
  private void analyzeMethod(Token method, UmpleElement uElement)
  {
    String modifier = "";
    Method aMethod = new Method("","","",false);

	// Set method position
	aMethod.setPosition(method.getPosition());

    // Add comments above the method to the method.
    for (Comment c : lastComments)
    {
      aMethod.addComment(c);
    }

    // Go through all the sub tokens of the "method token" to obtain details about it, using them to populate a method instance.
    List<String> langs = new ArrayList();
    CodeBlock cb = new CodeBlock();
    for(Token token : method.getSubTokens())
    {
      if (token.is("modifier"))
      {
        modifier += " " + (token.getValue());
        aMethod.setModifier(modifier);
      }
      else if (token.is("type"))
      {
        aMethod.setType(token.getValue());
      }
      else if (token.is("methodDeclarator"))
      {
        analyzeMethodDeclarator(token, aMethod);
      }
      else if (token.is("code"))
      {
      	if(langs.isEmpty())
        {
        	cb.setCode(token.getValue());
        }	
        else
        {
        	for(String str: langs)
        	{
        		cb.setCode(str,token.getValue());
        	}
        	langs.clear();
        }
      }
      else if (token.is("codeLang"))
      {
        langs.add(token.getValue());
      }
      else if (token.is("precondition")){ 
    	  if (uElement instanceof UmpleClass){
    		  analyzePrecondition(token, (UmpleClass) uElement, aMethod);
    	  }  
      }
    }
    MethodBody meth = new MethodBody(cb);
    aMethod.setMethodBody(meth);  

    // Add method to Class or Interface
    if (uElement instanceof UmpleClass)
    {
      UmpleClass uClass = (UmpleClass) uElement;
      boolean shouldAddMethod = verifyIfMethodIsConstructorOrGetSet(uClass, aMethod);
      if (!uClass.hasMethod(aMethod) && shouldAddMethod )
      {
        uClass.addMethod(aMethod); 
      }
    }
    else if (uElement instanceof UmpleInterface)
    {
      UmpleInterface uInterface = (UmpleInterface) uElement;
      if (!uInterface.hasMethod(aMethod))
      {
        uInterface.addMethod(aMethod); 
      }
    }  
  }

  /*
   * Analyzes a method header, from a token flagged to be one, to populate a method instance for things such as the
   * method name, type and parameters.
   * 
   * @param token The token flagged to be a method header.
   * @param aMethod The method to be populated from the analysis of the token.
   */
  @umplesourcefile(line=1366,file="UmpleInternalParser_CodeClass.ump",javaline=2135,length=35)
  private void analyzeMethodDeclarator(Token token, Method aMethod)
  {
    // Go through all sub tokens of the method token to obtain data such as the methods name, parameters etc and add them to the method.
    for(Token methodToken : token.getSubTokens())
    {
      if (methodToken.is("methodName"))
      {
        aMethod.setName(methodToken.getValue());
      }
      if (methodToken.is("parameterList"))
      {
        for(Token parameterToken : methodToken.getSubTokens())
        {
          boolean isList = false;
          if (parameterToken.is("parameter"))
          {
            String paramType="";
            if (parameterToken.getSubToken("type") != null)
            {
              paramType = parameterToken.getSubToken("type").getValue();
            }
            if (parameterToken.getSubToken("list") != null)
            {
              isList = parameterToken.getSubToken("list").getValue() != null;        
            }

            String paramName = parameterToken.getSubToken("name").getValue();
            MethodParameter aParameter  = new MethodParameter(paramName,paramType,null,null, false);
            aParameter.setIsList(isList);
            aMethod.addMethodParameter(aParameter);
          }
        }
      }
    }
  }

  /*
   * Analyzes a constant, from a token flagged to be one, to add a constant instance to an Umple element.
   * 
   * @param constantToken The token flagged to be a constant.
   * @param uElement The Umple element for which a new constant will be added (populated from analysis of the token).
   */
  @umplesourcefile(line=1408,file="UmpleInternalParser_CodeClass.ump",javaline=2178,length=38)
  private void analyzeConstant(Token constantToken, UmpleElement uElement)
  {
    Constant aConstant = new Constant("","","","");
    String modifier = "";

    // Create the Constant Object
    for(Token token : constantToken.getSubTokens())
    {
      if (token.is("modifier"))
      {
        modifier += " " + (token.getSubToken(0).getName());
        aConstant.setModifier(modifier);
      }
      else if (token.is("name"))
      {
        aConstant.setName(token.getValue());
      }
      else  if (token.is("type"))
      {
        aConstant.setType(token.getValue());
      }
      else  if (token.is("value"))
      {
        aConstant.setValue(token.getValue());
      }
    }  
    // Add constant to Class or Interface
    if (uElement instanceof UmpleClass)
    {
      UmpleClass uClass = (UmpleClass) uElement;
      uClass.addConstant(aConstant);
    }
    else if (uElement instanceof UmpleInterface)
    {
      UmpleInterface uInterface = (UmpleInterface) uElement;
      uInterface.addConstant(aConstant);
    }  
  }

  @umplesourcefile(line=1447,file="UmpleInternalParser_CodeClass.ump",javaline=2218,length=9)
  private void analyzeInjectionCode(Token injectToken, UmpleClass aClass)
  {
    String type = injectToken.is("beforeCode") ? "before" : "after";    
  	CodeBlock cb = new CodeBlock();
  	CodeInjection injection = new CodeInjection(type,injectToken.getValue("operationName"),"",aClass);
    makeCodeInject(injectToken,injection,cb,aClass);
    injection.setSnippet(cb);
    aClass.addCodeInjection(injection);    
  }
  
  @umplesourcefile(line=1457,file="UmpleInternalParser_CodeClass.ump",javaline=2229,length=32)
  private void makeCodeInject(Token injectToken,CodeInjection injection, CodeBlock cb, UmpleClass aClass)
  {
  	List<String> langs = new ArrayList<String>();
  	for(Token sub: injectToken.getSubTokens())
    {
    	if(sub.is("codeLang"))
    	{
    		langs.add(sub.getValue());
    	}
    	if(sub.is("code"))
    	{    		  
    		if(langs.size()==0)
    		{
    		  cb.setCode(sub.getValue());
    		}
    		else
    		{
    			for(String lang:langs)
    		    {
    			  cb.setCode(lang,sub.getValue());
    		    }
    		}
    		langs.clear();  		
    	}
    	if(sub.is("codeInject"))
    	{
    		makeCodeInject(sub,injection, cb , aClass);
    	}
    }    
    injection.setPosition(injectToken.getPosition());
    
  }

  @umplesourcefile(line=1490,file="UmpleInternalParser_CodeClass.ump",javaline=2263,length=85)
  private void analyzeKey(Token keyToken, UmpleClass aClass)
  {
    if (aClass.getKey().isProvided())
    {
      setFailedPosition(keyToken.getPosition(), 7, keyToken.getParentToken().getValue("name"));
    }

    if (keyToken.is("defaultKey"))
    {
      aClass.getKey().setIsDefault(true);
      return;
    }

    List<String> tokensAdded = new ArrayList<String>();
    Boolean tokenMatch;
    for(Token token : keyToken.getSubTokens())
    {
      tokenMatch = false;
      
      if (!token.is("keyId"))
      {
        continue;
      }
 
      //Checks for duplicate attributes/associations/stateMachines
      if(tokensAdded.contains(token.getValue()))
      {
        setFailedPosition(keyToken.getPosition(), 26, token.getValue(), keyToken.getParentToken().getValue("name"));
      }
 	
	  if(!aClass.hasAttributes() && !aClass.hasAssociations() && !aClass.hasStateMachines())
      {
        setFailedPosition(keyToken.getPosition(), 27, token.getValue(), keyToken.getParentToken().getValue("name"));
      }
      else{
        if(aClass.hasAttributes())
        {
          for(Attribute aAttribute : aClass.getAttributes())
          {
            if(aAttribute.getName().equals(token.getValue()))
            {
              tokenMatch = true;
            }
	      }
        }
      
        if(aClass.hasAssociations())
	    {
	      AssociationEnd firstEnd, secondEnd;
	      String firstEndName, secondEndName;
  
	      for(Association aAssociation : aClass.getAssociations())
          { 
            firstEnd = aAssociation.getEnd(0);
      	    secondEnd = aAssociation.getEnd(1);
            firstEndName =  firstEnd.getRoleName();
      	    secondEndName = secondEnd.getRoleName();        
                      
            if(firstEndName.equals(token.getValue()) || secondEndName.equals(token.getValue()))
            {
              tokenMatch = true;
            }
	      }
	    }  

        if(aClass.hasStateMachines())  
        {
	      for(StateMachine aStateMachine : aClass.getStateMachines())
	      {
            if(aStateMachine.getName().equals(token.getValue()))
            {
              tokenMatch = true;
            }	    
	      }
	    }  	  

	    if(!tokenMatch)
	    {
	      setFailedPosition(keyToken.getPosition(), 27, token.getValue(), keyToken.getParentToken().getValue("name"));
	    }
	  }
      aClass.getKey().addMember(token.getValue());
      tokensAdded.add(token.getValue()); 
    }
  }

  @umplesourcefile(line=1576,file="UmpleInternalParser_CodeClass.ump",javaline=2350,length=18)
  private void analyzeSymmetricReflexiveAssociation(Token symmetricReflexiveAssociationToken, UmpleClass aClass)
  {
    String myName = symmetricReflexiveAssociationToken.getValue("roleName");
    String myType = aClass.getName();
    String myModifier = "symmetricreflexive";
    String myBound = symmetricReflexiveAssociationToken.getValue("bound");
    String myLowerBound = symmetricReflexiveAssociationToken.getValue("lowerBound");
    String myUpperBound = symmetricReflexiveAssociationToken.getValue("upperBound");
    Multiplicity myMult = new Multiplicity();
    myMult.setBound(myBound);
    myMult.setRange(myLowerBound,myUpperBound);

    AssociationVariable myAs = new AssociationVariable(myName,myType,myModifier,null,myMult,true);
    AssociationVariable yourAs = new AssociationVariable(myName,myType,myModifier,null,myMult,true);

    myAs.setRelatedAssociation(yourAs);
    aClass.addAssociationVariable(yourAs);
  }

  @umplesourcefile(line=1595,file="UmpleInternalParser_CodeClass.ump",javaline=2370,length=7)
  private Association createAssociation(String navigation, AssociationEnd firstEnd, AssociationEnd secondEnd)
  {
    boolean isNavigable = "--".equals(navigation);
    boolean isFirstNavigable = "<-".equals(navigation) || isNavigable;
    boolean isSecondNavigable = "->".equals(navigation) || isNavigable;
    return new Association(isFirstNavigable,isSecondNavigable,firstEnd,secondEnd);
  }

  @umplesourcefile(line=1603,file="UmpleInternalParser_CodeClass.ump",javaline=2379,length=68)
  private Association analyzeAssociation(Token associationToken, String defaultMyType)
  {
  
    int myMultOffset = 0;
    int yourMultOffset = 2;
    Token associationModifier = associationToken.getSubToken("modifier");
    if (associationModifier != null)
    {
      myMultOffset++;
      yourMultOffset++;
    }
  	
    Token myMultToken = associationToken.getSubToken(myMultOffset);

    String navigation = associationToken.getValue("arrow");
    Token yourMultToken = associationToken.getSubToken(yourMultOffset);

    String myName = myMultToken.getValue("roleName");
    String myType = myMultToken.getValue("type") == null ? defaultMyType : myMultToken.getValue("type");

    String myModifier = myMultToken.getValue("modifier");
    String myBound = myMultToken.getValue("bound");
    String myLowerBound = myMultToken.getValue("lowerBound");
    String myUpperBound = myMultToken.getValue("upperBound");
    String myPriority = myMultToken.getValue("priority");
    
    Multiplicity myMult = new Multiplicity(); 
    myMult.setBound(myBound);
    myMult.setRange(myLowerBound,myUpperBound);

    // Report an error if the multiplicity is invalid
    if (!myMult.isValid())
    {    	    	
      String invalidBound = myBound == null ? invalidBound = myLowerBound + ".." + myUpperBound : myBound;
      setFailedPosition(myMultToken.getPosition(), 4, invalidBound);
      return null;
    }

    String yourName = yourMultToken.getValue("roleName");
    String yourType = yourMultToken.getValue("type");
    String yourModifier = yourMultToken.getValue("modifier");
    String yourBound = yourMultToken.getValue("bound");
    String yourLowerBound = yourMultToken.getValue("lowerBound");
    String yourUpperBound = yourMultToken.getValue("upperBound");
	String yourPriority = yourMultToken.getValue("priority");
	
    Multiplicity yourMult = new Multiplicity();
    yourMult.setBound(yourBound);
    yourMult.setRange(yourLowerBound,yourUpperBound);

    // Report an error if the multiplicity is invalid
    if (!yourMult.isValid())
    {    	    	
      String invalidBound = yourBound == null ? invalidBound = yourLowerBound + ".." + yourUpperBound : yourBound;
      setFailedPosition(yourMultToken.getPosition(), 4, invalidBound);
      return null;
    }

    // Trap cases where both ends are the same class (reflexive) and 
    // there is no or same role name and same multiplicity. Fixes issue 295
    if(myType.equals(yourType) && ((myName == null && yourName == null) || (myName != null && yourName != null && myName.equals(yourName))) && myMult.toString().equals(yourMult.toString())) {
      setFailedPosition(associationToken.getPosition(), 21, myType);
      return null;
    }    
    /* DEBUG block
    if(true) {
      setFailedPosition(associationToken.getPosition(), 8005, "1"+myType+" 2"+yourType+" 3"+myName+" 4"+yourName+" 5"+myMult+"  6"+yourMult);
    } */

    AssociationEnd firstEnd = new AssociationEnd(myName,myType,myModifier,yourType,myMult);
    AssociationEnd secondEnd = new AssociationEnd(yourName,yourType,yourModifier,myType,yourMult);
    updateAssociationEnds(firstEnd,secondEnd);

	if(myPriority != null)	{ firstEnd.setPriority(myPriority);	}
	if(yourPriority != null) { secondEnd.setPriority(yourPriority); }

    Association association = createAssociation(navigation,firstEnd,secondEnd);
    
    if (associationModifier != null && "immutable".equals(associationModifier.getValue()))
    {
      association.setImmutable();
    }

    association.setTokenPosition(associationToken.getPosition());
    if (!association.isValid())
    {
      Token atFaultToken = association.whoIsInvalid() == 0 ? myMultToken : yourMultToken;
      String invalidBound = atFaultToken.getValue("bound") == null ? invalidBound = atFaultToken.getValue("lowerBound") + ".." + atFaultToken.getValue("upperBound") : atFaultToken.getValue("bound");
      setFailedPosition(atFaultToken.getPosition(), 9, invalidBound);
      return null;
    }

    positionToClassNameReference.put(myMultToken.getPosition("type"),myType);
    positionToClassNameReference.put(yourMultToken.getPosition("type"),yourType);
    model.addAssociation(association);
    return association;
  }

  @umplesourcefile(line=1701,file="UmpleInternalParser_CodeClass.ump",javaline=2478,length=19)
  private void updateAssociationEnds(AssociationEnd firstEnd, AssociationEnd secondEnd)
  {

    if (firstEnd.getRoleName().length() == 0)
    { 
      String rawName = StringFormatter.toCamelCase(firstEnd.getClassName());
      String name = firstEnd.getMultiplicity().isMany() ? model.getGlossary().getPlural(rawName) : rawName;
      firstEnd.setRoleName(name);
      firstEnd.setIsDefaultRoleName(true);
    }

    if (secondEnd.getRoleName().length() == 0)
    {
      String rawName = StringFormatter.toCamelCase(secondEnd.getClassName());
      String name = secondEnd.getMultiplicity().isMany() ? model.getGlossary().getPlural(rawName) : rawName;
      secondEnd.setRoleName(name);
      secondEnd.setIsDefaultRoleName(true);
    }
  }

  /*
   * Analyzes a token flagged to be an association within an Umple class to create an instance of one and add it to the class.
   * 
   * @param inlineAssociationToken The token flagged to be an inline association.
   * @param aClass The Umple class for which an association instance will be added (populated from analysis of the token).
   */
  @umplesourcefile(line=1727,file="UmpleInternalParser_CodeClass.ump",javaline=2505,length=51)
  private void analyzeinlineAssociation(Token inlineAssociationToken, UmpleClass aClass)
  {
    Association association = analyzeAssociation(inlineAssociationToken,aClass.getName());

    if (!getParseResult().getWasSuccess())
    {
      return;
    }

    AssociationEnd myEnd = association.getEnd(0);
    AssociationEnd yourEnd = association.getEnd(1);

    AssociationVariable myAs = new AssociationVariable(myEnd.getRoleName(),myEnd.getClassName(),myEnd.getModifier(),null,myEnd.getMultiplicity(),association.getIsLeftNavigable());
    AssociationVariable yourAs = new AssociationVariable(yourEnd.getRoleName(),yourEnd.getClassName(),yourEnd.getModifier(),null,yourEnd.getMultiplicity(),association.getIsRightNavigable());
    myAs.setRelatedAssociation(yourAs);
    
    if(!"".equals(myEnd.getPriority())) { myAs.setPriority(myEnd.getPriority()); }
    if(!"".equals(yourEnd.getPriority())) { yourAs.setPriority(yourEnd.getPriority()); }
    
    if (association.isImmutable())
    {
      boolean set = myAs.setImmutable();
      if (set)
      {
        yourAs.setImmutable();
      }
      else
      {
        setFailedPosition(inlineAssociationToken.getPosition(),17);
      }
    }

    // Add comments above the association to the association.
    for (Comment c : lastComments)
    {
      yourAs.addComment(c);
    }

    boolean added = aClass.addAssociationVariable(yourAs);
    if (added)
    {
      unlinkedAssociationVariables.add(yourAs);
      aClass.addAssociation(association);
    }
    else
    {
      if (aClass.isImmutable()) { setFailedPosition(inlineAssociationToken.getPosition(),17); }
      
      else { setFailedPosition(inlineAssociationToken.getPosition(),13); }
    }
  }

  /*
   * Analyzes a token flagged to be an attribute within an Umple class to create an instance of one and add it to the class.
   * 
   * @param attributeToken The token flagged to be an attribute.
   * @param aClass The Umple class for which an attribute instance will be added (populated from analysis of the token).
   */
  @umplesourcefile(line=1785,file="UmpleInternalParser_CodeClass.ump",javaline=2564,length=125)
  private void analyzeAttribute(Token attributeToken, UmpleClass aClass)
  {
    boolean isAutounique = attributeToken.getValue("autounique") != null;
    boolean isUnique = attributeToken.getValue("unique") != null;
    boolean isLazy = attributeToken.getValue("lazy") != null;
    boolean validName = Token.isValidIdentifier(attributeToken.getValue("name"));
    boolean properName = !Token.isValidIdentifier(attributeToken.getValue("name"), "[A-Z]");
    boolean looksLikeAssociation = attributeToken.getValue("name").contains("--") || attributeToken.getValue("name").contains("->");
    looksLikeAssociation = looksLikeAssociation || attributeToken.getValue("name").contains("<-") || attributeToken.getValue("name").contains("..");
    looksLikeAssociation = looksLikeAssociation || attributeToken.getValue("name").contains("*");
    
    if(!validName)
    {
    	if(looksLikeAssociation){
    		setFailedPosition(attributeToken.getPosition(), 132, attributeToken.getValue("name"));
    	} else {
    		setFailedPosition(attributeToken.getPosition(), 130, attributeToken.getValue("name"));
    	}
    	
    	return;
    }
    
    if(!properName){
    	setFailedPosition(attributeToken.getPosition(), 131, attributeToken.getValue("name"));
    }
    
    if (aClass.getIsSingleton() && !isLazy) 
    {
      isLazy = true;
      setFailedPosition(attributeToken.getPosition(), 1, attributeToken.getValue("name"));
    }

    String modifier = attributeToken.getValue("modifier");
    String type = attributeToken.getValue("type");
    String name = attributeToken.getValue("name");
    String value = attributeToken.getValue("value");
    String derivedValue = attributeToken.getValue("code");

	if (isLazyRedundant(isLazy, value))
	{
		setFailedPosition(attributeToken.getPosition(), 3, aClass.getName(), name);
	}

    for(Attribute aAttribute : aClass.getAttributes()){
      if (aAttribute.getName().equals(name)){
        setFailedPosition(attributeToken.getPosition(), 22, aClass.getName(), name);
      }
	}
    CodeBlock languageSpecificCode = new CodeBlock();
    if (derivedValue != null)
    {
      value = "";
      List<String> codelangs = new ArrayList<String>();
      for(Token tkn: attributeToken.getSubTokens())
      {
    	if(tkn.is("codeLang"))
    	{
    		codelangs.add(tkn.getValue());
    	} else if(tkn.is("code")) {
    		if(codelangs.isEmpty())
    		{
    			languageSpecificCode.setCode(tkn.getValue());
    		} else {
    			for(String lang: codelangs)
    			{
    				languageSpecificCode.setCode(lang, tkn.getValue());
    			}
    			codelangs.clear();
    		}
    	}
      }
    }

    if ("defaulted".equals(modifier) && value == null)
    {
      setFailedPosition(attributeToken.getPosition(), 6, attributeToken.getValue("name"));
      return;
    }

    if (isUnique)
    {
      UniqueIdentifier uniqueIdentifier = new UniqueIdentifier(name,type,modifier,value);
      aClass.setUniqueIdentifier(uniqueIdentifier);
      return;
    }

    if (isAutounique)
    {
      type = "Integer";
    }

    if (type == null)
    {
      type = "String";
    } else {    	
    	if(!Pattern.matches("([a-z]|[A-Z]|_)(\\d|\\w|<|>|,)*", type)) {
    		setFailedPosition(attributeToken.getPosition(), 140, type);
        	return;
        }
    }

    Attribute attribute = new Attribute(name,type,modifier,value,isAutounique,aClass);
    attribute.setIsLazy(isLazy);
    boolean isList = attributeToken.getValue("list") != null;

    if (name == null)
    {
      String rawName = StringFormatter.toCamelCase(type); 
      name = isList ? model.getGlossary().getPlural(rawName) : rawName;
    }

    if (derivedValue != null)
    {
      attribute.setIsDerived(true);
      attribute.setCodeblock(languageSpecificCode);
    }

    attribute.setIsList(isList);

    // Add comments above the attribute to the attribute.
    for (Comment c : lastComments)
    {
      attribute.addComment(c);
    }
  }
  
  @umplesourcefile(line=1911,file="UmpleInternalParser_CodeClass.ump",javaline=2691,length=4)
  private Boolean isLazyRedundant(Boolean isLazy, String value)
  {
    return (isLazy && value != null);
  }
// line 20 ../../../../src/UmpleInternalParser_CodeConstraints.ump
  @umplesourcefile(line=20,file="UmpleInternalParser_CodeConstraints.ump",javaline=2697,length=11)
  private void analyzePrecondition (Token preconditionToken, UmpleClass aClass, Method method) 
  {
    List <ConstraintVariable> cvs = analyzeConstraint(preconditionToken, aClass); //adds all identifiers to constraints
    Precondition precondition = new Precondition(method);
    
    for(ConstraintVariable cv: cvs)
    {
    	precondition.addExpression(cv);
    }  
    aClass.addPrecondition(precondition);
  }
    
  /*
   * Analyzes a token recognized as a constraint
   * 
   * @param invariantToken The token containting the constraintsub.
   * @param aClass The Umple class for which an attribute is being constrained.
   */
  @umplesourcefile(line=38,file="UmpleInternalParser_CodeConstraints.ump",javaline=2716,length=11)
  private void analyzeInvariant (Token invariantToken, UmpleClass aClass)
  {
    List <ConstraintVariable> cvs = analyzeConstraint(invariantToken, aClass); //adds all identifiers to constraints
    Constraint constraint = new Constraint();
    
    for(ConstraintVariable cv: cvs)
    {
    	constraint.addExpression(cv);
    }  
    aClass.addConstraint(constraint);
  }
  
   //This recursive function parses the expression. It's very broken down to allow new features to be added easily.
  @umplesourcefile(line=51,file="UmpleInternalParser_CodeConstraints.ump",javaline=2730,length=46)
  private List<ConstraintVariable> analyzeConstraint(Token invariantToken, UmpleClass aClass)
  {
    List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
    List<Token> subs = invariantToken.getSubTokens();
    for (Token t : subs)
    {
      if (t.is(")") || t.is("("))
      {
        rawLine.add(new ConstraintVariable("SYNTAX",t.getName()));
      }
      if (!t.getValue().equals("STATIC"))
      { 
        if (t.is("genExpr"))
        {
          rawLine.addAll(analyzeGeneralConstraintExpression(t,aClass));  
        }        
        else if (t.is("boolExpr"))
        {
          rawLine.addAll(analyzeBooleanConstraintExpression(t,aClass));
        } 
        else if (t.is("stringExpr"))
        {
          rawLine.addAll(analyzeStringConstraintExpression(t,aClass));
        }
        else if (t.is("numExpr"))
        {
          rawLine.addAll(analyzeNumberConstraintExpression(t,aClass));
        }
        else if(t.is("loneBoolean"))
        {
          rawLine.add(analyzeConstraintName(t,aClass,false,true,"boolean"));
        }
        else if (t.is("linkingOp")){
        	rawLine.addAll(analyzeLinkingOpExpression(t,aClass));
        }
        else if (t.is("negativeConstraint")){
        	rawLine.addAll(analyzeNegativeConstraint(t, aClass));
        }
        else
        {
          rawLine.addAll(analyzeConstraint(t,aClass));
        }
      } 
    }
  return rawLine;
  }
  
  @umplesourcefile(line=98,file="UmpleInternalParser_CodeConstraints.ump",javaline=2778,length=23)
  private List <ConstraintVariable> analyzeNegativeConstraint(Token negativeConstraintToken, UmpleClass aClass){
	  List<Token> negativeConstraintSubtokens = negativeConstraintToken.getSubTokens();
	  List <Token> subtokensCopy = new ArrayList <Token> ();
	  subtokensCopy.addAll(negativeConstraintSubtokens);
	  
	  List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
	 
	  rawLine.add(new ConstraintVariable("OPERATOR","!"));
	  rawLine.add(new ConstraintVariable("SYNTAX","("));
	 
	  for (Token s :subtokensCopy){
		   String name = s.getName();
	      	if (name.equals("!")|name.equals("not")|name.equals("~")){
	      		negativeConstraintToken.removeSubToken(s);	
	      	}
	      }
	  
	  rawLine.addAll(analyzeConstraint(negativeConstraintToken, aClass)); 
	  
	  rawLine.add(new ConstraintVariable("SYNTAX",")"));

	  return rawLine; //rawLine = !(constraintBodystuff)
  }
  
  @umplesourcefile(line=122,file="UmpleInternalParser_CodeConstraints.ump",javaline=2803,length=25)
  private List<ConstraintVariable> analyzeLinkingOpExpression(Token linkingOpExpressionToken , UmpleClass aClass)
  {
	  List<Token> LinkingOpExpressionSubtokens = linkingOpExpressionToken.getSubTokens();
	  List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
	  
	  Token subOp = LinkingOpExpressionSubtokens.get(0); //the linking Expr (ie) &&, ||
	  
	
	  if (subOp.is("||")||subOp.is("orOp"))
	  {
		  rawLine.add(new ConstraintVariable("OPERATOR","||"));
	  } 
	  else if (subOp.is("andOp"))
	  {
		  rawLine.add(new ConstraintVariable("OPERATOR","&&"));
	  }
	  
	  Token subExpr = LinkingOpExpressionSubtokens.get(1); //the constraintExpr token
	  Token dummyToken = new Token ("dummyToken", null); //need to encapsulate constrainExpr in dummy token
	  dummyToken.addSubToken(subExpr);
	  rawLine.addAll(analyzeConstraint(dummyToken, aClass)); 

	  return rawLine;

  }
  @umplesourcefile(line=147,file="UmpleInternalParser_CodeConstraints.ump",javaline=2829,length=34)
  private List<ConstraintVariable> analyzeGeneralConstraintExpression(Token generalExpressionToken, UmpleClass aClass)
  {
	List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
	List<Token> generalExpressionSubtokens = generalExpressionToken.getSubTokens();
    for(Token sub : generalExpressionSubtokens)
    {
      if (sub.getValue().equals("STATIC"))
      {
        if(!sub.is("}")&&!sub.is("{")&&!sub.is("[")&&!sub.is("]")&&!sub.is("\'")&&!sub.is("\""))
          rawLine.add(new ConstraintVariable("SYNTAX",sub.getName()));
      } 
      else if(sub.is("name"))
      {
        rawLine.add(analyzeConstraintName(sub,aClass,true,true));
      } 
      else if(sub.is("tail"))
      {
        rawLine.add(analyzeConstraintName(sub,aClass,true,false));
      } 
      else if(sub.is("index"))
      {
        analyzeConstraintIndex(sub,rawLine.get(rawLine.size()-2));
      }
      else if(sub.is("equalsOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","=="));
      }
      else if(sub.is("notequalsOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","!="));
      }
    }
    return rawLine;
  }
  
  @umplesourcefile(line=182,file="UmpleInternalParser_CodeConstraints.ump",javaline=2865,length=38)
  private List<ConstraintVariable> analyzeBooleanConstraintExpression(Token booleanExpressionToken , UmpleClass aClass)
  {
  	List<Token> BooleanExpressionSubtokens = booleanExpressionToken.getSubTokens();
  	List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
  	for(Token sub : BooleanExpressionSubtokens)
    {
      if (sub.getValue().equals("STATIC"))
      {
        if(!sub.is("}")&&!sub.is("{")&&!sub.is("[")&&!sub.is("]")&&!sub.is("\'")&&!sub.is("\""))
          rawLine.add(new ConstraintVariable("SYNTAX",sub.getName()));
      }
      else if (sub.getValue().equals("true"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","true"));
      } 
      else if (sub.getValue().equals("false"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","false"));
      }
      else if(sub.is("name"))
      {
        rawLine.add(analyzeConstraintName(sub,aClass,false,true,"boolean"));
      }
      else if(sub.is("index"))
      {
        analyzeConstraintIndex(sub,rawLine.get(rawLine.size()-2));
      }
      else if(sub.is("equalsOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","=="));
      }
      else if(sub.is("notequalsOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","!="));
      }           
    }
    return rawLine;
  }
  
  @umplesourcefile(line=221,file="UmpleInternalParser_CodeConstraints.ump",javaline=2905,length=34)
  private List<ConstraintVariable> analyzeStringConstraintExpression(Token stringExpressionToken , UmpleClass aClass)
  {
  	List<Token> stringExpressionSubtokens = stringExpressionToken.getSubTokens();
  	List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
  	for(Token sub : stringExpressionSubtokens)
    {
      if (sub.getValue().equals("STATIC"))
      {
        if(!sub.is("}")&&!sub.is("{")&&!sub.is("[")&&!sub.is("]")&&!sub.is("\'")&&!sub.is("\""))
          rawLine.add(new ConstraintVariable("SYNTAX",sub.getName()));
      }
      else if (sub.is("quote"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","\""+sub.getValue()+"\""));
      }
      else if(sub.is("name"))
      {
        rawLine.add(analyzeConstraintName(sub,aClass,false,true,"string"));
      }
      else if(sub.is("index"))
      {
        analyzeConstraintIndex(sub,rawLine.get(rawLine.size()-2));
      }
      else if(sub.is("equalsOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","=="));
      }
      else if(sub.is("notequalsOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","!="));
      }           
    }
    return rawLine;
  }
  
  @umplesourcefile(line=256,file="UmpleInternalParser_CodeConstraints.ump",javaline=2941,length=42)
  private List<ConstraintVariable> analyzeNumberConstraintExpression(Token numberExpressionToken, UmpleClass aClass)
  {
	List<ConstraintVariable> rawLine = new ArrayList<ConstraintVariable>();
	List<Token> numberExpressionSubtokens = numberExpressionToken.getSubTokens();
    for(Token sub : numberExpressionSubtokens)
    {
      if (sub.getValue().equals("STATIC"))
      {
      	if(!sub.is("}")&&!sub.is("{")&&!sub.is("[")&&!sub.is("]")&&!sub.is("\'")&&!sub.is("\""))
          rawLine.add(new ConstraintVariable("SYNTAX",sub.getName()));
      } 
      else if(sub.is("name"))
      {
        rawLine.add(analyzeConstraintName(sub,aClass,true,true,"integer","float","double"));
      } 
      else if(sub.is("tail"))
      {
        rawLine.add(analyzeConstraintName(sub,aClass,true,false));
      } 
      else if(sub.is("index"))
      {
        analyzeConstraintIndex(sub,rawLine.get(rawLine.size()-2));
      }
      else if(sub.is("moreOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR",">"));
      }
      else if(sub.is("smallerOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","<"));
      }
      else if(sub.is("greaterOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR",">="));
      }
      else if(sub.is("lessOp"))
      {
        rawLine.add(new ConstraintVariable("OPERATOR","<="));
      }
    }
    return rawLine;
  }
  
  @umplesourcefile(line=299,file="UmpleInternalParser_CodeConstraints.ump",javaline=2985,length=79)
   private ConstraintVariable analyzeConstraintName(Token nameToken, UmpleClass aClass, boolean canBeInteger, boolean mustBeInClass, String... type)
  {
  	Token sub = nameToken;
  	Token grandparentToken = sub.getParentToken().getParentToken();
  	ConstraintVariable cv = new ConstraintVariable("",sub.getValue());
  	UmpleVariable attribute;
    if(mustBeInClass)
    {
      attribute = cv.getAttribute(aClass);
      if(attribute!=null)
      {
      	if(type!=null&&type.length!=0)
      	{
      	  boolean isType = false;
      	  String typesFailed = "";
      	  for(String t: type)
      	  {
      	    if(attribute.getType().toLowerCase().equals(t))
            {
              isType = true;
            }          
            else
            {
            	typesFailed += t+",";
            }
          }
          if(!isType)
          {
          	setFailedPosition(sub.getPosition(), 29, sub.getValue(), typesFailed);
          }
        }
        cv.setType(attribute.getType());
      }
      
      else if (grandparentToken.getName().equals("precondition")){ //if it was a precondition, check if it matches any of the method arguments
    	  Token concreteMethod = grandparentToken.getParentToken();
    	  ArrayList <MethodParameter> methodparams = new ArrayList <MethodParameter>();
    	  for (Token t : concreteMethod.getSubTokens()){
    		  if (t.getName().equals("methodDeclarator")){
    			  for (Token st : t.getSubTokens()){
    				  if (st.getName().equals("parameterList")){
    					  for (Token sst : st.getSubTokens()){
    						   if(sst.getName().equals("parameter")){
    							   String paramName = null;
    							   String paramType = null;
    							   for (Token ssst: sst.getSubTokens()){
    								   if (ssst.getName().equals("type")){
    									   paramType = ssst.getValue();
    								   }
    								   if (ssst.getName().equals("name")){
    									   paramName = ssst.getValue();
    								   }
    							   }
    							   MethodParameter mp = new MethodParameter(paramName, paramType, null, null, false);
    							   methodparams.add(mp);
    						   }
    					  }
    				  }
    			  }
    		  }
    	  }
    	  boolean matchesAnyMethodParams = false;
    	  for (MethodParameter mp : methodparams){ 
    		  if (mp.getName().equals(sub.getValue())){
    			  matchesAnyMethodParams = true; 
    			  cv.setType(mp.getType());
    		  }
    	  }

    	  if (!matchesAnyMethodParams){
    		  analyzeConstraintName_CanBeInteger(canBeInteger, sub, cv);
    	  } 
      }
      
      else
      {
    	analyzeConstraintName_CanBeInteger(canBeInteger, sub, cv);
      }
    }
    else
    {
      if(canBeInteger)
      {
        try {
      	  Integer.parseInt(sub.getValue());
            cv.setType("OPERATOR");
          } catch (NumberFormatException e) {
            setFailedPosition(sub.getPosition(), 28, sub.getValue(), sub.getName());
          } 
      }
      else
      {
      	cv.setType("OPERATOR");      
      }      
    }    
    return cv;
  }
  @umplesourcefile(line=396,file="UmpleInternalParser_CodeConstraints.ump",javaline=3083,length=15)
  private void analyzeConstraintName_CanBeInteger(boolean canBeInteger, Token sub, ConstraintVariable cv){
	  if(canBeInteger)
      {
        try {
    	    Integer.parseInt(sub.getValue());
            cv.setType("OPERATOR");
          } catch (NumberFormatException e) {
            setFailedPosition(sub.getPosition(), 28, sub.getValue(), sub.getName());
          } 
      }
      else
      {
  	  setFailedPosition(sub.getPosition(), 28, sub.getValue(), sub.getName());
  	}
  }
  @umplesourcefile(line=411,file="UmpleInternalParser_CodeConstraints.ump",javaline=3099,length=18)
  private void analyzeConstraintIndex(Token indexToken, ConstraintVariable cv)
  {
  	Token sub = indexToken;
  	try {
      int i = Integer.parseInt(sub.getValue());
      if(cv.getIsAssociation())
      {
        cv.setIndex(i);
      }
      else
      {
        setFailedPosition(sub.getPosition(), 29, cv.getValue(), "association or list");
      }
    } catch (NumberFormatException e) {
      setFailedPosition(sub.getPosition(), 29, sub.getValue(), "integer");
    } 
  	   
  }
// line 25 ../../../../src/UmpleInternalParser_CodeStateMachine.ump
  private static int numberOfActiveObjects = 1;
  
  //Check if "extra code" is likely a malformed state machine
  @umplesourcefile(line=28,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3122,length=1277)
  private boolean extraCodeIsMalformedStateMachine(Token extraCodeToken){
    String code = extraCodeToken.getValue();
    String[] parts = code.split("\\{");
    if(parts.length < 2) return false; //This means there are no opening brackets
    if(!Token.isValidIdentifier(parts[0].trim())) return false; //This means we don't have an identifier. If the SM name is misspelt, that should be picked up elsewhere.
    return true; //We have an identifier followe by a { followe by something else that is probably a state machine
  }
  
  // Analyze state machine related tokens
  @umplesourcefile(line=37,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3132,length=12)
  private void analyzeStateMachineToken(Token token, int analysisStep)
  {
    if (analysisStep != 1)
    {
      return;
    }
  
    if (token.is("stateMachineDefinition"))
    {
      analyzeStateMachineDefinition(token);
    }
  }  
  
  @umplesourcefile(line=50,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3146,length=10)
  private String getThreadName(Token activeToken)
  {
    Token name = activeToken.getSubToken("name");
    
    if (name == null)
    {
      return "thread1";
    }
    return name.getValue();
  }
  
  @umplesourcefile(line=61,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3158,length=6)
  private String getActiveCode(Token activeToken)
  {
    Token code = activeToken.getSubToken("code");
    
    return code.getValue();
  }
  
  @umplesourcefile(line=68,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3166,length=12)
  private void analyzeActiveObject(Token activeToken, UmpleClass aClass)
  {
    analyzeStateMachine(generateActiveStateMachineToken(activeToken), aClass);
    if (numberOfActiveObjects < numberOfActiveObjectsInClass(activeToken.getParentToken(), aClass))
    {
      numberOfActiveObjects++;
    }
    else
    {
      numberOfActiveObjects = 1;
    }
  }
  
  @umplesourcefile(line=81,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3180,length=10)
  private Token generateActiveStateMachineToken(Token stateMachineToken)
  {
    Token token = new Token("name", "stateMachine" + numberOfActiveObjects);
    
    token.addSubToken(new Token("{", "STATIC"));
    token.addSubToken(generateActiveTopLevelStateToken(stateMachineToken));
    token.addSubToken(new Token("}", "STATIC"));
    
    return token;
  }
  
  @umplesourcefile(line=92,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3192,length=11)
  private Token generateActiveTopLevelStateToken(Token stateMachineToken)
  {
    Token token = new Token("state", "START_TOKEN");
    
    token.addSubToken(new Token("stateName", "topLevel"));
    token.addSubToken(new Token("{", "STATIC"));
    token.addSubToken(generateActiveStateToken(stateMachineToken));
    token.addSubToken(new Token("}", "STATIC"));
    
    return token;
  }
  
  @umplesourcefile(line=104,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3205,length=11)
  private Token generateActiveStateToken(Token stateMachineToken)
  {
    Token token = new Token("state", "START_TOKEN");
    
    token.addSubToken(new Token("stateName", getThreadName(stateMachineToken)));
    token.addSubToken(new Token("{", "STATIC"));
    token.addSubToken(generateActivityToken(stateMachineToken));
    token.addSubToken(new Token("}", "STATIC"));
    
    return token;
  }
  
  @umplesourcefile(line=116,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3218,length=11)
  private Token generateActivityToken(Token stateMachineToken)
  {
    Token token = new Token("activity", "START_TOKEN");
    
    token.addSubToken(new Token("do", "STATIC"));
    token.addSubToken(new Token("{", "STATIC"));
    token.addSubToken(new Token("code", getActiveCode(stateMachineToken)));
    token.addSubToken(new Token("}", "STATIC"));
    
    return token;
  }
  

  // Analyze state machine related tokens within the context of an Umple class
  @umplesourcefile(line=130,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3233,length=32)
  private void analyzeStateMachineToken(Token token, UmpleClass aClass, int analysisStep)
  {
    if (analysisStep != 1)
    {
      return;
    }
    
    if (token.is("stateMachine"))
    {
      if (aClass.isImmutable())
      {
    	  setFailedPosition(token.getPosition(), 15, aClass.getName());
    	  return;
      }
      
      Token subToken = token.getSubToken(0);
      if (subToken.is("activeDefinition"))
      {
        analyzeActiveObject(subToken, aClass);
      }
      
      if (subToken.is("enum") || subToken.is("inlineStateMachine"))
      {
        analyzeStateMachine(subToken,aClass);
      }
      
      if (subToken.is("referencedStateMachine"))
      {
        analyzedReferencedStateMachine(subToken,aClass);
      }
    }
  }

  @umplesourcefile(line=163,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3267,length=21)
  private int numberOfActiveObjectsInClass(Token token, UmpleClass aClass)
  {
    int activeObjects = 0;
    
    Token parent = token.getParentToken();
    if (parent != null)
    {
      for (Token sub : parent.getSubTokens())
      {
        if (sub.is("stateMachine"))
        {
          if (sub.getSubToken(0).is("activeDefinition"))
          {
            activeObjects++;
          }
        }
      }
    }
    
    return activeObjects;
  }
  
  @umplesourcefile(line=185,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3290,length=15)
  private void postTokenStateMachineAnalysis()
  {
    // Generate error message for issue 354. Check for conflicting names between
    // state machines and attributes as well as state machines and association names
    checkStateMachineNameConflict();

    for(Iterator i = possiblyUnknownStates.entrySet().iterator(); i.hasNext();){
    	Map.Entry entry = (Map.Entry)i.next();
    	List<Token> tokens = (List<Token>)entry.getValue();
		for(int j = 0; j < tokens.size(); j++){
			setFailedPosition(tokens.get(j).getPosition(), 50, tokens.get(j).getValue("stateName"));
		}    
    }
    possiblyUnknownStates = new HashMap<State,List<Token>>();
  }
  
  /*
  	Check for name conflicts between state machines and attributes/association names
  	Author: Blake Quebec Desloges
  */
  @umplesourcefile(line=205,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3311,length=76)
  private void checkStateMachineNameConflict()
  {
    for(UmpleClass C : model.getUmpleClasses())
    {    
      // Create the list of attribute names 
      List<String> existingAttributeNames = new ArrayList<String>();
      for (Attribute attr : C.getAttributes())
      {
      	existingAttributeNames.add(attr.getName());
      }
      
      //Create the list of state machine names while checking for conflicts with attributes
      List<String> existingStateMachineNames = new ArrayList<String>();
      for (StateMachine sm : C.getStateMachines())
      {
      	// Check if the current state machine conflicts with an attribute name
      	if (existingAttributeNames.contains(sm.getName()))
      	{
      	   Token stateMachineToken = stateMachineNameToToken.get(sm.getName());
          setFailedPosition(stateMachineToken.getPosition(), 52, sm.getName());
       }
       else
          existingStateMachineNames.add(sm.getName());
      }
      
      List<String> existingAssociationNames = new ArrayList<String>();
      List<Association> visitedAssociations = new ArrayList<Association>();
      for(Association assoc : C.getAssociations())
      {
        if (visitedAssociations.contains(assoc))
        {
          continue;
        }
        
      	AssociationEnd firstEnd = assoc.getEnd(0);
      	AssociationEnd secondEnd = assoc.getEnd(1);
      	
      	Boolean checkFirstEnd = !firstEnd.getClassName().equals(C.getName());
      	Boolean checkSecondEnd = !secondEnd.getClassName().equals(C.getName());
      	Boolean associationIsReflexive = !checkFirstEnd && !checkSecondEnd;
      	
      	// check names on other-class end of associations to other classes
        if ((checkFirstEnd || associationIsReflexive) && assoc.getIsLeftNavigable())
        {
          // Check if the association name is the same as a state machine name
          if (existingStateMachineNames.contains(firstEnd.getRoleName()))
          {
             setFailedPosition(assoc.getTokenPosition(), 52, firstEnd.getRoleName());
          }
          else
          {
            existingAssociationNames.add(firstEnd.getRoleName());
          }
          
        }
        if ((checkSecondEnd || associationIsReflexive) && assoc.getIsRightNavigable())
        {
          // Check if the association name is the same as a state machine name
          if (existingStateMachineNames.contains(secondEnd.getRoleName()))
          {
             setFailedPosition(assoc.getTokenPosition(), 52, secondEnd.getRoleName());
          }
          else
          {
            existingAssociationNames.add(secondEnd.getRoleName());
          }
        }
         
        if (associationIsReflexive)
        { 
          // The UmpleClass is only expected to have duplicate references to reflexive associations
          visitedAssociations.add(assoc);
        }
      }
    }
  }
  
  
  @umplesourcefile(line=283,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3390,length=5)
  private void analyzeStateMachineDefinition(Token stateMachineDefinitionToken)
  {
    StateMachine smd = analyzeStateMachine(stateMachineDefinitionToken,null);
    model.addStateMachineDefinition(smd);
  }

  @umplesourcefile(line=289,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3397,length=32)
  private void analyzedReferencedStateMachine(Token stateMachineToken, UmpleClass aClass)
  {
    String name = stateMachineToken.getValue("name");

    if(!Token.isValidIdentifier(stateMachineToken.getValue("name"))){
    	setFailedPosition(stateMachineToken.getPosition(), 150, stateMachineToken.getValue("name"));
    }

    String definitionName = stateMachineToken.getValue("definitionName");
      
    Token stateMachineDefinitionToken = stateMachineNameToToken.get(definitionName);
    if (stateMachineDefinitionToken == null)
    {
      return;
    }

    StateMachine sm = new StateMachine(name);
    boolean wasSet = sm.setUmpleClass(aClass);
    if (!wasSet)
    {
    	// Future-proofing: currently all paths cause wasSet to be true
    	setFailedPosition(stateMachineToken.getPosition(), 15, aClass.getName());
    }
    
    populateStateMachine(stateMachineDefinitionToken,sm, aClass);
    Token extendedStateMachineTokens = stateMachineToken.getSubToken("extendedStateMachine");
    if (extendedStateMachineTokens != null)
    {
      populateStateMachine(extendedStateMachineTokens, sm, aClass);
    }

  }

  @umplesourcefile(line=322,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3431,length=27)
  private StateMachine analyzeStateMachine(Token stateMachineToken, UmpleClass aClass)
  {
    placeholderStateMachine = new StateMachine("PLACE_HOLDER");
    String name = stateMachineToken.getValue("name");

    if(!Token.isValidIdentifier(stateMachineToken.getValue("name"))){
    	setFailedPosition(stateMachineToken.getPosition(), 150, stateMachineToken.getValue("name"));
    }

    stateMachineNameToToken.put(name,stateMachineToken);
    
    StateMachine sm = new StateMachine(name);
    boolean wasSet = sm.setUmpleClass(aClass);
    if (!wasSet)
    {
    	// Future-proofing: currently all paths cause wasSet to be true
    	setFailedPosition(stateMachineToken.getPosition(), 15, aClass.getName());
    }
    
    populateStateMachine(stateMachineToken, sm, aClass);

    while (placeholderStateMachine.numberOfStates() > 0)
    {
      placeholderStateMachine.getState(0).setStateMachine(sm);
    }
    return sm;
  }

  @umplesourcefile(line=350,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3460,length=29)
  private State createStateFromTransition(Token transitionToken, StateMachine sm)
  {
    if(!Token.isValidIdentifier(transitionToken.getValue("stateName"))){
    	setFailedPosition(transitionToken.getPosition(), 152, transitionToken.getValue("stateName"));
    }
    String name = transitionToken.getValue("stateName");
    State nextState = sm.findState(name);
    
    if ("Final".equals(name))
    {
      nextState = new State(name,sm);
    }
    else
    {
      if (nextState == null)
      {
        nextState = placeholderStateMachine.findState(name);
        if(nextState != null)possiblyUnknownStates.get(nextState).add(transitionToken);
      }

      if (nextState == null)
      {
        nextState = new State(name,placeholderStateMachine);
        possiblyUnknownStates.put(nextState, new ArrayList());
        possiblyUnknownStates.get(nextState).add(transitionToken);
      }
    }
    return nextState;
  }

  @umplesourcefile(line=380,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3491,length=21)
  private State createStateFromDefinition(Token stateToken, StateMachine sm)
  {
    if(!Token.isValidIdentifier(stateToken.getValue("stateName"))){
    	setFailedPosition(stateToken.getPosition(), 152, stateToken.getValue("stateName"));
    }
    State s = sm.findState(stateToken.getValue("stateName"),false);
    if (s == null)
    {
      s = placeholderStateMachine.findState(stateToken.getValue("stateName"));
      if (s != null)
      {
        s.setStateMachine(sm);
      }
    }
    if (s == null)
    {
      s = new State(stateToken.getValue("stateName"),sm);
    }
    possiblyUnknownStates.remove(s);
    return s;
  }

  @umplesourcefile(line=402,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3514,length=45)
  private void populateStateMachine(Token stateMachineToken, StateMachine sm, UmpleClass aClass)
  {
    boolean isFirst = true;
    boolean isFinalState = false;
    String changeType = null;
    
    for(Token stateToken : stateMachineToken.getSubTokens())
    {
      // Concurrency is only allowed between nested state-machines
      if (stateToken.is("||"))
      {
        setFailedPosition(stateMachineToken.getPosition(), 53, sm.getName());
      }
      
      if(stateToken.is("trace"))
      {
    	analyzeTraceStatement(aClass,stateToken);
      }
      
      if (!stateToken.is("state") && !stateToken.is("stateName"))
      {
        if (stateToken.is("changeType")) { changeType = stateToken.getValue(); }      
        continue;
      }

      if ("-".equals(changeType))
      {
        State deleteMe = sm.findState(stateToken.getValue("stateName"));
        if (deleteMe != null) { deleteMe.delete(); }
      }
      else
      {
        State s = createStateFromDefinition(stateToken,sm);
        s.setFinalState(isFinalState);
        if (isFirst)
        {
          s.setIsStartState(true);
        }
        isFirst = false;
        isFinalState = false;
        analyzeState(stateToken, s);
        changeType = null;
      }
    }
  }

  @umplesourcefile(line=448,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3561,length=71)
  private void analyzeState(Token stateToken, State fromState)
  {
    boolean addNewSm = true;
    boolean isConcurrentState = false;
    boolean isFinalState = false;
    String changeType = null;
    
    for(Token subToken : stateToken.getSubTokens())
    {
      if (subToken.is("changeType"))
      {
        changeType = subToken.getValue();
      }
      else if (subToken.is("final"))
      {
        fromState.setFinalState(true);
      }
      else if (subToken.is("transition"))
      {
        analyzeTransition(false,subToken, fromState, changeType); 
      }
      else if (subToken.is("autoTransition"))
      {
        analyzeTransition(true,subToken, fromState, changeType);
      }
      else if (subToken.is("activity"))
      {
        analyzeActivity(subToken, fromState);
      }
      else if (subToken.is("entryOrExitAction"))
      {
      	fromState.addAction(analyzeAction(subToken, fromState));
      }
      else if (subToken.is("||"))
      {
        if (fromState.numberOfNestedStateMachines() == 0) { continue; }
        int previousSmIndex = fromState.numberOfNestedStateMachines() - 1;
              StateMachine nestedSm = fromState.getNestedStateMachine(previousSmIndex);
              if (nestedSm.numberOfStates() == 0) { continue; }
              nestedSm.setName(nestedSm.getState(0).getName());
              addNewSm = true;
              isConcurrentState = true;
      }
      else if (subToken.is("state"))
      {
        StateMachine nestedStateMachine = null;
        if (addNewSm)
        {
          nestedStateMachine = new StateMachine(fromState.getName());
          fromState.addNestedStateMachine(nestedStateMachine);
        }
        else
        {
          int lastIndex = fromState.numberOfNestedStateMachines() - 1;
          nestedStateMachine = fromState.getNestedStateMachine(lastIndex);
        }
        State s = createStateFromDefinition(subToken,nestedStateMachine);
        //alignStateDefinitionWithStateMachine(s,nestedStateMachine);
        if (addNewSm)
        {
                  if (isConcurrentState)
                  {
                    nestedStateMachine.setName(s.getName());
                  }
          s.setIsStartState(true);
        }
        addNewSm = false;
        analyzeState(subToken, s);
      }
    }
  }

  @umplesourcefile(line=520,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3634,length=27)
  private Activity analyzeActivity(Token activityToken, State fromState)
  {
    Activity act= new Activity("", fromState);
  	CodeBlock cb = new CodeBlock();
  	
  	List<String> codelangs = new ArrayList<String>();
  	for(Token tkn: activityToken.getSubTokens()){
  	  if(tkn.is("codeLang")){
  		codelangs.add(tkn.getValue());
  	  } else if(tkn.is("code")){
  		if(codelangs.isEmpty())
  		{
  		  cb.setCode(tkn.getValue());
  		} 
  		else {
  		  for(String lang: codelangs){
  			cb.setCode(lang, tkn.getValue());
          }
          codelangs.clear();
    	}
      }
    }
    act.setCodeblock(cb);
    
    act.setPosition(activityToken.getPosition());
    return act;
  }
  
  @umplesourcefile(line=548,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3663,length=27)
  private Action analyzeAction(Token actionToken, State fromState){
  	Action action= new Action("");
  	CodeBlock cb = new CodeBlock();
  	List<String> codelangs = new ArrayList<String>();
  	for(Token tkn: actionToken.getSubTokens()){
  	  if(tkn.is("codeLang")){
  		codelangs.add(tkn.getValue());
  	  } else if(tkn.is("code")){
  		if(codelangs.isEmpty())
  		{
  		  cb.setCode(tkn.getValue());
  		} 
  		else {
  		  for(String lang: codelangs){
  			cb.setCode(lang, tkn.getValue());
          }
          codelangs.clear();
    	}
      }
    }
    action.setCodeblock(cb);
    	
    action.setPosition(actionToken.getPosition());
    action.setActionType(actionToken.getValue("type"));
        
    return action;
  }
  @umplesourcefile(line=575,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3691,length=26)
  private Guard analyzeGuard(Token guardToken, State fromState){
  	Guard guard= new Guard("true");
  	CodeBlock cb = new CodeBlock();
  	List<String> codelangs = new ArrayList<String>();
  	for(Token tkn: guardToken.getSubTokens()){
  	  if(tkn.is("codeLang")){
  		codelangs.add(tkn.getValue());
  	  } else if(tkn.is("code")){
  		if(codelangs.isEmpty())
  		{
  		  cb.setCode(tkn.getValue());
  		} 
  		else {
  		  for(String lang: codelangs){
  			cb.setCode(lang, tkn.getValue());
          }
          codelangs.clear();
    	}
      }
    }
    guard.setCodeblock(cb);
    
    guard.setPosition(guardToken.getPosition());
    
    return guard;
  }

  @umplesourcefile(line=602,file="UmpleInternalParser_CodeStateMachine.ump",javaline=3719,length=71)
  private void analyzeTransition(boolean isAutoTransition, Token transitionToken, State fromState, String changeType)
  {
    State nextState = createStateFromTransition(transitionToken,fromState.getStateMachine());
    
    Transition t; 
    if ("-".equals(changeType))
    {
      t = Transition.createPlaceholder(nextState);
    }
    else
    {
      t = new Transition(fromState, nextState);
    }

    t.setAutoTransition(isAutoTransition);

    String eventName = transitionToken.getValue("event");
    String eventTimerAmount = transitionToken.getValue("timer");
	String eventArgs = transitionToken.getValue("eventArgs");
    if (eventName == null && eventTimerAmount != null)
    {
      eventName = fromState.newTimedEventName(nextState);
    }

    Token guardToken = transitionToken.getSubToken("guard");
    if (guardToken != null)
    {
      t.setGuard(analyzeGuard(guardToken, fromState));
    }

    Token actionToken = transitionToken.getSubToken("action");
    if (actionToken != null)
    {
      
      t.setAction(analyzeAction(actionToken,fromState));
    }

    if (eventName != null || isAutoTransition)
    {
      StateMachine sm = fromState.getStateMachine();
      UmpleClass uClass = sm.getUmpleClass();
      Event event = isAutoTransition ? Event.createAutoTransition() : uClass == null ? sm.findOrCreateEvent(eventName) : uClass.findOrCreateEvent(eventName);
      if(event.getArgs() != null && event.getArgs().equals(eventArgs == null?"":eventArgs) == false){
      	setFailedPosition(transitionToken.getPosition(), 51, eventArgs);
      }
      event.setArgs(eventArgs == null?"":eventArgs);
      if (eventTimerAmount != null)
      {
        event.setIsTimer(true);
        event.setTimerInSeconds(eventTimerAmount);
      }
      t.setEvent(event);
      
      if ("-".equals(changeType))
      {
        fromState.removeTransition(t);      
      }
      
      for(Token subToken : transitionToken.getSubTokens())
      {
        if (subToken.is("activity"))
        {
          t.setAutoTransition(false);
          Activity act = analyzeActivity(subToken, fromState);
          act.setOnCompletionEvent(event);
        }
    }      
    
    }

  }
// line 26 ../../../../src/UmpleInternalParser_CodeTrace.ump
  @umplesourcefile(line=26,file="UmpleInternalParser_CodeTrace.ump",javaline=3792,length=30)
  private void analyzeTraceToken(Token token, int analysisStep)
  {
    
    if (analysisStep != 1)
    {
      return;
    }
    
    if (token.is("traceType"))
    {
      Tracer tracer = model.getTracer();
      for(Token subToken: token.getSubTokens())
      {
      	if(subToken.is("tracerType"))
      	{
      	  tracer = new Tracer(subToken.getValue());
      	}
      	else if(subToken.is("tracerArgument"))
      	{
      	  tracer.addArgument(subToken.getValue());
      	}
      	else if(subToken.is("verbosity"))
      	{
      	  tracer.setVerbosity(true);
      	}
      }
      model.setTracer(tracer);
    }
    
  }
  
  // Process trace related tokens within the context of a class
  @umplesourcefile(line=58,file="UmpleInternalParser_CodeTrace.ump",javaline=3825,length=18)
  private void analyzeTraceToken(Token token, UmpleClass aClass, int analysisStep)
  {
    // Only process trace tokens once all other entities have been analyzed
    if (analysisStep == 1 || shouldProcessClassAgain)
    {
      shouldProcessClassAgain = true;
      return;
    }
    // Analyze trace statement token
    if (token.is("trace"))
    {
      if(model.getTracer()==null)
      {
      	model.setTracer(new Tracer("Console"));
      }
      analyzeTraceStatement(aClass, token);
    }
  }
  
  // Process Trace statement subtokens. Token could be trace directive or trace case
  @umplesourcefile(line=78,file="UmpleInternalParser_CodeTrace.ump",javaline=3846,length=152)
  private void analyzeTraceStatement( UmpleClass aClass, Token token)
  {
  
    TraceDirective traceDirective = new TraceDirective(model.getTracer());
    AttributeTraceItem traceAttr = new AttributeTraceItem(traceDirective);
    MethodTraceEntity mte = new MethodTraceEntity();
    TraceRecord traceRecord = null;
    traceDirective.setUmpleClass(aClass);
     
    boolean isFirst = true;
      
    for( Token traceToken : token.getSubTokens() )
    {
      if( traceToken.is("trace_entity") || traceToken.getName().equals("entry") || traceToken.getName().equals("exit"))
      {
        analyzeTraceItem( traceToken , traceDirective , mte, traceAttr);
      }
        
      else if( traceToken.is("traceWhen") ) //for where/giving/after/until
      {
        //using the constraint data type, used for holding umple specific conditionals
        int pos = 1;
        if(!traceToken.getSubToken(1).is("constraintToken")){
          String ctype = traceToken.getValue("conditionType");
          if(ctype!=null&&!ctype.equals("where"))
          {
          	traceAttr.setConditionType(ctype);
          	traceAttr.setConditionallyWhere(false);
          }
          
          pos = 2;
        }
        List <ConstraintVariable> cvs = analyzeConstraint(traceToken.getSubToken(pos), aClass); //adds all identifiers to constraints
        Constraint constraint = new Constraint();
        List<Attribute> others = new ArrayList<Attribute>();
        for(ConstraintVariable cv: cvs)
        {
          constraint.addExpression(cv);
          if(("after".equals(traceAttr.getConditionType())||
          	  "until".equals(traceAttr.getConditionType()))&&
          	  cv.getIsAttribute()
          )
          {
          	UmpleVariable av = cv.getAttribute(aClass);
            if(av instanceof Attribute)
            {
              others.add((Attribute)av);
            }
            else if(av instanceof AssociationVariable)
            {
              //others.add((AssociationVariable)av);
            }
            
          }
        }
        if(others.isEmpty())
        {
          traceAttr.setConstraint(constraint);
        }
        else
        {
          String  method = "";
          
          for(Attribute at:others)
          {
          	method+= ",set_"+at.getName();
          	method+= ",set"+at.getName().substring(0,1).toUpperCase()+at.getName().substring(1);          	
          }
          String flag = "traceFlag"+getTraceFlagId();
          String code = flag+"="+("after".equals(traceAttr.getConditionType())?"true":"until".equals(traceAttr.getConditionType())?"false":"");
          CodeInjection ci = new CodeInjection("after",method,"", aClass);
          CodeBlock cb = new CodeBlock(code+";");
          cb.setCode("Java",code+";");
          cb.setCode("Cpp",code+";");
          cb.setCode("Php",code+";");
          cb.setCode("Ruby",code);
          ci.setSnippet(cb);
          ci.setConstraint(constraint);
          aClass.addCodeInjection(ci);
          constraint = new Constraint();
          constraint.addExpression(new ConstraintVariable("",flag));
          traceAttr.setConstraint(constraint);
          new Attribute(flag,"Boolean","internal",("after".equals(traceAttr.getConditionType())?"false":"until".equals(traceAttr.getConditionType())?"true":""),false,aClass);
        }
      }
      else if ( traceToken.getName().equals("giving") )
      {
        TraceCondition tc = analyzeTraceCondition( token, "giving");
        traceDirective.addCondition(tc);
        UmpleVariable attr = traceDirective.getUmpleClass().getAttribute(token.getValue("LHS"));
        if(attr==null)
        {
          attr = traceDirective.getUmpleClass().getAssociationVariable(token.getValue("LHS"));
        }
        traceAttr.addUmpleVariable(attr);
      }     
      else if( traceToken.is("trace_for") )
      {
      	String flag = "traceFlag"+getTraceFlagId();
        traceAttr.setForClause(flag);
        Constraint constraint;
        if(traceAttr.getConstraint()==null)
        {
          constraint = new Constraint();
        } 
        else
        {
          constraint = traceAttr.getConstraint();
          constraint.addExpression(new ConstraintVariable("OPERATOR","&&"));
        }
        constraint.addExpression(new ConstraintVariable("",flag));
        constraint.addExpression(new ConstraintVariable("OPERATOR","<"));
        constraint.addExpression(analyzeConstraintName(traceToken,aClass,true,true));
        traceAttr.setConstraint(constraint);
        
        new Attribute(flag,"Integer","internal","0",false,aClass);
      }     
      else if( traceToken.is("trace_period") )
      {
      	traceAttr.setTraceSet(false);
      	traceAttr.setTraceGet(false);
      	traceAttr.setTraceConstructor(true);
        traceAttr.setPeriodClause(token.getValue("trace_period"));
      }
      else if( traceToken.is("trace_duration") )
      {
        traceAttr.setDuringClause(token.getValue("trace_duration"));
      }
      else if( traceToken.is("trace_record") || traceToken.getName().equals("only") )
      {
        if( isFirst )
        {
          traceRecord = new TraceRecord(traceDirective);
          analyzeTraceRecord(traceDirective,traceToken,traceRecord);
          isFirst = false;
        }
        else
          analyzeTraceRecord(traceDirective,traceToken,traceRecord);
      }
      else if( traceToken.is("trace_execute") )
      {
        traceAttr.setExecuteClause(token.getValue("trace_execute"));
      }  
      else if( traceToken.is("tracecase_name") )
      {
        analyzeTraceCaseToken( aClass , token );
        return;
      }
    }
    if( traceAttr.numberOfUmpleVariables() > 0 )
      traceDirective.addAttributeTraceItem(traceAttr);
  }
  
  // Analyze trace record in a trace directive
  @umplesourcefile(line=232,file="UmpleInternalParser_CodeTrace.ump",javaline=4001,length=15)
  private void analyzeTraceRecord(TraceDirective traceDirective, Token token, TraceRecord traceRecord) 
  {
    String record = token.getValue("trace_record");
    Attribute attr = traceDirective.getUmpleClass().getAttribute(token.getValue("trace_record"));
    
    // trace only what is in the record entity
    if( token.getName().equals("only") )
      traceRecord.setRecordOnly(true);  
    // recording a String 
    else if( record.contains("\"") )
      traceRecord.addRecord(record);
    // recording an attribute
    else if( attr != null )
      traceRecord.addAttribute(attr);
  }

  // Analyze Trace Item Token whether trace item is an attribute or a method ... etc
  @umplesourcefile(line=249,file="UmpleInternalParser_CodeTrace.ump",javaline=4019,length=61)
  private void analyzeTraceItem( Token traceToken, TraceDirective traceDirective, MethodTraceEntity mte, AttributeTraceItem traceAttr)
  {
    UmpleVariable attr = traceDirective.getUmpleClass().getAttribute(traceToken.getValue("trace_entity"));
    attr = attr==null?traceDirective.getUmpleClass().getAssociationVariable(traceToken.getValue("trace_entity")):attr;
    String methodName = traceToken.getValue("trace_entity");
    
    // here, i faced a problem of finding traced state machine because
    // -> in UmpleClass there no getStateMachine( String stm ) which gets state by searching its name
    analyzeStateMachineTraceItem(traceToken,traceDirective);
    analyzeAssociationTraceItem(traceToken,traceDirective);
    if( traceToken.getName().equals("entry") )
    {
      mte.setEntry(true);
      
    }
    else if( traceToken.getName().equals("exit") )
    {
      mte.setExit(true);
    }
    
    // if trace entity is a method
    if( methodName != null && methodName.contains("("))
    {
      if( mte.getName() !=  null)
      {
        mte = new MethodTraceEntity();
      }
      mte.setName(methodName);
      traceDirective.addMethodTraceEntity(mte);
    }
    // if trace entity is an attribute
    else if( attr != null )
    {
      traceAttr.setPosition(traceToken.getPosition());
      traceAttr.addUmpleVariable(attr);
      for(Token sub: traceToken.getParentToken().getSubTokens())
      {
        if(sub.is("traceOptions"))
        {
          boolean wasSet = false;
          for(Token sub2: sub.getSubTokens())
          {
            if( "get".equals(sub2.getValue("option")) )
            {
              wasSet = true;
              traceAttr.setTraceGet(true);
            }
            if( "set".equals(sub2.getValue("option")) )
            {
              wasSet = true;
              traceAttr.setTraceSet(true);
            }
          }
          if(!wasSet)
          {
            traceAttr.setTraceSet(true);
          }
        }
      }
    }
  }
  
  @umplesourcefile(line=311,file="UmpleInternalParser_CodeTrace.ump",javaline=4082,length=120)
  private void analyzeStateMachineTraceItem( Token traceToken, TraceDirective traceDirective)
  {
    List<StateMachine> stms = traceDirective.getUmpleClass().getStateMachines();
    StateMachine stm = null;
    State state = null;
    String stmTraceItem = traceToken.getValue("trace_entity");
    
    if( "transition".equals(traceToken.getParentToken().getSubToken(1).getValue("option")))
    {
      StateMachineTraceItem tracedStm = new StateMachineTraceItem(traceDirective);
      tracedStm.setPosition(traceToken.getPosition());
      if( traceToken.getParentToken().getValue("trace_level")!=null )
      {
        tracedStm.setLevel(Integer.parseInt(traceToken.getParentToken().getValue("trace_level")));
      }
      for( StateMachine sm:stms  )
      {
        for( State nestedState:sm.getStates())
        {
          for( Transition tsn:nestedState.getTransitions())
          {
            if( tsn.getEvent().getName().equals(traceToken.getValue()) )
            {
              tracedStm.setTransition(tsn);
              tracedStm.setStateMachine(sm);
              traceDirective.addStateMachineTraceItem(tracedStm);
            }
          }
        }
      }
      
    }
    
    if( stmTraceItem != null && stmTraceItem.contains("."))
    {
      String delimiter = "\\.";
      String[] temp = stmTraceItem.split(delimiter);
      stmTraceItem = temp[temp.length-1];
    }
    if(stm==null)
    {
      for( StateMachine sm:stms )
      {
        state=sm.findState(stmTraceItem, true);
        if(state!=null)
        {
          stm=state.getStateMachine();
          break;
        }
      }
    }
    if(stm==null)
    {
      for( StateMachine sm:stms )
      {
        if( sm.getFullName().equals(stmTraceItem))
        {
          stm = sm;
          break;
        }            
      }
    }
    //System.out.println(stmTraceItem+"<>"+stm);  
    // if trace entity is a state machine  
    if( stm != null)
    {  
      StateMachineTraceItem tracedStm = new StateMachineTraceItem(traceDirective);
      tracedStm.setPosition(traceToken.getPosition());  
      tracedStm.setStateMachine(stm);
      tracedStm.setState(state);
      if( traceToken.getParentToken().getValue("trace_level")!=null )
      {
        tracedStm.setLevel(Integer.parseInt(traceToken.getParentToken().getValue("trace_level")));
      }
      for(Token sub: traceToken.getParentToken().getSubTokens())
      {
        if(sub.is("traceOptions"))
        {
          boolean wasSet = false;
          boolean wasInOut = false;
          for(Token sub2: sub.getSubTokens())
          {
            if( "entry".equals(sub2.getValue("option")) )
            {
              wasSet = true;
              tracedStm.setEntry(true);
            }
            if( "exit".equals(sub2.getValue("option")) )
            {
              wasSet = true;
              tracedStm.setEntry(false);
            }
            if( "in".equals(sub2.getValue("option")) )
            {
              wasInOut = true;
              tracedStm.setIsIn(true);
            }
            if( "out".equals(sub2.getValue("option")) )
            {
              wasInOut = true;
              tracedStm.setIsOut(true);
            }
          }
          if(!wasSet)
          {
            tracedStm.setEntry(false);
            tracedStm.setExit(false);
          }
          if(!wasInOut)
          {
            tracedStm.setIsOut(true);
            tracedStm.setIsIn(false);
          }
        }
      }
      tracedStm.setTraceStateMachineFlag(state == null);
      traceDirective.addStateMachineTraceItem(tracedStm);
      
    }
  }

  @umplesourcefile(line=432,file="UmpleInternalParser_CodeTrace.ump",javaline=4204,length=13)
  private void analyzeAssociationTraceItem( Token traceToken, TraceDirective traceDirective)
  {
    if( traceToken.getParentToken().getSubToken(1).getName().equals("cardinality") )  
    {
      for( AssociationVariable aVar : traceDirective.getUmpleClass().getAssociationVariables() )
      {
        if( traceToken.getValue("trace_entity").equals(aVar.getName()))
          traceDirective.setAssociationVariable(aVar);
      }
      
      
    }
  }
  
  // Analyze Trace Condition Token. Called when different Trace Directive conditions are encountered (where,until,after)
  // Returns a trace condition filled with left and right hands operands, with comparison operator used
  @umplesourcefile(line=448,file="UmpleInternalParser_CodeTrace.ump",javaline=4221,length=17)
  private TraceCondition analyzeTraceCondition( Token traceConditionToken , String conditionType)
  {
    ConditionRhs rhs = new ConditionRhs();
    TraceCondition tc = new TraceCondition(rhs);
    tc.setConditionType(conditionType);
    for( Token subToken : traceConditionToken.getSubTokens() )
    {
      if(subToken.is("LHS"))
        tc.setLhs(subToken.getValue("LHS"));
      if(subToken.is("comparison_operator"))
        rhs.setComparisonOperator(subToken.getValue("comparison_operator"));
      if(subToken.is("RHS"))
        rhs.setRhs(subToken.getValue("RHS"));
    }
    tc.setRhs(rhs);
    return tc;
  }
  
  // Analyzes trace case token and its subtokens (i.e. trace directive tokens)
  @umplesourcefile(line=467,file="UmpleInternalParser_CodeTrace.ump",javaline=4241,length=26)
  public void analyzeTraceCaseToken( UmpleClass aClass , Token token )
  {
    TraceCase tca = new TraceCase();
    TraceDirective td = new TraceDirective(model.getTracer());
    td.setUmpleClass(aClass);
    // set trace case name
    tca.setName(token.getValue("tracecase_name"));
    
    for( Token traceToken : token.getSubTokens() )
    { 
      // "trace" indicates the beginning of a trace directive
      if( traceToken.getName().equals("trace") )
      {
        td = new TraceDirective(model.getTracer());
        td.setUmpleClass(aClass);
      }
      // ";" indicates the end of a trace directive, once true, then the trace directive is added to the trace case
      else if( traceToken.getName().equals(";") )
      {
        tca.addTraceDirective(td);
      }
      else
        analyzeTraceDirectiveFragments( td , aClass , traceToken, token );
    }
    aClass.addTraceCase(tca);
  }
  
  // Modified version of method "analyzeTraceDirective"
  // This method analyzes trace directive fragments inside a trace case
  @umplesourcefile(line=496,file="UmpleInternalParser_CodeTrace.ump",javaline=4271,length=44)
  private void analyzeTraceDirectiveFragments( TraceDirective traceDirective , UmpleClass aClass , Token traceToken , Token token )
  {
    AttributeTraceItem traceAttr = new AttributeTraceItem(traceDirective);
      MethodTraceEntity mte = new MethodTraceEntity();
      
      if( traceToken.is("trace_entity") )  
      {
        analyzeTraceItem( traceToken , traceDirective , mte, traceAttr );
      }
      else if( traceToken.getName().equals("entry") )
      {
        mte.setEntry(true);  
      }  
      else if( traceToken.getName().equals("exit") )  
      {   
        mte.setExit(true);      
      }     
      else if( traceToken.is("traceWhen") ) //for where/giving/after/until
      {
        //gets the string containing the condition's type
      String conditionType = traceToken.getValue("conditionType");
        TraceCondition tc = analyzeTraceCondition( traceToken, conditionType );
      traceDirective.addCondition(tc);
      }      
      else if( traceToken.is("trace_for") )    
      {  
        //traceAttr.setForClause(Integer.parseInt(token.getValue("trace_for")));     
      }          
      else if( traceToken.is("trace_period") )     
      {    
        traceAttr.setPeriodClause(token.getValue("trace_period"));       
      }         
      else if( traceToken.is("trace_duration") )     
      {     
        traceAttr.setDuringClause(token.getValue("trace_duration"));       
      }         
      else if( traceToken.is("trace_execute") )     
      {   
        traceAttr.setExecuteClause(token.getValue("trace_execute"));     
      }
      
      if( traceAttr.numberOfUmpleVariables() > 0 )
        traceDirective.addAttributeTraceItem(traceAttr);
  }
  
  // Perform post token analysis on trace related elements of the Umple language
  @umplesourcefile(line=542,file="UmpleInternalParser_CodeTrace.ump",javaline=4318,length=3)
  private void postTokenTraceAnalysis()
  {
  }
  
  //****************************************
  //********* End of Trace Glue Code   *****
  //****************************************
// line 21 ../../../../src/UmpleInternalParser_CodeLayout.ump
  @umplesourcefile(line=21,file="UmpleInternalParser_CodeLayout.ump",javaline=4327,length=3)
  private void analyzeLayoutToken(Token token, int analysisStep)
  {
  }

  // There are currently no core tokens of concern in the context of an UmpleClass
  // This method is available if needed
  @umplesourcefile(line=27,file="UmpleInternalParser_CodeLayout.ump",javaline=4334,length=21)
  private void analyzeLayoutToken(Token token, UmpleClass aClass, int analysisStep)
  {
    // Only process layout tokens once all other entities have been analyzed
    if (analysisStep == 1 || shouldProcessClassAgain)
    {
      shouldProcessClassAgain = true;
      return;
    }
  
    if (token.is("elementPosition"))
    {
      aClass.setCoordinates(new Coordinate(token.getIntValue("x"),token.getIntValue("y"), token.getIntValue("width"), token.getIntValue("height")));
    }
    else if (token.is("displayColor"))
    { // See near clone in UmpleInternalParser_CodeClass.ump
      String theColor = token.getValue("colorValue");
      if(theColor.startsWith("=")) theColor=theColor.substring(1,theColor.length());
      if(!theColor.startsWith("\"")) theColor= "\""+theColor;
      if(!theColor.endsWith("\"")) theColor= theColor+"\"";
      aClass.setDisplayColor(theColor);
    }
    else if (token.is("associationPosition"))
    {
      String name = token.getValue("name");
      Association assoc = model.getAssociation(name);

      if (assoc != null)
      {
        assoc.setName(name);
        for(Token position : token.getSubTokens())
        {
          if (position.is("coordinate"))
          {
            assoc.addPosition(new Coordinate(position.getIntValue("x"),position.getIntValue("y"),0,0));
          }
        }
      }
    }
  }

  // Perform post token analysis on core elements of the Umple language
  @umplesourcefile(line=68,file="UmpleInternalParser_CodeLayout.ump",javaline=4376,length=4)
  private void postTokenLayoutAnalysis()
  {
    layoutNewElements();
  }  

  // Look for any new elements and give them positions if undefined
  @umplesourcefile(line=74,file="UmpleInternalParser_CodeLayout.ump",javaline=4383,length=54)
  private void layoutNewElements()
  {
    // layout classes
    for (int i=0; i<model.numberOfUmpleClasses(); i++)
    {
      UmpleClass c = model.getUmpleClass(i);

      if (c.getCoordinates().getStatus() == Coordinate.Status.Defaulted)
      {
        // Do nothing
      }
      else if (c.getCoordinates().getStatus() == Coordinate.Status.Undefined)
      {
        c.setCoordinates(model.getDefaultClassPosition(i+1));
        c.getCoordinates().setStatus(Coordinate.Status.Defaulted);
      }
    }

    // layout interfaces
    for (int i=0; i<model.numberOfUmpleInterfaces(); i++)
    {
      UmpleInterface c = model.getUmpleInterface(i);

      if (c.getCoordinates().getStatus() == Coordinate.Status.Defaulted)
      {
        // Do nothing
      }
      else if (c.getCoordinates().getStatus() == Coordinate.Status.Undefined)
      {
        c.setCoordinates(model.getDefaultClassPosition(i+1));
        c.getCoordinates().setStatus(Coordinate.Status.Defaulted);
      }
    }

    // Layout associations
    for (int i=0; i<model.numberOfAssociations(); i++)
    {
      Association a = model.getAssociation(i);
      
      if (associationIsBetweenClassAndInterface (a)){continue;}
      
      int numberOfPositions = a.numberOfPositions();

      if (numberOfPositions < 2)
      {
        Coordinate[] defaults = model.getDefaultAssociationPosition(a);

        a.addPosition(defaults[0]);
        a.addPosition(defaults[1]);
        a.getPosition(0).setStatus(Coordinate.Status.Defaulted);
        a.getPosition(1).setStatus(Coordinate.Status.Defaulted);
      }
    }    
  }

}