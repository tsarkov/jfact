package uk.ac.manchester.cs.jfact.kernel.dl;

/* This file is part of the JFact DL reasoner
 Copyright 2011-2013 by Ignazio Palmisano, Dmitry Tsarkov, University of Manchester
 This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA*/
import java.io.Serializable;

import org.semanticweb.owlapi.model.IRI;

import uk.ac.manchester.cs.jfact.kernel.dl.interfaces.ConceptDataRVExpression;
import uk.ac.manchester.cs.jfact.kernel.dl.interfaces.DataExpression;
import uk.ac.manchester.cs.jfact.kernel.dl.interfaces.DataRoleArg;
import uk.ac.manchester.cs.jfact.kernel.dl.interfaces.DataRoleExpression;
import uk.ac.manchester.cs.jfact.visitors.DLExpressionVisitor;
import uk.ac.manchester.cs.jfact.visitors.DLExpressionVisitorEx;
import conformance.Original;
import conformance.PortedFrom;

/** exist data restriction */
@PortedFrom(file = "tDLExpression.h", name = "TDLConceptDataExists")
public class ConceptDataExists implements ConceptDataRVExpression, DataRoleArg,
        Serializable {

    private static final long serialVersionUID = 11000L;
    /** data role argument */
    @PortedFrom(file = "tDLExpression.h", name = "DR")
    @Original
    private final DataRoleExpression dataRoleExpression;
    @Original
    private final DataExpression delegate;

    /**
     * @param R
     *        R
     * @param E
     *        E
     */
    public ConceptDataExists(DataRoleExpression R, DataExpression E) {
        dataRoleExpression = R;
        delegate = E;
    }

    @Override
    @PortedFrom(file = "tDLExpression.h", name = "accept")
    public void accept(DLExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    @PortedFrom(file = "tDLExpression.h", name = "accept")
    public <O> O accept(DLExpressionVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    /** get access to the argument */
    @Override
    public DataRoleExpression getDataRoleExpression() {
        return dataRoleExpression;
    }

    @Override
    @PortedFrom(file = "tDLExpression.h", name = "getExpr")
    public DataExpression getExpr() {
        return delegate;
    }

    @Override
    public IRI getName() {
        return IRI.create(toString());
    }

    @Override
    public String toString() {
        return "DataExist(" + dataRoleExpression + " " + delegate + ")";
    }
}
