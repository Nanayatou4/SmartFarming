package com.project.elevage.intelligent.Smart_Farming.Services;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@Service
public class SystemMonitoringService {

    // Bean pour obtenir les informations système de l'OS
    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    // Bean pour obtenir les informations sur la mémoire JVM
    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * Retourne l'utilisation du CPU en pourcentage.
     * @return l'utilisation du CPU
     */
    public double getCpuUsage() {
        return osBean.getSystemCpuLoad() * 100; // Charge CPU en pourcentage
    }

    /**
     * Retourne la mémoire libre en Mo.
     * @return la mémoire libre en Mo
     */
    public long getFreeMemory() {
        return osBean.getFreePhysicalMemorySize() / (1024 * 1024); // Mémoire libre en MB
    }

    /**
     * Retourne la mémoire totale en Mo.
     * @return la mémoire totale en Mo
     */
    public long getTotalMemory() {
        return osBean.getTotalPhysicalMemorySize() / (1024 * 1024); // Mémoire totale en MB
    }

    /**
     * Retourne l'utilisation de la mémoire JVM en pourcentage.
     * @return l'utilisation de la mémoire JVM
     */
    public double getMemoryUsage() {
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        long usedMemory = memoryUsage.getUsed();
        long maxMemory = memoryUsage.getMax();

        if (maxMemory > 0) {
            return ((double) usedMemory / maxMemory) * 100; // Pourcentage de mémoire utilisée dans la JVM
        }
        return 0;
    }
}