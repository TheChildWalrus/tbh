package tbh.common;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
	@SubscribeEvent
	public void onServerChat(ServerChatEvent event)
	{
		EntityPlayerMP entityplayer = event.player;
		String message = event.message;
		String username = event.username;
		ChatComponentTranslation chatComponent = event.component;
		
		Random rand = entityplayer.getRNG();
		
		if (!MinecraftServer.getServer().getConfigurationManager().func_152596_g(entityplayer.getGameProfile()) || entityplayer.getCommandSenderName().equalsIgnoreCase("childwalrus"))
		{
			String key = chatComponent.getKey();
			Object[] formatArgs = chatComponent.getFormatArgs();
			for (int a = 0; a < formatArgs.length; a++)
			{
				Object arg = formatArgs[a];
				
				String chatText = null;
				boolean isRawString = false;
				
				if (arg instanceof ChatComponentText)
				{
					ChatComponentText componentText = (ChatComponentText)arg;
					chatText = componentText.getUnformattedText();
					isRawString = false;
				}
				else if (arg instanceof String)
				{
					chatText = (String)arg;
					isRawString = true;
				}
	
				if (chatText != null && chatText.equals(message))
				{
					String newText = convertMessage(chatText, rand);
					
					if (isRawString)
					{
						formatArgs[a] = newText;
					}
					else
					{
						formatArgs[a] = new ChatComponentText(newText);
					}
				}
			}
				
			ChatComponentTranslation newComponent = new ChatComponentTranslation(key, formatArgs);
			chatComponent = newComponent;
			
			event.component = chatComponent;
		}
	}

	private String convertMessage(String chatText, Random rand)
	{
		if (chatText.length() > 0)
		{
			if (rand.nextInt(10) == 0)
			{
				if (Character.isUpperCase(chatText.charAt(0)))
				{
					chatText = randomTbhUppercase(rand) + " " + chatText.substring(0, 1).toLowerCase() + chatText.substring(1);
				}
				else
				{
					chatText = randomTbh(rand) + " " + chatText;
				}
			}
			
			if (rand.nextInt(10) == 0)
			{
				chatText += " " + randomTbh(rand);
				if (rand.nextInt(3) == 0)
				{
					chatText += ".";
				}
			}
			
			if (rand.nextInt(6) == 0)
			{
				int maxReplaces = 6;
				int replaces = 0;
				for (int i = 0; i < maxReplaces; i++)
				{
					if (i == 0 || rand.nextInt(3 * i) == 0)
					{
						replaces++;
					}
				}
				
				for (int l = 0; l < replaces; l++)
				{
					int rndIndex = rand.nextInt(chatText.length());
					int indexOf = chatText.indexOf(" ", rndIndex);
					if (indexOf >= 0)
					{
						chatText = chatText.substring(0, indexOf) + " " + randomTbh(rand) + " " + chatText.substring(indexOf + 1);
					}
				}
			}
		}
		
		return chatText;
	}
	
	private String randomTbh(Random rand)
	{
		if (rand.nextInt(20) == 0)
		{
			return "not gonna lie";
		}
		if (rand.nextInt(12) == 0)
		{
			return "ngl";
		}
		if (rand.nextInt(10) == 0)
		{
			return "tbqh";
		}
		if (rand.nextInt(15) == 0)
		{
			return "to be quite honest";
		}
		if (rand.nextInt(20) == 0)
		{
			return "to be fair";
		}
		if (rand.nextInt(5) == 0)
		{
			return "tbf";
		}
		if (rand.nextInt(10) == 0)
		{
			return "to be honest";
		}
		return "tbh";
	} 
	
	private String randomTbhUppercase(Random rand)
	{
		String s = randomTbh(rand);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
