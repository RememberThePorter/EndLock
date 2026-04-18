package net.vercte.endlock;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(EndLock.MODID)
public class EndLock {
    public static final String MODID = "endlock";

    public EndLock(IEventBus bus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        bus.addListener(Config::onLoad);

        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public void registerCommands(final RegisterCommandsEvent event) {
        LockEndCommand.register(event.getDispatcher(), event.getBuildContext());
    }
}
