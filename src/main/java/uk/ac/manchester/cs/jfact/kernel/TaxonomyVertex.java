package uk.ac.manchester.cs.jfact.kernel;

/* This file is part of the JFact DL reasoner
 Copyright 2011-2013 by Ignazio Palmisano, Dmitry Tsarkov, University of Manchester
 This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.semanticweb.owlapi.model.IRI;

import uk.ac.manchester.cs.jfact.helpers.LogAdapter;
import uk.ac.manchester.cs.jfact.helpers.Templates;
import uk.ac.manchester.cs.jfact.kernel.options.JFactReasonerConfiguration;
import conformance.Original;
import conformance.PortedFrom;

/** taxonomy vertex */
@PortedFrom(file = "taxVertex.h", name = "TaxonomyVertex")
public class TaxonomyVertex implements Serializable {

    private static final long serialVersionUID = 11000L;
    /** immediate parents and children */
    @PortedFrom(file = "taxVertex.h", name = "Links")
    private LinkedHashSet<TaxonomyVertex> linksParent = new LinkedHashSet<TaxonomyVertex>();
    @PortedFrom(file = "taxVertex.h", name = "Links")
    private LinkedHashSet<TaxonomyVertex> linksChild = new LinkedHashSet<TaxonomyVertex>();
    /** entry corresponding to current tax vertex */
    @PortedFrom(file = "taxVertex.h", name = "sample")
    private ClassifiableEntry sample = null;
    /** synonyms of the sample entry */
    @PortedFrom(file = "taxVertex.h", name = "synonyms")
    private Set<ClassifiableEntry> synonyms = new LinkedHashSet<ClassifiableEntry>();
    // labels for different purposes. all for 2 directions: top-down and
    // bottom-up search
    /** flag if given vertex was checked; connected with checkLab */
    @PortedFrom(file = "taxVertex.h", name = "checked")
    private long checked;
    /** flag if given vertex has value; connected with valuedLab */
    @Original
    private long isValued;
    /** number of common parents of a node */
    @PortedFrom(file = "taxVertex.h", name = "common")
    private int common;
    /** satisfiability value of a valued vertex */
    @PortedFrom(file = "taxVertex.h", name = "checkValue")
    private boolean checkValue;
    /** flag to check whether the vertex is in use */
    @PortedFrom(file = "taxVertex.h", name = "inUse")
    private boolean inUse = true;

    /**
     * mark vertex as the one corresponding to a given ENTRY
     * 
     * @param entry
     *        entry
     */
    @PortedFrom(file = "taxVertex.h", name = "setVertexAsHost")
    public void setVertexAsHost(ClassifiableEntry entry) {
        entry.setTaxVertex(this);
    }

    /**
     * set sample to ENTRY
     * 
     * @param entry
     *        entry
     * @param linkBack
     *        linkBack
     */
    @PortedFrom(file = "taxVertex.h", name = "setSample")
    public void setSample(ClassifiableEntry entry, boolean linkBack) {
        sample = entry;
        if (linkBack) {
            entry.setTaxVertex(this);
        }
    }

    /**
     * @param upDirection
     *        upDirection
     * @return Links
     */
    @PortedFrom(file = "taxVertex.h", name = "neigh")
    public Iterable<TaxonomyVertex> neigh(boolean upDirection) {
        return upDirection ? linksParent : linksChild;
    }

    // checked part
    /**
     * @param checkLab
     *        checkLab
     * @return true if checked
     */
    @PortedFrom(file = "taxVertex.h", name = "isChecked")
    public boolean isChecked(long checkLab) {
        return checkLab == checked;
    }

    /**
     * @param checkLab
     *        checkLab
     */
    @PortedFrom(file = "taxVertex.h", name = "setChecked")
    public void setChecked(long checkLab) {
        checked = checkLab;
    }

    // value part
    /**
     * @param valueLab
     *        valueLab
     * @return true if values
     */
    @PortedFrom(file = "taxVertex.h", name = "isValued")
    public boolean isValued(long valueLab) {
        return valueLab == isValued;
    }

    /** @return value */
    @PortedFrom(file = "taxVertex.h", name = "getValue")
    public boolean getValue() {
        return checkValue;
    }

    /**
     * @param val
     *        val
     * @param valueLab
     *        valueLab
     * @return val
     */
    @PortedFrom(file = "taxVertex.h", name = "setValued")
    public boolean setValued(boolean val, long valueLab) {
        isValued = valueLab;
        checkValue = val;
        return val;
    }

    // common part
    /** @return true if common not 0 */
    @PortedFrom(file = "taxVertex.h", name = "isCommon")
    public boolean isCommon() {
        return common != 0;
    }

