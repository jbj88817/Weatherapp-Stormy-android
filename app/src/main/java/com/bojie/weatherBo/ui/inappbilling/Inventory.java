/* Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bojie.weatherbo.ui.inappbilling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a block of information about in-app items. An Inventory is returned by such methods as
 * {@link IabHelper#queryInventory}.
 */
@SuppressWarnings("javadoc")
public class Inventory {
    Map<String, Purchase> mPurchaseMap = new HashMap<String, Purchase>();
    Map<String, SkuDetails> mSkuMap = new HashMap<String, SkuDetails>();

    public Inventory() {
    }

    public void addPurchase(final Purchase p) {
        mPurchaseMap.put(p.getSku(), p);
    }

    public void addSkuDetails(final SkuDetails d) {
        mSkuMap.put(d.getSku(), d);
    }

    /**
     * Returns a list of all owned product IDs of a given type
     */
    public List<String> getAllOwnedSkus(final String itemType) {
        final List<String> result = new ArrayList<String>();
        for (final Purchase p : mPurchaseMap.values())
            if (p.getItemType().equals(itemType))
                result.add(p.getSku());
        return result;
    }

    /**
     * Erase a purchase (locally) from the inventory, given its product ID. This just modifies the Inventory object
     * locally and has no effect on the server! This is useful when you have an existing Inventory object which you know
     * to be up to date, and you have just consumed an item successfully, which means that erasing its purchase data
     * from the Inventory you already have is quicker than querying for a new Inventory.
     */
    public void erasePurchase(final String sku) {
        if (mPurchaseMap.containsKey(sku))
            mPurchaseMap.remove(sku);
    }

    /**
     * Returns a list of all owned product IDs.
     */
    public List<String> getAllOwnedSkus() {
        return new ArrayList<String>(mPurchaseMap.keySet());
    }

    /**
     * Returns a list of all purchases.
     */
    public List<Purchase> getAllPurchases() {
        return new ArrayList<Purchase>(mPurchaseMap.values());
    }

    /**
     * Returns purchase information for a given product, or null if there is no purchase.
     */
    public Purchase getPurchase(final String sku) {
        return mPurchaseMap.get(sku);
    }

    /**
     * Returns the listing details for an in-app product.
     */
    public SkuDetails getSkuDetails(final String sku) {
        return mSkuMap.get(sku);
    }

    /**
     * Return whether or not details about the given product are available.
     */
    public boolean hasDetails(final String sku) {
        return mSkuMap.containsKey(sku);
    }

    /**
     * Returns whether or not there exists a purchase of the given product.
     */
    public boolean hasPurchase(final String sku) {
        return mPurchaseMap.containsKey(sku);
    }

    @Override
    public String toString() {
        return mSkuMap.toString();
    }
}
