/*
 * Copyright (C) 2019 Igor Swieton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.igorswieton.reportplugin.util;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public final class ItemBuilder {

  private final ItemStack item;
  private final ItemMeta itemMeta;

  public ItemBuilder(Material material, short subID) {
    item = new ItemStack(material, 1, subID);
    itemMeta = item.getItemMeta();
  }

  public ItemBuilder(Material material) {
    this(material, (short) 0);
  }

  public ItemBuilder setName(String name) {
    itemMeta.setDisplayName(name);
    return this;
  }

  public ItemBuilder setLore(String... lore) {
    itemMeta.setLore(Arrays.asList(lore));
    return this;
  }

  public ItemBuilder setAmount(int amount) {
    item.setAmount(amount);
    return this;
  }

  public ItemBuilder addEnchantment(Enchantment enchantment, int level,
      boolean ignoreLevelRestriction) {
    itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
    return this;
  }

  public ItemBuilder removeFlag(ItemFlag flag) {
    itemMeta.removeItemFlags(flag);
    return this;
  }

  public ItemStack build() {
    item.setItemMeta(itemMeta);
    return item;
  }
}
