import clinicalnlp.pattern.AnnotationSequencer
import clinicalnlp.types.Segment
import clinicalnlp.types.SegmentHeading
import org.apache.uima.jcas.tcas.DocumentAnnotation

import java.util.regex.Matcher
import java.util.regex.Pattern

headings.each { Pattern pat, String code ->
    Matcher matcher = jcas.documentText =~ pat
    matcher.each {
        jcas.create(type: SegmentHeading, begin: matcher.start(0), end: matcher.end(0), code: code)
    }
}

jcas.select(type: DocumentAnnotation).each { DocumentAnnotation docAnn ->
    def sequence = new AnnotationSequencer(docAnn, [SegmentHeading]).first()
    segPattern1.matcher(sequence).each { Binding b ->
        SegmentHeading h1 = b.getVariable('h1')[0]
        SegmentHeading h2 = b.getVariable('h2')[0]
        jcas.create(type:Segment,
            begin:h1.end,
            end:h2.begin,
            divType:h1.code
        )
    }
    segPattern2.matcher(sequence).each { Binding b ->
        SegmentHeading h1 = b.getVariable('h1')[0]
        jcas.create(type:Segment,
            begin:h1.end,
            end:jcas.documentText.length(),
            divType:h1.code
        )
    }
}

if (jcas.select(type:Segment).size() == 0) {
    jcas.create(type:Segment, begin:0, end:jcas.documentText.length())
}

