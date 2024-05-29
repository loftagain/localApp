package com.company.localApp.dao;

import com.company.localApp.Clientele;

import java.util.List;

public interface ClientInterface {
    void save(Clientele theClientele);
    Clientele findById(Integer id);
    List<Clientele> findAll();
}
