import edu.northwestern.chip.types.AnatomicalSite
import edu.northwestern.chip.types.HistologyFinding
import gov.va.vinci.leo.sentence.types.Sentence
import static clinicalnlp.dsl.DSL.*

jcas.select(type:Sentence).each { Sentence sent ->
    Collection<AnatomicalSite> sites = jcas.select(type:AnatomicalSite, filter:coveredBy(sent))
    Collection<HistologyFinding> findings = jcas.select(type:HistologyFinding, filter:coveredBy(sent))
    if (sites.size() > 0) {
        AnatomicalSite site = sites[0]
        findings.each { HistologyFinding finding ->
            finding.site = site
        }
    }
}