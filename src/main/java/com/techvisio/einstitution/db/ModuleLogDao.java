package com.techvisio.einstitution.db;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ModuleLog;

@Component
public interface ModuleLogDao {
	public ModuleLog getModuleLog(int entityId);
	public void addModuleLog(ModuleLog moduleLog);
	public void updateModuleLog(ModuleLog moduleLog);
	public void deleteModuleLog(int entityId);
}
