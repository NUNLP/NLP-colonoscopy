

/* First created by JCasGen Tue Nov 29 22:48:30 CST 2016 */
package edu.northwestern.chip.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import clinicalnlp.types.NamedEntityMention;


/** 
 * Updated by JCasGen Tue Nov 29 22:48:30 CST 2016
 * XML source: /var/folders/k0/jcxw1d05549c48zgccrbj_q40000gp/T/leoTypeDescription_d30af467-93e0-418d-8f9f-65b866d376ba1787412053323460902.xml
 * @generated */
public class HistologyFinding extends NamedEntityMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(HistologyFinding.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected HistologyFinding() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public HistologyFinding(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public HistologyFinding(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public HistologyFinding(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: site

  /** getter for site - gets 
   * @generated
   * @return value of the feature 
   */
  public AnatomicalSite getSite() {
    if (HistologyFinding_Type.featOkTst && ((HistologyFinding_Type)jcasType).casFeat_site == null)
      jcasType.jcas.throwFeatMissing("site", "edu.northwestern.chip.types.HistologyFinding");
    return (AnatomicalSite)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((HistologyFinding_Type)jcasType).casFeatCode_site)));}
    
  /** setter for site - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSite(AnatomicalSite v) {
    if (HistologyFinding_Type.featOkTst && ((HistologyFinding_Type)jcasType).casFeat_site == null)
      jcasType.jcas.throwFeatMissing("site", "edu.northwestern.chip.types.HistologyFinding");
    jcasType.ll_cas.ll_setRefValue(addr, ((HistologyFinding_Type)jcasType).casFeatCode_site, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    