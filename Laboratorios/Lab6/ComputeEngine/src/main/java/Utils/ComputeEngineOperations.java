package Utils;

import com.google.api.services.compute.Compute;
import com.google.api.services.compute.model.*;

import java.io.IOException;
import java.util.Collections;

import static Utils.UtilsList.createDisk;
import static Utils.UtilsList.createNetworkInterface;

public class ComputeEngineOperations {
    protected static final String PROJECT_ID = "g05-leirt61d";
    protected static final String ZONE_NAME_US = "us-central1-a";
    protected static final String ZONE_NAME_EUROPE = "europe-west1-b";
    protected static final String MACHINE_TYPE = "f1-micro";

    public static void listVmInstancesInUsCentral(Compute compute) throws IOException {
        Compute.Instances.List instances = compute.instances().list(PROJECT_ID, ZONE_NAME_US);
        InstanceList list = instances.execute();
        if (list.getItems() != null) {
            System.out.println("US-Central Instances: ");
            for (Instance instance : list.getItems()) {
                System.out.println(instance.getName());
            }
            System.out.println("\n");
        }
    }

    public static void listVmInstancesInEurope(Compute compute) throws IOException {
        Compute.Instances.List instances = compute.instances().list(PROJECT_ID, ZONE_NAME_EUROPE);
        InstanceList list = instances.execute();
        if (list.getItems() != null) {
            System.out.println("Europe Instances: ");
            for (Instance instance : list.getItems()) {
                System.out.println(instance.getName());
            }
            System.out.println("\n");
        }
    }

    public static Operation createInstance(Compute compute, String instanceName) throws IOException {
        Instance instance = new Instance();
        instance.setName(instanceName);
        instance.setMachineType("https://www.googleapis.com/compute/v1/projects/"
                + PROJECT_ID + "/zones/"
                + ZONE_NAME_EUROPE + "/machineTypes/"
                + MACHINE_TYPE);

        instance.setNetworkInterfaces(Collections.singletonList(createNetworkInterface()));
        instance.setDisks(Collections.singletonList(createDisk(instanceName)));
        Compute.Instances.Insert insert = compute.instances().insert(PROJECT_ID, ZONE_NAME_EUROPE, instance);
        Operation op = insert.execute();
        return op;
    }

    public static Operation deleteInstance(Compute compute, String instanceName) throws IOException {
        Compute.Instances.Delete delete = compute.instances().delete(PROJECT_ID, ZONE_NAME_EUROPE, instanceName);
        return delete.execute();
    }

    public static void listGroupManagers(Compute compute) throws IOException {
        Compute.InstanceGroupManagers.List instances = compute.instanceGroupManagers().list(PROJECT_ID, ZONE_NAME_EUROPE);
        InstanceGroupManagerList list = instances.execute();
        for (InstanceGroupManager group : list.getItems()) {
            System.out.println("\nGroup: " + group.getName());
            System.out.println("Number of Instances: " + group.getTargetSize());
            System.out.println("Base instance name: " + group.getInstanceTemplate() + "\n");
        }
    }

    public static Operation resizeNumberOfInstances(Compute compute, String instanceGroup, int newSize) throws IOException {
        Compute.InstanceGroupManagers.Resize request = compute.instanceGroupManagers().resize(PROJECT_ID, ZONE_NAME_EUROPE, instanceGroup, newSize);
        return request.execute();
    }
}
