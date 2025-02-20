/*
 * Copyright 2015 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.api.interactions.commands.build.attributes;

import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.internal.utils.Checks;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Builder for scoped Application Commands.
 *
 * @see net.dv8tion.jda.api.interactions.commands.build.CommandData
 * @see net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
 * @see net.dv8tion.jda.api.interactions.commands.build.EntryPointCommandData
 */
public interface IScopedCommandData extends INamedCommandData
{
    /**
     * Sets whether this command is only usable in a guild (Default: false).
     * <br>This only has an effect if this command is registered globally.
     *
     * @param  guildOnly
     *         Whether to restrict this command to guilds
     *
     * @return The builder instance, for chaining
     *
     * @deprecated Replaced with {@link #setContexts(InteractionContextType...)}
     */
    @Nonnull
    @Deprecated
    @ReplaceWith("setContexts(InteractionContextType.GUILD)")
    IScopedCommandData setGuildOnly(boolean guildOnly);

    /**
     * Whether the command can only be used inside a guild.
     * <br>Always true for guild commands.
     *
     * @return True, if this command is restricted to guilds.
     *
     * @deprecated Replaced with {@link #getContexts()}
     */
    @Deprecated
    @ReplaceWith("getContexts().equals(EnumSet.of(InteractionContextType.GUILD))")
    boolean isGuildOnly();

    /**
     * Sets the contexts in which this command can be used (Default: Guild and Bot DMs).
     * <br>This only has an effect if this command is registered globally.
     *
     * @param  contexts
     *         The contexts in which this command can be used
     *
     * @throws IllegalArgumentException
     *         If {@code null} or no interaction context types were passed
     *
     * @return The builder instance, for chaining
     */
    @Nonnull
    default IScopedCommandData setContexts(@Nonnull InteractionContextType... contexts)
    {
        Checks.notEmpty(contexts, "Contexts");
        return setContexts(Arrays.asList(contexts));
    }

    /**
     * Sets the contexts in which this command can be used (Default: Guild and Bot DMs).
     * <br>This only has an effect if this command is registered globally.
     *
     * @param  contexts
     *         The contexts in which this command can be used
     *
     * @throws IllegalArgumentException
     *         If {@code null} or no interaction context types were passed
     *
     * @return The builder instance, for chaining
     */
    @Nonnull
    IScopedCommandData setContexts(@Nonnull Collection<InteractionContextType> contexts);

    /**
     * Sets the integration types on which this command can be installed on (Default: Guilds).
     * <br>This only has an effect if this command is registered globally.
     *
     * @param  integrationTypes
     *         The integration types on which this command can be installed on
     *
     * @throws IllegalArgumentException
     *         If {@code null} or no integration types were passed
     *
     * @return The builder instance, for chaining
     */
    @Nonnull
    default IScopedCommandData setIntegrationTypes(@Nonnull IntegrationType... integrationTypes)
    {
        Checks.notEmpty(integrationTypes, "Integration types");
        return setIntegrationTypes(Arrays.asList(integrationTypes));
    }

    /**
     * Sets the integration types on which this command can be installed on (Default: Guilds).
     * <br>This only has an effect if this command is registered globally.
     *
     * @param  integrationTypes
     *         The integration types on which this command can be installed on
     *
     * @throws IllegalArgumentException
     *         If {@code null} or no integration types were passed
     *
     * @return The builder instance, for chaining
     */
    @Nonnull
    IScopedCommandData setIntegrationTypes(@Nonnull Collection<IntegrationType> integrationTypes);

    /**
     * The contexts in which this command can be used.
     *
     * @return The contexts in which this command can be used
     */
    @Nonnull
    @UnmodifiableView
    Set<InteractionContextType> getContexts();

    /**
     * Gets the integration types on which this command can be installed on.
     *
     * @return The integration types on which this command can be installed on
     */
    @Nonnull
    @UnmodifiableView
    Set<IntegrationType> getIntegrationTypes();
}
