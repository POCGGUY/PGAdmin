package org.PGAdmin.globalVariables;

import org.PGAdmin.savedDataManager.savedDataKeys;

import java.util.ArrayList;

import static org.PGAdmin.Main.sdManager;

public class FrozenPlayers{
    private ArrayList<String> frozenPlayers;
    private final String key;

    public FrozenPlayers(){
        key = savedDataKeys.FrozenPlayersKey;
        if(sdManager.isDataExist(key)){
            this.frozenPlayers = sdManager.getListData(key);
        }
        else this.frozenPlayers = new ArrayList<String>();
    }

    public void addFrozenPlayer(String nickname) {
        frozenPlayers.add(nickname);
        sdManager.saveListData(key, frozenPlayers);
    }
    public boolean deleteFrozenPlayer(String nickname) {
        if(frozenPlayers.removeIf(nickname::equals)){
            sdManager.saveListData(key, frozenPlayers);
            return true;
        }
        else return false;
    }
    public boolean isPlayerFrozen(String nickname) {
        return frozenPlayers.contains(nickname);
    }
}