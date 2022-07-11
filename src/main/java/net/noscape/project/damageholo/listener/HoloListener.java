package net.noscape.project.damageholo.listener;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class HoloListener implements Listener {

    @EventHandler
    public void manipulate(PlayerArmorStandManipulateEvent e) {
        if (!e.getRightClicked().isVisible()) {
            e.setCancelled(true);
        }
    }
}
