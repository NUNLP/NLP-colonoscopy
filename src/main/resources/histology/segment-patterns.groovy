import clinicalnlp.pattern.AnnotationPattern
import clinicalnlp.pattern.AnnotationRegex
import clinicalnlp.types.SegmentHeading

import static clinicalnlp.pattern.AnnotationPattern.*

headings = [
    (~/(?m)^[A-Z]\./) : 'FINDING',
]

AnnotationRegex segPattern1 = new AnnotationRegex((AnnotationPattern)
    $N('h1', $A(SegmentHeading)) & $N('h2', $A(SegmentHeading)) >> (true)
)

AnnotationRegex segPattern2 = new AnnotationRegex((AnnotationPattern)
    $N('h1', $A(SegmentHeading)) & $N('h2', $A(SegmentHeading)) >> (false)
)

// ---------------------------------------------------------------------------------------------------------------------
// binding variable map
// ---------------------------------------------------------------------------------------------------------------------
[
    headings:headings,
    segPattern1:segPattern1,
    segPattern2:segPattern2
]

