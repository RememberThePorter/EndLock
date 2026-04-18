package net.vercte.endlock;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class LockEndCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(LockEndCommand.register(context));
    }

    public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
        return Commands.literal("lockEnd")
                .requires(cs -> cs.hasPermission(2))
                .then(
                        Commands.argument("lock", BoolArgumentType.bool())
                                .executes(LockEndCommand::lock)
                );
    }

    private static int lock(CommandContext<CommandSourceStack> context) {
        boolean locked = BoolArgumentType.getBool(context, "lock");

        Config.setLockEnd(locked);

        Component component = locked ? Component.translatable("endlock.command.lock.enabled") : Component.translatable("endlock.command.lock.disabled");
        context.getSource().sendSuccess(() -> component, true);

        return Command.SINGLE_SUCCESS;
    }
}
