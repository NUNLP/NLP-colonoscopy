package extent

import gov.va.queri.domain.ColonPolypConcept
import gov.va.queri.dsl.UIMA_DSL
import gov.va.queri.types.*
import gov.va.vinci.leo.window.types.Window

//---------------------------------------------------------------------------------------------------------------------
// create mention annotations from anatomical site patterns
//---------------------------------------------------------------------------------------------------------------------
UIMA_DSL.createMentions(
    patterns:[
        ////(~/(?i)\b(colon)\b/):[group:1] << COLON.map,
        (~/(?is)\b((left|distal)\s+colon)\b/):[group:1] << ColonPolypConcept.LEFT_COLON.map,
        (~/(?is)\b(colon,\s+left)\b/):[group:1] << ColonPolypConcept.LEFT_COLON.map,
        (~/(?is)\b((right|proximal)\s+colon)\b/):[group:1] << ColonPolypConcept.RIGHT_COLON.map,
        (~/(?is)\b(colon,\s+right)\b/):[group:1] << ColonPolypConcept.RIGHT_COLON.map,
        ////(~/(?is)\b(rectum)\b/):[group:1] << RECTUM.map,
        (~/(?is)\b(recto(\s|-)?sigmoid(\s+colon)?)\b/):[group:1] << ColonPolypConcept.RECTO_SIGMOID.map,
        (~/(?is)\b((proximal\s+|distal\s+)?sigmoid(\s+colon)?)\b/):[group:1] << ColonPolypConcept.SIGMOID_COLON.map,
        (~/(?is)\b((proximal\s+|distal\s+)?descending(\s+colon)?)\b/):[group:1] << ColonPolypConcept.DESCENDING_COLON.map,
        (~/(?is)\b(splenic(\s+flexures?)?)\b/):[group:1] << ColonPolypConcept.SPLENIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?transverse(\s+colon)?)\b/):[group:1] << ColonPolypConcept.TRANSVERSE_COLON.map,
        (~/(?is)\b(hepatic(\s+flexures?)?)\b/):[group:1] << ColonPolypConcept.HEPATIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?ascending(\s+colon)?)\b/):[group:1] << ColonPolypConcept.ASCENDING_COLON.map,
        (~/(?is)\b((terminal\s+)?ileum)\b/):[group:1] << ColonPolypConcept.ILEUM.map,
        (~/(?is)\b((ileo-?ca?ecal)(\s+valve)?)\b/):[group:1] << ColonPolypConcept.ILEOCECAL_VALVE.map,
        (~/(?is)\b(ICV|IC\s+valve)\b/):[group:1] << ColonPolypConcept.ILEOCECAL_VALVE.map,
        (~/(?is)\b(ca?ecum)\b/):[group:1] << ColonPolypConcept.CECUM.map,
        (~/(?is)\b((ori?fi?ce\s+of\s+the\s+)?app?endix|app?endic(e|i)al(\s+ori?fi?ce)?)\b/):[group:1] << ColonPolypConcept.APPENDIX.map,
        (~/(?is)\b(anastomosis)\b/):[group:1] << ColonPolypConcept.ANASTOMOSIS.map
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


