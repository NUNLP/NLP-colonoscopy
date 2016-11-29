import gov.va.queri.types.AnatomicalSite
import gov.va.queri.types.DictMatch
import org.apache.uima.jcas.JCas

// get the JCas instance
JCas jcas = (JCas) getProperty('jcas')

// only use matches that aren't embedded in other matches
jcas.removeCovered(
        anns:jcas.select(type:DictMatch),
        types:[DictMatch]
)

Collection<DictMatch> dictMatches = jcas.select(type:DictMatch)
dictMatches.each { DictMatch dm ->
    jcas.create(type:AnatomicalSite,
            begin:dm.begin,
            end:dm.end,
            code:dm.code,
            codeSystem:dm.vocabulary
    )
}
