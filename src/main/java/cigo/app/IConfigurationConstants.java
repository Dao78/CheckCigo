package cigo.app;


public interface IConfigurationConstants {
	
	final String MONITOR_APPLICATION_CONFIGURATION = "com.contship.shyra.presentation.monitor";
	final String CONFIGURATION = "com.contship.shyra.configuration";
	final String HAZELCAST = "com.contship.shyra.hazelcast"; // ShyraBusinessLogic module
	final String EXECUTOR = "app.executor";
	final String JTIS = "app.jtis";
	final String PM = "app.pm";
	final String JEDI = "app.jedi";
	final String JSTAT = "app.jstat";
	final String JTOP = "app.jtop";
	final String JSTATLOG = "app.jstatlog";
	
	final String SERVLET_DOWNLOAD = "ulc.common.download";
	
	final String JEDI_DATA_SOURCE 				= "jediDataSource";
	final String JEDI_SESSION_FACTORY 			= "jediSessionFactory";

	final String JSTAT_DATA_SOURCE 				= "jstatDataSource";
	final String JSTAT_SESSION_FACTORY 			= "jstatSessionFactory";

	final String JSTAT_LOG_DATA_SOURCE 			= "jstatLogDataSource";
	final String JSTAT_LOG_SESSION_FACTORY 		= "jstatLogSessionFactory";

	final String JTIS_DATA_SOURCE 				= "jtisDataSource";
	final String JTIS_SESSION_FACTORY 			= "jtisSessionFactory";
	final String JTIS_SESSION_FACTORY_AUDIT 	= "sessionFactoryAudit";

	final String PM_DATA_SOURCE 				= "pmDataSource";
	final String PM_SESSION_FACTORY 			= "pmSessionFactory";
	
	final String EVENT_JMS_FACTORY 				= "eventJmsFactory";
	final String EVENT_JMS_INCOMINGQUEUE 		= "eventJmsIncomingQueue";
	final String EVENT_JMS_TEMPLATE 			= "eventJmsTemplate";
	final String EVENT_JMS_SERVICE 				= "eventJmsService";
	
}
