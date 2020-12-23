package anticheat;

import Listeners.UserHandler;
import Sets.Savefile;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class VoiceReward extends ListenerAdapter {

    public static boolean isValid(String vcId) {
        if (Savefile.getValue("voice" + vcId + "activityBool") == null)
            return false;
        return Boolean.parseBoolean(Savefile.getValue("voice" + vcId + "activityBool"));
    }

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        int vcsize = 0;
        for (int i = 0; i < event.getChannelJoined().getMembers().size(); i++) {
            if(!event.getChannelJoined().getMembers().get(i).getUser().isBot())
                vcsize++;
        }

        if (vcsize >= 2) {
            Savefile.addKey("voice" + event.getChannelJoined().getId() + "activityBool", String.valueOf(true));
        } else {
            Savefile.addKey("voice" + event.getChannelJoined().getId() + "activityBool", String.valueOf(false));
        }
        UserHandler.voiceJoin(event);

    }

    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        int vcsize = 0;
        for (int i = 0; i < event.getChannelLeft().getMembers().size(); i++) {
            if(!event.getChannelLeft().getMembers().get(i).getUser().isBot())
                vcsize++;
        }
        if (vcsize >= 2) {
            Savefile.addKey("voice" + event.getChannelLeft().getId() + "activityBool", String.valueOf(true));
        } else {
            Savefile.addKey("voice" + event.getChannelLeft().getId() + "activityBool", String.valueOf(false));
        }

        UserHandler.voiceLeave(event);
    }

    @Override
    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        int vcsize = 0;
        for (int i = 0; i < event.getChannelJoined().getMembers().size(); i++) {
            if(!event.getChannelJoined().getMembers().get(i).getUser().isBot())
                vcsize++;
        }

        if (vcsize >= 2) {
            Savefile.addKey("voice" + event.getChannelJoined().getId() + "activityBool", String.valueOf(true));
        } else {
            Savefile.addKey("voice" + event.getChannelJoined().getId() + "activityBool", String.valueOf(false));
        }
        UserHandler.voiceJoin(event);

        vcsize = 0;
        for (int i = 0; i < event.getChannelLeft().getMembers().size(); i++) {
            if(!event.getChannelLeft().getMembers().get(i).getUser().isBot())
                vcsize++;
        }
        if (vcsize >= 2) {
            Savefile.addKey("voice" + event.getChannelLeft().getId() + "activityBool", String.valueOf(true));
        } else {
            Savefile.addKey("voice" + event.getChannelLeft().getId() + "activityBool", String.valueOf(false));
        }

        UserHandler.voiceLeave(event);
    }
}
