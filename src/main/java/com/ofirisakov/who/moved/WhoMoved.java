package com.ofirisakov.who.moved;

import java.io.File;

import com.ofirisakov.who.moved.events.BlockWithEntityCallback;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;

public class WhoMoved implements ModInitializer {
	String dbFileFolder = "mods/WhoMoved/";
	String dbFileName = "db.sqlite";
	

	@Override
	public void onInitialize() {
		System.out.println("WhoMoved Starting...");

		System.out.println("Creating folder for the database");
		File theDir = new File(dbFileFolder);
		if (!theDir.exists()){
			theDir.mkdirs();
		}

		System.out.println("Connecting to SQLite on: \"" + dbFileFolder + dbFileName + "\"...");
		SQLiteManager.connectToSQLiteDatabase("jdbc:sqlite:" + dbFileFolder + dbFileName);

		System.out.println("Initilizing the SQLite DB...");
		SQLiteManager.initTable();

		
		System.out.println("Registering callback event to log into SQLite DB");
		BlockWithEntityCallback.EVENT.register((state, world, pos, player, hand, hit) -> {
            SQLiteManager.insertLog(player.getName().getString(), state.getBlock().asItem().toString(), pos.getX(), pos.getY(), pos.getZ());
			return ActionResult.PASS;
        });
	}
}
