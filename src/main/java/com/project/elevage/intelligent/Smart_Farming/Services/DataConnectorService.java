package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.DataConnector.DataConnectorEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.DataConnectorEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataConnectorService {

    @Autowired
    private DataConnectorEntityRepository dataConnectorRepository;

    public DataConnectorEntity enregistrerConnector(String protocol, String endpoint, String parameters, Boolean enabled) {
        Optional<DataConnectorEntity> connectorOpt = dataConnectorRepository.findByProtocol(protocol);
        DataConnectorEntity connector = connectorOpt.orElse(new DataConnectorEntity());
        connector.setProtocol(protocol);
        connector.setEndpoint(endpoint);
        connector.setParameters(parameters);
        connector.setEnabled(enabled);
        return dataConnectorRepository.save(connector);
    }

    public DataConnectorEntity changerEtatConnector(String protocol, Boolean enabled) {
        DataConnectorEntity connector = dataConnectorRepository.findByProtocol(protocol)
                .orElseThrow(() -> new RuntimeException("Connecteur non trouvé"));
        connector.setEnabled(enabled);
        return dataConnectorRepository.save(connector);
    }

    public List<DataConnectorEntity> obtenirTousConnectors() {
        return dataConnectorRepository.findAll();
    }

    public void supprimerConnector(String protocol) {
        DataConnectorEntity connector = dataConnectorRepository.findByProtocol(protocol)
                .orElseThrow(() -> new RuntimeException("Connecteur non trouvé"));
        dataConnectorRepository.delete(connector);
    }
}
