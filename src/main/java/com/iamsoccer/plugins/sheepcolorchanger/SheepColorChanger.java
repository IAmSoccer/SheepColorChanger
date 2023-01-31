package com.iamsoccer.plugins.sheepcolorchanger;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public class SheepColorChanger extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "SheepColorChanger" + ChatColor.GRAY + "]" + ChatColor.GREEN + " Plugin has been enabled!");
        }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "SheepColorChanger" + ChatColor.GRAY + "]" + ChatColor.RED + " Plugin has been disabled!");
    }

    @EventHandler
    public void onSheepInteract(PlayerInteractEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getRightClicked() instanceof Sheep && event.getHand() == EquipmentSlot.HAND) {
            Sheep sheep = (Sheep) event.getRightClicked();
            ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
            if (event.getPlayer().isSneaking() && (itemInHand == null || itemInHand.getType() == Material.AIR)) {
                Random random = new Random();
                sheep.setColor(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
            }
        }
    }
}

