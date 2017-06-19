package br.com.piracraft.lobby2.menu;

import java.sql.ResultSet;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import br.com.piracraft.api.caixas.ItemAPI;
import br.com.piracraft.api.util.MySQL;
import br.com.piracraft.lobby2.utils.Cor;
import br.com.piracraft.lobby2.utils.LobbyAPI;

public class MenuFaction extends Menu{

	public MenuFaction() {
		super("�8Faction - Salas");
	}
	private static HashMap<Integer, String> servers = new HashMap<>();

	public void update() {
		int id = getPosition(2, 1);
		try {
			ResultSet rs = MySQL.getQueryResult("select * from "
					+ "V_MENU_SERVIDORES where ID_MINIGAME = '4' "
					+ "and SALA_TIPO = '2' and ID_NETWORK = '1';");
			
			while(rs.next()){
				getMenu().setItem(id, ItemAPI.Criar(Material.MUSHROOM_SOUP, 1,0 ,"�6Sala "+rs.getString("NOMEDASALA"), false));
				servers.put(id, rs.getString("NOME_BUNGEECORD"));
				id++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@EventHandler
	public void event(InventoryClickEvent e){
		if (e.getWhoClicked() instanceof Player){
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getTitle().equals(getMenu().getTitle())){
				e.setCancelled(true);
				int slot = e.getSlot();
				if (servers.containsKey(slot)){
					String server = servers.get(slot);
					LobbyAPI.enviarAlertaPlayer(p, "Factions",
							"Voce esta sendo enviado para " + server,
							Cor.Verde);
					LobbyAPI.enviar(p, server);
				}
			}
		}
	}

}