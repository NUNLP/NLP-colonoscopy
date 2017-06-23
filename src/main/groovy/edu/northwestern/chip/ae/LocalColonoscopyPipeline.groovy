package edu.northwestern.chip.ae

import clinicalnlp.dsl.ae.LocalDSLAnnotator
import clinicalnlp.sent.ae.LocalSentenceDetector
import clinicalnlp.token.ae.LocalTokenAnnotator
import clinicalnlp.types.Segment
import groovy.util.logging.Log4j
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.ExternalResourceFactory
import org.apache.uima.resource.ExternalResourceDescription

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription

@Log4j
class LocalColonoscopyPipeline {

    static AnalysisEngine buildPolypHistologyPipeline() {
        AggregateBuilder builder = new AggregateBuilder()
        builder.with {
            add(createEngineDescription(LocalDSLAnnotator,
                LocalDSLAnnotator.PARAM_BINDING_SCRIPT_FILE, 'histology/segment-patterns.groovy',
                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/segment-matchers.groovy'))
            add(createEngineDescription(LocalDSLAnnotator,
                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/anatomical-site.groovy'))
            add(createEngineDescription(LocalDSLAnnotator,
                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/polyp-histology.groovy'))
            add(createEngineDescription(LocalDSLAnnotator,
                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/histology-site-relations.groovy'))
        }

        AnalysisEngineDescription descr = builder.createAggregateDescription()
        File descriptorLocation = new File('src/main/resources/descriptors/LocalPolypHistologyPipeline.xml')
        descr.toXML(new PrintWriter(descriptorLocation))

        AnalysisEngine engine = builder.createAggregate()
        return engine
    }
}