    /** increment common */
    @PortedFrom(file = "taxVertex.h", name = "setCommon")
    public void setCommon() {
        ++common;
    }

    /** zero common */
    @PortedFrom(file = "taxVertex.h", name = "clearCommon")
    public void clearCommon() {
        common = 0;
    }

    /**
     * keep COMMON flag iff both flags are set;
     * 
     * @param n
     *        n
     * @return true if it is the case
     */
    @PortedFrom(file = "taxVertex.h", name = "correctCommon")
    public boolean correctCommon(int n) {
        if (common == n) {
            return true;
        }
        common = 0;
        return false;
    }

    /** put initial values on the flags */
    @PortedFrom(file = "taxVertex.h", name = "initFlags")
    private void initFlags() {
        checked = 0;
        isValued = 0;
        common = 0;
    }

    // get info about taxonomy structure
    /** @return synonyms */
    @PortedFrom(file = "taxVertex.h", name = "begin_syn")
    public Set<ClassifiableEntry> synonyms() {
        return synonyms;
    }

    /** default constructor */
    public TaxonomyVertex() {
        initFlags();
    }

    /**
     * init c'tor; use it only for Top/Bot initialisations
     * 
     * @param p
     *        p
     */
    public TaxonomyVertex(ClassifiableEntry p) {
        initFlags();
        setSample(p, true);
    }

    /**
     * add P as a synonym to curent vertex
     * 
     * @param p
     *        p
     */
    @PortedFrom(file = "taxVertex.h", name = "addSynonym")
    public void addSynonym(ClassifiableEntry p) {
        synonyms.add(p);
        p.setTaxVertex(this);
    }

    /** clears the vertex */
    @PortedFrom(file = "taxVertex.h", name = "clear")
    public void clear() {
        linksParent.clear();
        linksChild.clear();
        sample = null;
        initFlags();
    }

    /** @return primer */
    @PortedFrom(file = "taxVertex.h", name = "getPrimer")
    public ClassifiableEntry getPrimer() {
        return sample;
    }

    /**
     * add link in given direction to vertex
     * 
     * @param upDirection
     *        upDirection
     * @param p
     *        p
     */
    @PortedFrom(file = "taxVertex.h", name = "addNeighbour")
    public void addNeighbour(boolean upDirection, TaxonomyVertex p) {
        if (p == null) {
            throw new IllegalArgumentException("p cannot be null");
        }
        add(upDirection, p);
    }

    public void add(boolean direction, TaxonomyVertex t) {
        if (direction) {
            linksParent.add(t);
        } else {
            linksChild.add(t);
        }
    }

    /**
     * @param upDirection
     *        upDirection
     * @return check if vertex has no neighbours in given direction
     */
    @PortedFrom(file = "taxVertex.h", name = "noNeighbours")
    public boolean noNeighbours(boolean upDirection) {
        if (upDirection) {
            return linksParent.isEmpty();
        } else {
            return linksChild.isEmpty();
        }
    }

    /** @return v if node represents a synonym (v=Up[i]==Down[j]); null otherwise */
    @PortedFrom(file = "taxVertex.h", name = "getSynonymNode")
    public TaxonomyVertex getSynonymNode() {
        // try to find Vertex such that Vertex\in Up and Vertex\in Down
        for (TaxonomyVertex q : neigh(true)) {
            for (TaxonomyVertex r : neigh(false)) {
                if (q.equals(r)) {
                    return q;
                }
            }
        }
        return null;
    }

    /**
     * clear all links in a given direction
     * 
     * @param upDirection
     *        upDirection
     */
    @PortedFrom(file = "taxVertex.h", name = "clearLinks")
    public void clearLinks(boolean upDirection) {
        if (upDirection) {
            linksParent.clear();
        } else {
            linksChild.clear();
        }
    }

    /**
     * @param upDirection
     *        upDirection
     * @param p
     *        p
     * @return true if link removed
     */
    @PortedFrom(file = "taxVertex.h", name = "removeLink")
    public boolean removeLink(boolean upDirection, TaxonomyVertex p) {
        if (upDirection) {
            return linksParent.remove(p);
        }
        return linksChild.remove(p);
    }

