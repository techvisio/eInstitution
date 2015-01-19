package com.techvisio.einstitution.factory;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;

import com.techvisio.einstitution.util.ContextProvider;


public class WorkflowManagerFactory {

	private static final Logger logger = Logger.getLogger(WorkflowManagerFactory.class); 
	public static Object getWorkflowManager(Class type){
		Object result=ContextProvider.getContext().getBean(type);
		if(type !=null){
			String service=type.getName();
			String implPackage=service.substring(0,service.lastIndexOf("."))+".impl.";
			String className=service.substring(service.lastIndexOf(".")+1)+"Impl";
			String implementation=implPackage+className;
			try{
				
				Class.forName(implementation).newInstance();
			}catch(Exception e){
				logger.error("Error while loading client specific implementation", e);
			}
		}
		if(result==null){
			logger.error("No Workflow implementation found");
			throw new RuntimeErrorException(null, "No Workflow implementation found");
		}
		return result;
	}
	
}
