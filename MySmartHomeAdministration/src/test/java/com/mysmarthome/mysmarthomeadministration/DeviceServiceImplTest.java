package com.mysmarthome.mysmarthomeadministration;

import com.mysmarthome.mysmarthomeadministration.DAO.CameraRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.RecordValueRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.SensorRepository;
import com.mysmarthome.mysmarthomeadministration.Entites.Camera;
import com.mysmarthome.mysmarthomeadministration.Entites.RecordValue;
import com.mysmarthome.mysmarthomeadministration.Entites.Sensor;
import com.mysmarthome.mysmarthomeadministration.Services.DeviceServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class DeviceServiceImplTest {
    @Mock
    private SensorRepository sensorRepository;

    @Mock
    private CameraRepository cameraRepository;

    @Mock
    private RecordValueRepository recordValueRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRemoveExistingSensor() throws Exception {
        int existingSensorId = 1;
        Sensor existingSensor = new Sensor();
        existingSensor.setId(1);

        when(sensorRepository.findById(existingSensorId)).thenReturn(Optional.of(existingSensor));

        assertDoesNotThrow(() -> deviceService.removeSensor(existingSensorId));
        verify(sensorRepository, times(1)).delete(existingSensor);
    }

    @Test
    void testRemoveNonExistingSensor() {
        int nonExistingSensorId = 2;

        when(sensorRepository.findById(nonExistingSensorId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> deviceService.removeSensor(nonExistingSensorId));
        assertEquals("Le capteur n'existe pas !", exception.getMessage());
        verify(sensorRepository, never()).delete(any());
    }
    @Test
    void testRemoveExistingCamera() throws Exception {
        int existingCameraId = 1;
        Camera existingCamera = new Camera();

        when(cameraRepository.findById(existingCameraId)).thenReturn(Optional.of(existingCamera));

        assertDoesNotThrow(() -> deviceService.removeCamera(existingCameraId));
        verify(cameraRepository, times(1)).delete(existingCamera);
    }

    @Test
    void testRemoveNonExistingCamera() {
        int nonExistingCameraId = 2;

        when(cameraRepository.findById(nonExistingCameraId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> deviceService.removeCamera(nonExistingCameraId));
        assertEquals("La caméra n'existe pas !", exception.getMessage());
        verify(cameraRepository, never()).delete(any());
    }

    @Test
    void testAddSensorWithUniqueName() throws Exception {
        Sensor newSensor = new Sensor();
        newSensor.setName("UniqueSensor");

        when(sensorRepository.findByName(newSensor.getName())).thenReturn(Optional.empty());
        when(sensorRepository.save(newSensor)).thenReturn(newSensor);

        Sensor addedSensor = deviceService.addSensor(newSensor);

        assertEquals(newSensor, addedSensor);
        verify(sensorRepository, times(1)).save(newSensor);
    }

    @Test
    void testAddSensorWithExistingName() {
        String existingSensorName = "ExistingSensor";
        Sensor existingSensor = new Sensor();
        existingSensor.setName(existingSensorName);

        when(sensorRepository.findByName(existingSensorName)).thenReturn(Optional.of(existingSensor));

        Exception exception = assertThrows(Exception.class, () -> deviceService.addSensor(existingSensor));
        assertEquals("Un capteur avec ce nom existe déjà !", exception.getMessage());
        verify(sensorRepository, never()).save(any());
    }

    @Test
    void testAddCameraWithUniqueIp() throws Exception {
        Camera newCamera = new Camera();
        newCamera.setIpAdress("192.168.1.1");

        when(cameraRepository.findByIpAdress(newCamera.getIpAdress())).thenReturn(Optional.empty());
        when(cameraRepository.save(newCamera)).thenReturn(newCamera);

        Camera addedCamera = deviceService.addCamera(newCamera);

        assertEquals(newCamera, addedCamera);
        verify(cameraRepository, times(1)).save(newCamera);
    }

    @Test
    void testAddCameraWithExistingIp() {
        String existingCameraIp = "192.168.1.2";
        Camera existingCamera = new Camera();
        existingCamera.setIpAdress(existingCameraIp);

        when(cameraRepository.findByIpAdress(existingCameraIp)).thenReturn(Optional.of(existingCamera));

        Exception exception = assertThrows(Exception.class, () -> deviceService.addCamera(existingCamera));
        assertEquals("Une caméra avec cette adresse ipV4 existe déjà !", exception.getMessage());
        verify(cameraRepository, never()).save(any());
    }

    @Test
    void testGetSensors() throws Exception {
        List<Sensor> mockSensors = new ArrayList<>();
        mockSensors.add(new Sensor());
        mockSensors.add(new Sensor());

        when(sensorRepository.findAll()).thenReturn(mockSensors);

        List<Sensor> result = deviceService.getSensors();

        assertEquals(mockSensors, result);
        verify(sensorRepository, times(1)).findAll();
    }

    @Test
    void testGetCameras() throws Exception {
        List<Camera> mockCameras = new ArrayList<>();
        mockCameras.add(new Camera());
        mockCameras.add(new Camera());

        when(cameraRepository.findAll()).thenReturn(mockCameras);

        List<Camera> result = deviceService.getCameras();

        assertEquals(mockCameras, result);
        verify(cameraRepository, times(1)).findAll();
    }

    void testGetRecordValuesWithEmptySensorName() throws Exception {
        // Arrange
        List<RecordValue> mockRecordValues = new ArrayList<>();
        when(recordValueRepository.findAll()).thenReturn(mockRecordValues);

        // Act
        List<RecordValue> result = deviceService.getRecordValues("");

        // Assert
        assertSame(mockRecordValues, result);
        verify(recordValueRepository, times(1)).findAll();
        verifyNoMoreInteractions(recordValueRepository);
    }

    @Test
    void testGetRecordValuesWithNonEmptySensorName() throws Exception {
        // Arrange
        String sensorName = "CAPTOR";
        List<RecordValue> recordValues = new LinkedList<>();
        recordValues.add(new RecordValue()); // Ajoutez des éléments à la liste
        when(recordValueRepository.findRecordValueBySensorName(sensorName)).thenReturn(recordValues);

        // Act
        List<RecordValue> result = deviceService.getRecordValues(sensorName);

        // Assert
        assertIterableEquals(recordValues, result);
        verify(recordValueRepository, times(1)).findRecordValueBySensorName(sensorName);
        verifyNoMoreInteractions(recordValueRepository);
    }

    @Test
    void testGetRecordValuesWithNonExistentSensorName() throws Exception {
        // Arrange
        String sensorName = "NonExistentSensor";
        List<RecordValue> mockAllRecordValues = new ArrayList<>();
        when(recordValueRepository.findRecordValueBySensorName(sensorName)).thenReturn(new ArrayList<>());
        when(recordValueRepository.findAll()).thenReturn(mockAllRecordValues);

        // Act
        List<RecordValue> result = deviceService.getRecordValues(sensorName);

        // Assert
        assertSame(mockAllRecordValues, result);
        verify(recordValueRepository, times(1)).findRecordValueBySensorName(sensorName);
        verify(recordValueRepository, times(1)).findAll();
        verifyNoMoreInteractions(recordValueRepository);
    }
}
