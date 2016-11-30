package edu.northwestern.chip.types

import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription
import clinicalnlp.types.NamedEntityMention
import org.apache.uima.resource.metadata.impl.TypeDescription_impl

System.setProperty('user.dir', '/Users/willthompson/Code/northwestern/NU-colonoscopy')

LeoTypeSystemDescription types = new LeoTypeSystemDescription('clinicalnlp.types.CoreTypeSystem', true)
//types.addType('edu.northwestern.chip.types.HistologyFinding', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.AnatomicalSite', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.AnatomicalSiteReached', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.AnatomicalSiteVisualized', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.LeftWindow', '', 'uima.tcas.Annotation')
types.addType('edu.northwestern.chip.types.RightWindow', '', 'uima.tcas.Annotation')

TypeDescription_impl histFinding = new TypeDescription_impl('edu.northwestern.chip.types.HistologyFinding',
    '', NamedEntityMention.canonicalName)
histFinding.addFeature('site', '', 'edu.northwestern.chip.types.AnatomicalSite')
types.addType(histFinding)

types.toXML('src/main/resources/descriptors/ColonoscopyTypeSystem.xml')
types.jCasGen('src/main/java/', 'build/classes')
