package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.RuleEngineScriptEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.RuleEngineScriptEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleEngineScriptService {

    @Autowired
    private RuleEngineScriptEntityRepository ruleEngineScriptRepository;

    public RuleEngineScriptEntity enregistrerScript(String name, String script, Boolean enabled) {
        Optional<RuleEngineScriptEntity> scriptOpt = ruleEngineScriptRepository.findByName(name);
        RuleEngineScriptEntity ruleScript = scriptOpt.orElse(new RuleEngineScriptEntity());
        ruleScript.setName(name);
        ruleScript.setScript(script);
        ruleScript.setEnabled(enabled);
        return ruleEngineScriptRepository.save(ruleScript);
    }

    public RuleEngineScriptEntity changerEtatScript(String name, Boolean enabled) {
        RuleEngineScriptEntity ruleScript = ruleEngineScriptRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Script non trouvé"));
        ruleScript.setEnabled(enabled);
        return ruleEngineScriptRepository.save(ruleScript);
    }

    public List<RuleEngineScriptEntity> obtenirTousScripts() {
        return ruleEngineScriptRepository.findAll();
    }

    public void supprimerScript(String name) {
        RuleEngineScriptEntity ruleScript = ruleEngineScriptRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Script non trouvé"));
        ruleEngineScriptRepository.delete(ruleScript);
    }
}
