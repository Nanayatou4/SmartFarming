package com.project.elevage.intelligent.Smart_Farming.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerMetrics {

    private double chargeCpu;

    private long memoireTotale;

    private long memoireLibre;

    public ServerMetrics(double chargeCpu, long memTotal, long memLibre) {
        this.chargeCpu = chargeCpu;
        this.memoireTotale = memTotal;
        this.memoireLibre = memLibre;
    }
}
