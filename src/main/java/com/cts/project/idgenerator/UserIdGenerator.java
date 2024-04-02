package com.cts.project.idgenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import com.cts.project.service.Service;


public class UserIdGenerator implements IdentifierGenerator{
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		String prefix = Service.prefix; 

//		String prefix="XY";
        Connection connection = session.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Resources");
            if (resultSet.next()) {
                int sequence = resultSet.getInt(1) + 1;
                String sequenceNumber = String.format("%04d", sequence);
                return prefix + sequenceNumber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
		
	}


}
