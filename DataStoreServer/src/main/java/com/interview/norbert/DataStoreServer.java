package com.interview.norbert;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;

import com.interview.framework.VARIABLES;
import com.interview.services.Services;
import com.linkedin.norbert.javacompat.cluster.ClusterClient;
import com.linkedin.norbert.javacompat.cluster.ClusterListener;
import com.linkedin.norbert.javacompat.cluster.Node;
import com.linkedin.norbert.javacompat.cluster.ZooKeeperClusterClient;
import com.linkedin.norbert.javacompat.network.NetworkServerConfig;

public class DataStoreServer implements DisposableBean {
	Logger log = Logger.getLogger(DataStoreServer.class);
	ClusterClient cc = null;
	private int nodeId = 0;

	public DataStoreServer() {
		String norbertSearchServiceName = VARIABLES.NORBERT_DATASTORE_SERVICE_NAME;
		String norbertZkConnectionString = VARIABLES.NORBERT_ZK_CONNECTION_STRING;

		this.cc = new ZooKeeperClusterClient(norbertSearchServiceName, norbertZkConnectionString, 30000);
		this.cc.awaitConnectionUninterruptibly();
		cleanup();
		NetworkServerConfig config = new NetworkServerConfig();
		config.setServiceName(norbertSearchServiceName);
		config.setZooKeeperConnectString(norbertZkConnectionString);
		config.setZooKeeperSessionTimeoutMillis(30000);
		config.setRequestThreadCorePoolSize(5);
		config.setRequestThreadMaxPoolSize(10);
		config.setRequestThreadKeepAliveTimeSecs(300);

		configCluster(norbertSearchServiceName, norbertZkConnectionString);
		startServer(norbertSearchServiceName, norbertZkConnectionString);
		// startServer(norbertSearchServiceName, 2, norbertZkConnectionString);
	}

	private void cleanup() {
		Set<Node> nodes = cc.getNodes();
		for (Node node : nodes) {
			cc.markNodeUnavailable(node.getId());
			cc.removeNode(node.getId());
		}
	}

	private void startServer(String serviceName, String zkConnectStr) {
		// NettyRpcServer server =
		// new NettyRpcServer(new
		// NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
		// Executors.newCachedThreadPool()));

		Node me = cc.getNodeWithId(nodeId);
		System.out.println("node: id: " + me.getId() + " url: " + me.getUrl());

		String url = me.getUrl();
		Integer port = new Integer(url.split(":")[1]);

		try {
			Registry registry = LocateRegistry.createRegistry(port);
			Services.getInstance().getRMIServer().setRegistry(registry);
			log.debug("Datastore running at port: " + registry.toString());
			log.debug("Datastore running on port: " + port);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void configCluster(String serviceName, String zkConnectStr) {
		cc.awaitConnectionUninterruptibly();

		cc.addListener(new ClusterListener() {
			public void handleClusterConnected(Set<Node> nodes) {
				log.debug("connected to cluster: " + nodes);
			}

			public void handleClusterNodesChanged(Set<Node> nodes) {
				System.out.println("nodes changed: ");
				for (Node node : nodes) {
					log.debug("node: " + node);
				}
			}

			public void handleClusterDisconnected() {
				final Set<Node> nodes = cc.getNodes();
				log.debug("dis-connected from cluster: " + nodes);
			}

			public void handleClusterShutdown() {
				// final Set<Node> nodes = cc.getNodes();
				// log.debug("cluster shutdown: " + nodes);
			}
		});

		nodeId = cc.getNodes().size() + 1;
		cc.addNode(nodeId, "localhost:" + (5300 + nodeId));
		cc.markNodeAvailable(nodeId);

		Set<Node> nodes = cc.getNodes();
		for (Node node : nodes) {
			log.debug("DatastoreServer node: " + node.toString());
		}

		// cc.shutdown();
	}

	@Override
	public void destroy() throws Exception {
		log.debug("Removing DataStore nodes from zookeeper...");
		cc.markNodeUnavailable(nodeId);
		cc.removeNode(nodeId);
		cc.shutdown();
		log.debug("DataStore nodes removed from zookeeper successfully...");

	}
}
