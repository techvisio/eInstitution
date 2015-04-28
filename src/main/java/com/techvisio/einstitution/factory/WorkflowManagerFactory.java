package com.techvisio.einstitution.factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;

import com.techvisio.einstitution.util.ContextProvider;


public class WorkflowManagerFactory {

	private static final Logger logger = Logger.getLogger(WorkflowManagerFactory.class); 
	private static Map<Class,Object> factoryObjectMap=new HashMap<Class,Object>();
	private static <T> T getWorkflowManager(Class type){
		Object result=factoryObjectMap.get(type);
		if(result == null){
			if(type !=null){
				String service=type.getName();
				String implPackage=service.substring(0,service.lastIndexOf("."))+".impl.";
				String className=service.substring(service.lastIndexOf(".")+1)+"Impl";
				String implementation=implPackage+className;
				try{
					Constructor constructor=Class.forName(implementation).getDeclaredConstructor();
					constructor.setAccessible(true);
					result=constructor.newInstance();
					factoryObjectMap.put(type, result);
				}catch(Exception e){
					logger.error("Error while loading client specific implementation", e);
				}
			}
			if(result==null){
				logger.error("No Workflow implementation found");
				throw new RuntimeErrorException(null, "No Workflow implementation found");
			}
		}
		return (T)result;
	}
	
}
