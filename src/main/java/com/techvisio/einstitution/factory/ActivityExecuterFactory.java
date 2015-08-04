package com.techvisio.einstitution.factory;

import com.techvisio.einstitution.manager.ActivityExecuter;
import com.techvisio.einstitution.util.ContextProvider;

public class ActivityExecuterFactory {

	public static ActivityExecuter getActivityExecuter(ActivityType activityType){
		ActivityExecuter activityExecuter = null;
        switch (activityType) {
        case DISCOUNT_ADJUSTMENT:
        	 activityExecuter = (ActivityExecuter) ContextProvider.getContext().getBean("discountActivityExecuter");
            break;
 
        case SCHOLARSHIP_ADJUSTMENT:
            activityExecuter = (ActivityExecuter) ContextProvider.getContext().getBean("scholarshipActivityExecuter");
            break;
 
        }
        return activityExecuter;
    }
		
		
	}
