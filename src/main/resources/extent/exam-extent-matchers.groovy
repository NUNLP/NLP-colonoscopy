package extent

import gov.va.queri.types.*
import gov.va.vinci.leo.AnnotationLibrarian

// ---------------------------------------------------------------------------------------------------------------------
// match: (anatomical site + right window triggers)
// ---------------------------------------------------------------------------------------------------------------------
(jcas.select(type:RightWindow)).each {RightWindow rightWindow ->
    pat1.matcher(rightWindow).each { Binding b ->
        AnatomicalSite as1 = b.getVariable('as1')[0]
        Token t1 = b.getVariable('t1')[0]
        jcas.create(type:AnatomicalSiteReached, begin:as1.begin, end:t1.end, provenance:'RULE_1')
    }
    pat2.matcher(rightWindow).each { Binding b ->
        AnatomicalSite as1 = b.getVariable('as1')[0]
        Token t1 = b.getVariable('t1')[0]
        jcas.create(type:AnatomicalSiteVisualized, begin:as1.begin, end:t1.end, provenance:'RULE_2')
        //AnatomicalSite as2 = (b.hasVariable('as2') ? b.getVariable('as2')[0] : as1)
        //if (as1.code == APPENDIX.code || as2.code == APPENDIX.code) {
        //    jcas.create(type:AnatomicalSiteReached, begin:as1.begin, end:t1.end, provenance:'RULE_2')
        //}
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// match: (anatomical site + left window triggers)
// ---------------------------------------------------------------------------------------------------------------------
(jcas.select(type:LeftWindow)).each { LeftWindow leftWindow ->
    pat3.matcher(leftWindow).each { Binding b ->
        AnatomicalSite as1 = b.getVariable('as1')[0]
        Token t1 = b.getVariable('t1')[0]
        Token t2 = (b.hasVariable('t2') ? b.getVariable('t2')[0] : t1)
        jcas.create(type:AnatomicalSiteReached, begin:t2.begin, end:as1.end, provenance:'RULE_3')
    }
    pat4.matcher(leftWindow).each { Binding b ->
        AnatomicalSite as1 = b.getVariable('as1')[0]
        Token t1 = b.getVariable('t1')[0]
        jcas.create(type:AnatomicalSiteReached, begin:t1.begin, end:as1.end, provenance:'RULE_4')
    }
    pat5.matcher(leftWindow).each { Binding b ->
        AnatomicalSite as1 = b.getVariable('as1')[0]
        AnatomicalSite as2 = (b.hasVariable('as2') ? b.getVariable('as2')[0] : as1)
        Token t1 = b.getVariable('t1')[0]
        jcas.create(type:AnatomicalSiteVisualized, begin:t1.begin, end:as2.end, provenance:'RULE_5')
        //if (as1.code == APPENDIX.code || as2.code == APPENDIX.code) {
        //    jcas.create(type:AnatomicalSiteReached, begin:t1.begin, end:as2.end, provenance:'RULE_5')
        //}
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// only keep longest matches
// ---------------------------------------------------------------------------------------------------------------------
jcas.removeCovered(
        anns:jcas.select(type:AnatomicalSiteReached),
        types:[AnatomicalSiteReached]
)
jcas.removeCovered(
        anns:jcas.select(type:AnatomicalSiteVisualized),
        types:[AnatomicalSiteVisualized]
)

// ---------------------------------------------------------------------------------------------------------------------
// merge overlapping annotations
// ---------------------------------------------------------------------------------------------------------------------
AnnotationLibrarian.mergeAnnotations(jcas, AnatomicalSiteVisualized.canonicalName)
AnnotationLibrarian.mergeAnnotations(jcas, AnatomicalSiteReached.canonicalName)


