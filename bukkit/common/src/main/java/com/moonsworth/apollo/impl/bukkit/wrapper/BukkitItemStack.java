package com.moonsworth.apollo.impl.bukkit.wrapper;

import com.moonsworth.apollo.api.bridge.ApolloItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack; // !!!!

public class BukkitItemStack implements ApolloItemStack {

    private final ItemStack itemStack;

    public BukkitItemStack(org.bukkit.inventory.ItemStack item) {
        this.itemStack = CraftItemStack.asNMSCopy(item);
    }

    @Override
    public boolean hasTag(String key) {
        CompoundTag tag = this.itemStack.getTag();

        if(tag != null && tag.contains("lunar")) {
            CompoundTag lunar = tag.getCompound("lunar");
            return lunar.get(key) != null;
        }

        return false;
    }

    @Override
    public void addTag(String key, Object value) {
        CompoundTag tag = new CompoundTag();

        if(value instanceof Integer) {
            tag.putInt(key, (Integer) value);
        } else if(value instanceof Float) {
            tag.putFloat(key, (Float) value);
        } else if(value instanceof Double) {
            tag.putDouble(key, (Double) value);
        } else if(value instanceof String) {
            tag.putString(key, (String) value);
        } else if(value instanceof Byte) {
            tag.putByte(key, (Byte) value);
        } else if(value instanceof Short) {
            tag.putShort(key, (Short) value);
        } else if(value instanceof Long) {
            tag.putLong(key, (Long) value);
        } else if(value instanceof Boolean) {
            tag.putBoolean(key, (Boolean) value);
        } else if(value instanceof Tag) {
            tag.put(key, (Tag) value);
        } else {
            // Throw exception?
        }

        this.itemStack.addTagElement("lunar", tag);
    }

    @Override
    public void removeTag(String key) {
        CompoundTag tag = this.itemStack.getTagElement("lunar");

        if(tag == null) {
            // Throw an exception?
            return;
        }

        tag.remove(key);
    }

    public Object get() {
        return CraftItemStack.asBukkitCopy(this.itemStack);
    }
}
