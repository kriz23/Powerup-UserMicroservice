package com.pragma.powerup_usermicroservice.domain.spi;

import com.pragma.powerup_usermicroservice.domain.model.ObjectModel;
import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}