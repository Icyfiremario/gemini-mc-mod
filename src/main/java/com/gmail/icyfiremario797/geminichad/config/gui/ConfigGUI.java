package com.gmail.icyfiremario797.geminichad.config.gui;

import com.gmail.icyfiremario797.geminichad.config.GeminichadConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigGUI {

    public static Screen createConfigScreen (Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("ChadMC config"));

        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startStrField(Component.literal("Chad Server URL"), GeminichadConfig.CHAD_SERVER_URL.get())
                .setDefaultValue("https://gemini-mc-server.onrender.com/")
                .setSaveConsumer(newValue -> GeminichadConfig.CHAD_SERVER_URL.set(newValue))
                .build());

        builder.setSavingRunnable(GeminichadConfig.SPEC::save);

        return builder.build();
    }
}
