package edu.northwestern.chip.types

import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription
import clinicalnlp.types.NamedEntityMention

System.setProperty('user.dir', '/Users/willthompson/Code/northwestern/NU-colonoscopy')

LeoTypeSystemDescription types = new LeoTypeSystemDescription('clinicalnlp.types.CoreTypeSystem', true)
types.addType('edu.northwestern.chip.types.HistologyFinding', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.AnatomicalSite', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.AnatomicalSiteReached', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.AnatomicalSiteVisualized', '', NamedEntityMention.canonicalName)
types.addType('edu.northwestern.chip.types.LeftWindow', '', 'uima.tcas.Annotation')
types.addType('edu.northwestern.chip.types.RightWindow', '', 'uima.tcas.Annotation')

types.toXML('src/main/resources/descriptors/ColonoscopyTypeSystem.xml')
types.jCasGen('src/main/java/', 'build/classes')
