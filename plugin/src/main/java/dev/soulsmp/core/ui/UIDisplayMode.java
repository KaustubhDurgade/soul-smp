package dev.soulsmp.core.ui;

/**
 * Display mode for UI elements.
 */
public enum UIDisplayMode {
    /**
     * Show UI elements in the ActionBar (above hotbar).
     */
    ACTION_BAR,

    /**
     * Show UI elements as a BossBar (top of screen).
     */
    BOSS_BAR,

    /**
     * Show UI elements in both ActionBar and BossBar.
     */
    BOTH,

    /**
     * Disable all UI displays.
     */
    NONE
}
