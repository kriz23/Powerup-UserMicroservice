package com.pragma.powerup_usermicroservice.domain.usecase;

import com.pragma.powerup_usermicroservice.domain.api.IObjectServicePort;
import com.pragma.powerup_usermicroservice.domain.model.ObjectModel;
import com.pragma.powerup_usermicroservice.domain.spi.IObjectPersistencePort;

import java.util.List;

public class ObjectUseCase implements IObjectServicePort {

    private final IObjectPersistencePort objectPersistencePort;

    public ObjectUseCase(IObjectPersistencePort objectPersistencePort) {
        this.objectPersistencePort = objectPersistencePort;
    }

    @Override
    public void saveObject(ObjectModel objectModel) {
        objectPersistencePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectModel> getAllObjects() {
        return objectPersistencePort.getAllObjects();
    }
}