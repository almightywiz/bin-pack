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

package com.tobecontinued.util.bpack.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tobecontinued.util.bpack.exception.BinCapacityException;

/**
 * TODO Class Description
 * 
 * Copyright (c) 2013 Charles Lukas All Rights Reserved
 * 
 * @author Charles Lukas
 */
public class Bin {

    private List<BinItem> _items;
    private int           _capacityMax;
    private int           _capacityCurrent;
    private long          _identifier;
    private static long   ID = 0L;

    /**
     * 
     * @param max
     * @throws BinCapacityException
     */
    public Bin(int max) throws BinCapacityException {

        _items = new ArrayList<BinItem>();
        _capacityMax = max;
        _capacityCurrent = 0;

        if ((_capacityMax - _capacityCurrent) <= 0) throw new BinCapacityException();

        _identifier = ++ID;
    }

    /**
     * 
     * @param max
     * @param contents
     * @throws BinCapacityException
     */
    public Bin(int max, Collection<BinItem> contents) throws BinCapacityException {

        _items = new ArrayList<BinItem>(contents);
        _capacityMax = max;
        _capacityCurrent = 0;

        for (BinItem item : _items) {
            _capacityCurrent += item.binCapacity();
        }

        if ((_capacityMax - _capacityCurrent) <= 0) throw new BinCapacityException();

        _identifier = ++ID;
    }

    /**
     * 
     * @return
     */
    public int getMaxCapacity() {

        return _capacityMax;
    }

    /**
     * 
     * @return
     */
    public int getCurrentCapacity() {

        return _capacityCurrent;
    }

    /**
     * 
     * @return
     */
    public int getAvailableCapacity() {

        return _capacityMax - _capacityCurrent;
    }

    public long getIdentifier() {

        return _identifier;
    }

    /**
     * 
     * @param item
     * @return
     */
    public boolean canFit(BinItem item) {

        return (item.binCapacity() <= (_capacityMax - _capacityCurrent));
    }

    /**
     * 
     * @param item
     * @return
     * @throws BinCapacityException
     */
    public int addItem(BinItem item) throws BinCapacityException {

        if (!canFit(item)) throw new BinCapacityException();

        _items.add(item);
        _capacityCurrent += item.binCapacity();

        return _capacityCurrent;
    }

    // @see java.lang.Object#toString()

    @Override
    public String toString() {

        return "Bin [_capacityCurrent=" + _capacityCurrent + ", _capacityMax=" + _capacityMax + ", _items=" + _items
                + "]";
    }

}
