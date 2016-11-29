package edu.northwestern.chip.app

import com.google.gson.JsonObject
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

    synchronized static String[] processText(String text) {
        List<String> retVals = new ArrayList<String>()

        try {
            jcas.reset()
            jcas.setDocumentText(text)
            engine.process(jcas)
            jcas.select(type:HistologyFinding).each { HistologyFinding hf ->
                JsonObject jobject = new JsonObject()
                jobject.addProperty('code', hf.getCode())
                retVals.add(jobject.toString())
            }
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace()
        }

        return retVals.toArray(new String[retVals.size()])
    }


    static void main(args) {
        String text = new File('/Users/willthompson/Box Sync (u6003082@utah.edu)/Utah data/Utah-random-sample/path-notes/591672070.txt').text
        PolypHistologyApp.processText(text).each { println it }
    }
}

