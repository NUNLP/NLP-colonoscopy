import clinicalnlp.dsl.AnnotationPattern
import edu.northwestern.chip.types.AnatomicalSite
import clinicalnlp.types.Token

// ---------------------------------------------------------------------------------------------------------------------
// PATTERN 1: explicit mention of exam extent
// EXAMPLE: "cecum was easily reached."
// ---------------------------------------------------------------------------------------------------------------------
def rightTriggers1 = /reached|entered|intubated/
// noinspection GroovyAssignabilityCheck
AnnotationPattern pat1 = new AnnotationPattern(
    new NodeBuilder().regex (caseInsensitive:true) {
        include(type:AnatomicalSite)
        include(type:Token)
        match {
            node(type:AnatomicalSite, name:'as1')
            node(type:Token, range:[0,5])
            node(type:Token, name:'t1', text:rightTriggers1)
        }
    }
)
// ---------------------------------------------------------------------------------------------------------------------
// PATTERN 2: implicit mention of exam extent
// EXAMPLE: "cecum was identified."
// EXAMPLE: "ileocecal valve and appendiceal orifice were visualized"
// ---------------------------------------------------------------------------------------------------------------------
def rightTriggers2 = /identified|seen|verified|visualised|visualized|confirmed|recognized/
// noinspection GroovyAssignabilityCheck
AnnotationPattern pat2 = new AnnotationPattern(
    new NodeBuilder().regex (caseInsensitive:true) {
        include(type:AnatomicalSite)
        include(type:Token)
        match {
            node(type:AnatomicalSite, name:'as1')
            node(type:Token, range:[0,5])
            node(type:'group', range:[0,1]) {
                node(type:AnatomicalSite, name:'as2')
                node(type:Token, range:[0,5])
            }
            node(type:Token, name:'t1', text:rightTriggers2)
        }
    }
)
// ---------------------------------------------------------------------------------------------------------------------
// PATTERN 3: explicit mention of exam extent
// EXAMPLE: "Exam to the ascending colon"
// EXAMPLE: "Inserted to cecum"
// EXAMPLE: "PROCEDURE: to the terminal ileum"
// EXAMPLE: "Colonoscopy: to cecum"
// ---------------------------------------------------------------------------------------------------------------------
def leftTriggers1 = /procedure|exam|examination|extent|colonoscopy|inserted|done|performed/
// noinspection GroovyAssignabilityCheck
AnnotationPattern pat3 = new AnnotationPattern(
    new NodeBuilder().regex (caseInsensitive:true) {
        include(type:AnatomicalSite)
        include(type:Token)
        match {
            node(type:'group', range:[0,1]) {
                node(type:Token, name:'t2', text:leftTriggers1)
                node(type:Token, range:[0,1])
            }
            node(type:Token, name:'t1', text:leftTriggers1)
            node(type:Token, range:[0,1], text:/:/)
            node(type:Token, range:[0,1], text:/to/)
            node(type:Token, range:[0,1], text:/the/)
            node(type:AnatomicalSite, name:'as1')
        }
    }
)
// ---------------------------------------------------------------------------------------------------------------------
// PATTERN 4: explicit mention of exam extent
// EXAMPLE: "advanced to the sigmoid colon"
// EXAMPLE: "passed to the cecum"
// ---------------------------------------------------------------------------------------------------------------------
def leftTriggers2 = /advanced|passed|reached|driven|length|level|depth/
// noinspection GroovyAssignabilityCheck
AnnotationPattern pat4 = new AnnotationPattern(
    new NodeBuilder().regex (caseInsensitive:true) {
        include(type:AnatomicalSite)
        include(type:Token)
        match {
            node(type:Token, name:'t1', text:leftTriggers2)
            node(type:Token, range:[0,10])
            node(type:AnatomicalSite, name:'as1')
        }
    }
)
// ---------------------------------------------------------------------------------------------------------------------
// PATTERN 5: implicit mention of exam extent
// EXAMPLE: "confirmed by visualization of the appendiceal orifice"
// EXAMPLE: "identified by visualization of the ileocecal valve and appendiceal orifice."
// ---------------------------------------------------------------------------------------------------------------------
def leftTriggers3 = /confirmed|identified|verified|identification/
// noinspection GroovyAssignabilityCheck
AnnotationPattern pat5 = new AnnotationPattern(
    new NodeBuilder().regex (caseInsensitive:true) {
        include(type:AnatomicalSite)
        include(type:Token)
        match {
            node(type:Token, name:'t1', text:leftTriggers3)
            node(type:Token, range:[0,5])
            node(type:AnatomicalSite, name:'as1')
            node(type:'group', name:'conj', range:[0,1]) {
                node(type: Token, range: [0, 5])
                node(type: AnatomicalSite, name: 'as2')
            }
        }
    }
)

// -----------------------------------------------------------------------------------------------------------------
// binding variable map
// -----------------------------------------------------------------------------------------------------------------
[
    pat1:pat1, pat2:pat2, pat3:pat3, pat4:pat4, pat5:pat5
]
