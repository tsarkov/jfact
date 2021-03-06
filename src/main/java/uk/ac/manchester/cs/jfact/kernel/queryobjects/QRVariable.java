package uk.ac.manchester.cs.jfact.kernel.queryobjects;

import org.semanticweb.owlapi.model.IRI;

import uk.ac.manchester.cs.jfact.kernel.HasName;
/* This file is part of the JFact DL reasoner
 Copyright 2011-2013 by Ignazio Palmisano, Dmitry Tsarkov, University of Manchester
 This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA*/
import conformance.PortedFrom;

/** QR variable replacing the individual */
@PortedFrom(file = "QR.h", name = "QRVariable")
public class QRVariable extends QRiObject implements Comparable<QRVariable>,
        HasName {

    private static final long serialVersionUID = 11000L;
    /** name of a var */
    @PortedFrom(file = "QR.h", name = "Name")
    private final IRI Name;

    /** empty c'tor */
    public QRVariable() {
        this(IRI.create("urn:null"));
    }

    /**
     * init c'tor
     * 
     * @param name
     *        name
     */
    public QRVariable(IRI name) {
        Name = name;
    }

    /**
     * @param v
     *        v
     */
    public QRVariable(QRVariable v) {
        this(v.Name);
    }

    @Override
    @PortedFrom(file = "QR.h", name = "getName")
    public IRI getName() {
        return Name;
    }

    @Override
    public String toString() {
        return Name.toString();
    }

    @Override
    public int compareTo(QRVariable o) {
        return Name.compareTo(o.getName());
    }
}
