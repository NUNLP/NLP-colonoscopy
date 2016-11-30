import clinicalnlp.dsl.AnnotationPattern
import edu.northwestern.chip.types.AnatomicalSite

// ---------------------------------------------------------------------------------------------------------------------
// PATTERN 1: explicit mention of exam extent
// EXAMPLE: "cecum was easily reached."
// ---------------------------------------------------------------------------------------------------------------------
// noinspection GroovyAssignabilityCheck
AnnotationPattern pat1 = new AnnotationPattern(
    new NodeBuilder().regex (caseInsensitive:true) {
        include(type:AnatomicalSite)
        match {
            node(type:AnatomicalSite, name:'as1')
        }
        after(positive:true) {
            node(type:AnatomicalSite, name:'as2')
        }
    }
)

// -----------------------------------------------------------------------------------------------------------------
// binding variable map
// -----------------------------------------------------------------------------------------------------------------
[
    pat1:pat1
]
