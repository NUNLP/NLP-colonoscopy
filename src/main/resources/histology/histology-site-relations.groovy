import clinicalnlp.types.Segment
import edu.northwestern.chip.domain.Concept
import edu.northwestern.chip.types.AnatomicalSite
import edu.northwestern.chip.types.HistologyFinding

import static clinicalnlp.dsl.DSL.getCoveredBy

jcas.select(type:Segment).each { Segment segment ->
    Collection<AnatomicalSite> sites = jcas.select(type:AnatomicalSite, filter:coveredBy(segment))
    Collection<HistologyFinding> findings = jcas.select(type:HistologyFinding, filter:coveredBy(segment))

    AnatomicalSite site = null
    if (sites.size() > 0) {
        site = sites[0]
    }
    else {
        site = jcas.create(type:AnatomicalSite, begin:segment.begin, end:segment.end)
        site.code = Concept.COLON.code
    }

    findings.each { HistologyFinding finding ->
        finding.site = site
    }
}