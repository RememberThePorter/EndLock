package net.vercte.endlock;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LOCK_END = BUILDER.comment("Idiot. Can you fuckign read").define("lockEnd", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean lockEnd;

    static void setLockEnd(boolean lock) {
        LOCK_END.set(lock);
        SPEC.save();
    }

    static void onLoad(final ModConfigEvent event) {
        lockEnd = LOCK_END.get();
    }
}
