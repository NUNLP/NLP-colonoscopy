import clinicalnlp.types.Segment
import edu.northwestern.chip.types.AnatomicalSite
import gov.va.vinci.leo.sentence.types.Sentence

// ---------------------------------------------------------------------------------------------------------------------
// match: (anatomical site + right window triggers)
// ---------------------------------------------------------------------------------------------------------------------
(jcas.select(type:Segment)).each { Segment segment ->
    pat1.matcher(segment).each { Binding b ->
        AnatomicalSite as1 = b.getVariable('as1')[0]
        AnatomicalSite as2 = b.getVariable('as2')[0]
        jcas.create(type:Sentence, begin:as1.begin, end:as2.begin, provenance:'RULE_1')
    }
}

