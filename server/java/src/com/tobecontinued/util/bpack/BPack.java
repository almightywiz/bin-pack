/*
 * Copyright (c) 2013 Charles Lukas
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.tobecontinued.util.bpack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.tobecontinued.util.bpack.comparator.BestFitComparator;
import com.tobecontinued.util.bpack.comparator.BinItemComparator;
import com.tobecontinued.util.bpack.comparator.FirstFitComparator;
import com.tobecontinued.util.bpack.comparator.LastFitComparator;
import com.tobecontinued.util.bpack.comparator.WorstFitComparator;
import com.tobecontinued.util.bpack.core.Bin;
import com.tobecontinued.util.bpack.core.BinItem;
import com.tobecontinued.util.bpack.exception.BinCapacityException;

/**
 * Utility class that provides methods for solving bin packing problems.
 * 
 * Copyright (c) 2013 Charles Lukas All Rights Reserved
 * 
 * @author Charles Lukas
 */
public final class BPack {

    /**
     * Enumeration used for sorting bins prior to packing.
     * 
     * @author Charles Lukas
     */
    public static enum SORT_METHOD {
        ASC, DSC, NONE
    }

    private static SORT_METHOD _sortMethod = SORT_METHOD.NONE;

    /**
     * Set the sorting method for the BPack utility. The sorting method is
     * applied to the list of bin items, or input, prior to the bin packing
     * procedure.
     * 
     * @param method Method used for item sorting. Defaults to
     *            {@link SORT_METHOD.NONE}.
     */
    public static void setSortMethod(SORT_METHOD method) {

        _sortMethod = method;
    }

    /**
     * Determines if a packing problem is possible. We cannot tell if it is a
     * certainty that the problem is solvable, but we can at least determine if
     * it is not possible. This is done by verifying if the max capacity of the
     * bins can hold the total capacity of the input items.
     * 
     * @param bins Collection of bins.
     * @param input Collection of input bin items.
     * @return TRUE if there is a possibility that all bin items can fit into
     *         the collection of bins.
     */
    public static boolean isPossible(Collection<Bin> bins, Collection<BinItem> input) {

        int binCapacity = 0;
        int inputCapacity = 0;

        for (Bin bin : bins) {
            binCapacity += bin.getMaxCapacity();
        }

        for (BinItem item : input) {
            inputCapacity += item.binCapacity();
        }
        return binCapacity >= inputCapacity;
    }

    public static List<Bin> firstFit(LinkedList<Bin> bins, Collection<BinItem> input) throws BinCapacityException {

        return process(bins, input, new FirstFitComparator());
    }

    public static List<Bin> lastFit(LinkedList<Bin> bins, Collection<BinItem> input) throws BinCapacityException {

        return process(bins, input, new LastFitComparator());
    }

    public static List<Bin> bestFit(LinkedList<Bin> bins, Collection<BinItem> input) throws BinCapacityException {

        return process(bins, input, new BestFitComparator());
    }

    public static List<Bin> worstFit(LinkedList<Bin> bins, Collection<BinItem> input) throws BinCapacityException {

        return process(bins, input, new WorstFitComparator());
    }

    public static List<Bin> nextFit(LinkedList<Bin> bins, Collection<BinItem> input) throws BinCapacityException {

        List<Bin> results = new LinkedList<Bin>(bins);

        List<BinItem> items = new ArrayList<BinItem>(input);
        int i = 0;
        for (Bin bin : bins) {
            for (; i < items.size(); ++i) {
                BinItem item = items.get(i);
                if (!bin.canFit(item)) {
                    break;
                }
                bin.addItem(item);
            }
        }

        return results;
    }

    private static List<Bin> process(LinkedList<Bin> bins, Collection<BinItem> input, Comparator<Bin> comparator)
            throws BinCapacityException {

        Queue<Bin> binQueue = new PriorityQueue<Bin>(bins.size(), comparator);

        List<Bin> results = new LinkedList<Bin>(bins);
        List<BinItem> sortedInput = new ArrayList<BinItem>(input);
        Collections.sort(sortedInput, new BinItemComparator(_sortMethod));

        for (BinItem item : sortedInput) {
            place(item, binQueue, bins);
        }

        return results;
    }

    private static void place(BinItem item, Queue<Bin> bins, LinkedList<Bin> availableBins) throws BinCapacityException {

        if (bins.isEmpty() && availableBins.isEmpty()) {
            throw new BinCapacityException("No more bins are available for packing.");
        }
        else if (bins.isEmpty()) {
            bins.add(availableBins.removeFirst());
        }

        Bin bin = bins.remove();
        if (bin.canFit(item)) {
            bin.addItem(item);
        }
        else {
            place(item, bins, availableBins);
        }
        bins.add(bin);

    }

}
