package edu.northwestern.chip.ae

import clinicalnlp.dsl.ae.LeoDSLAnnotator
import clinicalnlp.token.ae.LeoTokenAnnotator
import clinicalnlp.types.Segment
import gov.va.vinci.leo.descriptors.LeoAEDescriptor
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription
import groovy.util.logging.Log4j

@Log4j
class LeoColononscopyPipeline {
    static LeoAEDescriptor buildPipeline() {
        LeoTypeSystemDescription types = new LeoTypeSystemDescription('descriptors.ColonoscopyTypeSystem', true)
        LeoAEDescriptor pipeline = new LeoAEDescriptor()
        pipeline.addDelegate(
                new LeoDSLAnnotator()
                        .setScriptFileName('extent/proc-note-segmenter.groovy')
                        .setLeoTypeSystemDescription(types)
                        .getLeoAEDescriptor())
        pipeline.addDelegate(
                new LeoTokenAnnotator()
                        .setTokenModelPath('classpath:models/en-token.bin')
                        .setContainerTypeName(Segment.canonicalName)
                        .setLeoTypeSystemDescription(types)
                        .getLeoAEDescriptor())
        pipeline.addDelegate(
                new LeoDSLAnnotator()
                        .setScriptFileName('extent/anatomical-site.groovy')
                        .setLeoTypeSystemDescription(types)
                        .getLeoAEDescriptor())
        pipeline.addDelegate(
                new LeoDSLAnnotator()
                        .setSBindingScriptFileName('extent/exam-extent-patterns.groovy')
                        .setScriptFileName('extent/exam-extent-matchers.groovy')
                        .setLeoTypeSystemDescription(types)
                        .getLeoAEDescriptor())

        return pipeline
    }
}
