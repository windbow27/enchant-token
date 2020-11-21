package me.WindBow.events;

import me.WindBow.Main;
import me.WindBow.enchants.CustomEnchants;
import me.WindBow.inventories.CleanserInv;
import me.WindBow.inventories.EnchantItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ApplyCleanser implements Listener {
    public Set<UUID> hadInvC = new HashSet<>();
    public ItemStack tool;
    public ItemStack cleanser;
    EnchantItem enchantItem = new EnchantItem();
    CustomEnchants ce = new CustomEnchants();

    @EventHandler
    public void onUse(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            ItemStack currentcleanser = e.getCursor();
            ItemStack currenttool = e.getCurrentItem();

            if (currentcleanser != null && currenttool != null) {
                if (currentcleanser.getType() == Material.REDSTONE && currentcleanser.hasItemMeta() && currenttool.hasItemMeta()) {
                    if (currentcleanser.getItemMeta().hasEnchant(Enchantment.LUCK) && currenttool.getType().getMaxDurability() > 5) {
                        if (currenttool.getItemMeta().hasLore()) {
                            if (currentcleanser.getAmount() > 1) {
                                player.sendMessage(ChatColor.RED + "You can't you stacked Cleanser!");
                                return;
                            }
                            tool = currenttool.clone();
                            cleanser = currentcleanser.clone();

                            currenttool.setAmount(0);
                            currentcleanser.setAmount(0);
                            hadInvC.add(player.getUniqueId());

                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    player.updateInventory();
                                    cancel();
                                }
                            }.runTaskLater(Main.getInstance(), 2);

                            int check = ThreadLocalRandom.current().nextInt(100) + 1;

                            Bukkit.broadcastMessage("check:" + check);

                            //Fail
                            if (cleanser.getItemMeta().getEnchantLevel(Enchantment.LUCK) < check) {
                                List<Enchantment> enchantments = new ArrayList<Enchantment>(tool.getItemMeta().getEnchants().keySet());
                                Enchantment remove = enchantments.get(ThreadLocalRandom.current().nextInt(enchantments.size()));
                                tool.removeEnchantment(remove);
                                enchantItem.checkMeta(tool);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        player.updateInventory();
                                        player.getInventory().addItem(tool);
                                        player.sendMessage(ChatColor.RED + "Failed! A random enchant has been removed.");
                                        hadInvC.remove(player.getUniqueId());
                                        cancel();
                                    }
                                }.runTaskLater(Main.getInstance(), 5);

                                //Success
                            } else {
                                Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GRAY + "");
                                CleanserInv.getInstance().createCleanser(player, inv);
                                int i = 10;
                                for (Enchantment enchantment : tool.getItemMeta().getEnchants().keySet()) {
                                    Bukkit.broadcastMessage("enchantment: " + enchantment);
                                    setInv(enchantment, inv, i);
                                    if (i == 10 || i == 19 || i == 28) {
                                        i += 9;
                                    } else {
                                        if (i == 37) {
                                            i = 14;
                                        } else {
                                            if (i == 14 || i == 23) {
                                                i += 9;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // get a list of enchant
    // i mean removing enchant based on gui item is fine but feels weird
    // for example: enchant midas remove barrier is on slot 99
    // when click slot 99
    // enchantsThatAreOnThisItem.get(99) => return THAT enchant
    // just a sguuesgtuin
    //sec to load
    HashMap<Integer, Enchantment> enchantsThatAreOnThisItem = new HashMap<>();


    //Switch doesn't accept enchantment, fucking yikes (switch only accept primiative types, and Enum and maybe more idk)
    //I swear to god if there's a faster way to do this dumbshit
    public void setInv(Enchantment enchantment, Inventory inv, int i) {
        //Example suggestion
        // see? just 2 line no if else o and another suggestion is
//        WrappedEnchantment wrapped = new WrappedEnchantment(enchantment);
//        inv.setItem(i, setLore(wrapped.getMaterial(), wrapped.getLore()), ... blalbla);

        if (enchantment.equals(ce.VEIN_MINER)) {
            inv.setItem(i, setLore(Material.VINE, ChatColor.LIGHT_PURPLE + "Vein Miner", ChatColor.LIGHT_PURPLE + "Legendary", "4"));
            inv.setItem(i + 2, setBarrier("Vein Miner"));
        } else {
            if (enchantment.equals(ce.EXPLOSIVE)) {
                inv.setItem(i, setLore(Material.TNT, ChatColor.LIGHT_PURPLE + "Explosive", ChatColor.LIGHT_PURPLE + "Legendary", "4"));
                inv.setItem(i + 2, setBarrier("Explosive"));
            } else {
                if (enchantment.equals(ce.SUPER_BREAKER)) {
                    inv.setItem(i, setLore(Material.DIAMOND_PICKAXE, ChatColor.LIGHT_PURPLE + "Super Breaker", ChatColor.LIGHT_PURPLE + "Legendary", "4"));
                    inv.setItem(i + 2, setBarrier("Super Breaker"));
                } else {
                    if (enchantment.equals(Enchantment.DIG_SPEED)) {
                        inv.setItem(i, setLore(Material.GOLDEN_SHOVEL, ChatColor.LIGHT_PURPLE + "Efficiency", ChatColor.LIGHT_PURPLE + "Legendary", "4"));
                        inv.setItem(i + 2, setBarrier("Efficiency"));
                    } else {
                        if (enchantment.equals(Enchantment.DURABILITY)) {
                            inv.setItem(i, setLore(Material.DIAMOND_CHESTPLATE, ChatColor.LIGHT_PURPLE + "Unbreaking", ChatColor.LIGHT_PURPLE + "Legendary", "3"));
                            inv.setItem(i + 2, setBarrier("Unbreaking"));
                        } else {
                            if (enchantment.equals(ce.HASTE)) {
                                inv.setItem(i, setLore(Material.GOLDEN_PICKAXE, ChatColor.AQUA + "Haste", ChatColor.AQUA + "Epic", "3"));
                                inv.setItem(i + 2, setBarrier("Haste"));
                            } else {
                                if (enchantment.equals(ce.SMELTING)) {
                                    inv.setItem(i, setLore(Material.FURNACE, ChatColor.AQUA + "Smelting", ChatColor.AQUA + "Epic", "3"));
                                    inv.setItem(i + 2, setBarrier("Smelting"));
                                } else {
                                    if (enchantment.equals(ce.MINER_TREAT)) {
                                        inv.setItem(i, setLore(Material.COOKIE, ChatColor.AQUA + "Miner's Treat", ChatColor.AQUA + "Epic", "3"));
                                        inv.setItem(i + 2, setBarrier("Miner's Treat"));
                                    } else {
                                        if (enchantment.equals(ce.HUG_ME)) {
                                            inv.setItem(i, setLore(Material.CREEPER_HEAD, ChatColor.AQUA + "Hug me", ChatColor.AQUA + "Epic", "4"));
                                            inv.setItem(i + 2, setBarrier("Hug me"));
                                        } else {
                                            if (enchantment.equals(ce.REPAIR)) {
                                                inv.setItem(i, setLore(Material.ANVIL, ChatColor.AQUA + "Repair", ChatColor.AQUA + "Epic", "4"));
                                                inv.setItem(i + 2, setBarrier("Repair"));
                                            } else {
                                                if (enchantment.equals(ce.SLOT)) {
                                                    inv.setItem(i, setLore(Material.EMERALD, ChatColor.AQUA + "Slot", ChatColor.AQUA + "Epic", "4"));
                                                    inv.setItem(i + 2, setBarrier("Slot"));
                                                } else {
                                                    if (enchantment.equals(ce.DRILL)) {
                                                        inv.setItem(i, setLore(Material.HOPPER, ChatColor.GOLD + "Drill", ChatColor.GOLD + "Rare", "4"));
                                                        inv.setItem(i + 2, setBarrier("Drill"));
                                                    } else {
                                                        if (enchantment.equals(ce.SPARE_CHANGE)) {
                                                            inv.setItem(i, setLore(Material.GOLD_NUGGET, ChatColor.GOLD + "Spare Change", ChatColor.GOLD + "Rare", "4"));
                                                            inv.setItem(i + 2, setBarrier("Spare Change"));
                                                        } else {
                                                            if (enchantment.equals(ce.REGENERATE)) {
                                                                inv.setItem(i, setLore(Material.COMPARATOR, ChatColor.GOLD + "Regenerate", ChatColor.GOLD + "Rare", "4"));
                                                                inv.setItem(i + 2, setBarrier("Regenerate"));
                                                            } else {

                                                                if (enchantment.equals(ce.MIDAS_TOUCH)) {
                                                                    inv.setItem(i, setLore(Material.GOLD_BLOCK, ChatColor.GOLD + "Midas Touch", ChatColor.GOLD + "Rare", "4"));
                                                                    inv.setItem(i + 2, setBarrier("Midas Touch"));
                                                                } else {
                                                                    if (enchantment.equals(ce.DYNAMITE_RAIN)) {
                                                                        inv.setItem(i, setLore(Material.REDSTONE_TORCH, ChatColor.GOLD + "Dynamite Rain", ChatColor.GOLD + "Rare", "4"));
                                                                        inv.setItem(i + 2, setBarrier("Dynamite Rain"));
                                                                    } else {
                                                                        if (enchantment.equals(ce.TREASURE_HUNTER)) {
                                                                            inv.setItem(i, setLore(Material.CHEST, ChatColor.GOLD + "Treasure Hunter", ChatColor.GOLD + "Rare", "4"));
                                                                            inv.setItem(i + 2, setBarrier("Treasure Hunter"));
                                                                        } else {
                                                                            if (enchantment.equals(ce.NIGHT_VISION)) {
                                                                                inv.setItem(i, setLore(Material.ENDER_EYE, ChatColor.WHITE + "Night Vision", ChatColor.WHITE + "Common", "3"));
                                                                                inv.setItem(i + 2, setBarrier("Night Vision"));
                                                                            } else {
                                                                                if (enchantment.equals(ce.JUMP_BOOST)) {
                                                                                    inv.setItem(i, setLore(Material.RABBIT_FOOT, ChatColor.WHITE + "Jump Boost", ChatColor.WHITE + "Common", "3"));
                                                                                    inv.setItem(i + 2, setBarrier("Jump Boost"));
                                                                                } else {
                                                                                    if (enchantment.equals(ce.SPEED_BOOST)) {
                                                                                        inv.setItem(i, setLore(Material.SUGAR, ChatColor.WHITE + "Speed Boost", ChatColor.WHITE + "Common", "3"));
                                                                                        inv.setItem(i + 2, setBarrier("Speed Boost"));
                                                                                    } else {
                                                                                        if (enchantment.equals(ce.ALCHEMY)) {
                                                                                            inv.setItem(i, setLore(Material.LINGERING_POTION, ChatColor.WHITE + "Alchemy", ChatColor.WHITE + "Common", "4"));
                                                                                            inv.setItem(i + 2, setBarrier("Alchemy"));
                                                                                        } else {
                                                                                            if (enchantment.equals(ce.FREEZE)) {
                                                                                                inv.setItem(i, setLore(Material.PACKED_ICE, ChatColor.WHITE + "Freeze", ChatColor.WHITE + "Common", "3"));
                                                                                                inv.setItem(i + 2, setBarrier("Freeze"));
                                                                                            } else {
                                                                                                if (enchantment.equals(Enchantment.SILK_TOUCH)) {
                                                                                                    inv.setItem(i, setLore(Material.DIAMOND_ORE, ChatColor.WHITE + "Silk Touch", ChatColor.WHITE + "Common", "1"));
                                                                                                    inv.setItem(i + 2, setBarrier("Silk Touch"));
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public ItemStack setBarrier(String name) {
        ItemStack enchant = new ItemStack(Material.BARRIER);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;
        ArrayList<String> lore = new ArrayList<>();
        encmeta.setDisplayName(ChatColor.RED + "Remove " + ChatColor.YELLOW + name);
        lore.add(ChatColor.GRAY + "Click to remove " + name);
        lore.add(ChatColor.GRAY + "from this item.");
        encmeta.setLore(lore);
        enchant.setItemMeta(encmeta);
        return enchant;
    }

    public ItemStack setLore(Material mat, String name, String rarity, String level) {
        ItemStack enchant = new ItemStack(mat);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;
        ArrayList<String> lore = new ArrayList<>();

        encmeta.setDisplayName(name);
        lore.add(ChatColor.GRAY + "Rarity: " + rarity);
        lore.add((ChatColor.GRAY + "Max Level: ") + (ChatColor.YELLOW + level));
        lore.add("");

        encmeta.setLore(lore);
        enchant.setItemMeta(encmeta);
        return enchant;
    }

    @EventHandler
    public void onBarrierClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (e.getClickedInventory() == null) return;
        if (e.getCurrentItem() == null) return;
        if (!(hadInvC.contains(e.getWhoClicked().getUniqueId())))
            return;
        Player player = (Player) e.getWhoClicked();
        if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
            if (e.getCurrentItem().getType() == Material.BARRIER) {
                ItemStack barrier = e.getCurrentItem();
                removeEnchant(barrier);
                enchantItem.checkMeta(tool);
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "Success!");
            }
        }
    }

    //I swear to god
    public void removeEnchant(ItemStack barrier) {
        if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Efficiency")) {
            tool.removeEnchantment(Enchantment.DIG_SPEED);
        } else {
            if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Unbreaking")) {
                tool.removeEnchantment(Enchantment.DURABILITY);
            } else {
                if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Super Breaker")) {
                    tool.removeEnchantment(ce.SUPER_BREAKER);
                } else {
                    if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Explosive")) {
                        tool.removeEnchantment(ce.EXPLOSIVE);
                    } else {
                        if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Vein Miner")) {
                            tool.removeEnchantment(ce.VEIN_MINER);
                        } else {
                            if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Haste")) {
                                tool.removeEnchantment(ce.HASTE);
                            } else {
                                if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Smelting")) {
                                    tool.removeEnchantment(ce.SMELTING);
                                } else {
                                    if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Hug Me")) {
                                        tool.removeEnchantment(ce.HUG_ME);
                                    } else {
                                        if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Miner's Treat")) {
                                            tool.removeEnchantment(ce.MINER_TREAT);
                                        } else {
                                            if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Repair")) {
                                                tool.removeEnchantment(ce.REPAIR);
                                            } else {
                                                if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Slot")) {
                                                    tool.removeEnchantment(ce.SLOT);
                                                } else {
                                                    if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Drill")) {
                                                        tool.removeEnchantment(ce.DRILL);
                                                    } else {
                                                        if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Spare Change")) {
                                                            tool.removeEnchantment(ce.SPARE_CHANGE);
                                                        } else {
                                                            if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Regenerate")) {
                                                                tool.removeEnchantment(ce.REGENERATE);
                                                            } else {
                                                                if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Midas Touch")) {
                                                                    tool.removeEnchantment(ce.MIDAS_TOUCH);
                                                                } else {
                                                                    if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Dynamite Rain")) {
                                                                        tool.removeEnchantment(ce.DYNAMITE_RAIN);
                                                                    } else {
                                                                        if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Treasure Hunter")) {
                                                                            tool.removeEnchantment(ce.TREASURE_HUNTER);
                                                                        } else {
                                                                            if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Night Vision")) {
                                                                                tool.removeEnchantment(ce.NIGHT_VISION);
                                                                            } else {
                                                                                if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Jump Boost")) {
                                                                                    tool.removeEnchantment(ce.JUMP_BOOST);
                                                                                } else {
                                                                                    if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Speed Boost")) {
                                                                                        tool.removeEnchantment(ce.SPEED_BOOST);
                                                                                    } else {
                                                                                        if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Alchemy")) {
                                                                                            tool.removeEnchantment(ce.ALCHEMY);
                                                                                        } else {
                                                                                            if (barrier.getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Silk Touch")) {
                                                                                                tool.removeEnchantment(Enchantment.SILK_TOUCH);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Cancel Stuffs
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (!(hadInvC.contains(e.getWhoClicked().getUniqueId())))
            return;
        if (e.getClickedInventory() == null) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;
        if (!(hadInvC.contains(e.getPlayer().getUniqueId())))
            return;
        Player player = (Player) e.getPlayer();
        player.getInventory().addItem(tool);
        hadInvC.remove(player.getUniqueId());
    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(hadInvC.contains(e.getEntity().getUniqueId())))
            return;
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
