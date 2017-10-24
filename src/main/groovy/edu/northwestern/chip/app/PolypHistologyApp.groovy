package edu.northwestern.chip.app

import com.opencsv.CSVWriter
import edu.northwestern.chip.types.HistologyFinding
import org.apache.uima.UIMAFramework
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.jcas.JCas
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.util.InvalidXMLException
import org.apache.uima.util.XMLInputSource

class PolypHistologyApp {
    static AnalysisEngine engine
    static JCas jcas
    static {
        try {
            AnalysisEngineDescription desc = UIMAFramework
                .getXMLParser()
                .parseAnalysisEngineDescription(
                new XMLInputSource(
                    PolypHistologyApp.getResource("/descriptors/LocalPolypHistologyPipeline.xml")))
            engine = AnalysisEngineFactory.createEngine(desc)
            jcas = engine.newJCas()
        } catch (InvalidXMLException | IOException
        | ResourceInitializationException e) {
            e.printStackTrace()
        }
        assert engine != null
    }

    synchronized static Collection<Tuple2<String, String>> processText(String text) {
        Collection<Tuple2<String, String>> retvals = new ArrayList<>()

        try {
            jcas.reset()
            jcas.setDocumentText(text)
            engine.process(jcas)
            jcas.select(type:HistologyFinding).each { HistologyFinding hf ->
                retvals << new Tuple2<>(hf.code, hf.site.code)
            }
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace()
        }

        return retvals
    }

    static void main(args) {
        Writer writer = new FileWriter(args[1])
        def w = new CSVWriter(writer)
        w.writeNext((String[]) ['sourceFile', 'histCode', 'siteCode'])
        new File(args[0]).eachFileMatch(~/.*.txt/) { file ->
            String text = file.text
            PolypHistologyApp.processText(text).each { Tuple2<String, String> result ->
                w.writeNext((String[]) [file.name.substring(0, file.name.length()-4), result[0], result[1]])
            }
        }
        w.close()
    }
}

