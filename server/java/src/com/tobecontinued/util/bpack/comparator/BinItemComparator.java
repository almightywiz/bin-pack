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

package com.tobecontinued.util.bpack.comparator;

import java.util.Comparator;

import com.tobecontinued.util.bpack.BPack.SORT_METHOD;
import com.tobecontinued.util.bpack.core.BinItem;

/**
 * TODO Class Description
 * 
 * Copyright (c) 2013 Charles Lukas All Rights Reserved
 * 
 * @author Charles Lukas
 */
public class BinItemComparator implements Comparator<BinItem> {

    private int _direction = 0;

    public BinItemComparator(SORT_METHOD method) {

        switch (method) {
            case ASC:
                _direction = 1;
                break;
            case DSC:
                _direction = -1;
                break;
            default:
                break;
        }
    }

    // @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)

    @Override
    public int compare(BinItem o1, BinItem o2) {

        if (o1.binCapacity() >= o2.binCapacity()) {
            return 1 * _direction;
        }
        else {
            return -1 * _direction;
        }

    }

}
