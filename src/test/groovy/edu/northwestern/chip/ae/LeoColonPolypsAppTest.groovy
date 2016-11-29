package edu.northwestern.chip.ae

import gov.va.vinci.leo.aucompare.comparators.SpanAuComparator
import gov.va.vinci.leo.aucompare.listener.AuCompareCSVListener
import gov.va.vinci.leo.cr.FileCollectionReader
import gov.va.vinci.leo.cr.LeoCollectionReaderInterface
import gov.va.vinci.leo.cr.XmiFileCollectionReader
import gov.va.vinci.leo.descriptors.LeoAEDescriptor
import gov.va.vinci.leo.listener.SimpleXmiListener
import gov.va.vinci.leo.tools.LeoUtils
import groovy.util.logging.Log4j
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Level
import org.apache.uima.aae.client.UimaAsBaseCallbackListener

@Log4j
class LeoColonPolypsAppTest {
    Process process = null;

    void startService() {
        log.info "Starting service."
        String UIMA_HOME = System.getenv('UIMA_HOME')
        String OS_NAME = System.getProperty("os.name")
        String ext = OS_NAME == 'Mac OS X' ? 'sh' : 'bat'
        this.process = new ProcessBuilder("${UIMA_HOME}/bin/startBroker.${ext}").start();
        assert this.process.alive
    }

    void stopService() {
        log.info "Stopping service."
        this.process.destroyForcibly()
        this.process.waitFor()
        assert !this.process.alive
    }

    void testApplication1(LeoAEDescriptor pipeline, String inputDir, String outputDir) {
        // pipeline descriptor
        pipeline.setIsDeleteOnExit(false)
        pipeline.setDescriptorLocator(new File('src/test/resources/descriptors/').toURI())
        pipeline.toXML('LeoPipelineDescriptor')
        pipeline.setIsAsync(false)
        pipeline.setNumberOfInstances(1)

        // create listeners
        HashMap auMap = ["gov.va.vinci.cr.types.Extent_of_Exam_Anatomical":"gov.va.queri.types.AnatomicalSiteReached"]
        String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
        String outPath =  "${outputDir}/auCompare${timeStamp}.csv"
        UimaAsBaseCallbackListener auCompareCSVListener = new AuCompareCSVListener(new File (outPath),
                new SpanAuComparator(auMap, true));
        File directory = new File("${outputDir}/xmi-${timeStamp}")
        assert directory.mkdir()
        SimpleXmiListener xmiListener = new SimpleXmiListener(directory)

        // create reader
        LeoCollectionReaderInterface reader = new XmiFileCollectionReader(new File(inputDir), false);

        // run service
        gov.va.vinci.leo.Service service = new gov.va.vinci.leo.Service()
        service.deploy(pipeline)

        // create and run client
        gov.va.vinci.leo.Client client = new gov.va.vinci.leo.Client();
        client.setBrokerURL('tcp://localhost:61616')
        client.setEndpoint('mySimpleQueueName')
        client.setCasPoolSize(1)
        client.setCCTimeout(1000)
        client.addUABListener(xmiListener)
        client.addUABListener(auCompareCSVListener)
        client.run(reader)

        // display results
        auCompareCSVListener.outputStatsToConsole()
    }

    void testApplication2(LeoAEDescriptor pipeline, String inputDir, String outputDir) {

        // pipeline descriptor
        pipeline.setIsDeleteOnExit(false)
        pipeline.setIsAsync(false)
        pipeline.setNumberOfInstances(1)

        // create listeners
        SimpleXmiListener xmiListener = new SimpleXmiListener(new File(outputDir))

        // create reader
        LeoCollectionReaderInterface reader = new FileCollectionReader(new File(inputDir), false);

        // run service
        gov.va.vinci.leo.Service service = new gov.va.vinci.leo.Service()
        service.setDescriptorDirectory('src/main/resources/descriptors')
        service.setDeleteOnExit(true)
        service.deploy(pipeline)

        // create and run client
        gov.va.vinci.leo.Client client = new gov.va.vinci.leo.Client();
        client.setBrokerURL('tcp://localhost:61616')
        client.setEndpoint('mySimpleQueueName')
        client.setCasPoolSize(1)
        client.setCCTimeout(1000)
        client.addUABListener(xmiListener)
        client.run(reader)
    }

    public static void main(String[] args) {
        BasicConfigurator.configure()
        log.setLevel(Level.INFO)

        Class.forName('gov.va.queri.dsl.UIMA_DSL')
        LeoAEDescriptor pipeline = LeoColonPolypsApp.buildPipeline()
        LeoColonPolypsAppTest testApp
        try {
            testApp = new LeoColonPolypsAppTest()
            ////testPipeline.startService()

//            String inputDir = '//vhacdwfpcfs02/Projects3/ORD_Gupta_201311044D/NLP/RefSt_FromChartReview_20160707_ColonoscopyOnly/xmi'
//            String outputDir = '//vhacdwfpcfs02/Projects3/ORD_Gupta_201311044D/NLP/TestData/output/WKT'
//            testApp.testApplication1(pipeline, inputDir, outputDir)

            String inputDir = '/Users/willthompson/Box Sync (u6003082@utah.edu)/Utah data/Utah-random-sample/proc-notes'
            String outputDir = '/Users/willthompson/Box Sync (u6003082@utah.edu)/Utah data/Utah-random-sample/proc-notes/xmi-leo'
            testApp.testApplication2(pipeline, inputDir, outputDir)
        }
        catch (Exception e) {
            println e.message
        }
        finally {
            ////testPipeline.stopService()
        }
    }
}
