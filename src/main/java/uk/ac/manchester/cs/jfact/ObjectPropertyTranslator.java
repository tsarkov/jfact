package uk.ac.manchester.cs.jfact;

import java.util.stream.Stream;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.impl.DefaultNode;
import org.semanticweb.owlapi.reasoner.impl.DefaultNodeSet;
import org.semanticweb.owlapi.reasoner.impl.OWLObjectPropertyNode;
import org.semanticweb.owlapi.reasoner.impl.OWLObjectPropertyNodeSet;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import uk.ac.manchester.cs.jfact.kernel.ExpressionCache;
import uk.ac.manchester.cs.jfact.kernel.dl.interfaces.ObjectRoleExpression;

/** object property translator */
public class ObjectPropertyTranslator extends
        OWLEntityTranslator<OWLObjectPropertyExpression, ObjectRoleExpression> {

    private static final long serialVersionUID = 11000L;

    /**
     * @param em
     *        em
     * @param df
     *        df
     * @param tr
     *        tr
     */
    public ObjectPropertyTranslator(ExpressionCache em, OWLDataFactory df,
            TranslationMachinery tr) {
        super(em, df, tr);
    }

    @Override
    protected ObjectRoleExpression getTopEntityPointer() {
        return em.objectRole(OWLRDFVocabulary.OWL_TOP_OBJECT_PROPERTY.getIRI());
    }

    @Override
    protected ObjectRoleExpression getBottomEntityPointer() {
        return em.objectRole(OWLRDFVocabulary.OWL_BOTTOM_OBJECT_PROPERTY
                .getIRI());
    }

    @Override
    protected ObjectRoleExpression registerNewEntity(
            OWLObjectPropertyExpression entity) {
        ObjectRoleExpression pointer = createPointerForEntity(entity);
        fillMaps(entity, pointer);
        OWLObjectPropertyExpression inverseentity = entity.getInverseProperty()
                .getSimplified();
        fillMaps(inverseentity, createPointerForEntity(inverseentity));
        return pointer;
    }

    @Override
    protected ObjectRoleExpression createPointerForEntity(
            OWLObjectPropertyExpression entity) {
        // FIXME!! think later!!
        ObjectRoleExpression p = em.objectRole(entity.getNamedProperty()
                .getIRI());
        if (entity.isAnonymous()) {
            p = em.inverse(p);
        }
        return p;
    }

    @Override
    protected OWLObjectProperty getTopEntity() {
        return df.getOWLTopObjectProperty();
    }

    @Override
    protected OWLObjectProperty getBottomEntity() {
        return df.getOWLBottomObjectProperty();
    }

    @Override
    protected DefaultNode<OWLObjectPropertyExpression> createDefaultNode(
            Stream<OWLObjectPropertyExpression> stream) {
        return new OWLObjectPropertyNode(stream);
    }

    @Override
    protected DefaultNodeSet<OWLObjectPropertyExpression> createDefaultNodeSet(
            Stream<Node<OWLObjectPropertyExpression>> stream) {
        return new OWLObjectPropertyNodeSet(stream);
    }
}
