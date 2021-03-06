package uk.ac.manchester.cs.jfact.kernel;

import java.util.ArrayList;
import java.util.List;

import uk.ac.manchester.cs.jfact.kernel.queryobjects.QRQuery;
import uk.ac.manchester.cs.jfact.kernel.queryobjects.VariableFactory;
import conformance.PortedFrom;

/** Conjunctive query set */
@PortedFrom(file = "ConjunctiveQuerySet.h", name = "CQSet")
public class ConjunctiveQuerySet {

    // / pointer to an expression manager
    protected ExpressionManager pEManager;
    // / pointer to a query var factory
    protected VariableFactory VarFactory;
    // / queries
    protected List<QRQuery> queries = new ArrayList<QRQuery>();
    // / flag whether an ABox is original or artificial (individuals correspond
    // class names)
    protected boolean artificialABox;

    /**
     * @param pEM
     *        expression manager
     * @param VarFact
     *        variable factory
     * @param art
     *        artificual abox
     */
    public ConjunctiveQuerySet(ExpressionManager pEM, VariableFactory VarFact,
            boolean art) {
        pEManager = pEM;
        VarFactory = VarFact;
        artificialABox = art;
    }

    // public ConceptExpression defC(name) {return pEManager.concept(#name);}
    // public ObjectRoleExpression defR(name) {return
    // pEManager.objectRole(#name);}
    // public QRVariable defV(name) {return VarFactory.getNewVar(#name);}
    /** @return true if ABox is artificial */
    public boolean isArtificialABox() {
        return artificialABox;
    }

    /**
     * @param i
     *        index
     * @return query by index
     */
    public QRQuery get(int i) {
        return queries.get(i);
    }

    /** @return number of queries */
    int size() {
        return queries.size();
    }
}
