/**
 * <copyright>
 * </copyright>
 *

 */
package cruise.umple.umple.util;

import cruise.umple.umple.AbstractElement;
import cruise.umple.umple.Action;
import cruise.umple.umple.Activity;
import cruise.umple.umple.AfterEvent;
import cruise.umple.umple.AfterEveryEvent;
import cruise.umple.umple.Association;
import cruise.umple.umple.AssociationClassContent;
import cruise.umple.umple.AssociationClassDefinition;
import cruise.umple.umple.AssociationDefinition;
import cruise.umple.umple.AssociationPosition;
import cruise.umple.umple.Attribute;
import cruise.umple.umple.ClassContent;
import cruise.umple.umple.ClassDefinition;
import cruise.umple.umple.CodeInjection;
import cruise.umple.umple.Coordinate;
import cruise.umple.umple.Depend;
import cruise.umple.umple.ElementPosition;
import cruise.umple.umple.Entity;
import cruise.umple.umple.EntryOrExitAction;
import cruise.umple.umple.Event;
import cruise.umple.umple.EventDefinition;
import cruise.umple.umple.ExternalDefinition;
import cruise.umple.umple.Generate;
import cruise.umple.umple.Glossary;
import cruise.umple.umple.Guard;
import cruise.umple.umple.GuardCode;
import cruise.umple.umple.InlineAssociation;
import cruise.umple.umple.InlineStateMachine;
import cruise.umple.umple.InterfaceDefinition;
import cruise.umple.umple.Key;
import cruise.umple.umple.KeyDefinition;
import cruise.umple.umple.Namespace;
import cruise.umple.umple.Position;
import cruise.umple.umple.ReferencedStateMachine;
import cruise.umple.umple.SingleAssociationEnd;
import cruise.umple.umple.Singleton;
import cruise.umple.umple.SoftwarePattern;
import cruise.umple.umple.State;
import cruise.umple.umple.StateEntity;
import cruise.umple.umple.StateMachine;
import cruise.umple.umple.StateMachineDefinition;
import cruise.umple.umple.SymmetricReflexiveAssociation;
import cruise.umple.umple.Transition;
import cruise.umple.umple.UmpleElement;
import cruise.umple.umple.UmpleModel;
import cruise.umple.umple.UmplePackage;
import cruise.umple.umple.UseStatement;
import cruise.umple.umple.Word;
import cruise.umple.umple.additiveExpression;
import cruise.umple.umple.block;
import cruise.umple.umple.blockStatement;
import cruise.umple.umple.breakstatement;
import cruise.umple.umple.conditionalAndExpression;
import cruise.umple.umple.conditionalExpression;
import cruise.umple.umple.conditionalOrExpression;
import cruise.umple.umple.endstatement;
import cruise.umple.umple.equalityExpression;
import cruise.umple.umple.expression;
import cruise.umple.umple.expressionList;
import cruise.umple.umple.expressionstatement;
import cruise.umple.umple.forControl;
import cruise.umple.umple.forInit;
import cruise.umple.umple.forUpdate;
import cruise.umple.umple.forstatement;
import cruise.umple.umple.functionCall;
import cruise.umple.umple.functionDeclaration;
import cruise.umple.umple.functionDefinition;
import cruise.umple.umple.ifstatement;
import cruise.umple.umple.isA;
import cruise.umple.umple.javaFunctionCall;
import cruise.umple.umple.javaFunctionDeclaration;
import cruise.umple.umple.javaFunctionDefinition;
import cruise.umple.umple.literal;
import cruise.umple.umple.localVariableDeclaration;
import cruise.umple.umple.localVariableDeclarationStatement;
import cruise.umple.umple.multiplicativeExpression;
import cruise.umple.umple.parExpression;
import cruise.umple.umple.phpBlock;
import cruise.umple.umple.phpFunction;
import cruise.umple.umple.primary;
import cruise.umple.umple.relationalExpression;
import cruise.umple.umple.returnstatement;
import cruise.umple.umple.statement;
import cruise.umple.umple.trystatement;
import cruise.umple.umple.unaryExpression;
import cruise.umple.umple.unaryExpressionNotPlusMinus;
import cruise.umple.umple.variableDeclarator;
import cruise.umple.umple.variableDeclarators;
import cruise.umple.umple.whilestatement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see cruise.umple.umple.UmplePackage
 * @generated
 */
