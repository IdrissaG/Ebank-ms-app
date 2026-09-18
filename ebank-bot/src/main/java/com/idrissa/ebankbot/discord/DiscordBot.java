package com.idrissa.ebankbot.discord;

import com.idrissa.ebankbot.agent.EbankAiAgent;
import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@DiscordController
public class DiscordBot {

    EbankAiAgent ebankAiAgent;

    public DiscordBot(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @DiscordMapping
    private void interaction(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
       String query = event.getMessage().getContentRaw();
        String response = ebankAiAgent.chat(query);
        event.getChannel().sendMessage(response).queue();

    }





}
