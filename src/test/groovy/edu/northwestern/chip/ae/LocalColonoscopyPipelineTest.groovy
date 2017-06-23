package edu.northwestern.chip.ae

import clinicalnlp.dsl.DSL
import clinicalnlp.listener.BratAnnotationFileConsumer
import clinicalnlp.listener.FileSystemXMIConsumer
import clinicalnlp.reader.FileSystemCollectionReader
import edu.northwestern.chip.types.AnatomicalSite
import groovy.util.logging.Log4j
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Level
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.collection.CollectionReader
import org.apache.uima.fit.factory.CollectionReaderFactory

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline

@Log4j
class LocalColonoscopyPipelineTest {
    static void testPipeline(AnalysisEngine pipeline, String inputDir,
                                    String xmiOutputDir, String bratOutputDir) {

        // -------------------------------------------------------------------
        // Create the collection reader
        // -------------------------------------------------------------------
        CollectionReader reader =
                CollectionReaderFactory.createReader(
                        FileSystemCollectionReader,
                        FileSystemCollectionReader.PARAM_DIRECTORY_PATH, inputDir)

        // -------------------------------------------------------------------
        // Create the consumer
        // -------------------------------------------------------------------
        AnalysisEngine consumer1 = createEngine(
                FileSystemXMIConsumer,
                FileSystemXMIConsumer.PARAM_DIRECTORY_PATH, xmiOutputDir)
        AnalysisEngine consumer2 = createEngine(
            BratAnnotationFileConsumer,
            BratAnnotationFileConsumer.PARAM_DIRECTORY_PATH, bratOutputDir,
            BratAnnotationFileConsumer.PARAM_OUTPUT_TYPE, AnatomicalSite.canonicalName
        )

        // -------------------------------------------------------------------
        // Run the pipeline
        // -------------------------------------------------------------------
        runPipeline(reader, pipeline, consumer1, consumer2)
    }

    static void main(String[] args) {
        Class.forName(DSL.canonicalName)
        BasicConfigurator.configure()
        log.level = Level.INFO


        AnalysisEngine pipeline = LocalColonoscopyPipeline.buildPolypHistologyPipeline()
        String inputDir = '/Users/willthompson/Data/northwestern/colon-path/input'
        String xmiOutputDir = '/Users/willthompson/Data/northwestern/colon-path/xmi'
        String bratOutputDir = '/Users/willthompson/Data/northwestern/colon-path/brat'
        LocalColonoscopyPipelineTest.testPipeline(pipeline, inputDir, xmiOutputDir, bratOutputDir)
    }
}
