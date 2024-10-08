package online.connlost;

import online.connlost.server.config.ConfigManager;
import online.connlost.util.ItemsHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigSync {
    private static final ItemsHelper itemsHelper = ItemsHelper.getItemsHelper();

    /**
     * Items' stacksize is directly modified, no config is held for that in the "client" side.
     * In contrast, we do store rules in client side as some mixins in client side classes need it.
     * @param configList the config list
     */
    public static void syncConfig(ArrayList<LinkedHashMap<String, Integer>> configList){
        AllStackable.LOGGER.info("[All Stackable] [Client] Sync config from server side!");
        itemsHelper.setCountByConfig(configList.get(0).entrySet(), false);
        ConfigManager.getConfigManager().setRulesMap(configList.get(1));
        AllStackable.LOGGER.info("[All Stackable] [Client] Sync rules:");
        for (Map.Entry<String, Integer> rule: configList.get(1).entrySet()){
            String tag = switch (rule.getValue()) {
                case 0 -> "false";
                case 1 -> "true";
                default -> rule.getValue().toString();
            };
            AllStackable.LOGGER.info("\t[{}] = {}", rule.getKey(), tag);
        }
        AllStackable.LOGGER.info("[All Stackable] [Client] Sync finished.");
    }

    public static void resetConfig(){
        itemsHelper.resetAll(false);
        ConfigManager.getConfigManager().setRulesMap(ConfigManager.getConfigManager().defaultRules(false));
    }
}
