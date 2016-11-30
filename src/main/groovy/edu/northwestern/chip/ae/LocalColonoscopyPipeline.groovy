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

    static AnalysisEngine buildExamExtentPipeline() {
        ExternalResourceDescription tokenResDesc = ExternalResourceFactory.createExternalResourceDescription(
            opennlp.uima.tokenize.TokenizerModelResourceImpl.class, "file:models/en-token.bin")
        ExternalResourceDescription posResDesc = ExternalResourceFactory.createExternalResourceDescription(
            opennlp.uima.postag.POSModelResourceImpl, "file:models/mayo-pos.zip")

        AggregateBuilder builder = new AggregateBuilder()
        builder.with {
            add(createEngineDescription(LocalDSLAnnotator,
                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'extent/proc-note-segmenter.groovy'))
            add(createEngineDescription(
                LocalTokenAnnotator,
                LocalTokenAnnotator.PARAM_CONTAINER_TYPE, Segment.canonicalName,
                LocalTokenAnnotator.TOKEN_MODEL_KEY, tokenResDesc))
            add(createEngineDescription(LocalDSLAnnotator,
                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'extent/anatomical-site.groovy'))
            add(createEngineDescription(LocalDSLAnnotator,
                LocalDSLAnnotator.PARAM_BINDING_SCRIPT_FILE, 'extent/exam-extent-patterns.groovy',
                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'extent/exam-extent-matchers.groovy'))
        }

        AnalysisEngineDescription descr = builder.createAggregateDescription()
        File descriptorLocation = new File('src/main/resources/descriptors/LocalExamExtentPipeline.xml')
        descr.toXML(new PrintWriter(descriptorLocation))

        AnalysisEngine engine = builder.createAggregate()

        return engine
    }

    static AnalysisEngine buildPolypHistologyPipeline() {
        ExternalResourceDescription tokenResDesc = ExternalResourceFactory.createExternalResourceDescription(
                opennlp.uima.tokenize.TokenizerModelResourceImpl.class, "file:clinicalnlp/models/en-token.bin")
        ExternalResourceDescription sentResDesc = ExternalResourceFactory.createExternalResourceDescription(
                opennlp.uima.sentdetect.SentenceModelResourceImpl, "file:clinicalnlp/models/sd-med-model.zip")
        ExternalResourceDescription posResDesc = ExternalResourceFactory.createExternalResourceDescription(
                opennlp.uima.postag.POSModelResourceImpl, "file:clinicalnlp/models/mayo-pos.zip")

        AggregateBuilder builder = new AggregateBuilder()
        builder.with {
            add(createEngineDescription(LocalDSLAnnotator,
                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/path-note-segmenter.groovy'))
            add(createEngineDescription(
                    LocalSentenceDetector,
                    LocalSentenceDetector.SENT_MODEL_KEY, sentResDesc,
                    LocalSentenceDetector.PARAM_SPLIT_PATTERN, '[\n\r]{2,}'
            ))
//            add(createEngineDescription(
//                    LocalTokenAnnotator,
//                    LocalTokenAnnotator.PARAM_CONTAINER_TYPE, Sentence.canonicalName,
//                    LocalTokenAnnotator.TOKEN_MODEL_KEY, tokenResDesc))
//            add(createEngineDescription(
//                opennlp.uima.postag.POSTagger,
//                UimaUtil.MODEL_PARAMETER, posResDesc,
//                "opennlp.uima.SentenceType", Sentence.canonicalName,
//                "opennlp.uima.TokenType", Token.canonicalName,
//                "opennlp.uima.POSFeature", "pos"))
            add(createEngineDescription(LocalDSLAnnotator,
                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/anatomical-site.groovy'))
            add(createEngineDescription(LocalDSLAnnotator,
                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/polyp-histology.groovy'))
//            add(createEngineDescription(LocalDSLAnnotator,
//                    LocalDSLAnnotator.PARAM_BINDING_SCRIPT_FILE, 'histology/histology-patterns.groovy',
//                    LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'histology/histology-matchers.groovy'))
        }

        AnalysisEngineDescription descr = builder.createAggregateDescription()
        File descriptorLocation = new File('src/main/resources/descriptors/LocalPolypHistologyPipeline.xml')
        descr.toXML(new PrintWriter(descriptorLocation))

        AnalysisEngine engine = builder.createAggregate()
        return engine
    }
}
