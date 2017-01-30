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

import com.tobecontinued.util.bpack.core.Bin;

/**
 * TODO Class Description
 * 
 * Copyright (c) 2013 Charles Lukas All Rights Reserved
 * 
 * @author Charles Lukas
 */
public class BestFitComparator implements Comparator<Bin> {

    // @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)

    @Override
    public int compare(Bin o1, Bin o2) {

        if (o1.getCurrentCapacity() > o2.getCurrentCapacity()) {
            return -1;
        }
        else if (o1.getCurrentCapacity() < o2.getCurrentCapacity()) {
            return 1;
        }
        else if (o1.getIdentifier() < o2.getIdentifier()) {
            return -1;
        }
        else {
            return 1;
        }

    }

}
