package com.syntaxnow.mock.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntaxnow.mock.model.Device;
import com.syntaxnow.mock.model.DeviceResponse;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class DeviceRepository {

    private final List<Device> devices = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = getClass().getResourceAsStream("/data/device.json")) {
            if (is == null) {
                throw new RuntimeException("device.json not found under /data/");
            }

            DeviceResponse response = mapper.readValue(is, DeviceResponse.class);

            response.getData().forEach(device -> {
                // Your JSON does NOT contain `id`, so generate one
                device.setId(UUID.randomUUID().toString());
                devices.add(device);
            });
        }
    }

    public List<Device> findAll() {
        return devices;
    }

    public Optional<Device> findById(String id) {
        return devices.stream()
                .filter(d -> d.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void save(Device device) {
        // Assign ID if new
        if (device.getId() == null) {
            device.setId(UUID.randomUUID().toString());
        }
        devices.add(device);
    }

    public void update(Device updated) {
        findById(updated.getId()).ifPresent(existing -> {
            existing.setDeviceSerialNo(updated.getDeviceSerialNo());
            existing.setDeviceName(updated.getDeviceName());
            existing.setDeviceModelNo(updated.getDeviceModelNo());
            existing.setDeviceFirmwareVersion(updated.getDeviceFirmwareVersion());
            existing.setImei(updated.getImei());
            existing.setIccid(updated.getIccid());
            existing.setConnectionStatus(updated.getConnectionStatus());
            existing.setFailoverStatus(updated.getFailoverStatus());
            existing.setServicePlanStatus(updated.getServicePlanStatus());
            existing.setProjectName(updated.getProjectName());
            existing.setCellOperator(updated.getCellOperator());
            existing.setVoiceLinkId(updated.getVoiceLinkId());
            existing.setRouterId(updated.getRouterId());
        });
    }

    public void delete(String id) {
        devices.removeIf(d -> d.getId().equalsIgnoreCase(id));
    }
}
