import clinicalnlp.dsl.DSL
import clinicalnlp.types.Segment
import edu.northwestern.chip.domain.Concept
import edu.northwestern.chip.types.AnatomicalSite

import static clinicalnlp.dsl.DSL.*

//---------------------------------------------------------------------------------------------------------------------
// create mention annotations from anatomical site patterns
//---------------------------------------------------------------------------------------------------------------------
createMentions(
    patterns:[
        //(~/(?i)\b(colon)\b/):[group:0] << Concept.COLON.map,
        (~/(?is)\b((left|distal)\s+colon)\b/):[group:0] << Concept.LEFT_COLON.map,
        (~/(?is)\b(colon,\s+left)\b/):[group:0] << Concept.LEFT_COLON.map,
        (~/(?is)\b((right|proximal)\s+colon)\b/):[group:0] << Concept.RIGHT_COLON.map,
        (~/(?is)\b(colon,\s+right)\b/):[group:0] << Concept.RIGHT_COLON.map,
        (~/(?is)\b(rectum|rectal)\b/):[group:0] << Concept.RECTUM.map,
        (~/(?is)\b(recto(\s|-)?sigmoid(\s+colon)?)\b/):[group:0] << Concept.RECTO_SIGMOID.map,
        (~/(?is)\b((proximal\s+|distal\s+)?sigmoid(\s+colon)?)\b/):[group:0] << Concept.SIGMOID_COLON.map,
        (~/(?is)\b((proximal\s+|distal\s+)?descending(\s+colon)?)\b/):[group:0] << Concept.DESCENDING_COLON.map,
        (~/(?is)\b(splenic(\s+flexures?)?)\b/):[group:0] << Concept.SPLENIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?transverse(\s+colon)?)\b/):[group:0] << Concept.TRANSVERSE_COLON.map,
        (~/(?is)\b(hepatic(\s+flexures?)?)\b/):[group:0] << Concept.HEPATIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?ascending(\s+colon)?)\b/):[group:0] << Concept.ASCENDING_COLON.map,
        (~/(?is)\b((terminal\s+)?ileum)\b/):[group:0] << Concept.ILEUM.map,
        (~/(?is)\b(ca?ecum|cecal)\b/):[group:0] << Concept.CECUM.map
    ],
    jcas:jcas,
    searchSet:jcas.select(type:Segment),
    type:AnatomicalSite,
    longestMatch:true
)



