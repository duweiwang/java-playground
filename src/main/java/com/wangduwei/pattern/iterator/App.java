/*
 * The MIT License
 * Copyright © 2014-2021 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wangduwei.pattern.iterator;


import com.wangduwei.pattern.iterator.bst.BstIterator;
import com.wangduwei.pattern.iterator.bst.TreeNode;
import com.wangduwei.pattern.iterator.list.ItemType;
import com.wangduwei.pattern.iterator.list.TreasureChest;

import static com.wangduwei.pattern.iterator.list.ItemType.*;

/**
 * 分离遍历算法和容器
 */
public class App {

  private static final TreasureChest TREASURE_CHEST = new TreasureChest();

  private static void demonstrateTreasureChestIteratorForType(ItemType itemType) {
    LOGGER.info("------------------------");
    LOGGER.info("Item Iterator for ItemType " + itemType + ": ");
    Iterator itemIterator = TREASURE_CHEST.iterator(itemType);
    while (itemIterator.hasNext()) {
      LOGGER.info(itemIterator.next().toString());
    }
  }

  private static void demonstrateBstIterator() {
    LOGGER.info("------------------------");
    LOGGER.info("BST Iterator: ");
    TreeNode root = buildIntegerBst();
    Iterator<TreeNode> bstIterator = new BstIterator<>(root);
    while (bstIterator.hasNext()) {
      LOGGER.info("Next node: " + bstIterator.next().getVal());
    }
  }

  private static TreeNode<Integer> buildIntegerBst() {
    TreeNode root = new TreeNode<>(8);

    root.insert(3);
    root.insert(10);
    root.insert(1);
    root.insert(6);
    root.insert(14);
    root.insert(4);
    root.insert(7);
    root.insert(13);

    return root;
  }

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    demonstrateTreasureChestIteratorForType(RING);
    demonstrateTreasureChestIteratorForType(POTION);
    demonstrateTreasureChestIteratorForType(WEAPON);
    demonstrateTreasureChestIteratorForType(ANY);

    demonstrateBstIterator();
  }
}
