package com.gmail.icyfiremario797.geminichad.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class GeminichadConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CHAD_SERVER_URL;

    static {
        BUILDER.push("Gemini Chad Client Config");

        CHAD_SERVER_URL = BUILDER.comment("This is a string").define("Chad Server URL", "http://127.0.0.1:5000");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }


}