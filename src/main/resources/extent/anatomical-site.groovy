import clinicalnlp.dsl.UIMA_DSL
import clinicalnlp.types.Segment
import clinicalnlp.types.Token
import edu.northwestern.chip.domain.Concept
import edu.northwestern.chip.types.AnatomicalSite
import edu.northwestern.chip.types.LeftWindow
import edu.northwestern.chip.types.RightWindow
import gov.va.vinci.leo.window.types.Window

//---------------------------------------------------------------------------------------------------------------------
// create mention annotations from anatomical site patterns
//---------------------------------------------------------------------------------------------------------------------
UIMA_DSL.createMentions(
    patterns:[
        ////(~/(?i)\b(colon)\b/):[group:1] << COLON.map,
        (~/(?is)\b((left|distal)\s+colon)\b/):[group:1] << Concept.LEFT_COLON.map,
        (~/(?is)\b(colon,\s+left)\b/):[group:1] << Concept.LEFT_COLON.map,
        (~/(?is)\b((right|proximal)\s+colon)\b/):[group:1] << Concept.RIGHT_COLON.map,
        (~/(?is)\b(colon,\s+right)\b/):[group:1] << Concept.RIGHT_COLON.map,
        ////(~/(?is)\b(rectum)\b/):[group:1] << RECTUM.map,
        (~/(?is)\b(recto(\s|-)?sigmoid(\s+colon)?)\b/):[group:1] << Concept.RECTO_SIGMOID.map,
        (~/(?is)\b((proximal\s+|distal\s+)?sigmoid(\s+colon)?)\b/):[group:1] << Concept.SIGMOID_COLON.map,
        (~/(?is)\b((proximal\s+|distal\s+)?descending(\s+colon)?)\b/):[group:1] << Concept.DESCENDING_COLON.map,
        (~/(?is)\b(splenic(\s+flexures?)?)\b/):[group:1] << Concept.SPLENIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?transverse(\s+colon)?)\b/):[group:1] << Concept.TRANSVERSE_COLON.map,
        (~/(?is)\b(hepatic(\s+flexures?)?)\b/):[group:1] << Concept.HEPATIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?ascending(\s+colon)?)\b/):[group:1] << Concept.ASCENDING_COLON.map,
        (~/(?is)\b((terminal\s+)?ileum)\b/):[group:1] << Concept.ILEUM.map,
        (~/(?is)\b((ileo-?ca?ecal)(\s+valve)?)\b/):[group:1] << Concept.ILEOCECAL_VALVE.map,
        (~/(?is)\b(ICV|IC\s+valve)\b/):[group:1] << Concept.ILEOCECAL_VALVE.map,
        (~/(?is)\b(ca?ecum)\b/):[group:1] << Concept.CECUM.map,
        (~/(?is)\b((ori?fi?ce\s+of\s+the\s+)?app?endix|app?endic(e|i)al(\s+ori?fi?ce)?)\b/):[group:1] << Concept.APPENDIX.map,
        (~/(?is)\b(anastomosis)\b/):[group:1] << Concept.ANASTOMOSIS.map
    ],
    jcas:jcas,
    searchSet:jcas.select(type:Segment),
    type:AnatomicalSite,
    longestMatch:true
)

//---------------------------------------------------------------------------------------------------------------------
// create token windows around anatomical site mentions
//---------------------------------------------------------------------------------------------------------------------
(jcas.select(type:Segment)).each { Segment seg ->
    jcas.createTokenWindow(
        ann:seg,
        anchorType:AnatomicalSite,
        tokenType:Token,
        windowType:Window,
        rightWindowType:RightWindow,
        leftWindowType:LeftWindow,
        leftTokenCount:10,
        rightTokenCount:10
    )
}


