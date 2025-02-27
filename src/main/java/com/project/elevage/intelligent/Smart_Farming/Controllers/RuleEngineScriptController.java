package com.project.elevage.intelligent.Smart_Farming.Controllers;

import com.project.elevage.intelligent.Smart_Farming.Entities.RuleEngineScriptEntity;
import com.project.elevage.intelligent.Smart_Farming.Services.RuleEngineScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rules")
public class RuleEngineScriptController {

    @Autowired
    private RuleEngineScriptService ruleEngineScriptService;

    @PostMapping("/ajouter")
    public RuleEngineScriptEntity ajouterScript(@RequestParam String name,
                                                @RequestParam String script,
                                                @RequestParam Boolean enabled) {
        return ruleEngineScriptService.enregistrerScript(name, script, enabled);
    }

    @GetMapping("/tous")
    public List<RuleEngineScriptEntity> obtenirScripts() {
        return ruleEngineScriptService.obtenirTousScripts();
    }
}
