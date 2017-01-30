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
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.tobecontinued.util.bpack.core.Bin;
import com.tobecontinued.util.bpack.core.BinItem;
import com.tobecontinued.util.bpack.exception.BinCapacityException;

/**
 * Test harness for exercising the BPack utility class.
 * 
 * Copyright (c) 2013 Charles Lukas All Rights Reserved
 * 
 * @author Charles Lukas
 */
public class TU_BPack {

    @Test
    public void testFirstFit() throws BinCapacityException {

        // 1. SETUP ///////////////////////////////////////////////////////////
        Collection<BinItem> item = setupItems();
        LinkedList<Bin> bins = setupBins();

        // 2. EXECUTE /////////////////////////////////////////////////////////
        List<Bin> newBins = BPack.firstFit(bins, item);

        // 3. VERIFY //////////////////////////////////////////////////////////
        System.out.println("=====================================================================");
        System.out.println("FIRST FIT");
        System.out.println("=====================================================================");
        for (Bin bin : newBins) {
            System.out.println(bin);
        }
        System.out.println("=====================================================================");

    }

    @Test
    public void testLastFit() throws BinCapacityException {

        // 1. SETUP ///////////////////////////////////////////////////////////
        Collection<BinItem> item = setupItems();
        LinkedList<Bin> bins = setupBins();

        // 2. EXECUTE /////////////////////////////////////////////////////////
        List<Bin> newBins = BPack.lastFit(bins, item);

        // 3. VERIFY //////////////////////////////////////////////////////////
        System.out.println("=====================================================================");
        System.out.println("LAST FIT");
        System.out.println("=====================================================================");
        for (Bin bin : newBins) {
            System.out.println(bin);
        }
        System.out.println("=====================================================================");

    }

    @Test
    public void testBestFit() throws BinCapacityException {

        // 1. SETUP ///////////////////////////////////////////////////////////
        Collection<BinItem> item = setupItems();
        LinkedList<Bin> bins = setupBins();

        // 2. EXECUTE /////////////////////////////////////////////////////////
        List<Bin> newBins = BPack.bestFit(bins, item);

        // 3. VERIFY //////////////////////////////////////////////////////////
        System.out.println("=====================================================================");
        System.out.println("BEST FIT");
        System.out.println("=====================================================================");
        for (Bin bin : newBins) {
            System.out.println(bin);
        }
        System.out.println("=====================================================================");

    }

    @Test
    public void testWorstFit() throws BinCapacityException {

        // 1. SETUP ///////////////////////////////////////////////////////////
        Collection<BinItem> item = setupItems();
        LinkedList<Bin> bins = setupBins();

        // 2. EXECUTE /////////////////////////////////////////////////////////
        List<Bin> newBins = BPack.worstFit(bins, item);

        // 3. VERIFY //////////////////////////////////////////////////////////
        System.out.println("=====================================================================");
        System.out.println("WORST FIT");
        System.out.println("=====================================================================");
        for (Bin bin : newBins) {
            System.out.println(bin);
        }
        System.out.println("=====================================================================");

    }

    @Test
    public void testNextFit() throws BinCapacityException {

        // 1. SETUP ///////////////////////////////////////////////////////////
        Collection<BinItem> item = setupItems();
        LinkedList<Bin> bins = setupBins();

        // 2. EXECUTE /////////////////////////////////////////////////////////
        List<Bin> newBins = BPack.nextFit(bins, item);

        // 3. VERIFY //////////////////////////////////////////////////////////
        System.out.println("=====================================================================");
        System.out.println("NEXT FIT");
        System.out.println("=====================================================================");
        for (Bin bin : newBins) {
            System.out.println(bin);
        }
        System.out.println("=====================================================================");

    }

    /**
     * Generate ten bin items with varying capacities.
     * 
     * @return Collection of bin items.
     */
    private Collection<BinItem> setupItems() {

        Collection<BinItem> items = new ArrayList<BinItem>();

        items.add(new BPackItem(5));
        items.add(new BPackItem(6));
        items.add(new BPackItem(2));
        items.add(new BPackItem(9));
        items.add(new BPackItem(6));
        items.add(new BPackItem(3));
        items.add(new BPackItem(1));
        items.add(new BPackItem(1));
        items.add(new BPackItem(8));
        items.add(new BPackItem(5));
        items.add(new BPackItem(9));
        items.add(new BPackItem(3));
        items.add(new BPackItem(5));

        return items;
    }

    /**
     * Generate ten bins with a capacity of 10 each.
     * 
     * @return LinkedList of empty bins.
     * @throws BinCapacityException
     */
    private LinkedList<Bin> setupBins() throws BinCapacityException {

        LinkedList<Bin> bins = Lists.newLinkedList();

        for (int i = 0; i < 10; ++i) {
            bins.add(new Bin(10));
        }

        return bins;
    }

    /**
     * Temporary testing class for bin items.
     * 
     * @author Charles Lukas
     */
    private class BPackItem extends BinItem {

        private int capacity;

        public BPackItem(int cap) {

            capacity = cap;
        }

        // @see com.redprairie.wmd.util.bpack.BinItem#binCapacity()

        @Override
        public int binCapacity() {

            return capacity;
        }

    }
}
