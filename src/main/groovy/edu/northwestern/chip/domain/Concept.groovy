package edu.northwestern.chip.domain

import groovy.transform.Immutable

@Immutable
class Concept {

    String cui
    String tui
    String codingScheme
    String code
    
    Map getMap() {
        return [cui:cui, tui:tui, codingScheme:codingScheme, code:code]
    }
    
    static final String SEG_FINAL_DIAGNOSIS = 'FINAL_DIAGNOSIS'
    static final String SEG_CASE_SUMMARY = 'CASE_SUMMARY'
    static final String SEG_CLINICAL_IMPRESSION = 'CLINICAL_IMPRESSION'
    static final String SEG_SPECIMEN_SITES = 'SPECIMEN_SITES'
    static final String SEG_COMMENT = 'COMMENT'
    static final String SEG_MICROSCOPIC = 'MICROSCOPIC'
    static final String SEG_GROSS = 'GROSS'
    static final String SEG_UNKNOWN = 'UNKNOWN'
    static final String SEG_SPECIMEN = 'SPECIMEN'
    
    static final String CODING_SCHEME_SNOMED = 'SNOMED'
    static final String CODING_SCHEME_UCUM = 'UCUM'
    
    static final String TUI_NEOPLASTIC_PROCESS = 'T191'
    static final String TUI_DISEASE_OR_SYNDROME = 'T047'
    static final String TUI_BODY_PART = 'T023'
    static final String TUI_ANATOMIC_ABNORMALITY = 'T190'
    static final String TUI_QUALITATIVE_CONCEPT = 'T080'
    static final String TUI_QUANTITATIVE_CONCEPT = 'T081'
    static final String TUI_SPATIAL_CONCEPT = 'T082'
    static final String TUI_PATHOLOGIC_FUNCTION = 'T046'
    static final String TUI_CELL_DYSFUNCTION = 'T049'
    static final String TUI_DIAGNOSTIC_PROCEDURE = 'T060'
    static final String TUI_PREVENTIVE_PROCEDURE = 'T061'
    static final String TUI_LAB_PROCEDURE = 'T059'
    static final String TUI_ACQUIRED_ABNORAMALITY = 'T0202'
    
    static final String ROLE_FINDING = 'Finding'
    static final String ROLE_LOCATION = 'LocationOf'
    static final String ROLE_PROCEDURE = 'Procedure'
    static final String ROLE_NUMBER = 'Number'
    static final String ROLE_DYSPLASIA = 'Dysplasia'
    static final String ROLE_VILLOUS = 'Villous'
    
    static final Concept COLONOSCOPY = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'73761001', cui:'C0009378', tui:TUI_DIAGNOSTIC_PROCEDURE)
    static final Concept SCREENING_COLONOSCOPY = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'444783004', cui:'C1882982', tui:TUI_DIAGNOSTIC_PROCEDURE)
    static final Concept SURVEILLANCE_COLONOSCOPY = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'313172000', cui:'C0730516', tui:TUI_DIAGNOSTIC_PROCEDURE)
    static final Concept DIAGNOSTIC_COLONOSCOPY = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'174184006', cui:'C0400018', tui:TUI_DIAGNOSTIC_PROCEDURE)
    
    static final Concept INFLAMMATORY_POLYP = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'inflammatory', cui:'C0032568', tui:TUI_DISEASE_OR_SYNDROME)
    static final Concept JUVENILE_POLYP = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'juvenile', cui:'C0221273', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept HAMARTOMATOUS_POLYP = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'hamartomatous', cui:'C0334092', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept HYPERPLASTIC_POLYP = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'hyperplastic', cui:'C0267364', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'adenoma', cui:'C0850572', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept TUBULAR_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'tubular', cui:'C0334292', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept TUBULOVILLOUS_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'tubulovillous', cui:'C0334307', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept VILLOUS_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'villous', cui:'C0149862', tui:TUI_NEOPLASTIC_PROCESS)

    static final Concept SERRATED_POLYP = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'serrated_polyp', cui:'C3266124', tui:TUI_ANATOMIC_ABNORMALITY)
    static final Concept SESSILE_SERRATED_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'sessile_serrated_adenoma', cui:'C2732618', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept SESSILE_SERRATED_POLYP = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'sessile_serrated_polyp', cui:'C2732618', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept SERRATED_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'serrated_adenoma', cui:'serrated_adenoma', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept TRADITIONAL_SERRATED_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'trad_serrated_adenoma', cui:'C1266025', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept TRADITIONAL_SESSILE_SERRATED_ADENOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'trad_sessile_serrated_adenoma', cui:'C3838988', tui:TUI_NEOPLASTIC_PROCESS)

	static final Concept ADENOCARCINOMA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'carcinoma', cui:'C0001418', tui:TUI_NEOPLASTIC_PROCESS)
    static final Concept DYSPLASIA = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'dysplasia', cui:'C0334044', tui:TUI_PATHOLOGIC_FUNCTION)
    
    static final Concept COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'na', cui:'C0009368', tui:TUI_BODY_PART)
    static final Concept LEFT_COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'left', cui:'C0227388', tui:TUI_BODY_PART)
    static final Concept RECTUM = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'left', cui:'C0227388', tui:TUI_BODY_PART)
    static final Concept RECTO_SIGMOID = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'left', cui:'C0521377', tui:TUI_BODY_PART)
    static final Concept SIGMOID_COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'left', cui:'C0227388', tui:TUI_BODY_PART)
    static final Concept DESCENDING_COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'left', cui:'C0227388', tui:TUI_BODY_PART)
    static final Concept SPLENIC_FLEXURE = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'left', cui:'C0227388', tui:TUI_BODY_PART)
    static final Concept RIGHT_COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C1305188', tui:TUI_BODY_PART)
    static final Concept TRANSVERSE_COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C1305188', tui:TUI_BODY_PART)
    static final Concept HEPATIC_FLEXURE = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C1305188', tui:TUI_BODY_PART)
    static final Concept ASCENDING_COLON = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C1305188', tui:TUI_BODY_PART)
    static final Concept ILEUM = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C1305188', tui:TUI_BODY_PART)
    static final Concept CECUM = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C1305188', tui:TUI_BODY_PART)
    static final Concept APPENDIX = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C0003617', tui:TUI_BODY_PART)
    static final Concept ILEOCECAL_VALVE = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'right', cui:'C0020880', tui:TUI_BODY_PART)

    static final Concept ANASTOMOSIS = new Concept(codingScheme:CODING_SCHEME_SNOMED, code:'na', cui:'C0332853', tui:TUI_ACQUIRED_ABNORAMALITY)
}