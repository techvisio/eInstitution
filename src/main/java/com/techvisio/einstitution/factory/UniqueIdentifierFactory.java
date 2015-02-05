package com.techvisio.einstitution.factory;

import java.util.Calendar;

import com.techvisio.einstitution.util.ContextProvider;

public class UniqueIdentifierFactory {

	public static UniqueIdentifierGenerator getGenerator(){
		return new UniqueIdentifierGenerator() {
		
			public String getUniqueIdentifierForAdmission() {

				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
			    
				SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				String fileNo = year+"-"+sf.getSequence("ADMISSION").toString();
				
			    return fileNo;
				
				
			}

		};
	}
}
