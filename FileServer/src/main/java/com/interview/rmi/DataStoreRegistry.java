package com.interview.rmi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.interview.framework.VARIABLES;
import com.interview.framework.rmi.common.IBidStore;
import com.interview.framework.rmi.common.IChatStore;
import com.interview.framework.rmi.common.IDispute;
import com.interview.framework.rmi.common.IDisputeMessageDataStore;
import com.interview.framework.rmi.common.IInterviewDataStore;
import com.interview.framework.rmi.common.IInterviewerDataStore;
import com.interview.framework.rmi.common.IJobStore;
import com.interview.framework.rmi.common.INotificationStore;
import com.interview.framework.rmi.common.IRatingStore;
import com.interview.framework.rmi.common.IRequestDataStore;
import com.interview.framework.rmi.common.IResetPasswordDataStore;
import com.interview.framework.rmi.common.ISkillsDataStore;
import com.interview.framework.rmi.common.ITransactionStore;
import com.interview.framework.rmi.common.IUploadedFileDataStore;
import com.linkedin.norbert.javacompat.cluster.Node;
import com.linkedin.norbert.javacompat.cluster.ZooKeeperClusterClient;


public class DataStoreRegistry {

  private Registry registry;
  private static DataStoreRegistry instance = null;

  Logger log = Logger.getLogger(DataStoreRegistry.class);

  public DataStoreRegistry(String dataStoreAddress, int port) {
    try {
      instance = this;

      String norbertSearchServiceName = VARIABLES.NORBERT_DATASTORE_SERVICE_NAME;
      String norbertZkConnectionString = VARIABLES.NORBERT_ZK_CONNECTION_STRING;

      ZooKeeperClusterClient cc =
          new ZooKeeperClusterClient(norbertSearchServiceName, norbertZkConnectionString, 30000);
      cc.awaitConnectionUninterruptibly();
      log.debug("String of cluster client: " + cc.toString());
      if (cc.isConnected()) {
        Set<Node> nodes = cc.getNodes();
        for (Node node : nodes) {
          log.debug("Backend connecting datastore node: " + node.toString());
          port = new Integer(node.getUrl().split(":")[1]);
          log.debug("Backend connecting datastore node at port : " + port);

          this.registry = LocateRegistry.getRegistry(dataStoreAddress, port);
          System.out.println("Registry:" + this.registry);
          if (this.registry != null) {
            final String[] boundNames = this.registry.list();
            log.debug("Names bound to RMI registry at host localhost and port " + port + ":");
            for (final String name : boundNames) {
              log.debug("\t" + name);
            }

            log.debug("Backend Registry for datastore: " + this.registry.toString());
          } else {
            log.debug("Datastore not running at the moment. Please start datastore.");
          }

        }
      } else {
        log.debug("not connected to cluster");
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
  
//  public DataStoreRegistry(String dataStoreAddress, int port) {
//    try {
//      instance = this;
//      registry = LocateRegistry.getRegistry(dataStoreAddress, port);
//
//    } catch (RemoteException e) {
//      e.printStackTrace();
//    }
//
//  }

  public static DataStoreRegistry getInstance() {
    return instance;
  }

  public IInterviewerDataStore getInterviewerDataStore() {
    try {

      return (IInterviewerDataStore) (this.registry.lookup(IInterviewerDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }



  public IRequestDataStore getRequestsDataStore() {
    try {
      return (IRequestDataStore) (this.registry.lookup(IRequestDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (RequestsDataStore)myContext.getBean("requestsDataStore");
  }

  public IChatStore getChatStore() {
    try {
      return (IChatStore) (this.registry.lookup(IChatStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (ChatStore)myContext.getBean("chatStore");
  }

  public IInterviewDataStore getInterviewDataStore() {
    try {

      return (IInterviewDataStore) (this.registry.lookup(IInterviewDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (InterviewDataStore)myContext.getBean("interviewDataStore");
  }

  public IBidStore getBidStore() {
    try {
      return (IBidStore) (this.registry.lookup(IBidStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (BidStore)myContext.getBean("bidStore");
  }

  public IRatingStore getRatingStore() {
    try {
      return (IRatingStore) (this.registry.lookup(IRatingStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (RatingStore)myContext.getBean("ratingStore");
  }

  public ITransactionStore getTransactionStore() {
    try {
      return (ITransactionStore) (this.registry.lookup(ITransactionStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (TransactionStore)myContext.getBean("transactionStore");
  }

  public INotificationStore getNotificationStore() {
    try {
      return (INotificationStore) (this.registry.lookup(INotificationStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (NotificationStore)myContext.getBean("notificationStore");
  }

  public IResetPasswordDataStore getResetPasswordDataStore() {
    try {
      return (IResetPasswordDataStore) (this.registry.lookup(IResetPasswordDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (ResetPasswordDataStore)myContext.getBean("resetPassStore");
  }

  public ISkillsDataStore getSkillsDataStore() {
    try {
      return (ISkillsDataStore) (this.registry.lookup(ISkillsDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (SkillsDataStore)myContext.getBean("skillsdatastore");
  }

  public IUploadedFileDataStore getUploadedFileDataStore() {
    try {
      return (IUploadedFileDataStore) (this.registry.lookup(IUploadedFileDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    // return (UploadedFileDataStore)myContext.getBean("uploadedfileDS");
  }

  public IDispute getDisputeDataStore() {
    try {
      return (IDispute) (this.registry.lookup(IDispute.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public IDisputeMessageDataStore getDisputeMessageDataStore() {
    try {
      return (IDisputeMessageDataStore) (this.registry.lookup(IDisputeMessageDataStore.NAME));
    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public IJobStore getJobStore() {
	try {

		return (IJobStore) (this.registry.lookup(IJobStore.NAME));
	} catch (AccessException e) {
		e.printStackTrace();
	} catch (RemoteException e) {
		e.printStackTrace();
	} catch (NotBoundException e) {
		e.printStackTrace();
	}
	return null;
}
}
