package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.db.TransportDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class TransportDaoImpl extends BaseDao implements TransportDao {
	private static CustomLogger logger = CustomLogger.getLogger(TransportDaoImpl.class);	
	@Autowired @Qualifier(value="transportQueryProps")
	private Properties transportQueryProps;

	public void setTransportQueryProps(Properties transportQueryProps) {
		this.transportQueryProps = transportQueryProps;
	}

	@Autowired
	CacheManager cacheManager;

	public List<AvailableTransport> getAvailableTransports(){
		logger.info("{} : get available transport",this.getClass().getName());		
		String getQuery = transportQueryProps.getProperty("getAvailableTransport");

		List<AvailableTransport> availableTransports = getNamedParamJdbcTemplate().query(getQuery, new RowMapper<AvailableTransport>() {

			public AvailableTransport mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				AvailableTransport availableTransport = new AvailableTransport();

				availableTransport.setRouteId(rs.getLong("Route_Id"));
				availableTransport.setAvailable(rs.getInt("Available_Seat"));
				availableTransport.setReserved(rs.getInt("Reserved_Seat"));
				availableTransport.setCapacity(rs.getString("Capacity"));
				availableTransport.setPrice(rs.getDouble("Price"));
				availableTransport.setDescription(rs.getString("Description"));
				availableTransport.setStop(rs.getString("Stop"));
				return availableTransport;
			}
		});
		return availableTransports;
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {
		String queryString="FROM TransportReservation tr WHERE tr.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<TransportReservation> result= (List<TransportReservation>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	public void saveTransportReservationDtl(TransportReservation transportReservation) {
			getCurrentSession().merge(transportReservation);
	}

	
	public void deleteTransportReservationDtl(Long fileNo) {
		String queryString="delete TransportReservation where fileNo =" + fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		int result = query.executeUpdate();
	}

}
