package uk.ac.manchester.cs.jfact.kernel.dl.interfaces;

import uk.ac.manchester.cs.jfact.kernel.HasName;
import uk.ac.manchester.cs.jfact.kernel.NamedEntry;
import conformance.PortedFrom;

/* This file is part of the JFact DL reasoner
 Copyright 2011-2013 by Ignazio Palmisano, Dmitry Tsarkov, University of Manchester
 This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA*/
/** named entity */
@PortedFrom(file = "tDLExpression.h", name = "TNamedEntity")
public interface NamedEntity extends HasName {

    /** @return named entry */
    @PortedFrom(file = "tDLExpression.h", name = "getEntry")
    NamedEntry getEntry();

    /**
     * @param e
     *        e
     */
    @PortedFrom(file = "tDLExpression.h", name = "setEntry")
    void setEntry(NamedEntry e);
}
