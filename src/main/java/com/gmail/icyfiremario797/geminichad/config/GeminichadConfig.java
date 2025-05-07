package com.gmail.icyfiremario797.geminichad.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class GeminichadConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CHAD_SERVER_URL;

    static {
        BUILDER.push("Gemini Chad Client Config");

        CHAD_SERVER_URL = BUILDER.comment("Chad Server URL").define("Chad Server URL", "https://gemini-mc-server.onrender.com/");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}