    // TODO does not work with synonyms
    /**
     * @param c
     *        c
     */
    @PortedFrom(file = "taxVertex.h", name = "incorporate")
    public void incorporate(JFactReasonerConfiguration c) {
        // setup links
        for (TaxonomyVertex d : neigh(false)) {
            for (TaxonomyVertex u : neigh(true)) {
                if (d.removeLink(true, u)) {
                    u.removeLink(false, d);
                }
            }
            d.removeLink(/* upDirection= */true, this);
            // safe in general case, crucial for incremental
            d.addNeighbour(true, this);
        }
        for (TaxonomyVertex u : neigh(true)) {
            u.addNeighbour(false, this);
        }
        if (c.isLoggingActive()) {
            LogAdapter logAdapter = c.getLog();
            logAdapter.printTemplate(Templates.INCORPORATE, sample.getName());
            logAdapter.print(names(neigh(true)));
            logAdapter.print("} and down = {");
            logAdapter.print(names(neigh(false)));
            logAdapter.print("}");
        }
    }

    Iterable<IRI> names(Iterable<TaxonomyVertex> l) {
        List<IRI> toReturn = new ArrayList<IRI>();
        for (TaxonomyVertex t : l) {
            toReturn.add(t.sample.getName());
        }
        return toReturn;
    }

    /**
     * remove one half of a given node from a graph
     * 
     * @param upDirection
     *        upDirection
     */
    @PortedFrom(file = "taxVertex.h", name = "removeLinks")
    public void removeLinks(boolean upDirection) {
        for (TaxonomyVertex p : neigh(upDirection)) {
            p.removeLink(!upDirection, this);
        }
        clearLinks(upDirection);
    }

    /** remove given node from a graph */
    @PortedFrom(file = "taxVertex.h", name = "remove")
    public void remove() {
        removeLinks(true);
        removeLinks(false);
        setInUse(false);
    }

    /** @return true iff the node is in use */
    @PortedFrom(file = "taxVertex.h", name = "isInUse")
    public boolean isInUse() {
        return inUse;
    }

    /**
     * set the inUse value of the node
     * 
     * @param value
     *        value
     */
    @PortedFrom(file = "taxVertex.h", name = "setInUse")
    public void setInUse(boolean value) {
        inUse = value;
    }

    /**
     * merge NODE which is independent to THIS
     * 
     * @param node
     *        node
     * @param excludes
     *        excludes
     * @param curEntry
     *        curEntry
     */
    @PortedFrom(file = "taxVertex.h", name = "mergeIndepNode")
    public void mergeIndepNode(TaxonomyVertex node,
            Set<TaxonomyVertex> excludes, ClassifiableEntry curEntry) {
        // copy synonyms here
        if (!node.getPrimer().equals(curEntry)) {
            addSynonym(node.getPrimer());
        }
        for (ClassifiableEntry q : node.synonyms()) {
            addSynonym(q);
        }
        boolean upDirection = true;
        for (TaxonomyVertex p : node.neigh(upDirection)) {
            if (!excludes.contains(p)) {
                addNeighbour(upDirection, p);
            }
            p.removeLink(!upDirection, node);
        }
        upDirection = false;
        for (TaxonomyVertex p : node.neigh(upDirection)) {
            if (!excludes.contains(p)) {
                addNeighbour(upDirection, p);
            }
            p.removeLink(!upDirection, node);
        }
    }

    /** @return synonyms debug print */
    @PortedFrom(file = "taxVertex.h", name = "printSynonyms")
    public String printSynonyms() {
        assert sample != null;
        StringBuilder o = new StringBuilder();
        if (synonyms.isEmpty()) {
            o.append('"');
            o.append(sample.getName());
            o.append('"');
        } else {
            o.append("(\"");
            o.append(sample.getName());
            for (ClassifiableEntry q : synonyms()) {
                o.append("\"=\"");
                o.append(q.getName());
            }
            o.append("\")");
        }
        return o.toString();
    }

    /**
     * @param list
     *        vertexes to print
     * @return neighbours debug print
     */
    @PortedFrom(file = "taxVertex.h", name = "printNeighbours")
    private static String printNeighbours(Collection<TaxonomyVertex> list) {
        StringBuilder o = new StringBuilder();
        o.append(" {");
        o.append(list.size());
        o.append(':');
        TreeSet<TaxonomyVertex> sorted = new TreeSet<TaxonomyVertex>(
                new Comparator<TaxonomyVertex>() {

                    @Override
                    public int compare(TaxonomyVertex o1, TaxonomyVertex o2) {
                        return o1.getPrimer().getName()
                                .compareTo(o2.getPrimer().getName());
                    }
                });
        sorted.addAll(list);
        for (TaxonomyVertex p : sorted) {
            o.append(" \"");
            o.append(p.sample.getName());
            o.append('"');
        }
        o.append('}');
        return o.toString();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(printSynonyms());
        b.append(printNeighbours(linksParent));
        b.append(printNeighbours(linksChild));
        b.append('\n');
        return b.toString();
    }
}
