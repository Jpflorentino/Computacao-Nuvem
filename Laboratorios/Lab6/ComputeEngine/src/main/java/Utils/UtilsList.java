package Utils;

import com.google.api.services.compute.model.AccessConfig;
import com.google.api.services.compute.model.AttachedDisk;
import com.google.api.services.compute.model.AttachedDiskInitializeParams;
import com.google.api.services.compute.model.NetworkInterface;

import java.util.ArrayList;
import java.util.List;

import static Utils.ComputeEngineOperations.PROJECT_ID;
import static Utils.ComputeEngineOperations.ZONE_NAME_EUROPE;

public class UtilsList {

    public static NetworkInterface createNetworkInterface() {
        NetworkInterface ifc = new NetworkInterface();
        ifc.setNetwork("https://www.googleapis.com/compute/v1/projects/" + PROJECT_ID + "/global/networks/default");
        List<AccessConfig> configs = new ArrayList<>();
        AccessConfig config = new AccessConfig();
        config.setType("ONE_TO_ONE_NAT");
        config.setName("External NAT");
        configs.add(config);
        ifc.setAccessConfigs(configs);
        return ifc;
    }

    public static AttachedDisk createDisk(String instanceName) {
        AttachedDisk disk = new AttachedDisk();
        disk.setBoot(true);
        disk.setAutoDelete(true);
        disk.setType("PERSISTENT");
        AttachedDiskInitializeParams params = new AttachedDiskInitializeParams();
        // Assign the Persistent Disk the same name as the VM Instance.
        params.setDiskName(instanceName);
        // more info
        // https://cloud.google.com/compute/docs/reference/rest/v1/disks/insert
        params.setSourceImage("projects/centos-cloud/global/images/family/centos-7");
        // Specify the disk type as Standard Persistent Disk
        params.setDiskType("https://www.googleapis.com/compute/v1/projects/"
                + PROJECT_ID + "/zones/"
                + ZONE_NAME_EUROPE + "/diskTypes/pd-standard");
        disk.setInitializeParams(params);
        return disk;
    }
}
