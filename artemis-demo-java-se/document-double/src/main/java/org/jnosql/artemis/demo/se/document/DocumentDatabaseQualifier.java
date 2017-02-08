package org.jnosql.artemis.demo.se.document;


import javax.enterprise.util.AnnotationLiteral;

public class DocumentDatabaseQualifier extends AnnotationLiteral<DocumentDatabase> implements DocumentDatabase {

    public static final DocumentDatabaseQualifier MONGODB = new DocumentDatabaseQualifier(DocumentDatabaseType.MONGODB);

    public static final DocumentDatabaseQualifier COUCHBASE = new DocumentDatabaseQualifier(DocumentDatabaseType.COUCHBASE);

    private final DocumentDatabaseType type;

    private DocumentDatabaseQualifier(DocumentDatabaseType type) {
        this.type = type;
    }

    @Override
    public DocumentDatabaseType value() {
        return type;
    }


}
