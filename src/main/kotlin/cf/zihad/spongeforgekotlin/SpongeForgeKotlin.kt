package cf.zihad.spongeforgekotlin

import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.plugin.Plugin

@Plugin(
    id = SpongeForgeKotlin.PLUGIN_ID,
    name = SpongeForgeKotlin.PLUGIN_NAME,
    version = SpongeForgeKotlin.VERSION,
    authors = [SpongeForgeKotlin.AUTHOR]
)
class SpongeForgeKotlin {
    @Inject
    lateinit var logger: Logger

    @Listener
    fun gameInitialization(event: GameInitializationEvent) {
        logger.info("Loading SpongeForgeKotlin v$VERSION!")
    }

    companion object {
        const val AUTHOR = "Zihad"
        const val PLUGIN_ID = "sponge-forge-kotlin-plugin"
        const val PLUGIN_NAME = "SpongeForgeKotlin-Plugin"
        const val VERSION = "1.0"
    }
}
