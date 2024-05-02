package br.com.wagnercaetano.spaceores.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public class ModEffectInstance extends MobEffectInstance {

    private boolean randomize;
    private double randomOdds;

    public ModEffectInstance(MobEffect pEffect, int pDuration, int pAmplifier, boolean pAmbient, boolean pVisible, boolean pShowIcon, boolean randomize, double randomOdds) {
        super(pEffect, pDuration, pAmplifier, pAmbient, pVisible, pShowIcon);
        this.randomize = randomize;
        this.randomOdds = randomOdds;
    }

    public boolean isRandomize() {
        return randomize;
    }

    public double getRandomOdds() {
        return randomOdds;
    }
}