public class UmpleSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static UmplePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UmpleSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = UmplePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case UmplePackage.UMPLE_MODEL:
      {
        UmpleModel umpleModel = (UmpleModel)theEObject;
        T result = caseUmpleModel(umpleModel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ABSTRACT_ELEMENT:
      {
        AbstractElement abstractElement = (AbstractElement)theEObject;
        T result = caseAbstractElement(abstractElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.GLOSSARY:
      {
        Glossary glossary = (Glossary)theEObject;
        T result = caseGlossary(glossary);
        if (result == null) result = caseAbstractElement(glossary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.WORD:
      {
        Word word = (Word)theEObject;
        T result = caseWord(word);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.GENERATE:
      {
        Generate generate = (Generate)theEObject;
        T result = caseGenerate(generate);
        if (result == null) result = caseAbstractElement(generate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.USE_STATEMENT:
      {
        UseStatement useStatement = (UseStatement)theEObject;
        T result = caseUseStatement(useStatement);
        if (result == null) result = caseAbstractElement(useStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.NAMESPACE:
      {
        Namespace namespace = (Namespace)theEObject;
        T result = caseNamespace(namespace);
        if (result == null) result = caseAbstractElement(namespace);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ENTITY:
      {
        Entity entity = (Entity)theEObject;
        T result = caseEntity(entity);
        if (result == null) result = caseAbstractElement(entity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.CLASS_DEFINITION:
      {
        ClassDefinition classDefinition = (ClassDefinition)theEObject;
        T result = caseClassDefinition(classDefinition);
        if (result == null) result = caseEntity(classDefinition);
        if (result == null) result = caseUmpleElement(classDefinition);
        if (result == null) result = caseAbstractElement(classDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EXTERNAL_DEFINITION:
      {
        ExternalDefinition externalDefinition = (ExternalDefinition)theEObject;
        T result = caseExternalDefinition(externalDefinition);
        if (result == null) result = caseEntity(externalDefinition);
        if (result == null) result = caseAbstractElement(externalDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.INTERFACE_DEFINITION:
      {
        InterfaceDefinition interfaceDefinition = (InterfaceDefinition)theEObject;
        T result = caseInterfaceDefinition(interfaceDefinition);
        if (result == null) result = caseEntity(interfaceDefinition);
        if (result == null) result = caseUmpleElement(interfaceDefinition);
        if (result == null) result = caseAbstractElement(interfaceDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ASSOCIATION_DEFINITION:
      {
        AssociationDefinition associationDefinition = (AssociationDefinition)theEObject;
        T result = caseAssociationDefinition(associationDefinition);
        if (result == null) result = caseEntity(associationDefinition);
        if (result == null) result = caseAbstractElement(associationDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.CLASS_CONTENT:
      {
        ClassContent classContent = (ClassContent)theEObject;
        T result = caseClassContent(classContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ASSOCIATION_CLASS_CONTENT:
      {
        AssociationClassContent associationClassContent = (AssociationClassContent)theEObject;
        T result = caseAssociationClassContent(associationClassContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.DEPEND:
      {
        Depend depend = (Depend)theEObject;
        T result = caseDepend(depend);
        if (result == null) result = caseClassContent(depend);
        if (result == null) result = caseAssociationClassContent(depend);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ASSOCIATION:
      {
        Association association = (Association)theEObject;
        T result = caseAssociation(association);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.SYMMETRIC_REFLEXIVE_ASSOCIATION:
      {
        SymmetricReflexiveAssociation symmetricReflexiveAssociation = (SymmetricReflexiveAssociation)theEObject;
        T result = caseSymmetricReflexiveAssociation(symmetricReflexiveAssociation);
        if (result == null) result = caseClassContent(symmetricReflexiveAssociation);
        if (result == null) result = caseAssociationClassContent(symmetricReflexiveAssociation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.INLINE_ASSOCIATION:
      {
        InlineAssociation inlineAssociation = (InlineAssociation)theEObject;
        T result = caseInlineAssociation(inlineAssociation);
        if (result == null) result = caseClassContent(inlineAssociation);
        if (result == null) result = caseAssociationClassContent(inlineAssociation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.SINGLE_ASSOCIATION_END:
      {
        SingleAssociationEnd singleAssociationEnd = (SingleAssociationEnd)theEObject;
        T result = caseSingleAssociationEnd(singleAssociationEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ASSOCIATION_CLASS_DEFINITION:
      {
        AssociationClassDefinition associationClassDefinition = (AssociationClassDefinition)theEObject;
        T result = caseAssociationClassDefinition(associationClassDefinition);
        if (result == null) result = caseEntity(associationClassDefinition);
        if (result == null) result = caseAbstractElement(associationClassDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.SOFTWARE_PATTERN:
      {
        SoftwarePattern softwarePattern = (SoftwarePattern)theEObject;
        T result = caseSoftwarePattern(softwarePattern);
        if (result == null) result = caseClassContent(softwarePattern);
        if (result == null) result = caseAssociationClassContent(softwarePattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.IS_A:
      {
        isA isA = (isA)theEObject;
        T result = caseisA(isA);
        if (result == null) result = caseSoftwarePattern(isA);
        if (result == null) result = caseClassContent(isA);
        if (result == null) result = caseAssociationClassContent(isA);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.SINGLETON:
      {
        Singleton singleton = (Singleton)theEObject;
        T result = caseSingleton(singleton);
        if (result == null) result = caseSoftwarePattern(singleton);
        if (result == null) result = caseClassContent(singleton);
        if (result == null) result = caseAssociationClassContent(singleton);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.KEY_DEFINITION:
      {
        KeyDefinition keyDefinition = (KeyDefinition)theEObject;
        T result = caseKeyDefinition(keyDefinition);
        if (result == null) result = caseSoftwarePattern(keyDefinition);
        if (result == null) result = caseClassContent(keyDefinition);
        if (result == null) result = caseAssociationClassContent(keyDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.CODE_INJECTION:
      {
        CodeInjection codeInjection = (CodeInjection)theEObject;
        T result = caseCodeInjection(codeInjection);
        if (result == null) result = caseSoftwarePattern(codeInjection);
        if (result == null) result = caseClassContent(codeInjection);
        if (result == null) result = caseAssociationClassContent(codeInjection);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ATTRIBUTE:
      {
        Attribute attribute = (Attribute)theEObject;
        T result = caseAttribute(attribute);
        if (result == null) result = caseClassContent(attribute);
        if (result == null) result = caseAssociationClassContent(attribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.STATE_MACHINE_DEFINITION:
      {
        StateMachineDefinition stateMachineDefinition = (StateMachineDefinition)theEObject;
        T result = caseStateMachineDefinition(stateMachineDefinition);
        if (result == null) result = caseEntity(stateMachineDefinition);
        if (result == null) result = caseAbstractElement(stateMachineDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.STATE_MACHINE:
      {
        StateMachine stateMachine = (StateMachine)theEObject;
        T result = caseStateMachine(stateMachine);
        if (result == null) result = caseClassContent(stateMachine);
        if (result == null) result = caseAssociationClassContent(stateMachine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.INLINE_STATE_MACHINE:
      {
        InlineStateMachine inlineStateMachine = (InlineStateMachine)theEObject;
        T result = caseInlineStateMachine(inlineStateMachine);
        if (result == null) result = caseStateMachine(inlineStateMachine);
        if (result == null) result = caseClassContent(inlineStateMachine);
        if (result == null) result = caseAssociationClassContent(inlineStateMachine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.REFERENCED_STATE_MACHINE:
      {
        ReferencedStateMachine referencedStateMachine = (ReferencedStateMachine)theEObject;
        T result = caseReferencedStateMachine(referencedStateMachine);
        if (result == null) result = caseStateMachine(referencedStateMachine);
        if (result == null) result = caseClassContent(referencedStateMachine);
        if (result == null) result = caseAssociationClassContent(referencedStateMachine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ENUM:
      {
        cruise.umple.umple.Enum enum_ = (cruise.umple.umple.Enum)theEObject;
        T result = caseEnum(enum_);
        if (result == null) result = caseStateMachine(enum_);
        if (result == null) result = caseClassContent(enum_);
        if (result == null) result = caseAssociationClassContent(enum_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.STATE:
      {
        State state = (State)theEObject;
        T result = caseState(state);
        if (result == null) result = caseStateEntity(state);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.STATE_ENTITY:
      {
        StateEntity stateEntity = (StateEntity)theEObject;
        T result = caseStateEntity(stateEntity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.TRANSITION:
      {
        Transition transition = (Transition)theEObject;
        T result = caseTransition(transition);
        if (result == null) result = caseStateEntity(transition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EVENT_DEFINITION:
      {
        EventDefinition eventDefinition = (EventDefinition)theEObject;
        T result = caseEventDefinition(eventDefinition);
        if (result == null) result = caseTransition(eventDefinition);
        if (result == null) result = caseStateEntity(eventDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EVENT:
      {
        Event event = (Event)theEObject;
        T result = caseEvent(event);
        if (result == null) result = caseEventDefinition(event);
        if (result == null) result = caseTransition(event);
        if (result == null) result = caseStateEntity(event);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.AFTER_EVERY_EVENT:
      {
        AfterEveryEvent afterEveryEvent = (AfterEveryEvent)theEObject;
        T result = caseAfterEveryEvent(afterEveryEvent);
        if (result == null) result = caseEventDefinition(afterEveryEvent);
        if (result == null) result = caseTransition(afterEveryEvent);
        if (result == null) result = caseStateEntity(afterEveryEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.AFTER_EVENT:
      {
        AfterEvent afterEvent = (AfterEvent)theEObject;
        T result = caseAfterEvent(afterEvent);
        if (result == null) result = caseEventDefinition(afterEvent);
        if (result == null) result = caseTransition(afterEvent);
        if (result == null) result = caseStateEntity(afterEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ACTION:
      {
        Action action = (Action)theEObject;
        T result = caseAction(action);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ENTRY_OR_EXIT_ACTION:
      {
        EntryOrExitAction entryOrExitAction = (EntryOrExitAction)theEObject;
        T result = caseEntryOrExitAction(entryOrExitAction);
        if (result == null) result = caseStateEntity(entryOrExitAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ACTIVITY:
      {
        Activity activity = (Activity)theEObject;
        T result = caseActivity(activity);
        if (result == null) result = caseStateEntity(activity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.GUARD:
      {
        Guard guard = (Guard)theEObject;
        T result = caseGuard(guard);
        if (result == null) result = caseTransition(guard);
        if (result == null) result = caseStateEntity(guard);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.GUARD_CODE:
      {
        GuardCode guardCode = (GuardCode)theEObject;
        T result = caseGuardCode(guardCode);
        if (result == null) result = caseGuard(guardCode);
        if (result == null) result = caseTransition(guardCode);
        if (result == null) result = caseStateEntity(guardCode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.POSITION:
      {
        Position position = (Position)theEObject;
        T result = casePosition(position);
        if (result == null) result = caseClassContent(position);
        if (result == null) result = caseAssociationClassContent(position);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ELEMENT_POSITION:
      {
        ElementPosition elementPosition = (ElementPosition)theEObject;
        T result = caseElementPosition(elementPosition);
        if (result == null) result = casePosition(elementPosition);
        if (result == null) result = caseClassContent(elementPosition);
        if (result == null) result = caseAssociationClassContent(elementPosition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ASSOCIATION_POSITION:
      {
        AssociationPosition associationPosition = (AssociationPosition)theEObject;
        T result = caseAssociationPosition(associationPosition);
        if (result == null) result = casePosition(associationPosition);
        if (result == null) result = caseClassContent(associationPosition);
        if (result == null) result = caseAssociationClassContent(associationPosition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.COORDINATE:
      {
        Coordinate coordinate = (Coordinate)theEObject;
        T result = caseCoordinate(coordinate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.UMPLE_ELEMENT:
      {
        UmpleElement umpleElement = (UmpleElement)theEObject;
        T result = caseUmpleElement(umpleElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.BLOCK:
      {
        block block = (block)theEObject;
        T result = caseblock(block);
        if (result == null) result = casestatement(block);
        if (result == null) result = caseblockStatement(block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.PAR_EXPRESSION:
      {
        parExpression parExpression = (parExpression)theEObject;
        T result = caseparExpression(parExpression);
        if (result == null) result = caseprimary(parExpression);
        if (result == null) result = caseunaryExpressionNotPlusMinus(parExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EXPRESSION_LIST:
      {
        expressionList expressionList = (expressionList)theEObject;
        T result = caseexpressionList(expressionList);
        if (result == null) result = caseforUpdate(expressionList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EXPRESSION:
      {
        expression expression = (expression)theEObject;
        T result = caseexpression(expression);
        if (result == null) result = caseGuardCode(expression);
        if (result == null) result = caseparExpression(expression);
        if (result == null) result = caseexpressionstatement(expression);
        if (result == null) result = caseGuard(expression);
        if (result == null) result = caseprimary(expression);
        if (result == null) result = casestatement(expression);
        if (result == null) result = caseTransition(expression);
        if (result == null) result = caseunaryExpressionNotPlusMinus(expression);
        if (result == null) result = caseblockStatement(expression);
        if (result == null) result = caseStateEntity(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.CONDITIONAL_EXPRESSION:
      {
        conditionalExpression conditionalExpression = (conditionalExpression)theEObject;
        T result = caseconditionalExpression(conditionalExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.CONDITIONAL_OR_EXPRESSION:
      {
        conditionalOrExpression conditionalOrExpression = (conditionalOrExpression)theEObject;
        T result = caseconditionalOrExpression(conditionalOrExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.CONDITIONAL_AND_EXPRESSION:
      {
        conditionalAndExpression conditionalAndExpression = (conditionalAndExpression)theEObject;
        T result = caseconditionalAndExpression(conditionalAndExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EQUALITY_EXPRESSION:
      {
        equalityExpression equalityExpression = (equalityExpression)theEObject;
        T result = caseequalityExpression(equalityExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.RELATIONAL_EXPRESSION:
      {
        relationalExpression relationalExpression = (relationalExpression)theEObject;
        T result = caserelationalExpression(relationalExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ADDITIVE_EXPRESSION:
      {
        additiveExpression additiveExpression = (additiveExpression)theEObject;
        T result = caseadditiveExpression(additiveExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.MULTIPLICATIVE_EXPRESSION:
      {
        multiplicativeExpression multiplicativeExpression = (multiplicativeExpression)theEObject;
        T result = casemultiplicativeExpression(multiplicativeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.UNARY_EXPRESSION:
      {
        unaryExpression unaryExpression = (unaryExpression)theEObject;
        T result = caseunaryExpression(unaryExpression);
        if (result == null) result = caseunaryExpressionNotPlusMinus(unaryExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.UNARY_EXPRESSION_NOT_PLUS_MINUS:
      {
        unaryExpressionNotPlusMinus unaryExpressionNotPlusMinus = (unaryExpressionNotPlusMinus)theEObject;
        T result = caseunaryExpressionNotPlusMinus(unaryExpressionNotPlusMinus);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.PRIMARY:
      {
        primary primary = (primary)theEObject;
        T result = caseprimary(primary);
        if (result == null) result = caseunaryExpressionNotPlusMinus(primary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.LITERAL:
      {
        literal literal = (literal)theEObject;
        T result = caseliteral(literal);
        if (result == null) result = caseprimary(literal);
        if (result == null) result = caseunaryExpressionNotPlusMinus(literal);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FUNCTION_CALL:
      {
        functionCall functionCall = (functionCall)theEObject;
        T result = casefunctionCall(functionCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FUNCTION_DECLARATION:
      {
        functionDeclaration functionDeclaration = (functionDeclaration)theEObject;
        T result = casefunctionDeclaration(functionDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FUNCTION_DEFINITION:
      {
        functionDefinition functionDefinition = (functionDefinition)theEObject;
        T result = casefunctionDefinition(functionDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.JAVA_FUNCTION_DECLARATION:
      {
        javaFunctionDeclaration javaFunctionDeclaration = (javaFunctionDeclaration)theEObject;
        T result = casejavaFunctionDeclaration(javaFunctionDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.JAVA_FUNCTION_DEFINITION:
      {
        javaFunctionDefinition javaFunctionDefinition = (javaFunctionDefinition)theEObject;
        T result = casejavaFunctionDefinition(javaFunctionDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.JAVA_FUNCTION_CALL:
      {
        javaFunctionCall javaFunctionCall = (javaFunctionCall)theEObject;
        T result = casejavaFunctionCall(javaFunctionCall);
        if (result == null) result = caseliteral(javaFunctionCall);
        if (result == null) result = casefunctionCall(javaFunctionCall);
        if (result == null) result = caseprimary(javaFunctionCall);
        if (result == null) result = caseunaryExpressionNotPlusMinus(javaFunctionCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.BLOCK_STATEMENT:
      {
        blockStatement blockStatement = (blockStatement)theEObject;
        T result = caseblockStatement(blockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.LOCAL_VARIABLE_DECLARATION_STATEMENT:
      {
        localVariableDeclarationStatement localVariableDeclarationStatement = (localVariableDeclarationStatement)theEObject;
        T result = caselocalVariableDeclarationStatement(localVariableDeclarationStatement);
        if (result == null) result = caseblockStatement(localVariableDeclarationStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.LOCAL_VARIABLE_DECLARATION:
      {
        localVariableDeclaration localVariableDeclaration = (localVariableDeclaration)theEObject;
        T result = caselocalVariableDeclaration(localVariableDeclaration);
        if (result == null) result = caselocalVariableDeclarationStatement(localVariableDeclaration);
        if (result == null) result = caseforInit(localVariableDeclaration);
        if (result == null) result = caseblockStatement(localVariableDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.VARIABLE_DECLARATORS:
      {
        variableDeclarators variableDeclarators = (variableDeclarators)theEObject;
        T result = casevariableDeclarators(variableDeclarators);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.VARIABLE_DECLARATOR:
      {
        variableDeclarator variableDeclarator = (variableDeclarator)theEObject;
        T result = casevariableDeclarator(variableDeclarator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.STATEMENT:
      {
        statement statement = (statement)theEObject;
        T result = casestatement(statement);
        if (result == null) result = caseblockStatement(statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.ENDSTATEMENT:
      {
        endstatement endstatement = (endstatement)theEObject;
        T result = caseendstatement(endstatement);
        if (result == null) result = casestatement(endstatement);
        if (result == null) result = caseblockStatement(endstatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.BREAKSTATEMENT:
      {
        breakstatement breakstatement = (breakstatement)theEObject;
        T result = casebreakstatement(breakstatement);
        if (result == null) result = casestatement(breakstatement);
        if (result == null) result = caseblockStatement(breakstatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.IFSTATEMENT:
      {
        ifstatement ifstatement = (ifstatement)theEObject;
        T result = caseifstatement(ifstatement);
        if (result == null) result = casestatement(ifstatement);
        if (result == null) result = caseblockStatement(ifstatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FORSTATEMENT:
      {
        forstatement forstatement = (forstatement)theEObject;
        T result = caseforstatement(forstatement);
        if (result == null) result = casestatement(forstatement);
        if (result == null) result = caseblockStatement(forstatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.WHILESTATEMENT:
      {
        whilestatement whilestatement = (whilestatement)theEObject;
        T result = casewhilestatement(whilestatement);
        if (result == null) result = casestatement(whilestatement);
        if (result == null) result = caseblockStatement(whilestatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.RETURNSTATEMENT:
      {
        returnstatement returnstatement = (returnstatement)theEObject;
        T result = casereturnstatement(returnstatement);
        if (result == null) result = casestatement(returnstatement);
        if (result == null) result = caseblockStatement(returnstatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.EXPRESSIONSTATEMENT:
      {
        expressionstatement expressionstatement = (expressionstatement)theEObject;
        T result = caseexpressionstatement(expressionstatement);
        if (result == null) result = casestatement(expressionstatement);
        if (result == null) result = caseblockStatement(expressionstatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.TRYSTATEMENT:
      {
        trystatement trystatement = (trystatement)theEObject;
        T result = casetrystatement(trystatement);
        if (result == null) result = casestatement(trystatement);
        if (result == null) result = caseblockStatement(trystatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FOR_CONTROL:
      {
        forControl forControl = (forControl)theEObject;
        T result = caseforControl(forControl);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FOR_INIT:
      {
        forInit forInit = (forInit)theEObject;
        T result = caseforInit(forInit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.FOR_UPDATE:
      {
        forUpdate forUpdate = (forUpdate)theEObject;
        T result = caseforUpdate(forUpdate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.PHP_FUNCTION:
      {
        phpFunction phpFunction = (phpFunction)theEObject;
        T result = casephpFunction(phpFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.PHP_BLOCK:
      {
        phpBlock phpBlock = (phpBlock)theEObject;
        T result = casephpBlock(phpBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmplePackage.KEY:
      {
        Key key = (Key)theEObject;
        T result = caseKey(key);
        if (result == null) result = caseKeyDefinition(key);
        if (result == null) result = caseSoftwarePattern(key);
        if (result == null) result = caseClassContent(key);
        if (result == null) result = caseAssociationClassContent(key);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUmpleModel(UmpleModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAbstractElement(AbstractElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Glossary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Glossary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGlossary(Glossary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Word</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Word</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWord(Word object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Generate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Generate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGenerate(Generate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Use Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Use Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUseStatement(UseStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamespace(Namespace object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntity(Entity object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassDefinition(ClassDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>External Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>External Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExternalDefinition(ExternalDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Interface Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInterfaceDefinition(InterfaceDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociationDefinition(AssociationDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassContent(ClassContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association Class Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association Class Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociationClassContent(AssociationClassContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Depend</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Depend</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDepend(Depend object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociation(Association object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Symmetric Reflexive Association</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Symmetric Reflexive Association</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSymmetricReflexiveAssociation(SymmetricReflexiveAssociation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Inline Association</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Inline Association</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInlineAssociation(InlineAssociation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Single Association End</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Single Association End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSingleAssociationEnd(SingleAssociationEnd object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association Class Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association Class Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociationClassDefinition(AssociationClassDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Software Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Software Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSoftwarePattern(SoftwarePattern object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>is A</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>is A</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseisA(isA object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Singleton</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Singleton</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSingleton(Singleton object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Key Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKeyDefinition(KeyDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Code Injection</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Code Injection</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeInjection(CodeInjection object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttribute(Attribute object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>State Machine Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Machine Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStateMachineDefinition(StateMachineDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>State Machine</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Machine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStateMachine(StateMachine object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Inline State Machine</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Inline State Machine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInlineStateMachine(InlineStateMachine object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Referenced State Machine</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Referenced State Machine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferencedStateMachine(ReferencedStateMachine object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enum</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enum</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnum(cruise.umple.umple.Enum object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>State</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseState(State object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>State Entity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStateEntity(StateEntity object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTransition(Transition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Event Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEventDefinition(EventDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEvent(Event object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>After Every Event</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>After Every Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAfterEveryEvent(AfterEveryEvent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>After Event</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>After Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAfterEvent(AfterEvent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAction(Action object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entry Or Exit Action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entry Or Exit Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntryOrExitAction(EntryOrExitAction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseActivity(Activity object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Guard</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Guard</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGuard(Guard object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Guard Code</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Guard Code</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGuardCode(GuardCode object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Position</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Position</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePosition(Position object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Position</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Position</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementPosition(ElementPosition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association Position</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association Position</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociationPosition(AssociationPosition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Coordinate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Coordinate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCoordinate(Coordinate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUmpleElement(UmpleElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseblock(block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>par Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>par Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseparExpression(parExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>expression List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>expression List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexpressionList(expressionList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexpression(expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditionalExpression(conditionalExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional Or Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional Or Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditionalOrExpression(conditionalOrExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional And Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional And Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditionalAndExpression(conditionalAndExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>equality Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>equality Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseequalityExpression(equalityExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>relational Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>relational Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserelationalExpression(relationalExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>additive Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>additive Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseadditiveExpression(additiveExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>multiplicative Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>multiplicative Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemultiplicativeExpression(multiplicativeExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>unary Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>unary Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseunaryExpression(unaryExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>unary Expression Not Plus Minus</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>unary Expression Not Plus Minus</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseunaryExpressionNotPlusMinus(unaryExpressionNotPlusMinus object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>primary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>primary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseprimary(primary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseliteral(literal object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunctionCall(functionCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunctionDeclaration(functionDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunctionDefinition(functionDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>java Function Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>java Function Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casejavaFunctionDeclaration(javaFunctionDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>java Function Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>java Function Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casejavaFunctionDefinition(javaFunctionDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>java Function Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>java Function Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casejavaFunctionCall(javaFunctionCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseblockStatement(blockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>local Variable Declaration Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>local Variable Declaration Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselocalVariableDeclarationStatement(localVariableDeclarationStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>local Variable Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>local Variable Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselocalVariableDeclaration(localVariableDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variable Declarators</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variable Declarators</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariableDeclarators(variableDeclarators object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variable Declarator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variable Declarator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariableDeclarator(variableDeclarator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestatement(statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>endstatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>endstatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseendstatement(endstatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>breakstatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>breakstatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casebreakstatement(breakstatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ifstatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ifstatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseifstatement(ifstatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>forstatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>forstatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseforstatement(forstatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>whilestatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>whilestatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casewhilestatement(whilestatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>returnstatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>returnstatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casereturnstatement(returnstatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>expressionstatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>expressionstatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexpressionstatement(expressionstatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>trystatement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>trystatement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetrystatement(trystatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for Control</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for Control</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseforControl(forControl object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for Init</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for Init</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseforInit(forInit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for Update</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for Update</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseforUpdate(forUpdate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>php Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>php Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casephpFunction(phpFunction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>php Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>php Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casephpBlock(phpBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Key</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKey(Key object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //UmpleSwitch
