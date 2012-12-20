/**
 * <copyright>
 * </copyright>
 *

 */
package cruise.umple.umple;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry Or Exit Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cruise.umple.umple.EntryOrExitAction#getType <em>Type</em>}</li>
 *   <li>{@link cruise.umple.umple.EntryOrExitAction#getActionCode <em>Action Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see cruise.umple.umple.UmplePackage#getEntryOrExitAction()
 * @model
 * @generated
 */
public interface EntryOrExitAction extends StateEntity
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see #setType(String)
   * @see cruise.umple.umple.UmplePackage#getEntryOrExitAction_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link cruise.umple.umple.EntryOrExitAction#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

  /**
   * Returns the value of the '<em><b>Action Code</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Action Code</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Action Code</em>' containment reference.
   * @see #setActionCode(block)
   * @see cruise.umple.umple.UmplePackage#getEntryOrExitAction_ActionCode()
   * @model containment="true"
   * @generated
   */
  block getActionCode();

  /**
   * Sets the value of the '{@link cruise.umple.umple.EntryOrExitAction#getActionCode <em>Action Code</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Action Code</em>' containment reference.
   * @see #getActionCode()
   * @generated
   */
  void setActionCode(block value);

} // EntryOrExitAction
