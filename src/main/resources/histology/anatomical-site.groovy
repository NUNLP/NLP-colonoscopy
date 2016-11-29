import static edu.northwestern.chip.domain.Concept.*
import edu.northwestern.chip.types.AnatomicalSite
import clinicalnlp.types.Segment
import clinicalnlp.dsl.UIMA_DSL

//---------------------------------------------------------------------------------------------------------------------
// create mention annotations from anatomical site patterns
//---------------------------------------------------------------------------------------------------------------------
UIMA_DSL.createMentions(
    patterns:[
        (~/(?is)\b(left|distal)\b/):[group:1] << LEFT_COLON.map,
        (~/(?is)\b(right|proximal)\b/):[group:1] << RIGHT_COLON.map,
        (~/(?is)\b(rectum|rectal)\b/):[group:1] << RECTUM.map,
        (~/(?is)\b(recto(\s|-)?sigmoid)\b/):[group:1] << RECTO_SIGMOID.map,
        (~/(?is)\b((proximal\s+|distal\s+)?sigmoid)\b/):[group:1] << SIGMOID_COLON.map,
        (~/(?is)\b((proximal\s+|distal\s+)?descending)\b/):[group:1] << DESCENDING_COLON.map,
        (~/(?is)\b(splenic(\s+flexures?)?)\b/):[group:1] << SPLENIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?transverse)\b/):[group:1] << TRANSVERSE_COLON.map,
        (~/(?is)\b(hepatic(\s+flexures?)?)\b/):[group:1] << HEPATIC_FLEXURE.map,
        (~/(?is)\b((proximal\s+|distal\s+)?ascending)\b/):[group:1] << ASCENDING_COLON.map,
        (~/(?is)\b((terminal\s+)?ileum)\b/):[group:1] << ILEUM.map,
        (~/(?is)\b((ileo-?ca?ecal)(\s+valve)?)\b/):[group:1] << ILEOCECAL_VALVE.map,
        (~/(?is)\b(ca?ecum)\b/):[group:1] << CECUM.map,
    ],
    jcas:jcas,
    searchSet:jcas.select(type:Segment),
    type:AnatomicalSite,
    longestMatch:true
)



