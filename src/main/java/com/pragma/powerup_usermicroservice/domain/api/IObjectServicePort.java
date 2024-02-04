package com.pragma.powerup_usermicroservice.domain.api;

import com.pragma.powerup_usermicroservice.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}