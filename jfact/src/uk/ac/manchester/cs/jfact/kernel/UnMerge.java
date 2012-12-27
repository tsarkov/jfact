package uk.ac.manchester.cs.jfact.kernel;

import uk.ac.manchester.cs.jfact.dep.DepSet;
import uk.ac.manchester.cs.jfact.helpers.FastSetSimple;
import conformance.PortedFrom;

@PortedFrom(file = "CWDArray.cpp", name = "UnMerge")
class UnMerge extends Restorer {
    private CWDArray label;
    private int offset;
    private FastSetSimple dep;

    UnMerge(CWDArray lab, ConceptWDep p, int offset) {
        label = lab;
        this.offset = offset;
        dep = p.getDep().getDelegate();
    }

    @Override
    @PortedFrom(file = "CWDArray.h", name = "restore")
    public void restore() {
        int concept = label.getBase().get(offset).getConcept();
        ConceptWDep conceptWDep = new ConceptWDep(concept, DepSet.create(dep));
        label.getBase().set(offset, conceptWDep);
    }
}
