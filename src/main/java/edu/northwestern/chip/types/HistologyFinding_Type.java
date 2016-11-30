
/* First created by JCasGen Tue Nov 29 22:48:30 CST 2016 */
package edu.northwestern.chip.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import clinicalnlp.types.NamedEntityMention_Type;

/** 
 * Updated by JCasGen Tue Nov 29 22:48:30 CST 2016
 * @generated */
public class HistologyFinding_Type extends NamedEntityMention_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (HistologyFinding_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = HistologyFinding_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new HistologyFinding(addr, HistologyFinding_Type.this);
  			   HistologyFinding_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new HistologyFinding(addr, HistologyFinding_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = HistologyFinding.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.northwestern.chip.types.HistologyFinding");
 
  /** @generated */
  final Feature casFeat_site;
  /** @generated */
  final int     casFeatCode_site;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSite(int addr) {
        if (featOkTst && casFeat_site == null)
      jcas.throwFeatMissing("site", "edu.northwestern.chip.types.HistologyFinding");
    return ll_cas.ll_getRefValue(addr, casFeatCode_site);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSite(int addr, int v) {
        if (featOkTst && casFeat_site == null)
      jcas.throwFeatMissing("site", "edu.northwestern.chip.types.HistologyFinding");
    ll_cas.ll_setRefValue(addr, casFeatCode_site, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public HistologyFinding_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_site = jcas.getRequiredFeatureDE(casType, "site", "edu.northwestern.chip.types.AnatomicalSite", featOkTst);
    casFeatCode_site  = (null == casFeat_site) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_site).getCode();

  }
}



    