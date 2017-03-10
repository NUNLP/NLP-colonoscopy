import clinicalnlp.types.Segment
import edu.northwestern.chip.types.HistologyFinding

import static edu.northwestern.chip.domain.Concept.*

//----------------------------------------------------------------------------------------------------------------------
// create mention annotations from finding patterns
//----------------------------------------------------------------------------------------------------------------------

findingPatterns = [
    (~/(?i)(?s)ADENOMA/):[group:0] << ADENOMA.map,
    (~/(?i)(?s)TUBULAR(\s+COLONIC)?\s+ADENOMA/):[group:0] << TUBULAR_ADENOMA.map,
    (~/(?i)(?s)TUBULOVILLOUS(\s+COLONIC)?\s+ADENOMA/):[group:0] << TUBULOVILLOUS_ADENOMA.map,
    (~/(?i)(?s)VILLOUS(\s+COLONIC)?\s+ADENOMA/):[group:0] << VILLOUS_ADENOMA.map,
    (~/(?i)(?s)SERRATED(\s+COLONIC)?\s+(ADENOMA|FEATURE)/):[group:0] << SERRATED_ADENOMA.map,
    (~/(?i)(?s)SERRATED(\s+COLONIC)?\s+(POLYP)/):[group:0] << SERRATED_POLYP.map,
    (~/(?i)(?s)SESSILE\s+SERRATED(\s+COLONIC)?\s+ADENOMA/):[group:0] << SERRATED_ADENOMA.map,
    (~/(?i)(?s)TRADITIONAL\s+SERRATED\s+ADENOMA/):[group:0] << TRADIITIONAL_SERRATED_ADENOMA.map,
    (~/(?i)(?s)TRADITIONAL\s+SESSILE\s+SERRATED\s+ADENOMA/):[group:0] << TRADITIONAL_SESSILE_SERRATED_ADENOMA.map,
    (~/(?i)(?s)HYPERPLASTIC/):[group:0] << HYPERPLASTIC_POLYP.map,
    (~/(?i)(?s)INFLAMMATORY/):[group:0] << INFLAMMATORY_POLYP.map,
    (~/(?i)(?s)JUVENILE/):[group:0] << JUVENILE_POLYP.map,
    (~/(?i)(?s)HAMARTOMA/):[group:0] << HAMARTOMATOUS_POLYP.map,
    (~/(?i)(?s)CARCINOMA/):[group:0] << ADENOCARCINOMA.map
]

UIMA_DSL.createMentions(
    patterns:findingPatterns,
    jcas:jcas,
    searchSet:jcas.select(type:Segment),
    type:HistologyFinding,
    longestMatch:true
)



