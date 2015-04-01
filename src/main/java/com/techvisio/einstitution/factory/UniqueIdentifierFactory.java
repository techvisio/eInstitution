package com.techvisio.einstitution.factory;

import java.util.Calendar;

import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.ContextProvider;

public class UniqueIdentifierFactory {

	public static UniqueIdentifierGenerator getGenerator(){
		return new UniqueIdentifierGenerator() {
		
			public Long getUniqueIdentifierForAdmission() {

				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
			    
				SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long fileNo = sf.getSequence(AppConstants.ADMISSION);
				
			    return fileNo;
				
				
			}
			
			public Long getUniqueIdentifierForEnquiry() {

				SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(AppConstants.ENQUIRY);
				
			    return Id;
				
				
			}

			@Override
			public Long getUniqueIdentifierForTask() {

                SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(AppConstants.TASK);
				
				
				return Id;
			}

			@Override
			public Long getUniqueIdentifierForConsultant() {

                SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(AppConstants.CONSULTANT);
				
				
				return Id;
			}

		};
	}
}
