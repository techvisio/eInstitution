package com.techvisio.einstitution.db.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.techvisio.einstitution.util.SqlFunction;



public class SequenceFactory extends BaseDao {
	
	public Long getSequence(String seqName){
		Long sequence=null;
		StoredProcedure sp=new SqlFunction(getDataSource(), "getSequence");
		sp.declareParameter(new SqlOutParameter("seq", Types.INTEGER));
		sp.declareParameter(new SqlParameter("seq_name",Types.VARCHAR));
		sp.compile();
		Map inputs = new HashMap();
		inputs.put("seq_name",seqName);
		Map output=sp.execute(inputs);
		if(output.get("seq")!=null){
		sequence=Long.valueOf(output.get("seq").toString());
		}
		return sequence;
	}

}
