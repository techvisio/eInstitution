package com.techvisio.einstitution.factory;

public class UniqueIdentifierFactory {

	public UniqueIdentifierGenerator getGenerator(){
		return new UniqueIdentifierGenerator() {
			
			public String getUniqueIdentifier(String entity) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
