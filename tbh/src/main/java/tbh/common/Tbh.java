package tbh.common;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "tbh", version = "1.0", acceptableRemoteVersions = "*")
public class Tbh
{
	@Mod.Instance
	public static Tbh instance;
	
	private EventHandler eventHandler;
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		eventHandler = new EventHandler();
		FMLCommonHandler.instance().bus().register(eventHandler);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}
